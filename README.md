# Grand Unified Divisibility Rule: Lemi's Formula

`Divisibility rules` are fast and simple calculations to help you to determine whether a given integer is divisible by
another integer without performing the division, usually by examining its digits in your head.

Divisibility rules of one-digit numbers are very easy to learn and remember, and very practical to use. Kids learn them
usually at primary school. Knowing these practical tricks increases your speed at solving mathematical problems.
Therefore, divisibility rules could be the most recognized mathematical shortcuts people use in calculations all around
the world.

With the work I did in 1997 when I was in high school, I can tell you that divisibility rules for any numbers can be
derived from one single formula. All divisibility rules are very similar, like the rule for 2 and 42, and even 692 are
the same. This document explains the root formula from where each divisibility rule derives.

I have no concerns about practicality, performance, or simplicity. My main concern is showing how divisibility rules
share a common algorithm and each one can be derived from the main formula.

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

If you are here reading my words, thanks for your interest! As of
2021, [I am a software crafter](https://www.linkedin.com/in/lemiorhan/) living in Istanbul. I am the co-founder
of [Craftgate, the cloud-based payment gateway](https://www.craftgate.io). I am neither a mathematician, not an
academician. This post is not in academic paper standards. However, it contains a true story of my passion and my
amateur findings of Grand Unified Divisibility Rule. I have already given the formula my name, `Lemi's Formula`.

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
* `Prime Factor` is any of the prime numbers that can be multiplied to give the original number. For instance, prime
  factors of 12 is 3, 2 and 2. Prime factors of 85 is 17 and 5.

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

# Divisibility Rules for Other Numbers

I noticed that divisibility rules of numbers having the same ones place digit are very similar, so similar that we can
end up with a formula about it. Let's deep dive into it by giving examples.

### For 11:

Adding 10 times the last digit of the number with the remaining digit gives a multiple of 11.

For instance, 352 is divisible by 11, because 10 x 2 is 20 and 20 + 35 is 55 and 55 is divisible by 11. 232 is not
divisible by 11, because 10 x 2 = 20 and 23 + 20 is 43 and 43 is not divisible by 11.

### For 17:

Adding twice the last digit of the number with 3 times the remaining digit gives a multiple of 17.

For instance, 391 is divisible by 17, because 1 x 2 is 2 and 39 x 3 is 117 and 2 + 117 is 119, and 119 is divisible by

17.

### For 21:

Adding 19 times the last digit of the number with the remaining digit gives a multiple of 21.

For instance, 777 is divisible by 21, because 19 x 7 is 133 and 133 + 77 is 210 and 210 is divisible by 21. 377 is not
divisible by 21, because 19 x 7 = 133 and 133 + 37 is 170 and 170 is not divisible by 21.

Wait a minute! How do I know 170 is not divisible by 21? If not sure about divisibility, then we should re-run the same
rule for the result of the first calculation. For 170, 19 x 0 is 0 and 17 + 0 is 17 and 17 is not divisible by 21.
Therefore 377 is not divisible by 21.

### For 33:

Adding 10 times the last digit of the number with the remaining digit gives a multiple of 33.

For instance, 396 is divisible by 33, because 6 x 10 is 60 and 39 + 60 is 99, and 99 is divisible by 33.

### For numbers between 11 and 50:

* Divisible by 11, if `(10 x last digit + 1 x remaining digits)` is divisible by 11
* Divisible by 12, if `(5 x last digit + 2 x remaining digits)` is divisible by 12
* Divisible by 13, if `(4 x last digit + 1 x remaining digits)` is divisible by 13
* Divisible by 14, if `(3 x last digit + 2 x remaining digits)` is divisible by 14
* Divisible by 15, if `(2 x last digit + 5 x remaining digits)` is divisible by 15
* Divisible by 16, if `(2 x last digit + 4 x remaining digits)` is divisible by 16
* Divisible by 17, if `(2 x last digit + 3 x remaining digits)` is divisible by 17
* Divisible by 18, if `(2 x last digit + 2 x remaining digits)` is divisible by 18
* Divisible by 19, if `(2 x last digit + 1 x remaining digits)` is divisible by 19
* Divisible by 21, if `(19 x last digit + 1 x remaining digits)` is divisible by 21
* Divisible by 22, if `(9 x last digit + 2 x remaining digits)` is divisible by 22
* Divisible by 23, if `(7 x last digit + 1 x remaining digits)` is divisible by 23
* Divisible by 24, if `(5 x last digit + 2 x remaining digits)` is divisible by 24
* Divisible by 25, if `(3 x last digit + 5 x remaining digits)` is divisible by 25
* Divisible by 26, if `(3 x last digit + 4 x remaining digits)` is divisible by 26
* Divisible by 27, if `(3 x last digit + 3 x remaining digits)` is divisible by 27
* Divisible by 28, if `(3 x last digit + 2 x remaining digits)` is divisible by 28
* Divisible by 29, if `(3 x last digit + 1 x remaining digits)` is divisible by 29
* Divisible by 31, if `(28 x last digit + 1 x remaining digits)` is divisible by 31
* Divisible by 32, if `(13 x last digit + 2 x remaining digits)` is divisible by 32
* Divisible by 33, if `(10 x last digit + 1 x remaining digits)` is divisible by 33
* Divisible by 34, if `(7 x last digit + 2 x remaining digits)` is divisible by 34
* Divisible by 35, if `(4 x last digit + 5 x remaining digits)` is divisible by 35
* Divisible by 36, if `(4 x last digit + 4 x remaining digits)` is divisible by 36
* Divisible by 37, if `(4 x last digit + 3 x remaining digits)` is divisible by 37
* Divisible by 38, if `(4 x last digit + 2 x remaining digits)` is divisible by 38
* Divisible by 39, if `(4 x last digit + 1 x remaining digits)` is divisible by 39
* Divisible by 41, if `(37 x last digit + 1 x remaining digits)` is divisible by 41
* Divisible by 42, if `(17 x last digit + 2 x remaining digits)` is divisible by 42
* Divisible by 43, if `(13 x last digit + 1 x remaining digits)` is divisible by 43
* Divisible by 44, if `(9 x last digit + 2 x remaining digits)` is divisible by 44
* Divisible by 45, if `(5 x last digit + 5 x remaining digits)` is divisible by 45
* Divisible by 46, if `(5 x last digit + 4 x remaining digits)` is divisible by 46
* Divisible by 47, if `(5 x last digit + 3 x remaining digits)` is divisible by 47
* Divisible by 48, if `(5 x last digit + 2 x remaining digits)` is divisible by 48
* Divisible by 49, if `(5 x last digit + 1 x remaining digits)` is divisible by 49

Hmm that's interesting:) It seems a pretty obvious pattern among these rules, isn't it?

# Divisibility Rules Formula for Numbers with Same Ones Place Digit

For the given:

* `a1`: Remaining digits of the dividend
* `a2`: Remaining digits of divisor
* `b1`: Ones place digit of the dividend

The formula:

* When ones digit of divisor is 1: `(1 x a1) + ((9 x a2) + 1) x b1`
* When ones digit of divisor is 2: `(2 x a1) + ((4 x a2) + 1) x b1`
* When ones digit of divisor is 3: `(1 x a1) + ((3 x a2) + 1) x b1`
* When ones digit of divisor is 4: `(2 x a1) + ((2 x a2) + 1) x b1`
* When ones digit of divisor is 5: `(5 x a1) + ((1 x a2) + 1) x b1`
* When ones digit of divisor is 6: `(4 x a1) + ((1 x a2) + 1) x b1`
* When ones digit of divisor is 7: `(3 x a1) + ((1 x a2) + 1) x b1`
* When ones digit of divisor is 8: `(2 x a1) + ((1 x a2) + 1) x b1`
* When ones digit of divisor is 9: `(1 x a1) + ((1 x a2) + 1) x b1`

If the calculated number with the formula in the first iteration is not equal to itself or the divisor, use it as the
new dividend and apply the same formula again in a new iteration. When iterations stop, check if the last calculated
number is divisible by the divisor or not.

# Lemi's Equation: Grand Unified Divisibility Rule For Any Numbers

For the given:

* `CEIL`: Mathematical ceil operator
* `a1`: Remaining digits of the dividend
* `a2`: Remaining digits of divisor
* `b1`: Ones digit of the dividend
* `b2`: Ones digit of divisor
* `x`: First coefficient as `(10 + b2 - (b2 * CEIL(10 / b2)))`
* `y`: Second coefficient as `(CEIL(10 / b2) - 1)`

Lemi's Equation becomes: `(x * a1) + ((y * a2) + 1) * b1`

# The Algorithm

The algorithm of executing the divisibility rules is very straightforward. To make it clearer, let's go over a
sample: `315 / 45`

**1. Find prime factors of the divisor**

It is important to find the prime factors of the divisor. If dividend is divisible by every prime factor of the divisor,
we can conclude that the dividend is divisible by the divisor. For instance, if a number is divisible by both 5 and 7,
we can say that it is divided by 35.

**2. For each prime factor, calculate divisibility.**

If a prime factor repeats (like prime factor 2 repeats 4 times for number 16 and 3 times for number 40), then algorithm
changes a little bit. Assume a prime factor PF repeats 3 times for the divisor. Then in order to say the dividend is
divisible by `PF^3`, we have to validate first the dividend is divided by PF, then the `dividend / PF` is divided by PF
and at last the `(dividend / PF) / PF` is divided by PR. For instance for validating 16 / 2, both 16 should be divisible
by 2, and 8 should be divisible by 2, and 4 should be divisible by 2.

As the sample we follow, prime factors of the divisor 45 is 3, 3 and 5. So if the dividend 315 is divisible by 3 and 5,
and (the dividend / 3) is divisible by 3, then we can assume that 315 is divisible by 45.

# The Implementation

The following code is written in Groovy. You can find the code
from [DivisibilityRule.groovy file](https://github.com/lemiorhan/grand-unified-divisibility-rule/blob/main/src/main/groovy/com/lemiorhanergin/divisibilityrules/DivisibilityRule.groovy)
.

Let's review the code line by line.

```
01:  def isDivisible(long dividend, long divisor) {
02:      def primeFactors = primeFactors(divisor)
03:      def factors = groupPrimeFactorsByRepetition(primeFactors)
04:      factors.every { factor ->
05:          calculateDivisibilityForFactorGroup(dividend, factor.key, factor.value, divisor, 1)
06:      }
07:  }
08:  
09:  def calculateDivisibilityForFactorGroup(long dividend, long factor, int count, long divisor, Integer iteration) {
10:      if (count == 0) return true
11:      return calculateDivisibilityForFactorGroup((long) (dividend / factor), factor, count - 1, divisor, iteration) &&
12:              calculateDivisibilityForFactor(dividend, factor, divisor, iteration)
13:  }
```

* **Line 01:** isDivisible is the only public method to call in order to decide the divisibility.
* **Line 02:** Prime factors are detected. A prime factor can repeat several times as in 16 (prime factor 2 repeats 4
  times)
* **Line 03:** Prime factors are grouped by its repetition count.
* **Line 04-05:** Calculation is triggered with the factor and the repetition count.
* **Line 11-12:** This method uses recursion to do logical-AND for the divisibility results of each prime factor.

```
01:  def calculateDivisibilityForFactor(long dividend, long factor, long divisor, int iteration) {
02:      if (iteration > MAX_ITERATION_COUNT_ALLOWED) return false
03:  
04:      long lastNumberOfDivisor = factor % 10
05:      long previousCalculated = dividend
06:  
07:      def calculation = execute(iteration, lastNumberOfDivisor, dividend, factor)
08:      if (calculation == previousCalculated && calculation > factor) calculation = calculation - factor
09:  
10:      while (continueIterating(++iteration, dividend, divisor, factor, calculation, previousCalculated)) {
11:          previousCalculated = calculation
12:          calculation = execute(iteration, lastNumberOfDivisor, calculation, factor)
13:          if (calculation == previousCalculated && calculation > factor) calculation = calculation - factor
14:      }
15:  
16:      def divisibilityResult = decideDivisibility(factor, calculation)
17:      if (!divisibilityResult && calculation > factor) {
18:          calculation = calculation - factor
19:          calculateDivisibilityForFactor(calculation, factor, divisor, iteration)
20:      }
21:      return divisibilityResult
22:  }
```

* **Line 02:** We have to limit iteration count since in some cases, the algorithm goes into loops.
* **Line 04:** Ones place digit of the divisor is very important. It is used to identify two coefficients in the
  formula.
* **Line 05:** Since we need to compare the calculated numbers of two latest consecutive iterations, we need to keep the
  calculated number of the previous iteration. That is the dividend at the beginning.
* **Line 07:** First iteration is executed.
* **Line 08:** if two consecutive iterations end up with the same number, factor is subtracted from the calculation
  result.
* **Line 10:** We need to determine if we have to run for an additional iteration or not. We will cover the conditions
  to skip iterating later.
* **Line 11:** Calculated number from the previous iteration is set.
* **Line 12:** New calculation is done in the new iteration.
* **Line 13:** If the last two consecutive iterations has the same calculated number and the number is still greater
  than divisor, we subtract divisor from the calculated number. That is a normalization step for iterating.
* **Line 16:** When iterations stop, we have to determine whether dividend is divided by the divisor or not. We will
  cover the logic behind later.
* **Line 17-20:** If divisibility is decided as false but the value of the calculation has not reached the value of the
  factor, when we do the normalization and subtract the factor from calculation again.

```
01:  private long execute(int iteration, long lastNumberOfDivisor, long dividend, long divisor) {
02:      // first coefficient
03:      def x = (10 + lastNumberOfDivisor - (lastNumberOfDivisor * Math.ceil(10 / lastNumberOfDivisor))) as int
04:      // second coefficient
05:      def y = (Math.ceil(10 / lastNumberOfDivisor) - 1) as int
06:      // remaining first digits of dividend
07:      long a1 = (long) (dividend / 10)
08:      // remaining first digits of divisor
09:      long a2 = (long) (divisor / 10)
10:      // last number of dividend
11:      long b1 = dividend % 10
12:      // constant
13:      long b2 = 1
14:
15:      def calculation = (x * a1) + (((y * a2) + b2) * b1) as long
16:      return calculation
17:  }
```

In order to execute Lemi's formula, we need to know the ones place digit of divisor, the dividend and the divisor. All
coefficients and variables can easily be calculated from definitions.

```
01:  private boolean continueIterating(int iteration, long dividend, long divisor, long factor, long calculated, long previousCalculated) {
02:      def maxIterationLimitExceeded = iteration >= MAX_ITERATION_COUNT_ALLOWED
03:      def calculatedNumberEqualToFactor = calculated == factor
04:      def calculatedNumberIsLowerThanFactor = calculated < factor
05:      def calculatedNumberEqualToDivisor = calculated == divisor
06:      def calculatedNumberEqualToDividend = calculated == dividend
07:      def calculatedNumberIsEqualToPreviousIteration = calculated == previousCalculated
08:      def calculatedNumberIsGreaterThanPreviousIteration = calculated > previousCalculated
09:      return !maxIterationLimitExceeded && !calculatedNumberIsEqualToPreviousIteration && !calculatedNumberIsLowerThanFactor & !calculatedNumberEqualToDividend && !calculatedNumberEqualToFactor & !calculatedNumberIsGreaterThanPreviousIteration
10:  }
```

There exists 7 different conditions to stop iterations. These are:

* **Line 02:** Max iteration limit should exceed. That is 50 by default.
* **Line 03:** Calculated number is equals to the factor.
* **Line 04:** Calculated number is lower than the factor.
* **Line 05:** Calculated number is equals to the divisor.
* **Line 06:** Calculated number is equals to the dividend.
* **Line 07:** Calculated number is equal to the calculated number of the previous iteration.
* **Line 08:** Calculated number is greater than the calculated number of the previous iteration.

If any of these happens, iterations stop.

```
01:  def boolean calculateDivisibility(long calculated, long factor) {
02:      return calculated == factor
03:  }
```

At the end of execution, we need to determine whether dividend is divisible by divisor or not. At the end of the
iterations, if the calculated number is equals to the factor, then we can assume that divisibility result for the given
factor is true.

```
01: def primeFactors(long number) {
02:     long n = number;
03:     List<Integer> factors = new ArrayList<Integer>()
04:     for (int i = 2; i <= n; i++) {
05:         while (n % i == 0) {
06:             factors.add(i)
07:             n /= i
08:         }
09:     }
10:     return factors
11: }
```

We used the most basic algorithm for detecting prime factors. That would be improved for better performance for sure.

# Examples

You can run your own tests too. Just open and
update [DivisibilityRuleExecutionTest.groovy file](https://github.com/lemiorhan/grand-unified-divisibility-rule/blob/main/src/test/groovy/com/lemiorhanergin/divisibilityrules/DivisibilityRuleExecutionTest.groovy)
and run.

I used [Groovy](https://groovy-lang.org/) for coding and [Spock Framework](https://spockframework.org/) for writing unit
tests. I used [Gradle](https://gradle.org/) for building the project. You can build and run the tests with the following
command from command line.

```
 > ./gradlew build
```

### Is 18 divisible by 3 ?

18 / 3 is divisible, so it is divisible

```
PRIME FACTORS: 3 FOR ONCE
DIVISIBILITY CHECK FOR [18/3]
ITERATION 1 => (1 x 1) + ((3 x 0) + 1) x 8 = 9
ITERATION 2 => (1 x 0) + ((3 x 0) + 1) x 9 = 9
ITERATION 3 => (1 x 0) + ((3 x 0) + 1) x 6 = 6
LAST CALCULATION: 3
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
18 IS DIVISIBLE BY 3. RESULT CALCULATED IN 3 ITERATIONS.
```

### Is 1044 divisible by 12 ?

1044 / 2 is divisible, 522 / 2 is divisible, 1044 / 3 is divisible, so it is divisible

```
PRIME FACTORS: 2 FOR TWICE, 3 FOR ONCE

DIVISIBILITY CHECK FOR [1044/2]
ITERATION 1 => (2 x 104) + ((4 x 0) + 1) x 4 = 212
ITERATION 2 => (2 x 21) + ((4 x 0) + 1) x 2 = 44
ITERATION 3 => (2 x 4) + ((4 x 0) + 1) x 4 = 12
ITERATION 4 => (2 x 1) + ((4 x 0) + 1) x 2 = 4
ITERATION 5 => (2 x 0) + ((4 x 0) + 1) x 4 = 4
LAST CALCULATION: 2
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
1044 IS DIVISIBLE BY 2. RESULT CALCULATED IN 5 ITERATIONS.

DIVISIBILITY CHECK FOR [522/2]
ITERATION 1 => (2 x 52) + ((4 x 0) + 1) x 2 = 106
ITERATION 2 => (2 x 10) + ((4 x 0) + 1) x 6 = 26
ITERATION 3 => (2 x 2) + ((4 x 0) + 1) x 6 = 10
ITERATION 4 => (2 x 1) + ((4 x 0) + 1) x 0 = 2
LAST CALCULATION: 2
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
522 IS DIVISIBLE BY 2. RESULT CALCULATED IN 4 ITERATIONS.

DIVISIBILITY CHECK FOR [1044/3]
ITERATION 1 => (1 x 104) + ((3 x 0) + 1) x 4 = 108
ITERATION 2 => (1 x 10) + ((3 x 0) + 1) x 8 = 18
ITERATION 3 => (1 x 1) + ((3 x 0) + 1) x 8 = 9
ITERATION 4 => (1 x 0) + ((3 x 0) + 1) x 9 = 9
ITERATION 5 => (1 x 0) + ((3 x 0) + 1) x 6 = 6
LAST CALCULATION 3
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
1044 IS DIVISIBLE BY 3. RESULT CALCULATED IN 5 ITERATIONS.
```

### Is 175789 divisible by 23 ?

175789 / 23 is divisible, so it is divisible

```
PRIME FACTORS: 23 FOR ONCE

DIVISIBILITY CHECK FOR [175789/23]
ITERATION 1 => (1 x 17578) + ((3 x 2) + 1) x 9 = 17641
ITERATION 2 => (1 x 1764) + ((3 x 2) + 1) x 1 = 1771
ITERATION 3 => (1 x 177) + ((3 x 2) + 1) x 1 = 184
ITERATION 4 => (1 x 18) + ((3 x 2) + 1) x 4 = 46
ITERATION 5 => (1 x 4) + ((3 x 2) + 1) x 6 = 46
LAST CALCULATION: 23
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
175789 IS DIVISIBLE BY 23. RESULT CALCULATED IN 5 ITERATIONS.
```

### Is 24 divisible by 8 ?

24 / 2 is divisible, 12 / 2 is divisible, 6 / 2 is divisible, so it is divisible

```
PRIME FACTORS: 2 FOR 3 TIMES

DIVISIBILITY CHECK FOR [24/2]
ITERATION 1 => (2 x 2) + ((4 x 0) + 1) x 4 = 8
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO DIVISOR"
ITERATION 2 => (2 x 0) + ((4 x 0) + 1) x 8 = 8
ITERATION 3 => (2 x 0) + ((4 x 0) + 1) x 6 = 6
ITERATION 4 => (2 x 0) + ((4 x 0) + 1) x 4 = 4
LAST CALCULATION: 2
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
24 IS DIVISIBLE BY 2. RESULT CALCULATED IN 4 ITERATIONS.

DIVISIBILITY CHECK FOR [12/2]
ITERATION 1 => (2 x 1) + ((4 x 0) + 1) x 2 = 4
ITERATION 2 => (2 x 0) + ((4 x 0) + 1) x 4 = 4
LAST CALCULATION: 2
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
12 IS DIVISIBLE BY 2. RESULT CALCULATED IN 2 ITERATIONS.

DIVISIBILITY CHECK FOR [6/2]
ITERATION 1 => (2 x 0) + ((4 x 0) + 1) x 6 = 6
ITERATION 2 => (2 x 0) + ((4 x 0) + 1) x 4 = 4
LAST CALCULATION: 2
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
6 IS DIVISIBLE BY 2. RESULT CALCULATED IN 2 ITERATIONS.

```

### Is 19 divisible by 3 ?

19 / 3 is NOT divisible so it is NOT divisible

```
PRIME FACTORS: 3 FOR ONCE

DIVISIBILITY CHECK FOR [19/3]
ITERATION 1 => (1 x 1) + ((3 x 0) + 1) x 9 = 10
ITERATION 2 => (1 x 1) + ((3 x 0) + 1) x 0 = 1
LAST CALCULATION: 1
ITERATION STOPS DUE TO "CALCULATED NUMBER IS LOWER THAN FACTOR"
19 IS NOT DIVISIBLE BY 3. RESULT CALCULATED IN 2 ITERATIONS.
```

### Is 41490 divisible by 45 ?

41490 / 3 is divisible, 13830 / 3 is divisible, 41490 / 5 is divisible, so it is divisible

```
PRIME FACTORS: 3 FOR TWICE, 5 FOR ONCE

DIVISIBILITY CHECK FOR [41490/3]
ITERATION 1 => (1 x 4149) + ((3 x 0) + 1) x 0 = 4149
ITERATION 2 => (1 x 414) + ((3 x 0) + 1) x 9 = 423
ITERATION 3 => (1 x 42) + ((3 x 0) + 1) x 3 = 45
ITERATION 4 => (1 x 4) + ((3 x 0) + 1) x 5 = 9
ITERATION 5 => (1 x 0) + ((3 x 0) + 1) x 9 = 9
ITERATION 6 => (1 x 0) + ((3 x 0) + 1) x 6 = 6
LAST CALCULATION:: 3
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
41490 IS DIVISIBLE BY 3. RESULT CALCULATED IN 6 ITERATIONS.

DIVISIBILITY CHECK FOR [13830/3]
ITERATION 1 => (1 x 1383) + ((3 x 0) + 1) x 0 = 1383
ITERATION 2 => (1 x 138) + ((3 x 0) + 1) x 3 = 141
ITERATION 3 => (1 x 14) + ((3 x 0) + 1) x 1 = 15
ITERATION 4 => (1 x 1) + ((3 x 0) + 1) x 5 = 6
ITERATION 5 => (1 x 0) + ((3 x 0) + 1) x 6 = 6
LAST CALCULATION: 3
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
13830 IS DIVISIBLE BY 3. RESULT CALCULATED IN 5 ITERATIONS.

DIVISIBILITY CHECK FOR [41490/5]
ITERATION 1 => (5 x 4149) + ((1 x 0) + 1) x 0 = 20745
ITERATION 2 => (5 x 2074) + ((1 x 0) + 1) x 5 = 10375
ITERATION 3 => (5 x 1037) + ((1 x 0) + 1) x 5 = 5190
ITERATION 4 => (5 x 519) + ((1 x 0) + 1) x 0 = 2595
ITERATION 5 => (5 x 259) + ((1 x 0) + 1) x 5 = 1300
ITERATION 6 => (5 x 130) + ((1 x 0) + 1) x 0 = 650
ITERATION 7 => (5 x 65) + ((1 x 0) + 1) x 0 = 325
ITERATION 8 => (5 x 32) + ((1 x 0) + 1) x 5 = 165
ITERATION 9 => (5 x 16) + ((1 x 0) + 1) x 5 = 85
ITERATION 10 => (5 x 8) + ((1 x 0) + 1) x 5 = 45
ITERATION 11 => (5 x 4) + ((1 x 0) + 1) x 5 = 25
ITERATION 12 => (5 x 2) + ((1 x 0) + 1) x 5 = 15
ITERATION 13 => (5 x 1) + ((1 x 0) + 1) x 5 = 10
ITERATION 14 => (5 x 1) + ((1 x 0) + 1) x 0 = 5
LAST CALCULATION: 5
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
41490 IS DIVISIBLE BY 5. RESULT CALCULATED IN 14 ITERATIONS.
```

### Is 74284 divisible by 11 ?

74284 / 11 is NOT divisible so it is NOT divisible

```
PRIME FACTORS: 11 FOR ONCE

DIVISIBILITY CHECK FOR [74284/11]
ITERATION 1 => (1 x 7428) + ((9 x 1) + 1) x 4 = 7468
ITERATION 2 => (1 x 746) + ((9 x 1) + 1) x 8 = 826
ITERATION 3 => (1 x 82) + ((9 x 1) + 1) x 6 = 142
ITERATION 4 => (1 x 14) + ((9 x 1) + 1) x 2 = 34
ITERATION 5 => (1 x 3) + ((9 x 1) + 1) x 4 = 43
ITERATION 6 => (1 x 3) + ((9 x 1) + 1) x 2 = 23
ITERATION 7 => (1 x 2) + ((9 x 1) + 1) x 3 = 32
ITERATION 8 => (1 x 2) + ((9 x 1) + 1) x 1 = 12
ITERATION 9 => (1 x 1) + ((9 x 1) + 1) x 2 = 21
ITERATION 10 => (1 x 1) + ((9 x 1) + 1) x 0 = 1
LAST CALCULATION: 1
ITERATION STOPS DUE TO "CALCULATED NUMBER IS LOWER THAN FACTOR"
10 IS NOT DIVISIBLE BY 11. RESULT CALCULATED IN 10 ITERATIONS.
```

### Is 25916917952 divisible by 55456 ?

25916917952 / 2 is divisible, 12958458976 / 2 is divisible, 6479229488 / 2 is divisible, 3239614744 / 2 is divisible,
1619807372 / 2 is divisible, 25916917952 / 1733 is divisible, so it is divisible

```
PRIME FACTORS: 2 FOR 5 TIMES, 1733 FOR ONCE

DIVISIBILITY CHECK FOR [25916917952/2]
ITERATION 1 => (2 x 2591691795) + ((4 x 0) + 1) x 2 = 5183383592
ITERATION 2 => (2 x 518338359) + ((4 x 0) + 1) x 2 = 1036676720
ITERATION 3 => (2 x 103667672) + ((4 x 0) + 1) x 0 = 207335344
ITERATION 4 => (2 x 20733534) + ((4 x 0) + 1) x 4 = 41467072
ITERATION 5 => (2 x 4146707) + ((4 x 0) + 1) x 2 = 8293416
ITERATION 6 => (2 x 829341) + ((4 x 0) + 1) x 6 = 1658688
ITERATION 7 => (2 x 165868) + ((4 x 0) + 1) x 8 = 331744
ITERATION 8 => (2 x 33174) + ((4 x 0) + 1) x 4 = 66352
ITERATION 9 => (2 x 6635) + ((4 x 0) + 1) x 2 = 13272
ITERATION 10 => (2 x 1327) + ((4 x 0) + 1) x 2 = 2656
ITERATION 11 => (2 x 265) + ((4 x 0) + 1) x 6 = 536
ITERATION 12 => (2 x 53) + ((4 x 0) + 1) x 6 = 112
ITERATION 13 => (2 x 11) + ((4 x 0) + 1) x 2 = 24
ITERATION 14 => (2 x 2) + ((4 x 0) + 1) x 4 = 8
ITERATION 15 => (2 x 0) + ((4 x 0) + 1) x 8 = 8
ITERATION 16 => (2 x 0) + ((4 x 0) + 1) x 6 = 6
ITERATION 17 => (2 x 0) + ((4 x 0) + 1) x 4 = 4
LAST CALCULATION: 2
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
25916917952 IS DIVISIBLE BY 2. RESULT CALCULATED IN 17 ITERATIONS.

DIVISIBILITY CHECK FOR [12958458976/2]
ITERATION 1 => (2 x 1295845897) + ((4 x 0) + 1) x 6 = 2591691800
ITERATION 2 => (2 x 259169180) + ((4 x 0) + 1) x 0 = 518338360
ITERATION 3 => (2 x 51833836) + ((4 x 0) + 1) x 0 = 103667672
ITERATION 4 => (2 x 10366767) + ((4 x 0) + 1) x 2 = 20733536
ITERATION 5 => (2 x 2073353) + ((4 x 0) + 1) x 6 = 4146712
ITERATION 6 => (2 x 414671) + ((4 x 0) + 1) x 2 = 829344
ITERATION 7 => (2 x 82934) + ((4 x 0) + 1) x 4 = 165872
ITERATION 8 => (2 x 16587) + ((4 x 0) + 1) x 2 = 33176
ITERATION 9 => (2 x 3317) + ((4 x 0) + 1) x 6 = 6640
ITERATION 10 => (2 x 664) + ((4 x 0) + 1) x 0 = 1328
ITERATION 11 => (2 x 132) + ((4 x 0) + 1) x 8 = 272
ITERATION 12 => (2 x 27) + ((4 x 0) + 1) x 2 = 56
ITERATION 13 => (2 x 5) + ((4 x 0) + 1) x 6 = 16
ITERATION 14 => (2 x 1) + ((4 x 0) + 1) x 6 = 8
ITERATION 15 => (2 x 0) + ((4 x 0) + 1) x 8 = 8
ITERATION 16 => (2 x 0) + ((4 x 0) + 1) x 6 = 6
ITERATION 17 => (2 x 0) + ((4 x 0) + 1) x 4 = 4
LAST CALCULATION: 2
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
12958458976 IS DIVISIBLE BY 2. RESULT CALCULATED IN 17 ITERATIONS.

DIVISIBILITY CHECK FOR [6479229488/2]
ITERATION 1 => (2 x 647922948) + ((4 x 0) + 1) x 8 = 1295845904
ITERATION 2 => (2 x 129584590) + ((4 x 0) + 1) x 4 = 259169184
ITERATION 3 => (2 x 25916918) + ((4 x 0) + 1) x 4 = 51833840
ITERATION 4 => (2 x 5183384) + ((4 x 0) + 1) x 0 = 10366768
ITERATION 5 => (2 x 1036676) + ((4 x 0) + 1) x 8 = 2073360
ITERATION 6 => (2 x 207336) + ((4 x 0) + 1) x 0 = 414672
ITERATION 7 => (2 x 41467) + ((4 x 0) + 1) x 2 = 82936
ITERATION 8 => (2 x 8293) + ((4 x 0) + 1) x 6 = 16592
ITERATION 9 => (2 x 1659) + ((4 x 0) + 1) x 2 = 3320
ITERATION 10 => (2 x 332) + ((4 x 0) + 1) x 0 = 664
ITERATION 11 => (2 x 66) + ((4 x 0) + 1) x 4 = 136
ITERATION 12 => (2 x 13) + ((4 x 0) + 1) x 6 = 32
ITERATION 13 => (2 x 3) + ((4 x 0) + 1) x 2 = 8
ITERATION 14 => (2 x 0) + ((4 x 0) + 1) x 8 = 8
ITERATION 15 => (2 x 0) + ((4 x 0) + 1) x 6 = 6
ITERATION 16 => (2 x 0) + ((4 x 0) + 1) x 4 = 4
LAST CALCULATION: 2
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
6479229488 IS DIVISIBLE BY 2. RESULT CALCULATED IN 16 ITERATIONS.

DIVISIBILITY CHECK FOR [3239614744/2]
ITERATION 1 => (2 x 323961474) + ((4 x 0) + 1) x 4 = 647922952
ITERATION 2 => (2 x 64792295) + ((4 x 0) + 1) x 2 = 129584592
ITERATION 3 => (2 x 12958459) + ((4 x 0) + 1) x 2 = 25916920
ITERATION 4 => (2 x 2591692) + ((4 x 0) + 1) x 0 = 5183384
ITERATION 5 => (2 x 518338) + ((4 x 0) + 1) x 4 = 1036680
ITERATION 6 => (2 x 103668) + ((4 x 0) + 1) x 0 = 207336
ITERATION 7 => (2 x 20733) + ((4 x 0) + 1) x 6 = 41472
ITERATION 8 => (2 x 4147) + ((4 x 0) + 1) x 2 = 8296
ITERATION 9 => (2 x 829) + ((4 x 0) + 1) x 6 = 1664
ITERATION 10 => (2 x 166) + ((4 x 0) + 1) x 4 = 336
ITERATION 11 => (2 x 33) + ((4 x 0) + 1) x 6 = 72
ITERATION 12 => (2 x 7) + ((4 x 0) + 1) x 2 = 16
ITERATION 13 => (2 x 1) + ((4 x 0) + 1) x 6 = 8
ITERATION 14 => (2 x 0) + ((4 x 0) + 1) x 8 = 8
ITERATION 15 => (2 x 0) + ((4 x 0) + 1) x 6 = 6
ITERATION 16 => (2 x 0) + ((4 x 0) + 1) x 4 = 4
LAST CALCULATION: 2
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
3239614744 IS DIVISIBLE BY 2. RESULT CALCULATED IN 16 ITERATIONS.

DIVISIBILITY CHECK FOR [1619807372/2]
ITERATION 1 => (2 x 161980737) + ((4 x 0) + 1) x 2 = 323961476
ITERATION 2 => (2 x 32396147) + ((4 x 0) + 1) x 6 = 64792300
ITERATION 3 => (2 x 6479230) + ((4 x 0) + 1) x 0 = 12958460
ITERATION 4 => (2 x 1295846) + ((4 x 0) + 1) x 0 = 2591692
ITERATION 5 => (2 x 259169) + ((4 x 0) + 1) x 2 = 518340
ITERATION 6 => (2 x 51834) + ((4 x 0) + 1) x 0 = 103668
ITERATION 7 => (2 x 10366) + ((4 x 0) + 1) x 8 = 20740
ITERATION 8 => (2 x 2074) + ((4 x 0) + 1) x 0 = 4148
ITERATION 9 => (2 x 414) + ((4 x 0) + 1) x 8 = 836
ITERATION 10 => (2 x 83) + ((4 x 0) + 1) x 6 = 172
ITERATION 11 => (2 x 17) + ((4 x 0) + 1) x 2 = 36
ITERATION 12 => (2 x 3) + ((4 x 0) + 1) x 6 = 12
ITERATION 13 => (2 x 1) + ((4 x 0) + 1) x 2 = 4
ITERATION 14 => (2 x 0) + ((4 x 0) + 1) x 4 = 4
LAST CALCULATION: 2
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
1619807372 IS DIVISIBLE BY 2. RESULT CALCULATED IN 14 ITERATIONS.

DIVISIBILITY CHECK FOR [25916917952/1733]
ITERATION 1 => (1 x 2591691795) + ((3 x 173) + 1) x 2 = 2591692835
ITERATION 2 => (1 x 259169283) + ((3 x 173) + 1) x 5 = 259171883
ITERATION 3 => (1 x 25917188) + ((3 x 173) + 1) x 3 = 25918748
ITERATION 4 => (1 x 2591874) + ((3 x 173) + 1) x 8 = 2596034
ITERATION 5 => (1 x 259603) + ((3 x 173) + 1) x 4 = 261683
ITERATION 6 => (1 x 26168) + ((3 x 173) + 1) x 3 = 27728
ITERATION 7 => (1 x 2772) + ((3 x 173) + 1) x 8 = 6932
ITERATION 8 => (1 x 693) + ((3 x 173) + 1) x 2 = 1733
LAST CALCULATION: 1733
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO FACTOR"
25916917952 IS DIVISIBLE BY 1733. RESULT CALCULATED IN 8 ITERATIONS.
```

### Is 23021493430 divisible by 49251 ?

23021493430 / 3 is NOT divisible, so it is NOT divisible

```
PRIME FACTORS: 3 FOR ONCE, 16417 FOR ONCE

DIVISIBILITY CHECK FOR [23021493430/3]
ITERATION 1 => (1 x 2302149343) + ((3 x 0) + 1) x 0 = 2302149343
ITERATION 2 => (1 x 230214934) + ((3 x 0) + 1) x 3 = 230214937
ITERATION 3 => (1 x 23021493) + ((3 x 0) + 1) x 7 = 23021500
ITERATION 4 => (1 x 2302150) + ((3 x 0) + 1) x 0 = 2302150
ITERATION 5 => (1 x 230215) + ((3 x 0) + 1) x 0 = 230215
ITERATION 6 => (1 x 23021) + ((3 x 0) + 1) x 5 = 23026
ITERATION 7 => (1 x 2302) + ((3 x 0) + 1) x 6 = 2308
ITERATION 8 => (1 x 230) + ((3 x 0) + 1) x 8 = 238
ITERATION 9 => (1 x 23) + ((3 x 0) + 1) x 8 = 31
ITERATION 10 => (1 x 3) + ((3 x 0) + 1) x 1 = 4
ITERATION 11 => (1 x 0) + ((3 x 0) + 1) x 4 = 4
LAST CALCULATION: 1
ITERATION STOPS DUE TO "CALCULATED NUMBER IS LOWER THAN FACTOR"
23021493430 IS NOT DIVISIBLE BY 3. RESULT CALCULATED IN 11 ITERATIONS.
```

# So, What's Next?

As I mentioned at the beginning, the divisibility rules of divisors greater than 12 is not practical. My main motivation
has always been showing that all divisibility rules are derived from one single formula and an algorithm. Practicality
has never been my main concern.

Since I am not a mathematician, I am not able to prove my formula in an academic way. So, I will be pleased if either
you can make a mathematical proof, or investigate further and extend the logic for further cases that I don't know for
today.

# Author

[Lemi Orhan Ergin](http://www.lemiorhanergin.com), co-founder of [Craftgate](https://www.craftgate.io)

# License [![CC BY 4.0][cc-by-shield]][cc-by]

This work is licensed under a
[Creative Commons Attribution 4.0 International License][cc-by].

[![CC BY 4.0][cc-by-image]][cc-by]

[cc-by]: http://creativecommons.org/licenses/by/4.0/

[cc-by-image]: https://i.creativecommons.org/l/by/4.0/88x31.png

[cc-by-shield]: https://img.shields.io/badge/License-CC%20BY%204.0-lightgrey.svg
