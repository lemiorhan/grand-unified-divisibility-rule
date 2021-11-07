# Grand Unified Divisibility Rule: Lemi's Formula

`Divisibility rules` are fast and simple calculations to help you to determine whether a given integer is divisible by another integer without performing the division, usually by examining its digits in your head.

Divisibility rules of one-digit numbers are very easy to learn and remember, and very practical to use. Kids learn them usually at primary school. Knowing these practical tricks increases your speed at solving mathematical problems. Therefore, divisibility rules could be the most recognized mathematical shortcuts people use in calculations all around the world.

With the work I did in 1997 when I was in high school, I can tell you that divisibility rules for any numbers can be derived from one single formula. All divisibility rules are very similar, like the rule for 2 and 42, and even 692 are the same. This document explains the root formula from where each divisibility rule derives.

I have no concerns about practicality, performance, or simplicity. My main concern is showing how divisibility rules share a common algorithm and each one can be derived from the main formula.

# History

When I was at high school in Turkey in 1997, I decided to investigate an algorithm for detecting prime numbers as a maths project. Since prime numbers are the numbers only divided by one and itself, I started to understand the divisibility rules of the first few prime numbers, 5, 7, 11, 13, 17, and 19. I thought that If I understand the logic behind these rules, I can find a common algorithm and detect prime numbers with that algorithm.

I had no computers at that time. With a basic calculator, I did thousands of calculations. At some point, I figured out that the divisibility rule of 2 is similar to the divisibility rule of 12, and 22, and 32, etc. Interestingly divisibility rule of 3 is also similar to the divisibility rule of 13, 23, etc. Then I had a eureka! moment. Clearly, the divisibility rules are very similar among numbers sharing the same ones place digit. For instance, the divisibility rule of 6 is very similar to the divisibility rule of 45732360346. 

After working months spending hundreds of hours with my calculator, I found one single formula covering all possible known divisibility rules. I documented everything with my very-amateur words to a paper, put it in an envelope, and sent it to [TUBITAK](https://en.wikipedia.org/wiki/Scientific_and_Technological_Research_Council_of_Turkey) as a Math Project on my own. Nothing happened of course. I had no expectations since it was very poorly written with no academic background, and I was just a high school kid working alone.

After 24 years, in 2021, I remembered the old good days of divisibility rules. I found my papers and wrote a [simple program](https://github.com/lemiorhan/grand-unified-divisibility-rule) for validating my findings. Guess the result: It works!

If you are here reading my words, thanks for your interest! As of 2021, [I am a software crafter](https://www.linkedin.com/in/lemiorhan/) living in Istanbul. I am the co-founder of [Craftgate, the cloud-based payment gateway](https://www.craftgate.io). I am neither a mathematician, not an academician. This post is not in academic paper standards. However, it contains a true story of my passion and my amateur findings of Grand Unified Divisibility Rule. I have already given the formula my name, `Lemi's Formula`.

# Terminology

* `Ones place digit` is the last digit of a number. For instance, for number 5632, ones place digit is 2.
* `Remaining digits` is the number containing all the digits except the last digit. For instance, for number 5632, the remaining digits are 563.
* `Coefficient` is a numerical or constant quantity placed before and multiplying the variable in an algebraic expression.
* `Dividend` is the number that is to be divided by the divisor. For instance, for division 75 / 20, 75 is the dividend.
* `Divisor` is the factor that divides the dividend. For instance, for division 75 / 20, 20 is the divisor.
* `Quotient` is the result of the division. For instance, for division 75 / 20, 3 is the quotient.
* `Remainder` is the number that is left after division. For instance, for division 75 / 20, 15 is the remainder.

# Practical Divisibility Rules for One-Digit Numbers

Before starting with my findings, let's look at some common well-known divisibility rules that we usually learn in the days of primary school. Even though the divisibility rule of 3 and 9, and the divisibility rule of 4 and 8 resemble each other, they don't share a common pattern. Each one seems to be specialized for being simpler and practical for being easy to remember by the majority.

### Divisible by 2
A number is even or a number whose ones place digit is an even number i.e. 0, 2, 4, 6, and 8.

For instance, 84 is divisible by 2, but 37 is not.

### Divisible by 3
The sum of all digits of the number should be divisible by 3.

For instance, 183 is divisible by 3 because 1+8+3 is 12, and 12 is divisible by 3. However, 121 is not divisible by 3, because 1+2+1 is 5, and 5 is not divisible by 3.

### Divisible by 4
The number formed by the last two digits of the number should be divisible by 4 or should be 00.

For instance, 816 is divisible by 4, because the last two digits 16 is divisible by 4. 3100 is divisible by 4 because the last two digits are 00. However, 450 is not divisible by 4, because the last two digits are not divisible by 4.

### Divisible by 5
Numbers having 0 or 5 as their ones place digit.

For instance, 40 is divisible by 5, because the last digit is 0. 41 is not divisible by 5, because the last digit is neither 0 or 5.

### Divisible by 6
A number that is divisible by both 2 and 3.

For instance, 222 is divisible by 6, because it is both divisible by 2 and 3. 266 is not divisible by 6, because it is divisible by 2 but not 3.

### Divisible by 7
Subtracting twice the last digit of the number from the remaining digits gives a multiple of 7.

For instance, 259 is divisible by 7, because 2 x the last digit 9 = 18 and 25 - 18 is 7 and 7 is divisible by 7. 155 is not divisible by 7, because 2 x 5 is 10 and 15 - 10 is 5 and 5 is not divisible by 7.

### Divisible by 8
The number formed by the last three digits of the number should be divisible by 8 or should be 000.

For instance, 25032 is divisible by 8, because the last three digits 032 is divisible by 8. 85000 is divisible by 8 because the last three digits are 000. 65310 is not divisible by 8, because the last three digits 310 is not divisible by 8.

### Divisible by 9
The sum of all the digits of the number should be divisible by 9.

For instance, 819 is divisible by 9, because 8+1+9 is 18 and 18 is divisible by 9. 219 is not divisible by 9 because 2+1+9 is 12, and 12 is not divisible by 9.

### Divisible by 10
Any number whose ones place digit is 0.

For instance, 560 is divisible by 10, because the last digit is 0. 651 is not divisible by 10, because the last digit is not 0.

As you see the divisibility rules for one-digit numbers are pretty practical. All can be done in your head in seconds. In contrast, the divisibility rules of bigger numbers require extra calculation and are not very practical in reality. 

# Divisibility Rules for Other Numbers

I noticed that divisibility rules of numbers having the same ones place digit are very similar, so similar that we can end up with a formula about it. Let's deep dive into it by giving examples.

### For 11:
Adding 10 times the last digit of the number with the remaining digit gives a multiple of 11.

For instance, 352 is divisible by 11, because 10 x 2 is 20 and 20 + 35 is 55 and 55 is divisible by 11. 232 is not divisible by 11, because 10 x 2 = 20 and 23 + 20 is 43 and 43 is not divisible by 11.

### For 17:
Adding twice the last digit of the number with 3 times the remaining digit gives a multiple of 17.

For instance, 391 is divisible by 17, because 1 x 2 is 2 and 39 x 3 is 117 and 2 + 117 is 119, and 119 is divisible by 17.

### For 21:
Adding 19 times the last digit of the number with the remaining digit gives a multiple of 21.

For instance, 777 is divisible by 21, because 19 x 7 is 133 and 133 + 77 is 210 and 210 is divisible by 21. 377 is not divisible by 21, because 19 x 7 = 133 and 133 + 37 is 170 and 170 is not divisible by 21.

Wait a minute! How do I know 170 is not divisible by 21? If not sure about divisibility, then we should re-run the same rule for the result of the first calculation. For 170, 19 x 0 is 0 and 17 + 0 is 17 and 17 is not divisible by 21. Therefore 377 is not divisible by 21.

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

If the calculated number with the formula in the first iteration is not equal to itself or the divisor, use it as the new dividend and apply the same formula again in a new iteration. When iterations stop, check if the last calculated number is divisible by the divisor or not.

# Lemi's Equation: Grand Unified Divisibility Rule For Any Numbers
For the given:
* `CEIL`: Mathematical ceil operator
* `a1`: Remaining digits of the dividend
* `a2`: Remaining digits of divisor
* `b1`: Ones digit of the dividend
* `b2`: Ones digit of divisor
* `x`: First coefficient as (10 + b2  - (b2 * CEIL(10 / b2)))
* `y`: Second coefficient as (CEIL(10 / b2) - 1)

Lemi's Equation becomes: `(x * a1) + ((y * a2) + 1) * b1`

# Implementation

The implementation of the formula is pretty straightforward. The following code is written in Groovy. You can find the code from [DivisibilityRule.groovy file](https://github.com/lemiorhan/grand-unified-divisibility-rule/blob/main/src/main/groovy/com/lemiorhanergin/divisibilityrules/DivisibilityRule.groovy).

```
01:  def isDivisible(long dividend, long divisor) {
02:      long lastNumberOfDivisor = divisor % 10
03:      def iteration = 1
04:      long calculated = execute(iteration, lastNumberOfDivisor, dividend, divisor)
05:      long previousCalculated = Long.MAX_VALUE
06:      while (continueIterating(++iteration, calculated, previousCalculated, divisor)) {
07:          previousCalculated = calculated
08:          calculated = execute(iteration, lastNumberOfDivisor, calculated, divisor)
09:          if (calculated == previousCalculated && calculated > divisor) {
10:              calculated = previousCalculated - divisor
11:          }
12:      }
13:      return calculateDivisibility(calculated, previousCalculated, divisor)
14:  }
```

Let's review the code line by line.

* **Line 01:** isDivisible method gets two parameters, a dividend and a divisor, nothing more.
* **Line 02:** Ones place digit of the divisor is very important. It is used to identify two coefficients in the formula.
* **Line 03:** Iteration count is initialized.
* **Line 04:** First iteration is executed.
* **Line 05:** Since we need to compare the calculated numbers of two latest consecutive iterations, we need to keep the calculated number of the previous iteration.
* **Line 06:** We need to determine if we have to run for an additional iteration or not. We will cover the conditions to skip iterating later.
* **Line 07:** Calculated number from the previous iteration is set. 
* **Line 08:** New calculation is done in the new iteration.
* **Line 09 and 10:** If the last two consecutive iterations has the same calculated number and the number is still greater than divisor, we subtract divisor from the calculated number. That is a normalization step for iterating.  
* **Line 13:** When iterations stop, we have to determine whether dividend is divided by the divisor or not. We will cover the logic behind later.
 
```
01:  def long execute(long lastNumberOfDivisor, long dividend, long divisor) {
02:      long x = (10 + lastNumberOfDivisor - (lastNumberOfDivisor * Math.ceil(10 / lastNumberOfDivisor))) as int // firstCoefficient
03:      long y = (Math.ceil(10 / lastNumberOfDivisor) - 1) as int // secondCoefficient
04:      long a1 = (long) (dividend / 10) // remainingFirstDigitsOfDividend
05:      long a2 = (long) (divisor / 10) // remainingFirstDigitsOfDivisor
06:      long b1 = dividend % 10 // lastNumberOfDividend
07:      return (x * a1) + ((y * a2) + 1) * b1
08:  }
```

In order to execute Lemi's formula, we need to know the ones place digit of divisor, the dividend and the divisor. All coefficients and variables can easily be calculated from definitions.

```
01:  def boolean continueIterating(int iteration, long calculated, long previousCalculated, long divisor) {
02:     def maxIterationLimitExceeded = iteration >= MAX_ITERATION_COUNT_ALLOWED
03:     def calculatedNumberEqualToDivisor = calculated == divisor
04:     def calculatedNumberIsGreaterThanPreviousIteration = calculated > previousCalculated
05:     def calculatedNumberIsLowerThanDivisor = (calculated - divisor) < 0
06:     return !maxIterationLimitExceeded && !calculatedNumberEqualToDivisor && !calculatedNumberIsGreaterThanPreviousIteration && !calculatedNumberIsLowerThanDivisor
07:  }
```

There exists 4 different conditions to stop iterations. These are:
* **Line 02:** Max iteration limit should exceed. That is 50 by default.
* **Line 03:** Calculated number is equals to the divisor. 
* **Line 04:** Calculated number is greater than the calculated number of the previous iteration.
* **Line 05:** Calculated number is lower than the divisor.
If any of these happens, iterations stop.

```
01:  def boolean calculateDivisibility(long calculated, long divisor) {
02:      return calculated == divisor
03:  }
```

At the end of execution, we need to determine whether dividend is divisible by divisor or not. At the end of the iterations, if the calculated number is equals to the divisor, then we can assume that divisibility result is true.

# Examples

You can run your own tests too. Just open and update [DivisibilityRuleExecutionTest.groovy file](https://github.com/lemiorhan/grand-unified-divisibility-rule/blob/main/src/test/groovy/com/lemiorhanergin/divisibilityrules/DivisibilityRuleExecutionTest.groovy) and run.

I used [Groovy](https://groovy-lang.org/) for coding and [Spock Framework](https://spockframework.org/) for writing unit tests. I used [Gradle](https://gradle.org/) for building the project. You can build and run the tests with the following command from command line.

```
 > ./gradlew build
```

### Is 18 divisible by 3 ?
```
ITERATION 1 =>   (1 x 1) + ((3 x 0) + 1) x 8 = 9
ITERATION 2 =>   (1 x 0) + ((3 x 0) + 1) x 9 = 9
ITERATION 3 =>   (1 x 0) + ((3 x 0) + 1) x 6 = 6
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO DIVISOR"
18 IS DIVISIBLE BY 3. RESULT CALCULATED IN 3 ITERATIONS.
```

### Is 1044 divisible by 12 ?
```
ITERATION 1 =>   (2 x 104) + ((4 x 1) + 1) x 4 = 228
ITERATION 2 =>   (2 x 22) + ((4 x 1) + 1) x 8 = 84
ITERATION 3 =>   (2 x 8) + ((4 x 1) + 1) x 4 = 36
ITERATION 4 =>   (2 x 3) + ((4 x 1) + 1) x 6 = 36
ITERATION 5 =>   (2 x 2) + ((4 x 1) + 1) x 4 = 24
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO DIVISOR"
1044 IS DIVISIBLE BY 12. RESULT CALCULATED IN 5 ITERATIONS.
```

### Is 175789 divisible by 23 ?
```
ITERATION 1 =>   (1 x 17578) + ((3 x 2) + 1) x 9 = 17641
ITERATION 2 =>   (1 x 1764) + ((3 x 2) + 1) x 1 = 1771
ITERATION 3 =>   (1 x 177) + ((3 x 2) + 1) x 1 = 184
ITERATION 4 =>   (1 x 18) + ((3 x 2) + 1) x 4 = 46
ITERATION 5 =>   (1 x 4) + ((3 x 2) + 1) x 6 = 46
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO DIVISOR"
175789 IS DIVISIBLE BY 23. RESULT CALCULATED IN 5 ITERATIONS.
```

### Is 24 divisible by 8 ?
```
ITERATION 1 =>   (2 x 2) + ((1 x 0) + 1) x 4 = 8
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO DIVISOR"
24 IS DIVISIBLE BY 8. RESULT CALCULATED IN 1 ITERATION.
```

### Is 19 divisible by 3 ?
```
ITERATION 1 =>   (1 x 1) + ((3 x 0) + 1) x 9 = 10
ITERATION 2 =>   (1 x 1) + ((3 x 0) + 1) x 0 = 1
ITERATION STOPS DUE TO "CALCULATED NUMBER IS LOWER THAN DIVISOR"
19 IS NOT DIVISIBLE BY 3. RESULT CALCULATED IN 2 ITERATIONS.
```

### Is 41490 divisible by 45 ?
```
ITERATION 1 =>   (5 x 4149) + ((1 x 4) + 1) x 0 = 20745
ITERATION 2 =>   (5 x 2074) + ((1 x 4) + 1) x 5 = 10395
ITERATION 3 =>   (5 x 1039) + ((1 x 4) + 1) x 5 = 5220
ITERATION 4 =>   (5 x 522) + ((1 x 4) + 1) x 0 = 2610
ITERATION 5 =>   (5 x 261) + ((1 x 4) + 1) x 0 = 1305
ITERATION 6 =>   (5 x 130) + ((1 x 4) + 1) x 5 = 675
ITERATION 7 =>   (5 x 67) + ((1 x 4) + 1) x 5 = 360
ITERATION 8 =>   (5 x 36) + ((1 x 4) + 1) x 0 = 180
ITERATION 9 =>   (5 x 18) + ((1 x 4) + 1) x 0 = 90
ITERATION 10 =>   (5 x 9) + ((1 x 4) + 1) x 0 = 45
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO DIVISOR"
41490 IS DIVISIBLE BY 45. RESULT CALCULATED IN 10 ITERATIONS.
```

### Is 74284 divisible by 11 ?
```
ITERATION 1 =>   (1 x 7428) + ((9 x 1) + 1) x 4 = 7468
ITERATION 2 =>   (1 x 746) + ((9 x 1) + 1) x 8 = 826
ITERATION 3 =>   (1 x 82) + ((9 x 1) + 1) x 6 = 142
ITERATION 4 =>   (1 x 14) + ((9 x 1) + 1) x 2 = 34
ITERATION 5 =>   (1 x 3) + ((9 x 1) + 1) x 4 = 43
ITERATION STOPS DUE TO "CALCULATED NUMBER IS GREATER THAN PREVIOUS ITERATION"
74284 IS NOT DIVISIBLE BY 11. RESULT CALCULATED IN 5 ITERATIONS.
```

### Is 25916917952 divisible by 55456 ?
```
ITERATION 1 =>   (4 x 2591691795) + ((1 x 5545) + 1) x 2 = 10366778272
ITERATION 2 =>   (4 x 1036677827) + ((1 x 5545) + 1) x 2 = 4146722400
ITERATION 3 =>   (4 x 414672240) + ((1 x 5545) + 1) x 0 = 1658688960
ITERATION 4 =>   (4 x 165868896) + ((1 x 5545) + 1) x 0 = 663475584
ITERATION 5 =>   (4 x 66347558) + ((1 x 5545) + 1) x 4 = 265412416
ITERATION 6 =>   (4 x 26541241) + ((1 x 5545) + 1) x 6 = 106198240
ITERATION 7 =>   (4 x 10619824) + ((1 x 5545) + 1) x 0 = 42479296
ITERATION 8 =>   (4 x 4247929) + ((1 x 5545) + 1) x 6 = 17024992
ITERATION 9 =>   (4 x 1702499) + ((1 x 5545) + 1) x 2 = 6821088
ITERATION 10 =>   (4 x 682108) + ((1 x 5545) + 1) x 8 = 2772800
ITERATION 11 =>   (4 x 277280) + ((1 x 5545) + 1) x 0 = 1109120
ITERATION 12 =>   (4 x 110912) + ((1 x 5545) + 1) x 0 = 443648
ITERATION 13 =>   (4 x 44364) + ((1 x 5545) + 1) x 8 = 221824
ITERATION 14 =>   (4 x 22182) + ((1 x 5545) + 1) x 4 = 110912
ITERATION 15 =>   (4 x 11091) + ((1 x 5545) + 1) x 2 = 55456
ITERATION STOPS DUE TO "CALCULATED NUMBER IS EQUAL TO DIVISOR"
25916917952 IS DIVISIBLE BY 55456. RESULT CALCULATED IN 15 ITERATIONS.
```

### Is 23021493430 divisible by 49251 ?
```
ITERATION 1 =>   (1 x 2302149343) + ((9 x 4925) + 1) x 0 = 2302149343
ITERATION 2 =>   (1 x 230214934) + ((9 x 4925) + 1) x 3 = 230347912
ITERATION 3 =>   (1 x 23034791) + ((9 x 4925) + 1) x 2 = 23123443
ITERATION 4 =>   (1 x 2312344) + ((9 x 4925) + 1) x 3 = 2445322
ITERATION 5 =>   (1 x 244532) + ((9 x 4925) + 1) x 2 = 333184
ITERATION 6 =>   (1 x 33318) + ((9 x 4925) + 1) x 4 = 210622
ITERATION 7 =>   (1 x 21062) + ((9 x 4925) + 1) x 2 = 109714
ITERATION 8 =>   (1 x 10971) + ((9 x 4925) + 1) x 4 = 188275
ITERATION STOPS DUE TO "CALCULATED NUMBER IS GREATER THAN PREVIOUS ITERATION"
23021493430 IS NOT DIVISIBLE BY 49251. RESULT CALCULATED IN 8 ITERATIONS.
```

# So, What's Next?

Since I am not a mathematician, I am not able to prove my formula in an academic way. So, I will be pleased if either you can make a mathematical proof, or investigate further and extend the logic for further cases that I don't know for today.

# License [![CC BY 4.0][cc-by-shield]][cc-by]

This work is licensed under a
[Creative Commons Attribution 4.0 International License][cc-by].

[![CC BY 4.0][cc-by-image]][cc-by]

[cc-by]: http://creativecommons.org/licenses/by/4.0/
[cc-by-image]: https://i.creativecommons.org/l/by/4.0/88x31.png
[cc-by-shield]: https://img.shields.io/badge/License-CC%20BY%204.0-lightgrey.svg