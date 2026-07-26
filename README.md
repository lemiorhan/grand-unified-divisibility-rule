# Grand Unified Divisibility Rule

[![CI](https://github.com/lemiorhan/grand-unified-divisibility-rule/actions/workflows/ci.yml/badge.svg)](https://github.com/lemiorhan/grand-unified-divisibility-rule/actions/workflows/ci.yml)
[![Code: Apache 2.0][apache-shield]][apache]
[![Prose: CC BY 4.0][cc-by-shield]][cc-by]
[![DOI](https://zenodo.org/badge/DOI/10.5281/zenodo.21572577.svg)](https://doi.org/10.5281/zenodo.21572577)
[![Cite this repository](https://img.shields.io/badge/Cite-CITATION.cff-brightgreen.svg)](CITATION.cff)

> 🌐 **Try it live:** [lemiorhan.github.io/grand-unified-divisibility-rule](https://lemiorhan.github.io/grand-unified-divisibility-rule/) —
> enter any dividend and divisor, and watch the algorithm run step by step in your browser.
> Available in English, Türkçe, 中文 and Español.

`Divisibility rules` are fast and simple calculations to help you to determine whether a given integer is divisible by
another integer without performing the division, usually by examining its digits in your head.

Divisibility rules of one-digit numbers are very easy to learn and remember, and very practical to use. Kids learn them
usually at primary school. Knowing these practical tricks increases your speed at solving mathematical problems.
Therefore, divisibility rules could be the most recognized mathematical shortcuts people use in calculations all around
the world.

With the work I did in 1997 when I was in high school, I can tell you that divisibility rules for any numbers can be
derived from one single formula. All divisibility rules are very similar, like the rule for 2 and 42, and even 692 are
the same. This document explains the root formula from where each divisibility rule derives — and how the formula
matured into a single rule that works for **every** divisor, with no prime factors, no division, and a three-line
proof.

I have no concerns about practicality, performance, or simplicity. My main concern is showing how divisibility rules
share a common algorithm and each one can be derived from the main formula.

> ### On novelty: this is a rediscovery, not a discovery
>
> After finishing this write-up I searched the literature properly, and the mathematics here turns out not to be new.
> It is a rediscovery, and in one case a rediscovery of something 372 years old:
>
> * The **generalized rule** in this document is **Pascal's divisibility criterion**, described by Blaise Pascal in
>   *De numeris multiplicibus* (written around 1654, published 1665). Pascal already had the weights, the coverage of
>   every divisor, the base independence, and the fact that the result is the remainder itself.
>   [[P]](#ref-pascal) [[M]](#ref-mcdowell)
> * The **1997 equation** belongs to the family of trimming tests published by **A. Zbikowski** in 1861, and is exactly
>   one member of the two-parameter family **Grinberg & Luryi** describe in 2014 — I verified the algebra, see
>   [Prior Art](#prior-art-and-related-work). [[Z]](#ref-zbikowski) [[GL]](#ref-grinberg)
> * The **2026 insight** that the trimming rule and the weighted-sum rule are the same rule run in opposite directions
>   is the subject of **O'Shea's 2019 paper** in *Mathematics Magazine*. [[O]](#ref-oshea)
>
> I found these independently, decades apart, without knowing they existed — but independent rediscovery is not
> invention. Every claim in this document is mapped to its published source in
> [Prior Art and Related Work](#prior-art-and-related-work), and the small part that does appear to be mine is set out
> honestly in [What This Project Adds](#what-this-project-adds). I have kept the story exactly as it was and added the
> references around it, because a rediscovery that names its predecessors is worth more than a discovery claim that
> does not survive a literature search.

# History

When I was at [high school](https://izmir60yilanadolu.meb.k12.tr/) in Turkey in 1997, I decided to investigate an
algorithm for detecting prime numbers as a maths project. Since prime numbers are the numbers only divided by one and
itself, I started to understand the divisibility rules of the first few prime numbers, 5, 7, 11, 13, 17, and 19. I
thought that If I understand the logic behind these rules, I can find a common algorithm and detect prime numbers with
that algorithm.

I had no computers at that time. With a basic calculator, I did thousands of calculations. At some point, I figured out
that the divisibility rule of 2 is similar to the divisibility rule of 12, and 22, and 32, etc. Interestingly
divisibility rule of 3 is also similar to the divisibility rule of 13, 23, etc. Then I had a eureka! moment. Clearly,
the divisibility rules are very similar among numbers sharing the same ones place digit. For instance, for the prime
numbers 3, 13, 443 and 7793, since the ones digit is the same (that is 3), the divisibility rules for these numbers are
very similar.

After working months spending hundreds of hours with my calculator, I found one single formula and an execution
algorithm covering all possible known divisibility rules. I documented everything with my very-amateur words to a paper,
put it in an envelope, and sent it
to [TUBITAK](https://en.wikipedia.org/wiki/Scientific_and_Technological_Research_Council_of_Turkey) as a Math Project on
my own. Nothing happened of course. I had no expectations since it was very poorly written with no academic background,
and I was just a high school kid working alone.

After 24 years, in 2021, I remembered the old good days of divisibility rules. I found my papers and wrote
a [simple program](https://github.com/lemiorhan/grand-unified-divisibility-rule) for validating my findings by executing
the algorithm. Guess the result: It works!

That first program had one thing that always bothered me: it needed the prime factors of the divisor before it could
run the formula. That was a workaround, not part of the idea. In 2026 I finally understood *why* the workaround was
needed, and generalized the formula so that the algorithm works for any divisor directly — no prime factors, no
division, nothing but digits, small multiplications and subtraction. This document tells the whole story: the original
formula, the reason it needed primes, and the generalized rule that doesn't.

If you are here reading my words, thanks for your interest! As of
2026, [I am a software crafter](https://www.linkedin.com/in/lemiorhan/) living in Istanbul. I am the co-founder
of [Craftgate, the cloud-based payment gateway](https://www.craftgate.io). I am neither a mathematician, not an
academician. This post is not in academic paper standards. However, it contains a true story of my passion and my
amateur findings of Grand Unified Divisibility Rule. For years I called the equation by my own name. I have dropped
that: the literature search I finally did in 2026 showed the result had been published long before I was born, and it
would be wrong to keep my name on someone else's theorem. What the name is attached to now is the write-up, the
implementation and the website — not the mathematics.

# Terminology

* `Ones place digit` is the last digit of a number. For instance, for number 5632, ones place digit is 2.
* `Remaining digits` is the number containing all the digits except the last digit. For instance, for number 5632, the
  remaining digits are 563.
* `Coefficient` is a numerical or constant quantity placed before and multiplying the variable in an algebraic
  expression.
* `Dividend` is the number that is to be divided by the divisor. For instance, for division 75 / 20, 75 is the dividend.
* `Divisor` is the factor that divides the dividend. For instance, for division 75 / 20, 20 is the divisor.
* `Quotient` is the result of the division. For instance, for division 75 / 20, 3 is the quotient.
* `Remainder` is the number that is left after division. For instance, for division 75 / 20, 15 is the remainder.
* `Weight` is the number a digit is multiplied with in the generalized rule. The ones place digit has weight 1, and
  every next digit gets its weight derived from the previous weight.

# Well-Known Divisibility Rules for One-Digit Numbers

Before starting with my findings, let's look at some common well-known divisibility rules that we usually learn in the
days of primary school. Even though the divisibility rule of 3 and 9, and the divisibility rule of 4 and 8 resemble each
other, they don't share a common pattern. Each one seems to be specialized for being simpler and practical for being
easy to remember by the majority.

### Divisible by 2

A number is even or a number whose ones place digit is an even number i.e. 0, 2, 4, 6, and 8.

For instance, 84 is divisible by 2, but 37 is not.

### Divisible by 3

The sum of all digits of the number should be divisible by 3.

For instance, 183 is divisible by 3 because 1+8+3 is 12, and 12 is divisible by 3. However, 121 is not divisible by 3,
because 1+2+1 is 4, and 4 is not divisible by 3.

### Divisible by 4

The number formed by the last two digits of the number should be divisible by 4 or should be 00.

For instance, 816 is divisible by 4, because the last two digits 16 is divisible by 4. 3100 is divisible by 4 because
the last two digits are 00. However, 450 is not divisible by 4, because the last two digits are not divisible by 4.

### Divisible by 5

Numbers having 0 or 5 as their ones place digit.

For instance, 40 is divisible by 5, because the last digit is 0. 41 is not divisible by 5, because the last digit is
neither 0 or 5.

### Divisible by 6

A number that is divisible by both 2 and 3.

For instance, 222 is divisible by 6, because it is both divisible by 2 and 3. 266 is not divisible by 6, because it is
divisible by 2 but not 3.

### Divisible by 7

Subtracting twice the last digit of the number from the remaining digits gives a multiple of 7.

For instance, 259 is divisible by 7, because 2 x the last digit 9 = 18 and 25 - 18 is 7 and 7 is divisible by 7. 155 is
not divisible by 7, because 2 x 5 is 10 and 15 - 10 is 5 and 5 is not divisible by 7.

### Divisible by 8

The number formed by the last three digits of the number should be divisible by 8 or should be 000.

For instance, 25032 is divisible by 8, because the last three digits 032 is divisible by 8. 85000 is divisible by 8
because the last three digits are 000. 65310 is not divisible by 8, because the last three digits 310 is not divisible
by 8.

### Divisible by 9

The sum of all the digits of the number should be divisible by 9.

For instance, 819 is divisible by 9, because 8+1+9 is 18 and 18 is divisible by 9. 219 is not divisible by 9 because
2+1+9 is 12, and 12 is not divisible by 9.

### Divisible by 10

Any number whose ones place digit is 0.

For instance, 560 is divisible by 10, because the last digit is 0. 651 is not divisible by 10, because the last digit is
not 0.

As you see the divisibility rules for one-digit numbers are pretty practical. All can be done in your head in seconds.
In contrast, the divisibility rules of bigger numbers require extra calculation and are not very practical in reality.

# The 1997 Discovery: One Formula for Numbers with the Same Ones Place Digit

I noticed that divisibility rules of numbers having the same ones place digit are very similar, so similar that we can
end up with a formula about it. Here are the rules for the numbers between 11 and 50, as I derived them in 1997. Only
divisors ending in 0 are missing: the equation cannot even be written for them, since `CEIL(10 / 0)` does not exist —
the generalized rule later in this document covers them like any other divisor.

> **Prior art for this section.** What follows is a *trimming test* in the standard terminology, and the family it
> belongs to was published by A. Zbikowski in 1861 [[Z]](#ref-zbikowski) — see Cherniavsky & Mouftakhov for a modern
> treatment [[CM]](#ref-cherniavsky). The particular closed form below, which reads the coefficients off the divisor's
> last digit, is the one thing here I have not found in print; the coefficients it produces, however, are the classical
> ones. Details in [Prior Art](#prior-art-and-related-work).

* Divisible by 11, if `(10 x last digit + 1 x remaining digits)` is divisible by 11
* Divisible by 12, if `(5 x last digit + 2 x remaining digits)` is divisible by 12
* Divisible by 13, if `(4 x last digit + 1 x remaining digits)` is divisible by 13
* Divisible by 14, if `(3 x last digit + 2 x remaining digits)` is divisible by 14
* Divisible by 15, if `(2 x last digit + 5 x remaining digits)` is divisible by 15
* Divisible by 16, if `(2 x last digit + 4 x remaining digits)` is divisible by 16 `(*)`
* Divisible by 17, if `(2 x last digit + 3 x remaining digits)` is divisible by 17
* Divisible by 18, if `(2 x last digit + 2 x remaining digits)` is divisible by 18 `(*)`
* Divisible by 19, if `(2 x last digit + 1 x remaining digits)` is divisible by 19
* Divisible by 21, if `(19 x last digit + 1 x remaining digits)` is divisible by 21
* Divisible by 22, if `(9 x last digit + 2 x remaining digits)` is divisible by 22
* Divisible by 23, if `(7 x last digit + 1 x remaining digits)` is divisible by 23
* Divisible by 24, if `(5 x last digit + 2 x remaining digits)` is divisible by 24
* Divisible by 25, if `(3 x last digit + 5 x remaining digits)` is divisible by 25
* Divisible by 26, if `(3 x last digit + 4 x remaining digits)` is divisible by 26
* Divisible by 27, if `(3 x last digit + 3 x remaining digits)` is divisible by 27 `(*)`
* Divisible by 28, if `(3 x last digit + 2 x remaining digits)` is divisible by 28
* Divisible by 29, if `(3 x last digit + 1 x remaining digits)` is divisible by 29
* Divisible by 31, if `(28 x last digit + 1 x remaining digits)` is divisible by 31
* Divisible by 32, if `(13 x last digit + 2 x remaining digits)` is divisible by 32
* Divisible by 33, if `(10 x last digit + 1 x remaining digits)` is divisible by 33
* Divisible by 34, if `(7 x last digit + 2 x remaining digits)` is divisible by 34
* Divisible by 35, if `(4 x last digit + 5 x remaining digits)` is divisible by 35
* Divisible by 36, if `(4 x last digit + 4 x remaining digits)` is divisible by 36 `(*)`
* Divisible by 37, if `(4 x last digit + 3 x remaining digits)` is divisible by 37
* Divisible by 38, if `(4 x last digit + 2 x remaining digits)` is divisible by 38 `(*)`
* Divisible by 39, if `(4 x last digit + 1 x remaining digits)` is divisible by 39
* Divisible by 41, if `(37 x last digit + 1 x remaining digits)` is divisible by 41
* Divisible by 42, if `(17 x last digit + 2 x remaining digits)` is divisible by 42
* Divisible by 43, if `(13 x last digit + 1 x remaining digits)` is divisible by 43
* Divisible by 44, if `(9 x last digit + 2 x remaining digits)` is divisible by 44
* Divisible by 45, if `(5 x last digit + 5 x remaining digits)` is divisible by 45 `(*)`
* Divisible by 46, if `(5 x last digit + 4 x remaining digits)` is divisible by 46
* Divisible by 47, if `(5 x last digit + 3 x remaining digits)` is divisible by 47
* Divisible by 48, if `(5 x last digit + 2 x remaining digits)` is divisible by 48
* Divisible by 49, if `(5 x last digit + 1 x remaining digits)` is divisible by 49

`(*)` Keep an eye on the six rules marked with a star: 16, 18, 27, 36, 38 and 45. In 1997 they looked exactly as
trustworthy as the other thirty, but they are the ones hiding a flaw that stayed invisible for decades — dissected in
"Why the First Algorithm Needed Prime Factors" below.

There is a pretty obvious pattern among these rules, isn't there? For the given:

* `CEIL`: Mathematical ceil operator
* `a1`: Remaining digits of the dividend
* `a2`: Remaining digits of divisor
* `b1`: Ones digit of the dividend
* `b2`: Ones digit of divisor
* `x`: First coefficient as `(10 + b2 - (b2 * CEIL(10 / b2)))`
* `y`: Second coefficient as `(CEIL(10 / b2) - 1)`

The 1997 equation becomes: `(x * a1) + ((y * a2) + 1) * b1`

Apply the equation to the dividend, then apply it again to the result, and keep going until the number is small. If
you end up on a multiple of the divisor, the dividend is divisible — or so I concluded in 1997. The next section shows
exactly where that conclusion breaks.

# Why the First Algorithm Needed Prime Factors

The 2021 implementation of the equation had a preprocessing step: find the prime factors of the divisor, and run the
formula for each prime factor separately. It always felt like a workaround. Here is the actual mathematics behind it.

Take the divisor `d = 10 x a2 + b2` and the dividend `N = 10 x a1 + b1`, and call the equation's result `f(N)`. The
definitions of `x` and `y` guarantee `x + (y · b2) = 10`, and rearranging the equation with that fact reveals an exact
identity:

```
f(N) = ((y · a2) + 1) · N - (y · a1) · d
```

In modular-arithmetic terms: `f(N)` is congruent to `N` multiplied by the constant `(y · a2) + 1` modulo `d`. So one
application of the equation does not preserve "the remainder of N by d" — it preserves it *scaled by that constant*.
The conclusion "d divides f(N) exactly when d divides N" is valid exactly when the scaling constant shares no factor
with the divisor:

```
gcd((y · a2) + 1, d) = 1
```

When the criterion fails, the equation breaks:

1. For d = 16 the scaling constant is `(1 x 1) + 1 = 2`, and 2 shares a factor with 16: the equation maps 8 to
   `(4 x 0) + 2 x 8 = 16`, landing exactly on the divisor and falsely declaring 8 divisible by 16.
2. For d = 27 the scaling constant is `(1 x 2) + 1 = 3`, and 3 shares a factor with 27: the equation maps 9 to
   `(3 x 0) + 3 x 9 = 27` — again landing exactly on the divisor, falsely declaring 9 divisible by 27.

Leaving aside divisors ending in 0, where the equation is not even defined, the divisors below 100 that break the
equation are 16, 18, 27, 36, 38, 45, 56, 57, 58, 76, 78, 87, 95, 96 and 98 — every single one of them composite. Now
the workaround explains itself: **the criterion holds automatically for every prime**. The scaling constant always
stays strictly between 0 and d (for one-digit divisors it is simply 1), so for a prime divisor it can never share a
factor with d. Factoring the divisor into primes was silently repairing the broken composite cases. For 29 years the
primes masked the real constraint of the formula.

> **Prior art for this section.** There is a sharper way to say what went wrong, and it comes from Grinberg & Luryi
> [[GL]](#ref-grinberg). They study exactly this shape of rule — they call `R = u·B + w·b` a *restricted rule*, with `B`
> the remaining digits and `b` the ones digit — and their validity condition is that `N = 10w − u` must be a multiple of
> the divisor. Substituting the coefficients above gives
>
> ```
> 10·((y·a2) + 1) − x = 10·(y·a2) + 10 − (10 − y·b2) = y·(10·a2 + b2) = y·d
> ```
>
> so the 1997 equation is precisely their restricted rule for the choice `q = y = CEIL(10 / b2) − 1`. I verified this
> for every divisor from 2 to 99 with no mismatches. The important consequence: **`q` is a free parameter, and 1997-me
> hard-wired one particular value of it.** Grinberg & Luryi's Table 1 lists working rules for 16, 18 and 27 — the very
> divisors that break here — by picking a different `q` (for 16 they take `N = −32`, giving `2B − 3b`). So the failures
> were never about primality. They were about a fixed coefficient choice that happens to share a factor with certain
> composite divisors. Prime factorization repaired the symptom; choosing `q` per divisor would have repaired the cause;
> and the generalization below sidesteps the parameter entirely.

So the fix is not to patch the equation. The fix is to see what the equation actually is — and generalize it.

# The Grand Unified Divisibility Rule

Here is the rule that works for **every** divisor, prime or composite, even or odd, with no factoring and no division:

> A number is divisible by `d` exactly when the **weighted sum of its digits** is divisible by `d`.
> The weight of the ones place digit is **1**. Every next weight is the previous weight with a zero appended
> (multiplied by 10), then **reduced below d by subtracting d** as many times as needed.

The full toolkit of the rule is: read a digit, multiply a digit by a weight, subtract. Exactly the operations the
classical divisibility rules allow. While summing, the running total is also reduced below `d` by subtraction, so all
numbers stay small. And as a bonus, the final total is not just a yes/no signal — it is precisely the **remainder** of
the division.

> **Prior art for this section — this rule is Pascal's.** This is the point where the literature search stopped being
> comfortable. The rule above is **Pascal's divisibility criterion**, from *De numeris multiplicibus*, written around
> 1654 and published in 1665 [[P]](#ref-pascal). Pascal's statement is that you may replace each power of ten in the
> number's representation by its remainder modulo `D`, and *"le nombre obtenu aura toujours même reste que A dans la
> division par D"* — the resulting number always has the same remainder as `A` divided by `D` [[FR]](#ref-frwiki). That
> single sentence already contains everything I present above as new: the weights are the remainders of the powers of
> ten, it applies to **every** positive integer divisor with no coprimality condition, and the output is the remainder
> itself rather than a yes/no answer. Pascal also noted it works in any base. McDowell's history of divisibility tests
> covers this directly [[M]](#ref-mcdowell). In modern terms the same rule is the *binomial test*
> `B_q(a) = Σ (10 − q)^j · a_j` [[O]](#ref-oshea), and Pal's paper is a good place to see the contrast — his
> trimming-style test explicitly *cannot* recover the remainder, which is exactly the advantage Pascal's form has
> [[Pal]](#ref-pal). What I contributed here is a division-free way to *generate* the weights and a traceable
> implementation of it, not the criterion.

### Every classical rule is a row of this rule

Compute the weight sequence for a divisor and you get the divisibility rule for that divisor. The famous rules stop
being separate tricks and become rows of one table:

| divisor | weights (ones digit first)     | the classical rule it becomes                                 |
|---------|--------------------------------|---------------------------------------------------------------|
| 2       | 1, 0, 0, 0, ...                | only the last digit matters                                    |
| 3       | 1, 1, 1, 1, ...                | sum of all digits                                              |
| 4       | 1, 2, 0, 0, ...                | last two digits (as `2 x tens + ones`)                         |
| 5       | 1, 0, 0, 0, ...                | only the last digit matters                                    |
| 7       | 1, 3, 2, 6, 4, 5, 1, 3, ...    | the classical weighted rule for 7                              |
| 8       | 1, 2, 4, 0, 0, ...             | last three digits                                              |
| 9       | 1, 1, 1, 1, ...                | sum of all digits                                              |
| 11      | 1, 10, 1, 10, ...              | alternating sum, because 10 acts as -1                         |
| 12      | 1, 10, 4, 4, 4, ...            | a rule the old algorithm needed prime factors for              |
| 13      | 1, 10, 9, 12, 3, 4, 1, 10, ... | repeats with period 6, like 7                                  |
| 16      | 1, 10, 4, 8, 0, 0, ...         | weights die out: last four digits decide                       |
| 20      | 1, 10, 0, 0, ...               | a divisor the 1997 equation could not even write down          |
| 27      | 1, 10, 19, 1, 10, 19, ...      | repeats with period 3 — the case that broke the 1997 equation  |
| 45      | 1, 10, 10, 10, ...             | one more rule that used to require factoring into 3, 3, 5      |

Divisors made only of 2s and 5s — 2, 4, 5, 8, 10, 16 and so on — make their weights collapse to 0; that is exactly why
only the last few digits matter for them. Divisors sharing no factor with 10 make their weights cycle forever. Divisors
mixing both kinds, like 12 and 45, do a little of each: after a short lead-in the weights settle into a nonzero cycle,
so every digit still matters. One mechanism explains all three behaviors.

### The proof

This time there is a proof, and it fits in three lines. It is the standard proof of Pascal's criterion — the same
argument found in any elementary number theory treatment [[P]](#ref-pascal) [[M]](#ref-mcdowell) — written out in the
vocabulary of this document:

1. Subtracting `d` from a number never changes its remainder by `d`. So by induction, every weight has the same
   remainder by `d` as the power of ten it replaced: `weight(t)` is congruent to `10^t (mod d)`.
2. Therefore the weighted digit sum is congruent to `digit(0) x 10^0 + digit(1) x 10^1 + ...` which *is* the number
   `N` itself: the weighted sum is congruent to `N (mod d)`.
3. The running total is kept below `d` and stays congruent to `N`, so the final total is exactly the remainder of `N`
   divided by `d`. It is 0 exactly when `d` divides `N`. ∎

### Where the 1997 equation fits

The 1997 equation is this same rule executed in the opposite direction. Trimming the ones digit and folding it back with
a multiplier is walking the digits right-to-left with weights that are powers of the **inverse of 10 modulo d** — and
an inverse of 10 exists only when d shares no factor with 10. That is the deep reason the old algorithm had to fall
back to prime factors. The generalized rule walks left with plain powers of 10, which exist for every divisor, so the
constraint disappears.

The equation itself survives as the elegant special case for divisors ending in 1, 3, 7 and 9 — with one correction.
The exact "fold the last digit back" multiplier `m` satisfying `10 x m = k x d + 1` is:

| divisor ends in | multiplier m     | k (the digit making k x d end in 9) |
|-----------------|------------------|--------------------------------------|
| 1               | `9 x a2 + 1`     | 9                                    |
| 3               | `3 x a2 + 1`     | 3                                    |
| 7               | `7 x a2 + 5`     | 7                                    |
| 9               | `1 x a2 + 1`     | 1                                    |

For divisors ending in 1, 3 and 9 these are exactly the coefficients `(y x a2) + 1` of the 1997 equation — the high
school formulas were already the exact inverses. Only the row for 7 differed, and that single row was the other crack
that prime factorization was papering over. With `m` in hand, `N = 10 x a1 + b1` is divisible by `d` exactly when
`a1 + m x b1` is divisible by `d`, for any `d` coprime to 10, composite or prime.

> **Prior art for this section — and a correction.** Two things need naming here.
>
> First, "the two rules are the same rule run in opposite directions" is the result of O'Shea's 2019 paper in
> *Mathematics Magazine*, whose entire subject is unifying the trimming and summing families [[O]](#ref-oshea). It
> proves `(Stack ∘ T_q)^n(a) = S_q(a)` — iterating Zbikowski's right-trimming yields Khare's summing test — and
> `(LStack ∘ LT_q)^n(a) = B_q(a)` — left-trimming yields the binomial test, which is Pascal's rule. The right-to-left
> direction with inverse-of-10 weights is Khare's 1997 test [[K]](#ref-khare); O'Shea notes that "Khare's `γ_q` equals
> Zbikowski's `ω_q`". So both directions and the bridge between them were already in print.
>
> Second, the table above is **not a correction I discovered** — `m` is simply the inverse of 10 modulo `d`, which is
> Zbikowski's `ω_q` [[Z]](#ref-zbikowski) and, in the Vedic arithmetic tradition, the *osculator* obtained by the
> `Ekādhikena Pūrvena` recipe: multiply the divisor until it ends in 9, then take one more than the leading part
> [[V]](#ref-vedic). The row for 7 gives `m = 5`, and multiplying by 5 while walking the digits of a number to test
> divisibility by 7 is Kordemsky's puzzle from *The Moscow Puzzles* [[Ko]](#ref-kordemsky), reproduced as the opening
> example of Pal's paper [[Pal]](#ref-pal). I checked all four rows of the table against `pow(10, -1, d)` for sixteen
> divisors and they agree exactly — which is the point: they agree because they are the classical multipliers, not
> because 2026-me fixed something.

# The Algorithm

The algorithm is now a single pass over the digits of the dividend. For divisor `d`:

1. Start with `weight = 1` and `total = 0`.
2. Take the next digit of the dividend, starting from the ones place. Add `digit x weight` to the total. If the total
   reached `d` or more, subtract `d` until it drops below `d`.
3. Compute the next weight: multiply the current weight by 10, and subtract `d` until it drops below `d`.
4. When the digits are consumed, the total is the remainder. The dividend is divisible exactly when it is 0.

No prime factors. No division. No iteration limit — the algorithm finishes in exactly as many steps as the dividend
has digits.

# The Implementation

The code is written in Groovy. You can find it in
[DivisibilityRule.groovy](https://github.com/lemiorhan/grand-unified-divisibility-rule/blob/main/src/main/groovy/com/lemiorhanergin/divisibilityrules/DivisibilityRule.groovy).
The whole algorithm is one loop — input validation and the `trace` output lines are elided here, see the full source
for them:

```groovy
boolean isDivisible(long dividend, long divisor, Appendable trace = null) {
    long remaining = dividend
    long weight = 1
    long total = 0

    while (true) {
        long digit = remaining % 10
        total = reduceBySubtraction(total + (digit * weight), divisor)

        remaining = remaining.intdiv(10)
        if (remaining == 0) break

        weight = reduceBySubtraction(weight * 10, divisor)
    }

    return total == 0
}

private static long reduceBySubtraction(long value, long divisor) {
    long reduced = value
    while (reduced >= divisor) reduced -= divisor
    return reduced
}
```

`remaining % 10` and `remaining.intdiv(10)` only read and trim digits — the base-ten equivalents of looking at the
number written on paper. The divisor is never used for division; it is only ever subtracted. The optional `trace`
parameter receives a line for every single calculation step, so every check can be verified by eye. Before applying
the formula, every check first announces the exact variation it is about to apply — the divisor's weight row, one
weight per digit of the dividend — as a `FORMULA FOR ...` line, and only then do the steps follow.

# Examples

### Is 1044 divisible by 12?

The old algorithm factored 12 into 2 x 2 x 3 and ran three separate checks. The rule now answers directly:

```
DIVISIBILITY CHECK FOR [1044/12]
FORMULA FOR 12 => (1 x DIGIT 1) + (10 x DIGIT 2) + (4 x DIGIT 3) + (4 x DIGIT 4) (DIGIT 1 = ONES PLACE)
STEP 1 => DIGIT 4 x WEIGHT 1 = 4 | TOTAL 4
WEIGHT FOR STEP 2 => 10 x 1 = 10
STEP 2 => DIGIT 4 x WEIGHT 10 = 40 | TOTAL 44, REDUCED TO 8 BY SUBTRACTING 12
WEIGHT FOR STEP 3 => 10 x 10 = 100, REDUCED TO 4 BY SUBTRACTING 12
STEP 3 => DIGIT 0 x WEIGHT 4 = 0 | TOTAL 8
WEIGHT FOR STEP 4 => 10 x 4 = 40, REDUCED TO 4 BY SUBTRACTING 12
STEP 4 => DIGIT 1 x WEIGHT 4 = 4 | TOTAL 12, REDUCED TO 0 BY SUBTRACTING 12
FINAL REMAINDER: 0 => 1044 IS DIVISIBLE BY 12
```

### Is 175789 divisible by 23?

```
DIVISIBILITY CHECK FOR [175789/23]
FORMULA FOR 23 => (1 x DIGIT 1) + (10 x DIGIT 2) + (8 x DIGIT 3) + (11 x DIGIT 4) + (18 x DIGIT 5) + (19 x DIGIT 6) (DIGIT 1 = ONES PLACE)
STEP 1 => DIGIT 9 x WEIGHT 1 = 9 | TOTAL 9
WEIGHT FOR STEP 2 => 10 x 1 = 10
STEP 2 => DIGIT 8 x WEIGHT 10 = 80 | TOTAL 89, REDUCED TO 20 BY SUBTRACTING 23
WEIGHT FOR STEP 3 => 10 x 10 = 100, REDUCED TO 8 BY SUBTRACTING 23
STEP 3 => DIGIT 7 x WEIGHT 8 = 56 | TOTAL 76, REDUCED TO 7 BY SUBTRACTING 23
WEIGHT FOR STEP 4 => 10 x 8 = 80, REDUCED TO 11 BY SUBTRACTING 23
STEP 4 => DIGIT 5 x WEIGHT 11 = 55 | TOTAL 62, REDUCED TO 16 BY SUBTRACTING 23
WEIGHT FOR STEP 5 => 10 x 11 = 110, REDUCED TO 18 BY SUBTRACTING 23
STEP 5 => DIGIT 7 x WEIGHT 18 = 126 | TOTAL 142, REDUCED TO 4 BY SUBTRACTING 23
WEIGHT FOR STEP 6 => 10 x 18 = 180, REDUCED TO 19 BY SUBTRACTING 23
STEP 6 => DIGIT 1 x WEIGHT 19 = 19 | TOTAL 23, REDUCED TO 0 BY SUBTRACTING 23
FINAL REMAINDER: 0 => 175789 IS DIVISIBLE BY 23
```

Notice the weights of 23 running as 1, 10, 8, 11, 18, 19 — the opening of the weight sequence that *is* the
divisibility rule of 23. Unlike the weights of 13, they do not repeat after six steps: the cycle of 23 runs through 22
different weights before returning to 1.

### Is 24 divisible by 16?

A case no last-digit formula can answer, and the old algorithm handled by dividing by 2 four times:

```
DIVISIBILITY CHECK FOR [24/16]
FORMULA FOR 16 => (1 x DIGIT 1) + (10 x DIGIT 2) (DIGIT 1 = ONES PLACE)
STEP 1 => DIGIT 4 x WEIGHT 1 = 4 | TOTAL 4
WEIGHT FOR STEP 2 => 10 x 1 = 10
STEP 2 => DIGIT 2 x WEIGHT 10 = 20 | TOTAL 24, REDUCED TO 8 BY SUBTRACTING 16
FINAL REMAINDER: 8 => 24 IS NOT DIVISIBLE BY 16
```

The final total 8 is the true remainder of 24 / 16.

### Is 9 divisible by 27?

The case that silently broke the 1997 equation (which mapped 9 straight onto 27):

```
DIVISIBILITY CHECK FOR [9/27]
FORMULA FOR 27 => (1 x DIGIT 1) (DIGIT 1 = ONES PLACE)
STEP 1 => DIGIT 9 x WEIGHT 1 = 9 | TOTAL 9
FINAL REMAINDER: 9 => 9 IS NOT DIVISIBLE BY 27
```

### Is 25916917952 divisible by 55456?

The old algorithm needed the prime factorization 2 x 2 x 2 x 2 x 2 x 1733, six separate checks and around eighty
iterations. The rule now answers in eleven steps, one per digit:

```
DIVISIBILITY CHECK FOR [25916917952/55456]
FORMULA FOR 55456 => (1 x DIGIT 1) + (10 x DIGIT 2) + (100 x DIGIT 3) + (1000 x DIGIT 4) + (10000 x DIGIT 5) + (44544 x DIGIT 6) + (1792 x DIGIT 7) + (17920 x DIGIT 8) + (12832 x DIGIT 9) + (17408 x DIGIT 10) + (7712 x DIGIT 11) (DIGIT 1 = ONES PLACE)
STEP 1 => DIGIT 2 x WEIGHT 1 = 2 | TOTAL 2
WEIGHT FOR STEP 2 => 10 x 1 = 10
STEP 2 => DIGIT 5 x WEIGHT 10 = 50 | TOTAL 52
WEIGHT FOR STEP 3 => 10 x 10 = 100
STEP 3 => DIGIT 9 x WEIGHT 100 = 900 | TOTAL 952
WEIGHT FOR STEP 4 => 10 x 100 = 1000
STEP 4 => DIGIT 7 x WEIGHT 1000 = 7000 | TOTAL 7952
WEIGHT FOR STEP 5 => 10 x 1000 = 10000
STEP 5 => DIGIT 1 x WEIGHT 10000 = 10000 | TOTAL 17952
WEIGHT FOR STEP 6 => 10 x 10000 = 100000, REDUCED TO 44544 BY SUBTRACTING 55456
STEP 6 => DIGIT 9 x WEIGHT 44544 = 400896 | TOTAL 418848, REDUCED TO 30656 BY SUBTRACTING 55456
WEIGHT FOR STEP 7 => 10 x 44544 = 445440, REDUCED TO 1792 BY SUBTRACTING 55456
STEP 7 => DIGIT 6 x WEIGHT 1792 = 10752 | TOTAL 41408
WEIGHT FOR STEP 8 => 10 x 1792 = 17920
STEP 8 => DIGIT 1 x WEIGHT 17920 = 17920 | TOTAL 59328, REDUCED TO 3872 BY SUBTRACTING 55456
WEIGHT FOR STEP 9 => 10 x 17920 = 179200, REDUCED TO 12832 BY SUBTRACTING 55456
STEP 9 => DIGIT 9 x WEIGHT 12832 = 115488 | TOTAL 119360, REDUCED TO 8448 BY SUBTRACTING 55456
WEIGHT FOR STEP 10 => 10 x 12832 = 128320, REDUCED TO 17408 BY SUBTRACTING 55456
STEP 10 => DIGIT 5 x WEIGHT 17408 = 87040 | TOTAL 95488, REDUCED TO 40032 BY SUBTRACTING 55456
WEIGHT FOR STEP 11 => 10 x 17408 = 174080, REDUCED TO 7712 BY SUBTRACTING 55456
STEP 11 => DIGIT 2 x WEIGHT 7712 = 15424 | TOTAL 55456, REDUCED TO 0 BY SUBTRACTING 55456
FINAL REMAINDER: 0 => 25916917952 IS DIVISIBLE BY 55456
```

### Is 74284 divisible by 11?

```
DIVISIBILITY CHECK FOR [74284/11]
FORMULA FOR 11 => (1 x DIGIT 1) + (10 x DIGIT 2) + (1 x DIGIT 3) + (10 x DIGIT 4) + (1 x DIGIT 5) (DIGIT 1 = ONES PLACE)
STEP 1 => DIGIT 4 x WEIGHT 1 = 4 | TOTAL 4
WEIGHT FOR STEP 2 => 10 x 1 = 10
STEP 2 => DIGIT 8 x WEIGHT 10 = 80 | TOTAL 84, REDUCED TO 7 BY SUBTRACTING 11
WEIGHT FOR STEP 3 => 10 x 10 = 100, REDUCED TO 1 BY SUBTRACTING 11
STEP 3 => DIGIT 2 x WEIGHT 1 = 2 | TOTAL 9
WEIGHT FOR STEP 4 => 10 x 1 = 10
STEP 4 => DIGIT 4 x WEIGHT 10 = 40 | TOTAL 49, REDUCED TO 5 BY SUBTRACTING 11
WEIGHT FOR STEP 5 => 10 x 10 = 100, REDUCED TO 1 BY SUBTRACTING 11
STEP 5 => DIGIT 7 x WEIGHT 1 = 7 | TOTAL 12, REDUCED TO 1 BY SUBTRACTING 11
FINAL REMAINDER: 1 => 74284 IS NOT DIVISIBLE BY 11
```

The weights of 11 alternate between 1 and 10 — and since 10 behaves as -1, this is the well-known alternating sum
rule, falling out of the general rule by itself.

# The Tests

I used [Groovy](https://groovy-lang.org/) for coding, [Spock Framework](https://spockframework.org/) for the tests and
[Gradle](https://gradle.org/) for building. Run everything with:

```
 > ./gradlew clean test
```

The main suite
is [GrandUnifiedDivisibilityRuleSpec.groovy](https://github.com/lemiorhan/grand-unified-divisibility-rule/blob/main/src/test/groovy/com/lemiorhanergin/divisibilityrules/GrandUnifiedDivisibilityRuleSpec.groovy),
which verifies that:

* the rule matches real division for **every dividend and divisor pair from 1 to 100** — all 10,000 combinations,
  including the even divisors and multiples of 10 that the old algorithm could only reach through prime factors,
* the rule matches real division for **25 random dividends of 3 to 9 digits** (with a fixed random seed, so every run
  checks the same numbers),
* the implementation contains **no prime factor detection** — the suite fails if any method whose name mentions
  primes or factors ever sneaks back into `DivisibilityRule`,
* **every calculation step of every check** is written to `build/divisibility-steps.log`, so all ten thousand plus
  executions can be double-checked by eye, line by line.

The older suites from 2021 are kept and pass unchanged against the generalized rule: `DivisibilityRuleExecutionTest`
is a data-table regression suite of 120 fixed dividend/divisor cases, and `DivisibilityRuleAllExecutionTest` sweeps
every divisor up to each dividend from 2 to 1000, logging any mismatch it finds.

# Prior Art and Related Work

In July 2026, after this document had been written, I finally ran a proper literature search instead of assuming that
"nobody seems to have written this down" meant nobody had. The result is unambiguous: **every mathematical claim in
this document already exists in the literature**, some of it for centuries. This section maps each claim to its
source and says precisely where the overlap lies. Nothing below is hedged — where a source contains my result outright,
it says so.

If you want the field rather than my particular corner of it, start with three surveys: McDowell's history of
divisibility tests [[M]](#ref-mcdowell), Ganzell's *Divisibility Tests, Old and New* [[G]](#ref-ganzell), and Volume I
of Dickson's *History of the Theory of Numbers* [[D]](#ref-dickson), which catalogues divisibility criteria back to
antiquity.

### Claim-by-claim mapping

| What this document presents | Where it already exists | Exactly how they overlap |
|---|---|---|
| The generalized rule: weights are the powers of ten reduced modulo `d`, and the weighted digit sum is congruent to `N` | **Pascal, ~1654** [[P]](#ref-pascal), via McDowell [[M]](#ref-mcdowell) | Complete overlap. Pascal's criterion *is* "replace each power of 10 by its remainder mod `D`". Same weights, same rule. |
| The final total is the exact remainder, not just a yes/no signal — presented above as "a bonus" | **Pascal, ~1654** [[P]](#ref-pascal) [[FR]](#ref-frwiki) | Complete overlap. Pascal's own statement is that the reduced number *"aura toujours même reste que A"* — has the same remainder as `A`. The remainder property is the original formulation, not a bonus on top of it. |
| The rule needs no coprimality condition and covers 2, 4, 8, 16, 20, 45 and every other divisor | **Pascal, ~1654** [[P]](#ref-pascal); the binomial test [[O]](#ref-oshea) | Complete overlap. `a ≡ Σ (10 − q)^j · a_j (mod q)` holds unconditionally for every `q`, which is why the criterion never needed a coprimality hypothesis in the first place. |
| Every classical rule (2, 3, 4, 8, 9, 11) is a row of one table | **Pascal** [[P]](#ref-pascal); **Grinberg & Luryi, 2014** [[GL]](#ref-grinberg) | Complete overlap. Grinberg & Luryi's abstract states it in as many words: *"Well-known divisibility rules for exemplary divisors in the decimal system follow from the universal expression as special cases."* |
| Weights of 2/5-only divisors collapse to 0; coprime-to-10 divisors cycle; mixed divisors do both | Standard consequence of Pascal's criterion [[M]](#ref-mcdowell); Wikipedia [[W]](#ref-wikipedia) | Complete overlap. This is the textbook explanation of *why* the rules for 4 and 8 look at the last digits. |
| The three-line proof | Standard proof of Pascal's criterion [[P]](#ref-pascal) [[M]](#ref-mcdowell) | Complete overlap. Steps 1–3 are the usual induction on `10^t ≡ weight(t) (mod d)`. |
| The 1997 trimming equation `(x·a1) + ((y·a2)+1)·b1` | **Zbikowski, 1861** [[Z]](#ref-zbikowski); **Cherniavsky & Mouftakhov, 2014** [[CM]](#ref-cherniavsky) | Same family. Zbikowski's test is `T_q(a) = ā + ω_q·a₀` with `ω_q = 10⁻¹ mod q`, for divisors ending in 1, 3, 7, 9 — the 1997 equation with the classical multiplier. |
| …and specifically as a member of a two-parameter family | **Grinberg & Luryi, 2014** [[GL]](#ref-grinberg) | Exact identification, verified. Their restricted rule is `R = u·B + w·b`, valid when `N = 10w − u` is a multiple of `d`. Substituting gives `10w − x = y·d`, so the 1997 equation is their rule at `q = y = CEIL(10/b2) − 1`. Checked for all `d` in 2..99: zero mismatches. |
| The corrected multiplier table (`m` for divisors ending in 1, 3, 7, 9) | **Zbikowski, 1861** [[Z]](#ref-zbikowski); Vedic osculators [[V]](#ref-vedic); **Kordemsky** [[Ko]](#ref-kordemsky) | Complete overlap. `m = 10⁻¹ mod d` throughout — verified against `pow(10, -1, d)` for 16 divisors. The `m = 5` for divisors ending in 7 is the classical positive osculator, and the ×5 walk for testing 7 is Kordemsky's problem 320. |
| The trimming rule and the weighted-sum rule are the same rule in opposite directions | **O'Shea, 2019**, *Mathematics Magazine* [[O]](#ref-oshea) | Complete overlap; this is the paper's thesis. Theorem 2: `(Stack ∘ T_q)^n(a) = S_q(a)`. Theorem 3: `(LStack ∘ LT_q)^n(a) = B_q(a)`. Trimming → summing, in both directions. |
| The right-to-left direction uses powers of the inverse of 10 | **Khare, 1997** [[K]](#ref-khare); **Pal, 2005** [[Pal]](#ref-pal) | Complete overlap. Khare's summing test is `S_q(a) = Σ ω_q^j · a_{n−j}` with `ω_q = 10⁻¹ mod q`. Pal gives the same test in Horner form with proof. |
| The trimming rule requires `gcd(d, 10) = 1`, which is why it cannot reach even divisors | Textbook; Wikipedia [[W]](#ref-wikipedia); Sathaye [[S]](#ref-sathaye); Pal's Lemma 2 [[Pal]](#ref-pal) | Complete overlap. Wikipedia states it directly: the rule *"is really a rule for divisibility by any integer relatively prime to 10"*. |
| "One unified framework from which all classical rules follow" as a research goal | **Grinberg & Luryi, 2014** [[GL]](#ref-grinberg); **O'Shea, 2019** [[O]](#ref-oshea); also attempted in [[U]](#ref-udf) | Same goal, reached earlier and more generally — Grinberg & Luryi cover arbitrary bases and give an octal table alongside the decimal one. |

### The two sources that matter most

If you read only two things from the list, read these.

**Pascal, *De numeris multiplicibus* (~1654).** This is the generalized rule of this document, in full, 372 years
earlier — weights, universality, base independence, and the remainder property. There is no meaningful gap between
Pascal's criterion and "The Grand Unified Divisibility Rule" section above. My write-up adds a division-free procedure
for producing the weights and a step-by-step trace; the mathematics is Pascal's.

**Grinberg & Luryi, *General Divisibility Criteria* (2014).** This is the paper that explains the 1997 equation to me
better than I ever explained it myself. It shows the equation was one arbitrary member of a family with a free
parameter, and that the same family covers 16, 18 and 27 perfectly well once you stop hard-wiring that parameter. The
"failing divisors are all composite / primes are special" story in this document is therefore a *coincidence of my
parameter choice*, not a fact about divisibility. It is the single most useful correction the literature search
produced, and it is the reason the section above now reads the way it does.

# What This Project Adds

With the above on the table, here is what I believe is genuinely mine — stated small, because it is small.

1. **A closed form for the trimming coefficients read off the divisor's last digit alone.**
   `x = 10 + b2 − b2·CEIL(10/b2)` and `y = CEIL(10/b2) − 1`. Zbikowski's `ω_q` and Grinberg & Luryi's `(u, w)` are
   obtained by a recipe or chosen from a table; this is a formula in `b2` that needs no search and no table lookup. I
   have not found this particular parametrization in print. It is a convenience, not a theorem.
2. **The exactness criterion for that parametrization.** The identity `f(N) = ((y·a2)+1)·N − (y·a1)·d` and the
   criterion `gcd((y·a2)+1, d) = 1`, together with the complete list of divisors below 100 where it fails — 16, 18, 27,
   36, 38, 45, 56, 57, 58, 76, 78, 87, 95, 96, 98. Because the parametrization in (1) is not in the literature, its
   failure set is not either. Verified two independent ways: by the gcd criterion, and by brute force over all
   dividends below 3000. Both give the identical set. In the language of [[GL]](#ref-grinberg) this answers "which
   members of the family does the choice `q = y` land on, and when is that choice legal".
3. **An independent rediscovery, 1997, on paper, with a pocket calculator.** Not a contribution to mathematics. Still
   the part of this story I am most attached to, and the reason the repository exists.
4. **The engineering and the pedagogy.** A division-free implementation whose only operations on the divisor are
   subtraction, a full trace of every step, exhaustive verification over all 10,000 dividend/divisor pairs from 1 to
   100, and a live four-language visualization of the algorithm. Pascal's criterion is in every number theory book;
   a working, traceable, tested presentation of it that a curious person can run in a browser is rarer, and that is
   what this repository actually offers.

# References

<a id="ref-pascal"></a>
**[P]** Pascal, B. *De numeris multiplicibus ex sola characterum numericorum additione agnoscendis.* Written c. 1654,
presented to the Académie Parisienne in 1654, published 1665 in *Traité du triangle arithmétique*. — The general
divisibility criterion: replace each power of ten by its remainder modulo the divisor.

<a id="ref-zbikowski"></a>
**[Z]** Zbikowski, A. (1861). *Note sur la divisibilité des nombres.* Bulletin de l'Académie impériale des sciences de
St. Pétersbourg, Classe physico-mathématique, **3**: 151–153. — The family of trimming tests.

<a id="ref-cherniavsky"></a>
**[CM]** Cherniavsky, Y., Mouftakhov, A. (2014). *Zbikowski's Divisibility Criterion.* The College Mathematics Journal
**45**(1): 17–21. <https://doi.org/10.4169/college.math.j.45.1.017> — Modern treatment of [[Z]](#ref-zbikowski).

<a id="ref-khare"></a>
**[K]** Khare, A. (1997). *Divisibility Tests.* Furman University Electronic Journal of Undergraduate Mathematics
**3**: 1–5. <https://scholarexchange.furman.edu/fuejum/vol3/iss1/1/> — The summing tests with inverse-of-10 weights.

<a id="ref-pal"></a>
**[Pal]** Pal, P. B. (2005). *Divisibility tests with weighted digital sums.* arXiv:math/0507011.
<https://arxiv.org/abs/math/0507011> — Horner-form test for divisors ending in 1, 3, 7, 9, with proof; notes
explicitly that it does *not* yield the remainder.

<a id="ref-grinberg"></a>
**[GL]** Grinberg, A. A., Luryi, S. (2014). *General Divisibility Criteria.* arXiv:1401.5486.
<https://arxiv.org/abs/1401.5486> — The two-parameter universal criterion; contains the 1997 equation as one member.

<a id="ref-mcdowell"></a>
**[M]** McDowell, E. L. (2018). *Divisibility Tests: A History and User's Guide.* MAA Convergence, May 2018.
<https://old.maa.org/press/periodicals/convergence/divisibility-tests-a-history-and-users-guide> — History of the
field, with dedicated sections on the Pascal and Zbikowski tests.

<a id="ref-oshea"></a>
**[O]** O'Shea, E. (2019). *Divisibility Tests Unified: Stacking the Trimmings for Sums.* Mathematics Magazine;
arXiv:1903.04903. <https://arxiv.org/abs/1903.04903> — Proves that the trimming and summing families are the same
tests, in both directions.

<a id="ref-ganzell"></a>
**[G]** Ganzell, S. (2017). *Divisibility Tests, Old and New.* The College Mathematics Journal **48**(1): 36–40.
<https://doi.org/10.4169/college.math.j.48.1.36> — Survey of the landscape.

<a id="ref-vedic"></a>
**[V]** Tirthaji, B. K. (1965). *Vedic Mathematics.* Motilal Banarsidass. — The *osculator* (`Ekādhikena Pūrvena`)
construction, which produces the same multipliers as [[Z]](#ref-zbikowski). See also
<https://instavm.org/wp-content/uploads/2021/05/M33.pdf>.

<a id="ref-kordemsky"></a>
**[Ko]** Kordemsky, B. A. *The Moscow Puzzles: 359 Mathematical Recreations.* Ed. Martin Gardner, Dover, 1992,
problem 320. — The ×5 walk for testing divisibility by 7.

<a id="ref-wikipedia"></a>
**[W]** *Divisibility rule.* Wikipedia. <https://en.wikipedia.org/wiki/Divisibility_rule> — Documents the
inverse-of-10 construction and the `gcd(d, 10) = 1` requirement.

<a id="ref-frwiki"></a>
**[FR]** *Critères de divisibilité.* Wikipédia (French).
<https://fr.wikipedia.org/wiki/Crit%C3%A8res_de_divisibilit%C3%A9> — Statement of Pascal's criterion, including the
remainder-preserving property quoted above.

<a id="ref-sathaye"></a>
**[S]** Sathaye, A. *A universal divisibility test.* University of Kentucky, MA330 course notes.
<https://www.ms.uky.edu/~sohum/ma330/files/note2.pdf> — The trimming test and its coprimality requirement.

<a id="ref-dickson"></a>
**[D]** Dickson, L. E. *History of the Theory of Numbers, Volume I: Divisibility and Primality.* Dover, 2005. —
Historical survey of divisibility criteria.

<a id="ref-udf"></a>
**[U]** Mathur, A. (2025). *Universal Divisibility Framework: A Unified Theory of Divisibility.* viXra:2512.0030.
<https://vixra.org/abs/2512.0030> — A recent attempt at the same unification goal. Listed for completeness; viXra is
not peer reviewed.

# So, What's Next?

My motivation has always been showing that all divisibility rules derive from one single formula and an algorithm. That
turned out to be true, and also to have been known since Pascal — which is a better outcome than it sounds, because the
literature search replaced a private conviction with a proper map of the territory, and corrected two things I had
wrong (see [Prior Art](#prior-art-and-related-work)). What started as a high school notebook full of calculator
experiments is now a rediscovery I can name, with a three-line proof, a division-free implementation and 10,000
verified executions behind it.

The open ends I would still like to chase, and would love to hear about if you get there first:

* Other number bases. Pascal's criterion is base-independent and Grinberg & Luryi tabulate the octal case
  [[GL]](#ref-grinberg); the closed form in [What This Project Adds](#what-this-project-adds) is decimal-only, and I do
  not know what its base-`t` analogue looks like.
* The mental-arithmetic shortcuts hiding in the weight cycles — the cycle length of the weights of `d` is the
  multiplicative order of 10 modulo `d`, and short cycles are what make a rule memorable.
* Whether the closed form in (1) has a principled reason to exist, or is a numerical accident of base ten.

If you find that any part of [What This Project Adds](#what-this-project-adds) is also already in print, please open an
issue. I would rather have a correct map than a flattering one.

# Author

[Lemi Orhan Ergin](http://www.lemiorhanergin.com), co-founder of [Craftgate](https://www.craftgate.io)

# Citation

If you use this rule, its proof, or this implementation in academic work, teaching material, an article or a talk,
please cite it. GitHub's **"Cite this repository"** button generates the citation for you from
[CITATION.cff](CITATION.cff), in BibTeX or APA. Releases are archived on Zenodo, and DOI
[10.5281/zenodo.21572577](https://doi.org/10.5281/zenodo.21572577) always resolves to the most recent archived
version.

```bibtex
@software{ergin_grand_unified_divisibility_rule_2026,
  author  = {Ergin, Lemi Orhan},
  title   = {Grand Unified Divisibility Rule: a traceable implementation of
             Pascal's divisibility criterion, with an independently rediscovered
             closed form for the trimming coefficients},
  year    = {2026},
  version = {2.2},
  doi     = {10.5281/zenodo.21572577},
  url     = {https://github.com/lemiorhan/grand-unified-divisibility-rule},
  note    = {Independent rediscovery; see the Prior Art section for the
             underlying results of Pascal (c. 1654), Zbikowski (1861),
             Khare (1997), Grinberg and Luryi (2014) and O'Shea (2019)}
}
```

**Please cite the mathematics, not this repository, when the mathematics is what you are using.** If you need the
general criterion itself, the citation you want is Pascal [[P]](#ref-pascal) — or McDowell [[M]](#ref-mcdowell) for an
accessible modern account. For the trimming tests, cite Zbikowski [[Z]](#ref-zbikowski) or Grinberg & Luryi
[[GL]](#ref-grinberg); for the equivalence of the two families, O'Shea [[O]](#ref-oshea). Cite this repository for the
implementation, the trace format, the website, or the closed form and failure set described in
[What This Project Adds](#what-this-project-adds).

The mathematics itself is not subject to copyright, so this is a request grounded in academic convention rather than a
licence condition. The attribution requirement in [NOTICE](NOTICE), which applies when you redistribute these files, is
a licence condition.

# License

This project is licensed in two parts, because it is two kinds of work.

| What | Licence | Files |
| ---- | ------- | ----- |
| **Source code** | [Apache License 2.0](LICENSE) | `src/**`, `docs/algorithm.js`, `docs/app.js`, `docs/style.css`, the markup of `docs/index.html`, `build.gradle`, `settings.gradle`, `gradle/**` |
| **Prose and documentation** | [CC BY 4.0](LICENSE-DOCS) | `README.md`, `CHANGELOG.md`, the text shown on the website — including the translated strings in `docs/i18n.js` and the page copy in `docs/index.html` — and `images/**` |

**Why two.** Apache 2.0 is a software licence: it carries an express patent grant, a warranty disclaimer written for
code, and in Section 4(d) a genuine *mandatory* attribution requirement — anyone redistributing this code must carry
[NOTICE](NOTICE) with it. Creative Commons explicitly advise against using their licences for software, so CC BY would
be the wrong tool for `src/`. But CC BY is the right tool for the write-up, and it is the only licence here that
compels credit from someone who quotes or adapts the explanation and the proof. Keeping both means each part of the
project is governed by the licence designed for it.

**In short:**

- Use the algorithm in your own software, commercially or not — Apache 2.0 permits it. Keep the `NOTICE` attribution.
- Quote, translate or build on the write-up — CC BY 4.0 permits it. Credit Lemi Orhan Ergin.
- Publish about the result — please cite it, as above.

[Contributions](CONTRIBUTING.md) are licensed under the same terms; Section 5 of Apache 2.0 handles this for code, so
there is no CLA to sign.

[cc-by]: http://creativecommons.org/licenses/by/4.0/

[cc-by-image]: https://i.creativecommons.org/l/by/4.0/88x31.png

[cc-by-shield]: https://img.shields.io/badge/License-CC%20BY%204.0-lightgrey.svg

[apache-shield]: https://img.shields.io/badge/Code-Apache%202.0-blue.svg

[apache]: https://www.apache.org/licenses/LICENSE-2.0
