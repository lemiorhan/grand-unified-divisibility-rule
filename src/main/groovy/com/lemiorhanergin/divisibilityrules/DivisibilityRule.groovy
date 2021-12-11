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

        factors.every { factor ->
            calculateDivisibilityForFactorGroup(dividend, factor.key, factor.value, divisor, 1)
        }
    }

    def calculateDivisibilityForFactorGroup(long dividend, long factor, int count, long divisor, Integer iteration) {
        if (count == 0) return true
        return calculateDivisibilityForFactorGroup((long) (dividend / factor), factor, count - 1, divisor, iteration) &&
                calculateDivisibilityForFactor(dividend, factor, divisor, iteration)
    }

    def calculateDivisibilityForFactor(long dividend, long factor, long divisor, int iteration) {
        if (iteration > MAX_ITERATION_COUNT_ALLOWED) return false

        long lastNumberOfDivisor = factor % 10
        if (isLogEnabled) log.info("DIVISIBILITY CHECK FOR [{}/{}]", dividend, factor)

        long previousCalculated = dividend

        def calculation = execute(iteration, lastNumberOfDivisor, dividend, factor)
        if (calculation == previousCalculated && calculation > factor) calculation = calculation - factor
        if (isLogEnabled) log.info("1. ITERATION: {}", calculation)

        while (continueIterating(++iteration, dividend, divisor, factor, calculation, previousCalculated)) {
            previousCalculated = calculation

            calculation = execute(iteration, lastNumberOfDivisor, calculation, factor)
            if (calculation == previousCalculated && calculation > factor) calculation = calculation - factor
            if (isLogEnabled) log.info("{}. ITERATION: {}", iteration, calculation)
        }

        def divisibilityResult = decideDivisibility(factor, calculation)
        if (isLogEnabled) log.info("{} IS {} BY {}. RESULT CALCULATED IN {} {}.", dividend, (divisibilityResult ? "DIVISIBLE" : "NOT DIVISIBLE"), factor, iteration - 1, (iteration - 1 == 1 ? "ITERATION" : "ITERATIONS"))
        if (!divisibilityResult && calculation > factor) {
            calculation = calculation - factor
            calculateDivisibilityForFactor(calculation, factor, divisor, iteration)
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
    private long execute(int iteration, long lastNumberOfDivisor, long dividend, long divisor) {
        // first coefficient
        def x = (10 + lastNumberOfDivisor - (lastNumberOfDivisor * Math.ceil(10 / lastNumberOfDivisor))) as int
        // second coefficient
        def y = (Math.ceil(10 / lastNumberOfDivisor) - 1) as int
        // remaining first digits of dividend
        long a1 = (long) (dividend / 10)
        // remaining first digits of divisor
        long a2 = (long) (divisor / 10)
        // last number of dividend
        long b1 = dividend % 10
        // constant
        long b2 = 1


        def calculation = (x * a1) + (((y * a2) + b2) * b1) as long
        if (isLogEnabled) log.info("ITERATION {} => ({} x {}) + (({} x {}) + {}) x {} = {}", iteration, x, a1, y, a2, b2, b1, calculation)
        return calculation
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
        def calculatedNumberIsLowerThanFactor = calculated < factor
        def calculatedNumberEqualToDivisor = calculated == divisor
        def calculatedNumberEqualToDividend = calculated == dividend
        def calculatedNumberIsEqualToPreviousIteration = calculated == previousCalculated

        if (isLogEnabled) {
            if (calculatedNumberEqualToFactor) log.info("ITERATION STOPS DUE TO \"CALCULATED NUMBER IS EQUAL TO FACTOR\"")
            if (calculatedNumberIsLowerThanFactor) log.info("ITERATION STOPS DUE TO \"CALCULATED NUMBER IS LOWER THAN FACOR\"")
            if (calculatedNumberEqualToDivisor) log.info("ITERATION STOPS DUE TO \"CALCULATED NUMBER IS EQUAL TO DIVISOR\"")
            if (calculatedNumberEqualToDividend) log.info("ITERATION STOPS DUE TO \"CALCULATED NUMBER IS EQUAL TO DIVIDEND\"")
            if (calculatedNumberIsEqualToPreviousIteration) log.info("ITERATION STOPS DUE TO \"CALCULATED NUMBER IS EQUAL TO PREVIOUS VERSION\"")
        }

        return !maxIterationLimitExceeded && !calculatedNumberIsEqualToPreviousIteration && !calculatedNumberIsLowerThanFactor & !calculatedNumberEqualToDividend && !calculatedNumberEqualToFactor
    }

    /**
     * Defines if the dividend is divided to divisor with zero reminder
     *
     * @param factor
     * @param calculation , calculation number after each iteration
     * @return boolean, true if divisible, false if not divisible
     */
    private boolean decideDivisibility(long factor, long calculation) {
        return (calculation == factor)
    }

    /**
     * Detects prime factors
     *
     * @param number , the given number
     * @return list of prime factors (a prime factor can be available multiple times)
     */
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

    /**
     * Groups prime factors and creates a [primeFactor, number of repetition] map
     *
     * @param primeFactors , list of prime factors
     * @return Map of
     */
    def factors(List<Long> primeFactors) {
        return primeFactors.countBy { it }
    }
}
