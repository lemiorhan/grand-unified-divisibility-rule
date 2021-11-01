package com.lemiorhanergin.divisibilityrules

import groovy.util.logging.Slf4j

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

    static Closure<Long> of(long num) {
        return FORMULAS."FOR_$num"
    }
}
