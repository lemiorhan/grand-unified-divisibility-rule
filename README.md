# Grand Unified Divisibility Rule: The Lemi's Formula

`Divisibility rules` are fast and simple calculations to help you to determine whether a given integer is divisible by another integer without performing the division, usually by examining its digits in your head.

Every kid learn divisibility rules of one-digit numbers, usually at primary school. Knowing these practical tricks increase your speed at solving mathematical problems. Therefore, divisibility rules could be most recognized shortcuts people use in calculations in all around the world.

[Lemi Orhan Ergin](https://www.linkedin.com/in/lemiorhan/) identified a unified divisibility rule for all numbers in 1997 while he was at high school. This document explains the root formula where each divisibility rule derives from.

# History

When I was at high school in Turkey in 1997, I decided to investigate an algorithm for detecting prime numbers as a maths project. Since prime numbers are the numbers only divided by one and itself, I started to understand the divisibility rules of first few prime numbers, 5, 7, 11, 13, 17 and 19. I thought that If I understand the logic behind these rules, I can find a common algorithm and detect prime numbers with that algorithm.

I had no computers at that time. With a basic calculator, I did thousands of calculations. At some point I figured out that divisibility rule of 2 is similar to divisibility rule of 12, and 22, and 32, etc. Interestingly divisibility rule of 3 is also similar to divisibility rule of 13, 23, etc. Then I had an eureca! moment. Clearly the divisibility rules are very similar among numbers sharing same ones place digit. For instance, divisibility rule of 6 is very similar to divisibility rule of 45732360346. Around 1997, 

Afters working months spending hundreds of hours with my calculator, I found one single formula covering all possible known divisibility rules. I documented everything with my very-amateur words to a paper, put it to an envelope and sent it to [TUBITAK](https://en.wikipedia.org/wiki/Scientific_and_Technological_Research_Council_of_Turkey) as a Math Project by my own. Nothing happened of course. I had no expectations, since it was very poorly written with no academic background, and I was just a high school kid working alone. 

After 24 years, in 2021, I remembered old good days of divisibility rules. I found my papers and wrote a [simple program](https://github.com/lemiorhan/grand-unified-divisibility-rules) for validating my findings. Guess the result: It works!

If you are here reading my words, thanks for your interest! As of 2021, [I am a software crafter](https://www.linkedin.com/in/lemiorhan/) living in Istanbul. I am the co-founder of [Craftgate, the cloud based payment gateway](https://www.craftgate.io). I am neither a mathematician, not an academician. This post is not an academic paper too. However, it contains a true story of my passion and my amateur findings about Grand Unified Divisibility Rule. I already give the formula my name, `The Lemi's Formula`.   

# Terminology

* `Ones place digit` is the last digit of a number. For instance, for number 5632, ones place digit is 2.
* `Remaining digits` is the number containing all the digits except the last digit. For instance, for number 5632, the remaining digits is 563.
* `Coefficient` is a numerical or constant quantity placed before and multiplying the variable in an algebraic expression.
* `Dividend` is the number that is to be divided by the divisor. For instance for the division 75 / 20, 75 is the dividend. 
* `Divisor` is the factor that divides the dividend. For instance for the division 75 / 20, 20 is the divisor.
* `Quotient` is the result of the division. For instance for the division 75 / 20, 3 is the quotient.
* `Remainder` is the number that is left after division. For instance for the division 75 / 20, 15 is the remainder.

# Practical Divisibility Rules for One-Digit Numbers

Before starting with my findings, let's look at some common well-known divisibility rules that we usually learn in the days of primary school. 

### Divisible by 2
A number is even or a number whose last digit is an even number i.e. 0, 2, 4, 6, and 8. 

For instance 84 is divisible by 2, but 37 is not.

### Divisible by 3
The sum of all the digits of the number should be divisible by 3. 

For instance 183 is divisible by 3 because 1+8+3 is 12 and 12 is divisible by 3. However 121 is not divisible by 3, because 1+2+1 is 5 and 5 is not divisible by 3.

### Divisible by 4
Number formed by the last two digits of the number should be divisible by 4 or should be 00. 

For instance, 816 is divisible by 4, because last two digits 16 is divisible by 4. 3100 is divisible by 4, because the last two digits is 00. However 450 is not divisible by 4, because the last two digits is not divisible by 4.

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
Number formed by the last three digits of the number should be divisible by 8 or should be 000. 

For instance, 25032 is divisible by 8, because last three digits 032 is divisible by 8. 85000 is divisible by 8, because the last three digits is 000. 65310 is not divisible by 8, because the last three digits 310 is not divisible by 8.

### Divisible by 9
The sum of all the digits of the number should be divisible by 9. 

For instance, 819 is divisible by 9, because 8+1+9 is 18 and 18 is divisible by 9. 219 is not divisible by 9 because 2+1+9 is 12 and 12 is not divisible by 9.

### Divisible by 10
Any number whose ones place digit is 0. 

For instance 560 is divisible by 10, because the last digit is 0. 651 is not divisible by 10, because the last digit is not 0. 

As you see the divisibility rules for one-digit numbers are pretty practical. All can be done in your head in seconds. In contrast, the divisibility rules of bigger numbers requires extra calculation and not practical in reality. However if we do not stop and investigate for bigger numbers, we end up with complex but working algorithms for deciding divisibility.

# Divisibility Rules for All Numbers

During investigation, I noticed that divisibility rules of numbers having same ones place digit are very similar, so similar that we can end up with a formula about it. Let's deep dive into it by giving examples.

## Divisibilty Rules for Bigger Numbers

### For 11:
Adding 10 times the last digit of the number with the remaining digit gives a multiple of 11. 

For instance, 352 is divisible by 11, because 10 x 2 is 20 and 20 + 35 is 55 and 55 is divisible by 11. 232 is not divisible by 11, because 10 x 2 = 20 and 23 + 20 is 43 and 43 is not divisible by 11.

### For 17:
Adding twice the last digit of the number with 3 times the remaining digit gives a multiple of 17. 

For instance, 391 is divisible by 17, because 1 x 2 is 2 and 39 x 3 is 117 and 2 + 117 is 119 and 119 is divisible by 17. 

### For 21:
Adding 19 times the last digit of the number with the remaining digit gives a multiple of 21. 

For instance, 777 is divisible by 21, because 19 x 7 is 133 and 133 + 77 is 210 and 210 is divisible by 21. 377 is not divisible by 21, because 19 x 7 = 133 and 133 + 37 is 170 and 170 is not divisible by 21.

Wait a second, how do I know 170 is not divisible by 21? If not sure about divisibility, then we should re-run the same rule for the result of the first calculation. For 170, 19 x 0 is 0 and 17 + 0 is 17 and 17 is not divisible by 21. Therefore 377 is not divisible by 21.

### For 33:
Adding 10 times the last digit of the number with the remaining digit gives a multiple of 33. 

For instance, 396 is divisible by 33, because 6 x 10 is 60 and 39 + 60 is 99 and 99 is divisible by 33. 

### Generalization:

* Divisible by 11, if `(10 x last digit + remaining digits)` is divisible by 11
* Divisible by 21, if `(19 x last digit + remaining digits)` is divisible by 21
* Divisible by 31, if `(28 x last digit + remaining digits)` is divisible by 31
* Divisible by 41, if `(37 x last digit + remaining digits)` is divisible by 41

# Divisibility Rules Formula for Numbers with Same Ones Digit

For the given:
* `a1`: Remaining digits of dividend
* `a2`: Remaining digits of divisor
* `b1`: Ones digit of dividend
 
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
 
If the calculated number with the formula in first iteration is not equal to itself or the divisor, use it as the new dividend and apply the same formula again in a new iteration. When iterations stop, check if the last calculated number is divisible by the divisor or not.
 
# Lemi's Equation: Grand Unified Divisibility Rule For All Number
For the given:
* `CEIL`: Mathematical ceil operator
* `a1`: Remaining digits of dividend
* `a2`: Remaining digits of divisor
* `b1`: Ones digit of dividend
* `b2`: Ones digit of divisor
* `x`: First coefficient as (10 + b2  - (b2 * CEIL(10 / b2)))
* `y`: Second coefficient as (CEIL(10 / b2) - 1)

Lemi's Equation becomes:
`(x * a1) + ((y * a2) + 1) * b1`









