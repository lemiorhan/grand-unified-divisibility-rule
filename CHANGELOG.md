# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

The 1997 entry describes the history of the discovery itself rather than a
release; it is recorded here because this repository is the only published
account of it.

## [Unreleased]

Nothing yet.

## [2.2] - 2026-07-26

A literature search, done properly for the first time, established that the
mathematics in this project is not new. This release makes the project say so.
No code changed; every claim did.

### Added

- **`Prior Art and Related Work` section in `README.md`**, mapping every
  mathematical claim in the document to its published source, with the exact
  point of overlap named for each. Sixteen references, from Pascal (c. 1654) to
  O'Shea (2019).
- **`What This Project Adds` section in `README.md`**, stating the four things
  that do appear to be original — a closed form for the trimming coefficients,
  its exactness criterion and failure set, the 1997 rediscovery itself, and the
  implementation and its presentation — and stating them as small.
- **`References` section in `README.md`** with anchored, fully cited entries,
  linked from every claim throughout the document.
- **`references:` block in `CITATION.cff`**, so that tooling which reads the
  citation metadata also sees the prior art.
- **`PRIOR ART` section in `NOTICE`**, naming Pascal, Zbikowski, Grinberg &
  Luryi and O'Shea, and stating plainly that this is an independent rediscovery.
- **`Prior Art` section on the website**, in all four languages, with links to
  every source.

### Changed

- **The name `Lemi's Formula` is retired**, everywhere: `README.md`,
  `CITATION.cff`, `NOTICE`, the page title, the site navigation and all four
  translations. `Lemi's Equation` is now `The 1997 Equation`. The result is
  Pascal's, and keeping a personal name on it was wrong.
- **The generalized rule is now attributed to Pascal**, *De numeris
  multiplicibus* (c. 1654): the weights, the coverage of every divisor, the base
  independence and the remainder property were all already there. The three-line
  proof is identified as the standard proof of Pascal's criterion.
- **The prime-factor story is corrected.** The 1997 equation is exactly Grinberg
  & Luryi's restricted rule at the parameter choice `q = CEIL(10/b2) − 1`,
  verified algebraically for every divisor from 2 to 99. The failures at 16, 18,
  27 and the rest are an artifact of hard-wiring that parameter, not a fact
  about composite divisors — those divisors have perfectly good rules in
  Grinberg & Luryi's Table 1. "Primes are special here" was a coincidence of one
  coefficient choice.
- **The multiplier table for divisors ending in 1, 3, 7, 9 is no longer
  presented as a correction discovered in 2026.** The multipliers are
  `10⁻¹ mod d` — Zbikowski's `ω_q`, and the osculators of Vedic arithmetic. The
  `m = 5` row for divisors ending in 7 is Kordemsky's puzzle 320.
- **The claim that the trimming and summing rules are "the same rule in opposite
  directions" is attributed to O'Shea (2019)**, whose paper in *Mathematics
  Magazine* proves exactly this.
- **The citation guidance now points elsewhere first.** `README.md` and `NOTICE`
  ask that the mathematics be cited to its authors, and this repository only for
  the implementation and the narrow original parts.
- **`CITATION.cff` title and abstract rewritten** to describe the project as a
  traceable implementation and an independent rediscovery.

## [2.1] - 2026-07-26

The project is set up as an open-source project, and relicensed so that the code
carries a software licence.

### Changed

- **License, split by material type.** Source code moved from CC BY 4.0 to the
  **Apache License 2.0**, which is a software license and carries an express
  patent grant. Prose and documentation remain under **CC BY 4.0**, now in
  `LICENSE-DOCS`. Attribution requirements are collected in `NOTICE`, whose
  contents Section 4(d) of Apache 2.0 requires every redistribution to carry.
  Source files gained an Apache header with an SPDX identifier, and the site
  footer names both licenses in all four languages. See the License section of
  `README.md`.
- **Toolchain upgraded to support Java 25.** Gradle 7.6.4 → **9.6.1**, Groovy
  3.0.8 → **5.0.6**, Spock 2.0 → **2.4-groovy-5.0**, SLF4J 1.7.32 → **2.0.18**.
  Groovy 5 is what carries JDK 25 support, and it moves the Groovy groupId from
  `org.codehaus.groovy` to `org.apache.groovy`. The explicit `junit-jupiter`
  dependencies are dropped — every test is a Spock specification, and Spock
  supplies the JUnit Platform it needs — and `junit-platform-launcher` is now
  declared, because Gradle 9 no longer adds it implicitly. **JDK 17 is now the
  minimum**, since Groovy 5 requires it.
- **Build metadata.** `group` corrected from the placeholder `org.example` to
  `com.lemiorhanergin`, and the version from `1.0-SNAPSHOT` onward. Property
  assignment switched to `=` syntax, which Gradle 10 will require; the build is
  warning-free.

### Added

- **`NOTICE`**, collecting the attribution requirements and separating what is a
  license condition from what is an academic request — the mathematics itself is
  not subject to copyright.
- **`CITATION.cff`**, driving GitHub's "Cite this repository" button, and a
  Zenodo archive. The concept DOI
  [10.5281/zenodo.21572577](https://doi.org/10.5281/zenodo.21572577) always
  resolves to the newest archived version.
- **Project files**: `CONTRIBUTING.md`, `CODE_OF_CONDUCT.md` (Contributor
  Covenant 2.0), `SECURITY.md`, this changelog, `.editorconfig`, `CODEOWNERS`,
  a pull request template, and three issue templates including a Counterexample
  form, since a wrong answer is the most valuable report this project can get.
- **CI** on every push and pull request, running the Groovy suite on JDK 17, 21
  and 25, plus a job that keeps the site in step with the reference
  implementation: every language must define identical keys, every key used in
  `index.html` must exist, and the JavaScript port must agree with integer
  division across all 10,000 dividend/divisor pairs up to 100. **Dependabot** is
  enabled for Gradle and Actions.

## [2.0] - 2026-07-25

The rule is generalized, proved, implemented, tested and published, together
with the interactive site. Archived on Zenodo as
[10.5281/zenodo.21572578](https://doi.org/10.5281/zenodo.21572578), which
predates the relicensing and therefore records CC BY 4.0.

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
- **Test suite** covering all 10,000 dividend/divisor pairs from 1 to 100, 25
  seeded random dividends of 3 to 9 digits, and a guard test that fails the
  build if prime-factor detection ever returns. The 2021 suites are retained and
  pass unchanged.
- **Interactive website** at
  [lemiorhan.github.io/grand-unified-divisibility-rule](https://lemiorhan.github.io/grand-unified-divisibility-rule/),
  which runs the algorithm step by step in the browser and reproduces the Groovy
  trace exactly. Available in English, Türkçe, 中文 and Español, with light and
  dark themes.

### Changed

- **The site's explanation.** The section on why the first algorithm needed
  prime factors is removed; the one fact it carried is folded into the closing
  note of the 1997 Formula section, which links to the README for the algebra.
  The eleven cells of the "classical rule it becomes" column now state the
  classical rule a reader half-remembers from school, instead of terse fragments
  and commentary about the 1997 equation.

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

## [1.0] - 2021-12-14

### Added

- A program written to validate the 1997 equation, 24 years after it was
  devised. It worked, but only after factoring the divisor into primes first —
  a step that always felt like a workaround rather than part of the idea.
- `DivisibilityRuleExecutionTest`, a data-table regression suite of 120 fixed
  dividend/divisor cases, and `DivisibilityRuleAllExecutionTest`, which sweeps
  every divisor up to each dividend from 2 to 1000.

## 1997 — the original equation

Not a release; recorded for the history.

The original equation, devised by a high-school student in Izmir with a basic
calculator while hunting for a way to detect prime numbers. Noticing that
divisors sharing a ones digit have similar divisibility rules led to one
equation covering them all. Mailed to TÜBİTAK as a mathematics project. Nothing
came of it for 24 years.

[Unreleased]: https://github.com/lemiorhan/grand-unified-divisibility-rule/compare/v2.1...HEAD
[2.1]: https://github.com/lemiorhan/grand-unified-divisibility-rule/compare/v2.0...v2.1
[2.0]: https://github.com/lemiorhan/grand-unified-divisibility-rule/compare/v1.0...v2.0
[1.0]: https://github.com/lemiorhan/grand-unified-divisibility-rule/releases/tag/v1.0
