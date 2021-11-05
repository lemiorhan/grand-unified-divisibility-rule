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

    public static final int MAX_ITERATION_COUNT_ALLOWED = 50

    def isDivisible(long dividend, long divisor) {
        // formula is selected with the last digit of divisor
        long lastNumberOfDivisor = divisor % 10

        log.info("============[ {}/{} ]==============", dividend, divisor)
        log.info("FORMULA SELECTED FOR {}", lastNumberOfDivisor)

        // run the formula and calculate a number of iteration #1
        long calculated = execute(lastNumberOfDivisor, dividend, divisor)
        log.info("{}. iteration -> {}", 1, calculated)

        long previousCalculated = Long.MAX_VALUE
        def iteration = 2

        // let's do the iterations
        while (continueIterating(iteration, calculated, previousCalculated, divisor)) {
            previousCalculated = calculated

            // run the formula and calculate a number for each iteration
            calculated = execute(lastNumberOfDivisor, calculated, divisor)

            log.info("{}. iteration -> {}", iteration, calculated)
            iteration++

            // for repeats, try reducing the calculated number for the next iteration
            if (calculated == previousCalculated && calculated > divisor) {
                calculated = previousCalculated - divisor
            }
        }

        // identify if dividend can be divided to divisor with a zero remainder
        def divisibilityResult = calculateDivisibility(calculated, previousCalculated, divisor)
        log.info("RESULT: " + (divisibilityResult ? "DIVISIBLE" : "NOT DIVISIBLE") + " CALCULATED IN ${iteration - 1} STEPS")
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
    private long execute(long lastNumberOfDivisor, long dividend, long divisor) {
        def x = (10 + lastNumberOfDivisor - (lastNumberOfDivisor * Math.ceil(10 / lastNumberOfDivisor))) as int // firstCoefficient
        def y = (Math.ceil(10 / lastNumberOfDivisor) - 1) as int // secondCoefficient

        long a1 = (long) (dividend / 10) // remainingFirstDigitsOfDividend
        long a2 = (long) (divisor / 10) // remainingFirstDigitsOfDivisor
        long b = dividend % 10 // lastNumberOfDividend

        return (x * a1) + ((y * a2) + 1) * b
    }

    /**
     * Decides if it is ok to continue iterating or not
     *
     * @param iteration, current number of iteration
     * @param calculated, calculated number after each iteration
     * @param previousCalculated, calculated number of previous iteration
     * @param divisor
     * @return boolean, true if continue iterating, false if stop iterating and continue
     */
    private boolean continueIterating(int iteration, long calculated, long previousCalculated, long divisor) {
        return iteration < MAX_ITERATION_COUNT_ALLOWED && calculated != divisor && calculated != previousCalculated && calculated < previousCalculated && (calculated - divisor) > 0
    }

    /**
     * Defines if the dividend is divided to divisor with zero reminder
     *
     * @param calculated, calculated number after each iteration
     * @param previousCalculation, calculated number of previous iteration
     * @param divisor
     * @return boolean, true if divisible, false if not divisible
     */
    private boolean calculateDivisibility(long calculated, long previousCalculation, long divisor) {
        return calculated == divisor || calculated == previousCalculation
    }
}
