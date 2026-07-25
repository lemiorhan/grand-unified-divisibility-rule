package com.lemiorhanergin.divisibilityrules

import groovy.util.logging.Slf4j

/**
 * Grand unified divisibility rule.
 *
 * A number N is divisible by d exactly when the weighted sum of N's digits
 * is divisible by d, where the weight of the ones digit is 1 and every next
 * weight is derived from the previous one by appending a zero (multiplying
 * by 10) and then subtracting d until the value drops below d.
 *
 * Proof: by induction each weight is congruent to the matching power of ten
 * modulo d, so the weighted digit sum is congruent to N itself modulo d.
 *
 * All well-known divisibility rules are rows of this single rule:
 *   d = 3, 9  -> weights 1,1,1,...        (digit sum)
 *   d = 11    -> weights 1,10,1,10,...    (alternating sum, since 10 = -1 mod 11)
 *   d = 2,4,8 -> weights die to 0         (only the last 1, 2, 3 digits matter)
 *   d = 7     -> weights 1,3,2,6,4,5,...  (the classical rule for 7)
 *
 * No prime factors, no division by the divisor. The only operations are
 * reading digits, multiplying a digit by a weight, and subtraction.
 **/
@Slf4j
class DivisibilityRule {

    public static boolean isLogEnabled = false

    boolean isDivisible(long dividend, long divisor, Appendable trace = null) {
        if (divisor < 1) throw new IllegalArgumentException("Divisor must be a positive number")
        if (dividend < 0) throw new IllegalArgumentException("Dividend must not be negative")

        emit(trace, "DIVISIBILITY CHECK FOR [${dividend}/${divisor}]")
        if (isLogEnabled || trace != null) emit(trace, formulaVariation(dividend, divisor))

        long remaining = dividend
        long weight = 1
        long total = 0
        int step = 1

        while (true) {
            long digit = remaining % 10
            long contribution = digit * weight
            long unreducedTotal = total + contribution
            total = reduceBySubtraction(unreducedTotal, divisor)
            emit(trace, "STEP ${step} => DIGIT ${digit} x WEIGHT ${weight} = ${contribution} | TOTAL ${unreducedTotal}${reductionNote(unreducedTotal, total, divisor)}")

            remaining = remaining.intdiv(10)
            if (remaining == 0) break

            long nextWeightUnreduced = weight * 10
            weight = reduceBySubtraction(nextWeightUnreduced, divisor)
            emit(trace, "WEIGHT FOR STEP ${step + 1} => 10 x ${nextWeightUnreduced.intdiv(10)} = ${nextWeightUnreduced}${reductionNote(nextWeightUnreduced, weight, divisor)}")
            step++
        }

        boolean divisible = (total == 0)
        emit(trace, "FINAL REMAINDER: ${total} => ${dividend} IS ${divisible ? '' : 'NOT '}DIVISIBLE BY ${divisor}")
        emit(trace, "")
        return divisible
    }

    /**
     * Builds the variation of the formula that will be applied to this dividend:
     * the divisor's weight row, one weight per digit of the dividend.
     */
    private static String formulaVariation(long dividend, long divisor) {
        int digitCount = String.valueOf(dividend).length()
        List<String> terms = []
        long weight = 1
        for (int digit = 1; digit <= digitCount; digit++) {
            terms << "(${weight} x DIGIT ${digit})".toString()
            weight = reduceBySubtraction(weight * 10, divisor)
        }
        return "FORMULA FOR ${divisor} => ${terms.join(' + ')} (DIGIT 1 = ONES PLACE)"
    }

    /**
     * Brings a value below the divisor using nothing but subtraction.
     */
    private static long reduceBySubtraction(long value, long divisor) {
        long reduced = value
        while (reduced >= divisor) reduced -= divisor
        return reduced
    }

    private static String reductionNote(long before, long after, long divisor) {
        return before == after ? "" : ", REDUCED TO ${after} BY SUBTRACTING ${divisor}"
    }

    private void emit(Appendable trace, String line) {
        if (isLogEnabled) log.info(line)
        trace?.append(line)?.append(System.lineSeparator())
    }
}
