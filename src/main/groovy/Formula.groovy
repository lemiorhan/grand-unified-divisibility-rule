class Formula {

    def static parameterize(long dividend, long divisor) {
        long lastNumberOfDividend = dividend % 10
        long remainingFirstDigitsOfDivisor = (long) (divisor / 10)
        long remainingFirstDigitsOfDividend = (long) (dividend / 10)
        return [ a1 : remainingFirstDigitsOfDividend, a2 : remainingFirstDigitsOfDivisor, b : lastNumberOfDividend]
    }

    def static FORMULAS = [
            FOR_1: { params -> (1 * params.a1) + ((9 * params.a2) + 1) * params.b },
            FOR_2: { params -> (2 * params.a1) + ((4 * params.a2) + 1) * params.b },
            FOR_3: { params -> (1 * params.a1) + ((3 * params.a2) + 1) * params.b },
            FOR_4: { params -> (2 * params.a1) + ((2 * params.a2) + 1) * params.b },
            FOR_5: { params -> (5 * params.a1) + ((1 * params.a2) + 1) * params.b },
            FOR_6: { params -> (4 * params.a1) + ((1 * params.a2) + 1) * params.b },
            FOR_7: { params -> (3 * params.a1) + ((1 * params.a2) + 1) * params.b },
            FOR_8: { params -> (1 * params.a1) + ((1 * params.a2) + 1) * params.b },
            FOR_9: { params -> (1 * params.a1) + ((1 * params.a2) + 1) * params.b }
    ]

    def static log(iteration,calculated) {
        println "$iteration -> $calculated"
    }

    static void main(String[] args) {
        def dividend = 3497960750
        def divisor = 362

        long lastNumberOfDivisor = divisor % 10
        println "FORMULA SELECTE FOR $lastNumberOfDivisor"

        long calculated = FORMULAS."FOR_$lastNumberOfDivisor".call(parameterize(dividend, divisor))
        log(1, calculated)

        def previousCalculated = Long.MAX_VALUE
        def iteration = 2
        while (iteration < 50 && calculated != divisor && calculated != previousCalculated && calculated < previousCalculated) {
            previousCalculated = calculated
            calculated = FORMULAS."FOR_$lastNumberOfDivisor".call(parameterize(calculated, divisor))
            log(iteration, calculated)
            iteration++
        }
        println("RESULT: YES -> " + (isDivisible(calculated, previousCalculated, divisor) ? "DIVISIBLE" : "NOT DIVISIBLE") + " CALCULATED IN ${iteration-1} STEPS")
    }

    private static boolean isDivisible(long calculated, long previousCalculation, int divisor) {
        calculated == previousCalculation || calculated == divisor
    }
}
