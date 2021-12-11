package com.lemiorhanergin.divisibilityrules

import groovy.util.logging.Slf4j
import spock.lang.Specification

@Slf4j
class DivisibilityRuleAllExecutionTest extends Specification {

    def "should find divisibility results for number between 2 to 1000"() {
        when:
        def divisibilityRule = new DivisibilityRule()
        DivisibilityRule.isLogEnabled = false

        then:
        (2..1000).each { dividend ->
            log.info("{}", dividend)
            (2..1000).each { divisor ->
                if (dividend > divisor && (divisor % 10 != 0)) {
                    def isDivisible = divisibilityRule.isDivisible(dividend, divisor)
                    def result = isDivisible ? (dividend % divisor == 0) : (dividend % divisor != 0)
                    if (!result) log.warn("ALERT!!! [{}/{}]", dividend, divisor)
                }
            }
        }
    }
}
