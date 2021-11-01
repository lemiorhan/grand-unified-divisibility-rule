package com.lemiorhanergin.divisibilityrules

import spock.lang.Specification

class FormulaExecutionTest extends Specification {

    def "should find divisibility results"() {
        given:
        def formulaExecutor = new FormulaExecutor()

        when:
        def isDivisible = formulaExecutor.execute(dividend, divisor)

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
    }
}
