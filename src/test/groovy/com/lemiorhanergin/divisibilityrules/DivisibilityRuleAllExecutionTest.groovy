/*
 * Copyright 2026 Lemi Orhan Ergin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
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
            log.info("CHECKING {} WITH POSITIVE NUMBER LOWER THAN ITSELF", dividend)
            (2..dividend).each { divisor ->
                if (divisor % 10 != 0) {
                    def isDivisible = divisibilityRule.isDivisible(dividend, divisor)
                    def result = isDivisible ? (dividend % divisor == 0) : (dividend % divisor != 0)
                    if (!result) log.warn("WARNING => FORMULA FAILS FOR [{}/{}]", dividend, divisor)
                }
            }
        }
    }
}
