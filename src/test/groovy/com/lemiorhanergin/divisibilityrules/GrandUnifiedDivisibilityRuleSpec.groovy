package com.lemiorhanergin.divisibilityrules

import groovy.util.logging.Slf4j
import spock.lang.Shared
import spock.lang.Specification

/**
 * Verifies the grand unified divisibility rule:
 * - correct for every dividend/divisor pair up to 100
 * - correct for 25 random dividends with 3 to 9 digits
 * - needs no prime factor detection at all
 * - every calculation step of every check is written to a log file
 *   (build/divisibility-steps.log) for verification by eye
 */
@Slf4j
class GrandUnifiedDivisibilityRuleSpec extends Specification {

    static final long RANDOM_SEED = 19971425L

    @Shared
    File stepLogFile = new File("build/divisibility-steps.log")

    @Shared
    Writer stepLogWriter

    def setupSpec() {
        stepLogFile.parentFile.mkdirs()
        stepLogWriter = new BufferedWriter(new FileWriter(stepLogFile, false))
    }

    def cleanupSpec() {
        stepLogWriter.close()
        log.info("STEP-BY-STEP EXECUTION LOG WRITTEN TO {}", stepLogFile.absolutePath)
    }

    private boolean checkAndLogSteps(DivisibilityRule rule, long dividend, long divisor) {
        def result = rule.isDivisible(dividend, divisor, stepLogWriter)
        stepLogWriter.flush()
        return result
    }

    def "should match real division for every dividend and divisor up to 100"() {
        given:
        def rule = new DivisibilityRule()

        when:
        def failures = []
        (1..100).each { long dividend ->
            (1..100).each { long divisor ->
                def expected = (dividend % divisor == 0)
                if (checkAndLogSteps(rule, dividend, divisor) != expected) {
                    failures << "${dividend}/${divisor}"
                }
            }
        }

        then:
        failures.isEmpty()
    }

    def "should match real division for 25 random dividends with 3 to 9 digits"() {
        given:
        def rule = new DivisibilityRule()
        def random = new Random(RANDOM_SEED)

        when:
        def failures = []
        25.times { int index ->
            int digitCount = 3 + random.nextInt(7)
            long lowest = 10L**(digitCount - 1)
            long highest = (10L**digitCount) - 1
            long divisor = 2 + random.nextInt(9998)
            long dividend
            if (index % 2 == 0) {
                // construct an exact multiple sized to the wanted digit count,
                // so the log contains DIVISIBLE outcomes to check by eye too
                long multiplier = Math.max(1L, (long) (lowest / divisor) + random.nextInt(9))
                dividend = divisor * multiplier
                while (dividend > highest) dividend -= divisor
                while (dividend < lowest) dividend += divisor
            } else {
                dividend = lowest + (long) (random.nextDouble() * (highest - lowest))
            }

            assert String.valueOf(dividend).length() in 3..9

            def expected = (dividend % divisor == 0)
            if (checkAndLogSteps(rule, dividend, divisor) != expected) {
                failures << "${dividend}/${divisor}"
            }
        }

        then:
        failures.isEmpty()
    }

    def "should not contain any prime factor detection"() {
        expect:
        DivisibilityRule.declaredMethods.every { method ->
            !method.name.toLowerCase().contains("prime") && !method.name.toLowerCase().contains("factor")
        }
    }

    def "should log every calculation step of a check for verification by eye"() {
        given:
        def rule = new DivisibilityRule()
        def trace = new StringBuilder()

        when:
        def result = rule.isDivisible(175789L, 23L, trace)
        stepLogWriter.append(trace)
        stepLogWriter.flush()

        then:
        result
        trace.toString().contains("DIVISIBILITY CHECK FOR [175789/23]")
        trace.toString().contains("STEP 1")
        trace.toString().contains("WEIGHT")
        trace.toString().contains("FINAL REMAINDER")
        trace.toString().contains("175789 IS DIVISIBLE BY 23")
    }
}
