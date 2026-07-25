# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

Dates before 2026 describe the history of the discovery itself rather than
releases of this repository; they are recorded here because the repository is the
only published account of it.

## [Unreleased]

Nothing yet.

## [1.0.0] - 2026-07-25

The first tagged release. The rule is generalized, proved, implemented, tested
and published, and the project is set up as an open-source project.

### Added

- **The Grand Unified Divisibility Rule.** A single algorithm producing the
  divisibility rule of every divisor, using no prime factorization and no
  division. A number is divisible by `d` exactly when the weighted sum of its
  digits is divisible by `d`, where the ones digit has weight 1 and each
  subsequent weight is the previous one times 10, reduced below `d` by
  subtraction alone. The final total is the exact remainder.
- **A three-line proof** of the rule.
- **Identification of the hidden constraint** in the 1997 equation:
  `gcd((y*a2)+1, d) = 1`. This is why the original formulation needed prime
  factorization — the scaling constant stays strictly between 0 and `d`, so the
  criterion is automatic for prime divisors, and factoring into primes was
  silently repairing the broken composite cases. Fifteen divisors below 100
  break the original equation, and every one of them is composite.
- **`DivisibilityRule.groovy`** — the reference implementation, which emits a
  full trace of every digit, multiplication and subtraction.
- **Test suite** covering all 10,000 dividend/divisor pairs from 1 to 100, 25
  seeded random dividends of 3 to 9 digits, and a guard test that fails the
  build if prime-factor detection ever returns. The 2021 suites are retained and
  pass unchanged.
- **Interactive website** at
  [lemiorhan.github.io/grand-unified-divisibility-rule](https://lemiorhan.github.io/grand-unified-divisibility-rule/),
  which runs the algorithm step by step in the browser and reproduces the Groovy
  trace exactly. Available in English, Türkçe, 中文 and Español, with light and
  dark themes.
- **Open-source project files**: `CONTRIBUTING.md`, `CODE_OF_CONDUCT.md`,
  `SECURITY.md`, `CITATION.cff`, `NOTICE`, issue and pull request templates,
  CI on every push and pull request, and Dependabot.

### Changed

- **License.** Source code moved from CC BY 4.0 to the **Apache License 2.0**,
  which is a software license and carries an express patent grant. Prose and
  documentation remain under **CC BY 4.0**, now in `LICENSE-DOCS`. Attribution
  requirements are collected in `NOTICE`; see the License section of
  `README.md`.
- **Build metadata.** `group` corrected from the placeholder `org.example` to
  `com.lemiorhanergin`, and the version from `1.0-SNAPSHOT` to `1.0.0`.

### Fixed

- A validation error shown by the site's calculator kept the language it was
  produced in, even after the reader switched languages. Errors are now stored
  by translation key and re-resolved on every language change, which also fixes
  the message announced to screen readers via `role="alert"`.
- Two English connectives in the equation block, `where` and `and`, had no
  translation keys and stayed English in all three other languages.
- Mathematical and idiomatic corrections across the Turkish, Chinese and Spanish
  translations, including a Turkish line that claimed only the *last digit*
  decides divisibility by 4 and 8, a Chinese phrase that read as "divisors
  written with the digits 2 and 5" rather than "divisors whose only prime factors
  are 2 and 5", and the Spanish name of the rule, which had dropped "Grand".

## [0.2.0] - 2021

Not tagged in this repository; recorded for the history.

### Added

- A program written to validate the 1997 equation, 24 years after it was
  devised. It worked, but only after factoring the divisor into primes first —
  a step that always felt like a workaround rather than part of the idea.
- `DivisibilityRuleExecutionTest` and `DivisibilityRuleAllExecutionTest`.

## [0.1.0] - 1997

Not tagged in this repository; recorded for the history.

### Added

- The original equation, devised by a high-school student in Izmir with a basic
  calculator while hunting for a way to detect prime numbers. Noticing that
  divisors sharing a ones digit have similar divisibility rules led to one
  equation covering them all. Mailed to TÜBİTAK as a mathematics project.
  Nothing came of it for 24 years.

[Unreleased]: https://github.com/lemiorhan/grand-unified-divisibility-rule/compare/v1.0.0...HEAD
[1.0.0]: https://github.com/lemiorhan/grand-unified-divisibility-rule/releases/tag/v1.0.0
