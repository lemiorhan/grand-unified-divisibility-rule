package com.lemiorhanergin.divisibilityrules

import groovy.util.logging.Slf4j

/**
 * Individual formumas that the unified formula is found from.
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
class Formula {

    private static FORMULAS = [
            FOR_1: { params -> (1 * params.a1) + ((9 * params.a2) + 1) * params.b },
            FOR_2: { params -> (2 * params.a1) + ((4 * params.a2) + 1) * params.b },
            FOR_3: { params -> (1 * params.a1) + ((3 * params.a2) + 1) * params.b },
            FOR_4: { params -> (2 * params.a1) + ((2 * params.a2) + 1) * params.b },
            FOR_5: { params -> (5 * params.a1) + ((1 * params.a2) + 1) * params.b },
            FOR_6: { params -> (4 * params.a1) + ((1 * params.a2) + 1) * params.b },
            FOR_7: { params -> (3 * params.a1) + ((1 * params.a2) + 1) * params.b },
            FOR_8: { params -> (2 * params.a1) + ((1 * params.a2) + 1) * params.b },
            FOR_9: { params -> (1 * params.a1) + ((1 * params.a2) + 1) * params.b }
    ]

    /**
     *  ( n * max(y) ) + x = 10
     *  9 * 1 + 1 = 10
     *  8 * 1 + 2 = 10
     *  7 * 1 + 3 = 10
     *  6 * 1 + 4 = 10
     *  5 * 1 + 5 = 10
     *  4 * 2 + 2 = 10
     *  3 * 3 + 1 = 10
     *  2 * 4 + 2 = 10
     *  1 * 9 + 1 = 10
     *
     *  max(y) = ceil(10 / n) - 1
     *  10 / 1 - 1 = 9
     *  10 / 2 - 1 = 4
     *  10 / 3 - 1 = 3
     *  10 / 4 - 1 = 2
     *  10 / 5 - 1 = 1
     *  10 / 6 - 1 = 1
     *  10 / 7 - 1 = 1
     *  10 / 8 - 1 = 1
     *  10 / 9 - 1 = 1
     *
     *  abs ( 10 + n - (n * ceil(10 / n)) )
     *  abs ( 10 + 1 - (1 * 10) ) = 1
     *  abs ( 10 + 2 - (2 * 5) ) = 2
     *  abs ( 10 + 3 - (3 * 4) ) = 1
     *  abs ( 10 + 4 - (4 * 3) ) = 2
     *  abs ( 10 + 5 - (5 * 2) ) = 5
     */

    static Closure<Long> of(long num) {
        return { params ->
            def x = (10 + num - (num * Math.ceil(10 / num))) as int
            def y = (Math.ceil(10 / num) - 1) as int
            def a1 = params.a1
            def a2 = params.a2
            def b = params.b

            return (x * a1) + ((y * a2) + 1) * b
        }
    }
}
