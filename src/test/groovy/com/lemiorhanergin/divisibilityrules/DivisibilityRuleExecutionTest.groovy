package com.lemiorhanergin.divisibilityrules

import groovy.util.logging.Slf4j
import spock.lang.Specification

@Slf4j
class DivisibilityRuleExecutionTest extends Specification {

    def "should find divisibility results"() {
        given:
        def divisibilityRule = new DivisibilityRule()
        DivisibilityRule.isLogEnabled = true

        when:
        log.info("==============[{}/{}]==============", dividend, divisor)
        def isDivisible = divisibilityRule.isDivisible(dividend, divisor)

        then:
        isDivisible ? (dividend % divisor == 0) : (dividend % divisor != 0)

        where:
        dividend     | divisor
        // divisible - simple numbers
        10           | 2
        18           | 3
        8            | 4
        15           | 5
        12           | 6
        21           | 7
        24           | 8
        18           | 9
        // NOT divisible - simple numbers
        11           | 2
        19           | 3
        9            | 4
        16           | 5
        13           | 6
        22           | 7
        25           | 8
        19           | 9
        // divisible - two digit numbers
        74283        | 11
        1044         | 12
        175789       | 23
        22406        | 34
        41490        | 45
        132832       | 56
        38391        | 67
        650286       | 78
        515221       | 89
        // NOT divisible - two digit numbers
        74284        | 11
        1045         | 12
        175788       | 23
        22407        | 34
        41491        | 45
        132833       | 56
        38390        | 67
        650287       | 78
        515222       | 89
        // divisible - five digit numbers
        7162290074   | 10961
        6027414224   | 16882
        9448278984   | 20637
        13386093162  | 37414
        23021493432  | 49251
        25916917952  | 55456
        13092489839  | 60073
        75939187148  | 73778
        222387483957 | 89909
        // NOT divisible - five digit numbers
        7162290073   | 10961
        6027414222   | 16882
        9448278985   | 20637
        13386093161  | 37414
        23021493430  | 49251
        25916917953  | 55456
        13092489836  | 60073
        75939187142  | 73778
        222387483951 | 89909
    }


    def "should find divisibility results2"() {
        given:
        def divisibilityRule = new DivisibilityRule()
        DivisibilityRule.isLogEnabled = true

        when:
        log.info("==============[{}/{}]==============", dividend, divisor)
        def isDivisible = divisibilityRule.isDivisible(dividend, divisor)

        then:
        isDivisible ? (dividend % divisor == 0) : (dividend % divisor != 0)

        where:
        dividend | divisor
        837      | 216
        837      | 558
        838      | 16
        838      | 27
        839      | 16
        839      | 27
        840      | 16
        840      | 27
        840      | 96
        840      | 336
        841      | 16
        841      | 27
        841      | 58
        841      | 87
        841      | 116
        841      | 145
        842      | 16
        842      | 27
        843      | 16
        843      | 27
        844      | 16
        844      | 27
        845      | 16
        845      | 27
        845      | 117
        24       | 16
        32       | 16
        27       | 18
        28       | 16
        36       | 16
        36       | 27
        38       | 16
        39       | 27
        40       | 16
        3        | 2
        4        | 3
        5        | 2
        240      | 36
        240      | 9
        6        | 2
        8        | 2
        186      | 3
        270      | 45
        104      | 16
        108      | 16
        108      | 45
        116      | 16
        117      | 18
        117      | 27
        117      | 36
        117      | 45
        120      | 16
        124      | 16
        126      | 27
        126      | 36
        126      | 45
        132      | 16
        135      | 18
        135      | 36
    }


    def "should find divisibility results3"() {
        given:
        def divisibilityRule = new DivisibilityRule()
        DivisibilityRule.isLogEnabled = true

        when:
        log.info("==============[{}/{}]==============", dividend, divisor)
        def isDivisible = divisibilityRule.isDivisible(dividend, divisor)

        then:
        isDivisible ? (dividend % divisor == 0) : (dividend % divisor != 0)

        where:
        dividend    | divisor
        18          | 3
        1044        | 12
        175789      | 23
        24          | 8
        19          | 3
        41490       | 45
        74284       | 11
        25916917952 | 55456
        23021493430 | 49251

    }


}
