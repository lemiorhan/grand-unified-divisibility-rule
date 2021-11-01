package com.lemiorhanergin.divisibilityrules

import groovy.util.logging.Slf4j

@Slf4j
class FormulaExecutor {


    public static final int MAX_ITERATION_COUNT_ALLOWED = 50

    def execute(long dividend, long divisor) {
        // formula is selected with the last digit of divisor
        long lastNumberOfDivisor = divisor % 10

        log.info("============[ {}/{} ]==============", dividend, divisor)
        log.info("FORMULA SELECTED FOR {}", lastNumberOfDivisor)

        // run the formula and calculate a number of iteration #1
        long calculated = Formula.of(lastNumberOfDivisor).call(parameterize(dividend, divisor))
        log.info("{}. iteration -> {}", 1, calculated)

        long previousCalculated = Long.MAX_VALUE
        def iteration = 2

        // let's do the iterations
        while (continueIterating(iteration, calculated, previousCalculated, divisor)) {
            previousCalculated = calculated

            // run the formula and calculate a number for each iteration
            calculated = Formula.of(lastNumberOfDivisor).call(parameterize(calculated, divisor))

            log.info("{}. iteration -> {}", iteration, calculated)
            iteration++

            // for repeats, try reducing the calculated number for the next iteration
            if (calculated == previousCalculated && calculated > divisor) {
                calculated = previousCalculated - divisor
            }
        }

        // identify if dividend can be divided to divisor with a zero remainder
        def divisible = isDivisible(calculated, previousCalculated, divisor)
        log.info("RESULT: " + (divisible ? "DIVISIBLE" : "NOT DIVISIBLE") + " CALCULATED IN ${iteration - 1} STEPS")
        return divisible
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
     * Creates the parameters required to execute the formula
     *
     * @param dividend
     * @param divisor
     * @return Map of parameters, that is the input given to the formula
     */
    private Map parameterize(long dividend, long divisor) {
        long lastNumberOfDividend = dividend % 10
        long remainingFirstDigitsOfDivisor = (long) (divisor / 10)
        long remainingFirstDigitsOfDividend = (long) (dividend / 10)
        return [a1: remainingFirstDigitsOfDividend, a2: remainingFirstDigitsOfDivisor, b: lastNumberOfDividend]
    }

    /**
     * Defines if the dividend is divided to divisor with zero reminder
     *
     * @param calculated, calculated number after each iteration
     * @param previousCalculation, calculated number of previous iteration
     * @param divisor
     * @return boolean, true if divisible, false if not divisible
     */
    private boolean isDivisible(long calculated, long previousCalculation, long divisor) {
        return calculated == divisor || calculated == previousCalculation
    }
}
