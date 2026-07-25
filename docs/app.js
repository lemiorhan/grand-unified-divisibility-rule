/*
 * Copyright 2026 Lemi Orhan Ergin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
/* Grand Unified Divisibility Rule — interactive calculator, translations and page behavior */
(function () {
    'use strict'

    var LANGS = window.I18N_ORDER || ['en']
    var DEFAULT_LANG = 'en'
    // BCP-47 tags for the html lang attribute
    var HTML_LANG = { en: 'en', tr: 'tr', zh: 'zh-CN', es: 'es' }
    var currentLang = DEFAULT_LANG
    var lastResult = null
    // the error on screen is remembered by key, not by rendered text, so it can be re-translated
    var lastError = null

    function store(key, value) {
        try { localStorage.setItem(key, value) } catch (e) { /* private mode */ }
    }
    function restore(key) {
        try { return localStorage.getItem(key) } catch (e) { return null }
    }

    // ---------- translation ----------
    function t(key, vars) {
        var table = window.I18N[currentLang] || {}
        var text = table[key]
        if (text === undefined) text = (window.I18N[DEFAULT_LANG] || {})[key]
        if (text === undefined) return ''
        if (vars) {
            Object.keys(vars).forEach(function (name) {
                text = text.split('{' + name + '}').join(String(vars[name]))
            })
        }
        return text
    }

    function detectLanguage() {
        // an explicit ?lang= wins, so a link can point straight at one language
        var requested = null
        try { requested = new URLSearchParams(window.location.search).get('lang') } catch (e) { /* older browser */ }
        if (requested && LANGS.indexOf(requested) !== -1) return requested

        var saved = restore('gudr-lang')
        if (saved && LANGS.indexOf(saved) !== -1) return saved
        var candidates = (navigator.languages && navigator.languages.length)
            ? navigator.languages : [navigator.language || DEFAULT_LANG]
        for (var i = 0; i < candidates.length; i++) {
            var prefix = String(candidates[i]).toLowerCase().split('-')[0]
            if (LANGS.indexOf(prefix) !== -1) return prefix
        }
        return DEFAULT_LANG
    }

    function applyLanguage(lang) {
        currentLang = LANGS.indexOf(lang) !== -1 ? lang : DEFAULT_LANG
        store('gudr-lang', currentLang)
        document.documentElement.setAttribute('lang', HTML_LANG[currentLang] || currentLang)

        document.title = t('doc.title')
        var desc = document.querySelector('meta[name="description"]')
        if (desc) desc.setAttribute('content', t('doc.desc'))

        // static content — translation strings are authored in i18n.js and may carry markup
        var nodes = document.querySelectorAll('[data-i18n]')
        Array.prototype.forEach.call(nodes, function (node) {
            var value = t(node.getAttribute('data-i18n'))
            if (value) node.innerHTML = value
        })

        // templated content (the rule cards keep their coefficients in data attributes)
        var tpls = document.querySelectorAll('[data-i18n-tpl]')
        Array.prototype.forEach.call(tpls, function (node) {
            node.innerHTML = t(node.getAttribute('data-i18n-tpl'), {
                a: node.getAttribute('data-a'),
                b: node.getAttribute('data-b')
            })
        })

        langSelect.setAttribute('aria-label', stripMarkup(t('lang.label')))
        themeToggle.setAttribute('aria-label', stripMarkup(t('theme.label')))
        langSelect.value = currentLang

        if (lastResult) render(lastResult, false)
        // a validation error already on screen must follow the language switch too
        if (lastError) errorBox.textContent = errorText(lastError)
    }

    function stripMarkup(html) {
        var tmp = document.createElement('div')
        tmp.innerHTML = html
        return tmp.textContent || ''
    }

    // ---------- theme ----------
    var themeToggle = document.getElementById('themeToggle')
    var langSelect = document.getElementById('langSelect')

    function applyTheme(theme) {
        document.documentElement.setAttribute('data-theme', theme)
        store('gudr-theme', theme)
    }
    var storedTheme = restore('gudr-theme')
    if (storedTheme) applyTheme(storedTheme)
    else if (window.matchMedia && window.matchMedia('(prefers-color-scheme: light)').matches) applyTheme('light')
    else applyTheme('dark')

    themeToggle.addEventListener('click', function () {
        applyTheme(document.documentElement.getAttribute('data-theme') === 'light' ? 'dark' : 'light')
    })
    langSelect.addEventListener('change', function () {
        applyLanguage(langSelect.value)
        // keep the address bar shareable without adding history entries
        if (window.history && window.history.replaceState) {
            try {
                var url = new URL(window.location.href)
                url.searchParams.set('lang', currentLang)
                window.history.replaceState(null, '', url)
            } catch (e) { /* ignore */ }
        }
    })

    // ---------- calculator ----------
    var form = document.getElementById('calcForm')
    var dividendInput = document.getElementById('dividend')
    var divisorInput = document.getElementById('divisor')
    var errorBox = document.getElementById('calcError')
    var output = document.getElementById('calcOutput')
    var formulaChips = document.getElementById('formulaChips')
    var digitRow = document.getElementById('digitRow')
    var stepsList = document.getElementById('stepsList')
    var verdict = document.getElementById('verdict')
    var srStatus = document.getElementById('srStatus')
    var rawlog = document.getElementById('rawlog')
    var rawlogWrap = document.getElementById('rawlogWrap')
    var copyBtn = document.getElementById('copyLog')

    var animationTimers = []
    function clearTimers() {
        animationTimers.forEach(clearTimeout)
        animationTimers = []
    }
    function later(fn, delay) { animationTimers.push(setTimeout(fn, delay)) }

    function el(tag, className, text) {
        var node = document.createElement(tag)
        if (className) node.className = className
        if (text !== undefined) node.textContent = text
        return node
    }

    // key is null for messages thrown by the algorithm itself, which are not translated
    function errorText(err) {
        if (!err) return ''
        var text = err.key ? stripMarkup(t(err.key)) : ''
        return text || err.fallback || ''
    }

    function showError(key, field, fallback) {
        clearTimers()
        lastError = { key: key, field: field, fallback: fallback }
        errorBox.style.display = 'block'
        errorBox.textContent = errorText(lastError)
        output.style.display = 'none'
        dividendInput.removeAttribute('aria-invalid')
        divisorInput.removeAttribute('aria-invalid')
        if (field) field.setAttribute('aria-invalid', 'true')
        lastResult = null
    }

    function clearError() {
        lastError = null
        errorBox.style.display = 'none'
        errorBox.textContent = ''
        dividendInput.removeAttribute('aria-invalid')
        divisorInput.removeAttribute('aria-invalid')
    }

    function flashCopyButton(key) {
        copyBtn.textContent = stripMarkup(t(key))
        setTimeout(function () { copyBtn.textContent = stripMarkup(t('calc.copy')) }, 1600)
    }

    function render(result, animate) {
        clearTimers()
        output.style.display = 'block'

        // 1. the formula variation, announced before it is applied
        formulaChips.innerHTML = ''
        result.weights.forEach(function (weight, i) {
            if (i > 0) formulaChips.appendChild(el('span', 'term-plus', '+'))
            var chip = el('span', 'term-chip')
            chip.appendChild(el('b', null, String(weight)))
            chip.appendChild(document.createTextNode(' × d' + (i + 1)))
            formulaChips.appendChild(chip)
        })
        formulaChips.appendChild(el('span', 'term-plus', stripMarkup(t('calc.legend'))))

        // 2. digit boxes, most significant first; step k consumes the k-th digit from the right
        digitRow.innerHTML = ''
        var digits = result.digits.split('')
        var boxes = []
        digits.forEach(function (digit, i) {
            var box = el('div', 'digit-box', digit)
            var position = digits.length - i
            box.appendChild(el('span', 'pos', 'd' + position))
            digitRow.appendChild(box)
            boxes[position] = box
        })

        // 3. every step
        stepsList.innerHTML = ''
        verdict.className = 'verdict'
        verdict.innerHTML = ''

        var reduceMotion = window.matchMedia && window.matchMedia('(prefers-reduced-motion: reduce)').matches
        var staggered = animate && !reduceMotion && result.steps.length <= 60
        var delayUnit = staggered ? 230 : 0
        var delay = 0

        result.steps.forEach(function (step) {
            var row
            if (step.type === 'step') {
                row = el('div', 'step-row')
                row.appendChild(el('span', 'step-no', stripMarkup(t('calc.stepNo', { n: step.step }))))
                var math = el('span', 'step-math')
                math.innerHTML = t('calc.stepMath', {
                    digit: step.digit, weight: step.weight, product: step.contribution,
                    before: step.totalBefore, sum: step.unreduced
                })
                row.appendChild(math)
                if (step.subtractions > 0n) {
                    row.appendChild(el('span', 'reduce-pill',
                        '− ' + result.divisor + ' × ' + step.subtractions + ' → ' + step.total))
                }
                row.appendChild(el('span', 'total-pill', stripMarkup(t('calc.runningTotal', { total: step.total }))))
                ;(function (position) {
                    later(function () {
                        boxes.forEach(function (box) { if (box) box.classList.remove('active') })
                        if (boxes[position]) boxes[position].classList.add('active')
                        for (var p = 1; p < position; p++) if (boxes[p]) boxes[p].classList.add('done')
                    }, delay)
                })(step.step)
            } else {
                row = el('div', 'step-row weight-row')
                row.appendChild(el('span', 'step-no', stripMarkup(t('calc.weightLabel'))))
                var weightMath = el('span', 'step-math')
                weightMath.innerHTML = t('calc.weightMath', { prev: step.previousWeight, unreduced: step.unreduced })
                row.appendChild(weightMath)
                if (step.subtractions > 0n) {
                    row.appendChild(el('span', 'reduce-pill',
                        '− ' + result.divisor + ' × ' + step.subtractions + ' → ' + step.weight))
                }
            }
            ;(function (node) { later(function () { stepsList.appendChild(node) }, delay) })(row)
            delay += delayUnit
        })

        // 4. verdict
        later(function () {
            boxes.forEach(function (box) { if (box) { box.classList.remove('active'); box.classList.add('done') } })
            var divisible = result.divisible
            var headline = t(divisible ? 'calc.isDivisible' : 'calc.isNotDivisible',
                { n: result.dividend, d: result.divisor })
            var detail = t(divisible ? 'calc.finalZero' : 'calc.finalRemainder', { r: result.remainder })
            verdict.className = 'verdict ' + (divisible ? 'divisible' : 'not-divisible')
            verdict.appendChild(el('span', null, stripMarkup(headline)))
            verdict.appendChild(el('small', null, stripMarkup(detail)))
            srStatus.textContent = stripMarkup(headline) + '. ' + stripMarkup(detail)
        }, delay)

        // 5. the raw log stays in English so it matches the Groovy output exactly
        rawlog.textContent = result.rawLog.join('\n')
    }

    function run() {
        var dividendStr = dividendInput.value.replace(/[\s,.]/g, '')
        var divisorStr = divisorInput.value.replace(/[\s,.]/g, '')

        if (!/^\d+$/.test(dividendStr)) return showError('calc.errDividend', dividendInput)
        if (!/^\d+$/.test(divisorStr) || BigInt(divisorStr) < 1n) return showError('calc.errDivisor', divisorInput)

        var result
        try { result = DivisibilityRule.check(dividendStr, divisorStr) }
        catch (e) { return showError(null, dividendInput, e.message) }

        clearError()
        lastResult = result
        render(result, true)
    }

    form.addEventListener('submit', function (event) { event.preventDefault(); run() })

    document.getElementById('examples').addEventListener('click', function (event) {
        var chip = event.target.closest('.example-chip')
        if (!chip) return
        dividendInput.value = chip.getAttribute('data-n')
        divisorInput.value = chip.getAttribute('data-d')
        run()
    })

    copyBtn.addEventListener('click', function () {
        var text = rawlog.textContent
        if (navigator.clipboard && navigator.clipboard.writeText) {
            navigator.clipboard.writeText(text)
                .then(function () { flashCopyButton('calc.copied') })
                .catch(function () { flashCopyButton('calc.copyFailed') })
        } else {
            flashCopyButton('calc.copyFailed')
        }
    })

    applyLanguage(detectLanguage())
})()
