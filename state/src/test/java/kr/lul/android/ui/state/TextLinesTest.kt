package kr.lul.android.ui.state

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs
import kr.lul.android.ui.test.AbstractBehaviorTest
import kotlin.random.Random

class TextLinesTest : AbstractBehaviorTest() {
    init {
        given("TextLines - 사용할 수 없는 lines") {
            table(
                headers("lines"),
                row(Int.MIN_VALUE),
                row(-1),
                row(0)
            ).forAll { lines ->
                logger.info { "[GIVEN] lines=$lines" }

                `when`("사용 불가능한 줄 수를 사용하면") {
                    val e = shouldThrow<IllegalArgumentException> { TextLines(lines) }
                    logger.info { "[WHEN] e=$e" }

                    then("예외가 발생한다") {
                        e.shouldNotBeNull()
                    }
                }
            }
        }

        given("TextLines - min이 max보다 큰  MIN, max") {
            repeat(100) {
                val max = Random.nextInt(1, Int.MAX_VALUE / 2)
                val min = Random.nextInt(max + 1, Int.MAX_VALUE)
                logger.info { "[GIVEN] min=$min, max=$max" }

                `when`("사용 가능한 줄 수를 사용하면") {
                    val e = shouldThrow<IllegalArgumentException> { TextLines(min, max) }
                    logger.info { "[WHEN] e=$e" }

                    then("예외가 발생한다") {
                        e.shouldNotBeNull()
                    }
                }
            }
        }

        given("TextLines - lines") {
            table(
                headers("lines"),
                row(1),
            ).forAll { lines ->
                logger.info { "[GIVEN] lines=$lines" }

                `when`("사용 가능한 줄 수를 사용하면") {
                    val lines = TextLines(lines)
                    logger.info { "[WHEN] lines=$lines" }

                    then("SingleTextLine 이다.") {
                        lines shouldBeSameInstanceAs SingleTextLine
                    }
                }
            }
        }

        given("TextLines - 1, MAX") {
            `when`("사용 가능한 줄 수를 사용하면") {
                val lines = TextLines(1, Int.MAX_VALUE)
                logger.info { "[WHEN] lines=$lines" }

                then("DefaultTextLines 이다.") {
                    lines shouldBeSameInstanceAs DefaultTextLines
                }
            }
        }

        given("TextLines - 1, random") {
            repeat(100) {
                val max = Random.nextInt(2, Int.MAX_VALUE)
                logger.info { "[GIVEN] max=$max" }

                `when`("임의의 사용 가능한 줄 수를 사용하면") {
                    val lines = TextLines(1, max)
                    logger.info { "[WHEN] lines=$lines" }

                    then("MultiTextLines를 반환한다.") {
                        lines.shouldBeInstanceOf<MultiTextLines>()
                        lines.min shouldBe 1
                        lines.max shouldBe max
                    }
                }
            }
        }

        given("TextLines - random, random") {
            repeat(100) {
                val min = Random.nextInt(1, Int.MAX_VALUE / 2)
                val max = Random.nextInt(min + 1, Int.MAX_VALUE)
                logger.info { "[GIVEN] min=$min, max=$max" }

                `when`("임의의 사용 가능한 줄 수를 사용하면") {
                    val lines = TextLines(min, max)
                    logger.info { "[WHEN] lines=$lines" }

                    then("MultiTextLines를 반환한다.") {
                        lines.shouldBeInstanceOf<MultiTextLines>()
                        lines.min shouldBe min
                        lines.max shouldBe max
                    }
                }
            }
        }

        given("TextLines - random, MAX") {
            repeat(100) {
                val min = Random.nextInt(1, Int.MAX_VALUE)
                logger.info { "[GIVEN] min=$min" }

                `when`("임의의 사용 가능한 줄 수를 사용하면") {
                    val lines = TextLines(min, Int.MAX_VALUE)
                    logger.info { "[WHEN] lines=$lines" }

                    then("MultiTextLines를 반환한다.") {
                        lines.shouldBeInstanceOf<MultiTextLines>()
                        lines.min shouldBe min
                        lines.max shouldBe Int.MAX_VALUE
                    }
                }
            }
        }
    }
}