# Security Policy

## Scope

This repository contains a mathematical result, a reference implementation of it
in Groovy, and a static website that runs the same algorithm in the browser.
There is no server, no database, no authentication and no network I/O anywhere in
the project, so the realistic security surface is small. Reports that are in
scope include:

- A correctness bug with security consequences for a downstream user — for
  example an input that makes `DivisibilityRule` return the wrong answer, throw
  unexpectedly, or fail to terminate.
- Unbounded resource consumption reachable from ordinary input, such as an
  input size that makes the reduction loop run for an unreasonable time.
- Anything in the published site under `docs/` that could execute untrusted
  content — for instance a way to get attacker-controlled markup into the page
  through the calculator inputs or the `?lang=` query parameter.
- A supply-chain problem in the build, such as a compromised or
  wrongly-pinned dependency in `build.gradle`.

Out of scope: the mathematics being unfashionable, the site's lack of a Content
Security Policy on GitHub Pages, missing security headers that a static host
does not control, and automated scanner output with no demonstrated impact.

## Supported versions

The `main` branch is the only supported version. Fixes land there and are
included in the next release.

| Version | Supported |
| ------- | --------- |
| `main`  | Yes       |
| Tagged releases before the latest | No — please upgrade |

## Reporting a vulnerability

**Please do not open a public issue for a security report.**

Report privately through GitHub:

1. Go to
   [Security → Report a vulnerability](https://github.com/lemiorhan/grand-unified-divisibility-rule/security/advisories/new).
2. Describe the issue, the impact you believe it has, and the steps to
   reproduce it. A failing input pair — dividend and divisor — is ideal.
3. Mention the commit or release you tested against.

This creates a private advisory visible only to you and the maintainer. No email
address is published for this project deliberately; the private advisory form is
the intended channel.

> Maintainer note: private vulnerability reporting must be switched on once, in
> **Settings → Advanced Security → Private vulnerability reporting**, for the
> link above to work.

## What to expect

This is a single-maintainer project worked on in spare time, so the timelines
below are honest intentions rather than a contractual guarantee.

| Stage | Target |
| ----- | ------ |
| Acknowledgement of your report | Within 7 days |
| Initial assessment, including whether it is in scope | Within 14 days |
| Fix or a documented decision not to fix | Within 90 days |

If a report is valid you will be credited in the advisory and in
`CHANGELOG.md`, unless you would rather stay anonymous. Please give the
maintainer a chance to publish a fix before disclosing publicly; coordinated
disclosure is appreciated but not legally required of you.

## Verifying a fix yourself

The whole result is checkable from source. After any change:

```sh
./gradlew test
```

The suite exhaustively verifies the algorithm against integer division over a
large range of dividend/divisor pairs, so a correctness regression should fail
the build rather than reach a release.
