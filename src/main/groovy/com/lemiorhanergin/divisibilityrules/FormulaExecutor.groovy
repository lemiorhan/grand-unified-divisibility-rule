package com.lemiorhanergin.divisibilityrules

import groovy.util.logging.Slf4j

@Slf4j
class FormulaExecutor {

    def execute(long dividend, long divisor) {
        log.info("============[ {}/{} ]==============", dividend, divisor)
        long lastNumberOfDivisor = divisor % 10
        log.info("FORMULA SELECTED FOR {}", lastNumberOfDivisor)

        long calculated = Formula.of(lastNumberOfDivisor).call(parameterize(dividend, divisor))
        log.info("{}. iteration -> {}", 1, calculated)

        long previousCalculated = Long.MAX_VALUE
        def iteration = 2
        while (iteration < 50 && calculated != divisor && calculated != previousCalculated && calculated < previousCalculated && (calculated - divisor) > 0) {
            previousCalculated = calculated
            calculated = Formula.of(lastNumberOfDivisor).call(parameterize(calculated, divisor))

            log.info("{}. iteration -> {}", iteration, calculated)
            iteration++

            if (calculated == previousCalculated && calculated > divisor) {
                calculated = previousCalculated - divisor
            }
        }


        def divisible = isDivisible(calculated, previousCalculated, dividend, divisor)
        log.info("RESULT: " + (divisible ? "DIVISIBLE" : "NOT DIVISIBLE") + " CALCULATED IN ${iteration - 1} STEPS")
        return divisible
    }

    def parameterize(long dividend, long divisor) {
        long lastNumberOfDividend = dividend % 10
        long remainingFirstDigitsOfDivisor = (long) (divisor / 10)
        long remainingFirstDigitsOfDividend = (long) (dividend / 10)
        return [a1: remainingFirstDigitsOfDividend, a2: remainingFirstDigitsOfDivisor, b: lastNumberOfDividend]
    }

    def isDivisible(long calculated, long previousCalculation, long dividend, long divisor) {
        return calculated == divisor || calculated == previousCalculation
    }
}
