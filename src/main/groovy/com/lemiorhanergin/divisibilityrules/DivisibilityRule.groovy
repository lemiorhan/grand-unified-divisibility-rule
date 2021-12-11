package com.lemiorhanergin.divisibilityrules

import groovy.util.logging.Slf4j

/**
 * Individual formulas that the unified formula is found from.
 *
 * FOR_1: { params -> (1 * params.a1) + ((9 * params.a2) + 1) * params.b },
 * FOR_2: { params -> (2 * params.a1) + ((4 * params.a2) + 1) * params.b },
 * FOR_3: { params -> (1 * params.a1) + ((3 * params.a2) + 1) * params.b },
 * FOR_4: { params -> (2 * params.a1) + ((2 * params.a2) + 1) * params.b },
 * FOR_5: { params -> (5 * params.a1) + ((1 * params.a2) + 1) * params.b },
 * FOR_6: { params -> (4 * params.a1) + ((1 * params.a2) + 1) * params.b },
 * FOR_7: { params -> (3 * params.a1) + ((1 * params.a2) + 1) * params.b },
 * FOR_8: { params -> (2 * params.a1) + ((1 * params.a2) + 1) * params.b },
 * FOR_9: { params -> (1 * params.a1) + ((1 * params.a2) + 1) * params.b }*
 *
 * Each formula can be used too.
 **/
@Slf4j
class DivisibilityRule {

    public static boolean isLogEnabled = false
    public static final int MAX_ITERATION_COUNT_ALLOWED = 50

    def isDivisible(long dividend, long divisor) {
        def primeFactors = primeFactors(divisor)
        def factors = factors(primeFactors)
        if (isLogEnabled) log.info("FACTORS: {}", factors)
        return factors.every { calculateDivisibility(dividend, it, divisor) }
    }

    def calculateDivisibility(long dividend, long factor, long divisor) {
        // formula is selected with the last digit of factor
        def coefficient = 1
        long lastNumberOfDivisor = factor % 10
        if (isLogEnabled) log.info("DIVISIBILITY CHECK FOR [{}/{}]", dividend, factor)

        // run the formula and calculate a number of iteration #1
        def iteration = 1
        long previousCalculated = dividend

        def calculated = execute(iteration, lastNumberOfDivisor, dividend, factor, coefficient)
        if (calculated == previousCalculated && calculated > factor) calculated = calculated - factor
        if (isLogEnabled) log.info("1. calculated: {}", calculated)

        // let's do the iterations
        while (continueIterating(++iteration, dividend, divisor, factor, calculated, previousCalculated)) {
            previousCalculated = calculated
            // run the formula and calculate a number for each iteration

            calculated = execute(iteration, lastNumberOfDivisor, calculated, factor, coefficient)
            if (calculated == previousCalculated && calculated > factor) calculated = calculated - factor
            if (isLogEnabled) log.info("n. calculated: {}", calculated)
        }

        // identify if dividend can be divided to factor with a zero remainder
        def divisibilityResult = decideDivisibility(dividend, divisor, factor, calculated, previousCalculated, coefficient)
        if (isLogEnabled) log.info("{} IS {} BY {}. RESULT CALCULATED IN {} {}.", dividend, (divisibilityResult ? "DIVISIBLE" : "NOT DIVISIBLE"), factor, iteration - 1, (iteration - 1 == 1 ? "ITERATION" : "ITERATIONS"))
        if (!divisibilityResult && calculated > factor) {
            calculateDivisibility(calculated, factor, divisor)
        }
        return divisibilityResult
    }

    /**
     * This is the unified formula to execute
     *
     * @param lastNumberOfDivisor the last number of divisor
     * @param dividend the dividend number
     * @param divisor the divisor number
     * @return calculated result as long
     */
    private long execute(int iteration, long lastNumberOfDivisor, long dividend, long divisor, long coefficient) {
        def x = (10 + lastNumberOfDivisor - (lastNumberOfDivisor * Math.ceil(10 / lastNumberOfDivisor))) as int
        // firstCoefficient
        def y = (Math.ceil(10 / lastNumberOfDivisor) - 1) as int // secondCoefficient

        long a1 = (long) (dividend / 10) // remainingFirstDigitsOfDividend
        long a2 = (long) (divisor / 10) // remainingFirstDigitsOfDivisor
        long b1 = dividend % 10 // lastNumberOfDividend
        long b2 = 1


        def calculated = (x * a1) + (((y * a2) + b2) * b1) as long
        if (isLogEnabled) log.info("ITERATION {} => ({} x {}) + (({} x {}) + {}) x {} = {}", iteration, x, a1, y, a2, b2, b1, calculated)
        return calculated
    }

    /**
     * Decides if it is ok to continue iterating or not
     *
     * @param iteration , current number of iteration
     * @param calculated , calculated number after each iteration
     * @param previousExecutionResult , calculated number of previous iteration
     * @param divisor
     * @return boolean, true if continue iterating, false if stop iterating and continue
     */
    private boolean continueIterating(int iteration, long dividend, long divisor, long factor, long calculated, long previousCalculated) {
        def maxIterationLimitExceeded = iteration >= MAX_ITERATION_COUNT_ALLOWED
        def calculatedNumberEqualToFactor = calculated == factor
        def calculatedNumberEqualToDivisor = calculated == divisor
        def calculatedNumberEqualToDividend = calculated == dividend
        def calculatedNumberIsGreaterThanPreviousIteration = calculated > previousCalculated
        def calculatedNumberIsEqualToPreviousIteration = calculated == previousCalculated
        //def calculatedNumberIsLowerThanDivisor = (calculated - divisor) < 0
        def calculatedNumberIsLowerThanDivisor = calculated == 0

        if (isLogEnabled) {
            if (calculatedNumberEqualToFactor) log.info("ITERATION STOPS DUE TO \"CALCULATED NUMBER IS EQUAL TO FACTOR\"")
            if (calculatedNumberEqualToDivisor) log.info("ITERATION STOPS DUE TO \"CALCULATED NUMBER IS EQUAL TO DIVISOR\"")
            if (calculatedNumberEqualToDividend) log.info("ITERATION STOPS DUE TO \"CALCULATED NUMBER IS EQUAL TO DIVIDEND\"")
            //if (calculatedNumberIsGreaterThanPreviousIteration) log.info("ITERATION STOPS DUE TO \"CALCULATED NUMBER IS GREATER THAN PREVIOUS ITERATION\"")
            if (calculatedNumberIsLowerThanDivisor) log.info("ITERATION STOPS DUE TO \"CALCULATED NUMBER IS LOWER THAN DIVISOR\"")
            if (calculatedNumberIsEqualToPreviousIteration) log.info("ITERATION STOPS DUE TO \"CALCULATED NUMBER IS EQUAL TO PREVIOUS VERSION\"")
        }

        return !maxIterationLimitExceeded && !calculatedNumberIsEqualToPreviousIteration && !calculatedNumberIsLowerThanDivisor & !calculatedNumberEqualToDividend & !calculatedNumberEqualToDivisor && !calculatedNumberEqualToFactor
    }

    /**
     * Defines if the dividend is divided to divisor with zero reminder
     *
     * @param calculated , calculated number after each iteration
     * @param previousCalculation , calculated number of previous iteration
     * @param divisor
     * @return boolean, true if divisible, false if not divisible
     */
    private boolean decideDivisibility(long dividend, long divisor, long factor, long calculated, long previousCalculated, long coefficient) {
        if (isLogEnabled) log.info("calculated: {}", calculated)
        if (isLogEnabled) log.info("previousCalculated: {}", previousCalculated)
        //return dividend != calculated && (calculated == 0 || calculated == divisor)
        //return (calculated == divisor || calculated == dividend || calculated == factor)
        return (calculated == factor || calculated == dividend)
        //return calculated == divisor
    }

    def gcd(a, b) {
        if (!b) a
        else gcd(b, a % b)
    }

    def lcm(a, b) {
        a * b / gcd(a, b)
    }

    def primeFactors(long number) {
        long n = number;
        List<Integer> factors = new ArrayList<Integer>()
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                factors.add(i)
                n /= i
            }
        }
        return factors
    }

    def factors(List<Long> primeFactors) {
        def factorCountsMap = primeFactors.countBy { it }
        def factorsList = []
        factorCountsMap.forEach { factor, count ->
            (1..count).forEach {
                factorsList << (long) Math.pow(factor, it)
            }
        }
        return factorsList as List<Long>
    }

    def coefficient(List<Long> primeFactors, long divisor) {
        def factorCountsMap = primeFactors.countBy { it }
        if (factorCountsMap.size() == 1) {
            def primeFactor = (int) primeFactors.get(0) // 2
            def factor = factorCountsMap.collect { factor, count -> (long) Math.pow(factor, count) }.get(0) // 8
            def count = factorCountsMap.get((int) primeFactors.get(0)) // 3
            if (isLogEnabled) log.info("divisor: {}, primeFactor: {}, factor: {}, count: {}", divisor, primeFactor, factor, count)
            return divisor != primeFactor && count > 1 ? count - 1 : 1
        }
        return 1
    }
}
