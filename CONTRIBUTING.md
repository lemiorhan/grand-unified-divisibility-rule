# Contributing

Thank you for taking an interest in this project. It began as one person's
high-school notebook in 1997 and became a proved theorem in 2026, so
contributions that sharpen the mathematics or the explanation are especially
welcome.

By participating you agree to abide by the [Code of Conduct](CODE_OF_CONDUCT.md).

## The one thing to know first

This repository has an unusual constraint, and it is the whole point of the
project:

> **The algorithm must never factor the divisor into primes, and must never
> perform a division.**

Only reading digits, small multiplications, addition and subtraction are allowed.
There is a test that fails the build if any method whose name mentions primes or
factors reappears in `DivisibilityRule`. A patch that speeds things up by
reintroducing `%`, `/` or prime factorization defeats the result and will not be
merged, however fast it is.

## Ways to contribute

**Found a counterexample.** This is the most valuable report possible. Open an
issue with the dividend and the divisor, and it will be turned into a
regression test before anything else happens.

**Improving the proof or the explanation.** The three-line proof lives in
`README.md` and on the site. Corrections to the mathematics, tighter phrasing,
or a clearer worked example are all welcome.

**Translations.** The site is available in English, Türkçe, 中文 and Español.
See the translation notes below — they have a few rules of their own.

**Ports to other languages.** A port that reproduces the reference trace line
for line is a good contribution. Open an issue first so it can be discussed
before you spend real effort.

**Documentation, typos, tooling.** Always welcome and never too small.

## Getting set up

You need a JDK 8 or newer. Gradle comes with the repository via the wrapper, so
there is nothing else to install.

```sh
git clone https://github.com/lemiorhan/grand-unified-divisibility-rule.git
cd grand-unified-divisibility-rule
./gradlew clean test
```

The website is a static page with no build step and no dependencies. Serve
`docs/` over HTTP and open it — `file://` will not work, because the browser
blocks the module loading the page relies on:

```sh
cd docs && python3 -m http.server 8000
```

## The test suite

Everything is verified from source. `./gradlew clean test` runs:

- **`GrandUnifiedDivisibilityRuleSpec`** — the main suite. It checks the rule
  against real integer division for every dividend/divisor pair from 1 to 100,
  which is all 10,000 combinations; for 25 random dividends of 3 to 9 digits
  under a fixed seed, so every run checks the same numbers; that no prime-factor
  detection has crept back in; and that the weight row is announced before it is
  applied. Every calculation step of every check is written to
  `build/divisibility-steps.log` so the whole run can be re-read by eye.
- **`DivisibilityRuleExecutionTest`** — the 2021 regression suite, 120 fixed
  dividend/divisor cases, kept passing unchanged against the generalized rule.
- **`DivisibilityRuleAllExecutionTest`** — sweeps every divisor up to each
  dividend from 2 to 1000.

If you change `DivisibilityRule`, the JavaScript port in `docs/algorithm.js`
must stay in step with it. The two are expected to produce an identical trace,
and the site tells visitors so — please verify a few cases in both before
opening the pull request.

## Translation notes

Translations live in `docs/i18n.js`, one block per language, with English also
present in `docs/index.html` as the no-JavaScript default. If you touch either,
keep them in sync.

- Every language block must contain exactly the same set of keys. A missing key
  silently falls back to English.
- Strings are inserted with `innerHTML`, so markup inside them matters. Keep the
  same count of `<b>`, `<code>` and `<a>` elements as the English, and keep
  `href` values identical.
- Keep `&mdash;` and `&minus;` where the English uses them. Chinese is the
  exception: zh-CN typography needs the full-width double dash `——`, so the zh
  block doubles them.
- Mathematical claims must survive translation exactly. Do not drop qualifiers —
  "strictly between 0 and d", "the last two digits", "if and only if" all carry
  weight. Several past bugs were exactly this: a dropped "only" or a singular
  where the English was plural.
- Terminology is fixed per language and must stay consistent: digit is `rakam`
  in Turkish, `数字` in Chinese, `cifra` in Spanish; digit *position* is
  `basamak` / `数位` / `posición`. Do not mix them.
- The raw execution log is deliberately never translated. It must stay identical
  to the Groovy output, line for line.

## Pull requests

1. Branch from `main`.
2. Keep the change focused. One idea per pull request.
3. Add a test for any behavior change. A counterexample belongs in
   `GrandUnifiedDivisibilityRuleSpec`.
4. Run `./gradlew clean test` and make sure it is green.
5. Match the surrounding style. There is an `.editorconfig`; the Groovy uses
   four-space indentation, and the site's JavaScript uses four spaces and no
   semicolons.
6. Update `CHANGELOG.md` under `## [Unreleased]`.
7. Write a commit message that says what changed and why.

## Licensing of contributions

This project is licensed in two parts:

- **Code** — Apache License 2.0. Under Section 5 of that license, any
  contribution you intentionally submit for inclusion is licensed to the project
  under Apache 2.0, with no extra paperwork and no CLA to sign.
- **Prose and documentation** — CC BY 4.0. By submitting text you agree to it
  being published under that license.

Please only submit work you have the right to submit. If a change is based on
someone else's material, say so in the pull request so it can be attributed
properly.

## Questions

Open a [discussion or an issue](https://github.com/lemiorhan/grand-unified-divisibility-rule/issues).
A question about why the rule works is a perfectly good issue — if the
explanation was unclear enough to prompt the question, the explanation is what
needs fixing.
