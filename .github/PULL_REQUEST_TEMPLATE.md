# What does this change?

<!-- One or two sentences. What changed, and why. -->

Fixes #

## Type of change

<!-- Tick everything that applies. -->

- [ ] Bug fix
- [ ] New feature or improvement
- [ ] Documentation, README or the write-up
- [ ] Translation
- [ ] Tests or tooling
- [ ] Breaking change

## Checklist

- [ ] `./gradlew clean test` passes locally
- [ ] I added or updated a test covering this change
- [ ] I updated `CHANGELOG.md` under `## [Unreleased]`
- [ ] I have the right to submit this work, and I understand it will be licensed
      under Apache 2.0 (code) or CC BY 4.0 (prose), per `CONTRIBUTING.md`

## The hard constraint

The algorithm must never factor the divisor into primes and never perform a
division — only reading digits, small multiplications, addition and subtraction.

- [ ] This change does not reintroduce prime factorization or division, or it
      does not touch the algorithm

## If you changed the algorithm

`DivisibilityRule.groovy` and `docs/algorithm.js` are expected to produce an
identical trace, and the site tells visitors so.

- [ ] Both implementations are updated and produce the same trace
- [ ] I verified a few cases in both

## If you changed translations

- [ ] Every language block still defines exactly the same keys
- [ ] Markup counts and `href` values match the English
- [ ] No mathematical qualifier was dropped ("only", "strictly", "the last two
      digits", "if and only if")
- [ ] English in `docs/index.html` still matches the `en` block in
      `docs/i18n.js`

## Anything reviewers should know?

<!-- Trade-offs, things you were unsure about, parts you would like a second
     opinion on. -->
