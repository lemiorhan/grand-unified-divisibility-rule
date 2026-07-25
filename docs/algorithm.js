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
/*
 * Grand Unified Divisibility Rule — JavaScript port of DivisibilityRule.groovy.
 *
 * A number N is divisible by d exactly when the weighted sum of N's digits is
 * divisible by d, where the weight of the ones digit is 1 and every next weight
 * is the previous weight times 10, reduced below d by subtraction only.
 * The final total is the exact remainder of N / d.
 *
 * Uses BigInt so dividends of any length work. The rawLog output reproduces the
 * Groovy implementation's trace format line by line.
 */
(function (root, factory) {
    if (typeof module === 'object' && module.exports) module.exports = factory()
    else root.DivisibilityRule = factory()
})(typeof self !== 'undefined' ? self : this, function () {
    'use strict'

    // brings a value below the divisor using nothing but subtraction;
    // every value handed to it is below 10 x divisor, so the loop runs at most 9 times
    function reduceBySubtraction(value, divisor) {
        let reduced = value
        let subtractions = 0n
        while (reduced >= divisor) {
            reduced -= divisor
            subtractions++
        }
        return { reduced: reduced, subtractions: subtractions }
    }

    function check(dividendInput, divisorInput) {
        const dividendStr = String(dividendInput).trim()
        const divisorStr = String(divisorInput).trim()
        if (!/^\d+$/.test(dividendStr)) throw new Error('Dividend must be a non-negative whole number')
        if (!/^\d+$/.test(divisorStr)) throw new Error('Divisor must be a positive whole number')

        const dividend = BigInt(dividendStr)
        const divisor = BigInt(divisorStr)
        if (divisor < 1n) throw new Error('Divisor must be a positive whole number')

        const digits = dividend.toString()
        const digitCount = digits.length

        // the variation of the formula that will be applied: one weight per digit
        const weights = []
        let w = 1n
        for (let i = 0; i < digitCount; i++) {
            weights.push(w)
            w = reduceBySubtraction(w * 10n, divisor).reduced
        }
        const formulaTerms = weights.map(function (weight, i) {
            return '(' + weight + ' x DIGIT ' + (i + 1) + ')'
        })
        const formulaLine = 'FORMULA FOR ' + divisor + ' => ' + formulaTerms.join(' + ') + ' (DIGIT 1 = ONES PLACE)'

        const rawLog = []
        rawLog.push('DIVISIBILITY CHECK FOR [' + dividend + '/' + divisor + ']')
        rawLog.push(formulaLine)

        const steps = []
        let remaining = dividend
        let weight = 1n
        let total = 0n
        let step = 1

        while (true) {
            const digit = remaining % 10n
            const contribution = digit * weight
            const unreduced = total + contribution
            const totalBefore = total
            const reduction = reduceBySubtraction(unreduced, divisor)
            total = reduction.reduced

            steps.push({
                type: 'step',
                step: step,
                digit: digit,
                weight: weight,
                contribution: contribution,
                totalBefore: totalBefore,
                unreduced: unreduced,
                total: total,
                subtractions: reduction.subtractions
            })
            rawLog.push('STEP ' + step + ' => DIGIT ' + digit + ' x WEIGHT ' + weight + ' = ' + contribution +
                ' | TOTAL ' + unreduced +
                (unreduced === total ? '' : ', REDUCED TO ' + total + ' BY SUBTRACTING ' + divisor))

            remaining = remaining / 10n
            if (remaining === 0n) break

            const nextUnreduced = weight * 10n
            const weightReduction = reduceBySubtraction(nextUnreduced, divisor)
            const previousWeight = weight
            weight = weightReduction.reduced

            steps.push({
                type: 'weight',
                step: step + 1,
                previousWeight: previousWeight,
                unreduced: nextUnreduced,
                weight: weight,
                subtractions: weightReduction.subtractions
            })
            rawLog.push('WEIGHT FOR STEP ' + (step + 1) + ' => 10 x ' + previousWeight + ' = ' + nextUnreduced +
                (nextUnreduced === weight ? '' : ', REDUCED TO ' + weight + ' BY SUBTRACTING ' + divisor))
            step++
        }

        const divisible = total === 0n
        rawLog.push('FINAL REMAINDER: ' + total + ' => ' + dividend + ' IS ' + (divisible ? '' : 'NOT ') + 'DIVISIBLE BY ' + divisor)

        return {
            dividend: dividend,
            divisor: divisor,
            digits: digits,
            weights: weights,
            formulaLine: formulaLine,
            steps: steps,
            remainder: total,
            divisible: divisible,
            rawLog: rawLog
        }
    }

    return { check: check }
})
