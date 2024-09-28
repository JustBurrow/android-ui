package kr.lul.android.ui.state

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertSame

@Suppress("NonAsciiCharacters")
class TextLinesTest {
    private val logger = KotlinLogging.logger { }

    @Test
    fun `TextLines - 0`() {
        // WHEN
        val e = assertFails { TextLines(0) }
        logger.info { "[WHEN] e=$e" }

        // THEN
        assertNotNull(e)
    }

    @Test
    fun `TextLines - 1`() {
        // WHEN
        val lines = TextLines(1)
        logger.info { "[WHEN] lines=$lines" }

        // THEN
        assertSame(SingleTextLine, lines)
    }

    @Test
    fun `TextLines - 1, MAX`() {
        // WHEN
        val lines = TextLines(1, Int.MAX_VALUE)
        logger.info { "[WHEN] lines=$lines" }

        // THEN
        assertSame(DefaultTextLines, lines)
    }

    @Test
    fun `TextLines - 1, random`() {
        for (i in 0..1000) {
            // GIVEN
            val max = Random.nextInt(2, Int.MAX_VALUE)
            logger.info { "[GIVEN] max=$max" }

            // WHEN
            val lines = TextLines(1, max)
            logger.info { "[WHEN] lines=$lines" }

            // THEN
            assertIs<MultiTextLines>(lines)
            assertEquals(1, lines.min)
            assertEquals(max, lines.max)
            println()
        }
    }

    @Test
    fun `TextLines - 유효한 random, 유효한 random`() {
        for (i in 0..1000) {
            // GIVEN
            val min = Random.nextInt(1, Int.MAX_VALUE / 2)
            val max = Random.nextInt(min + 1, Int.MAX_VALUE)
            logger.info { "[GIVEN] min=$min, max=$max" }

            // WHEN
            val lines = TextLines(min, max)
            logger.info { "[WHEN] lines=$lines" }

            // THEN
            assertIs<MultiTextLines>(lines)
            assertEquals(min, lines.min)
            assertEquals(max, lines.max)
            println()
        }
    }

    @Test
    fun `TextLines - random, MAX`() {
        for (i in 0..1000) {
            // GIVEN
            val min = Random.nextInt(1, Int.MAX_VALUE)
            logger.info { "[GIVEN] min=$min" }

            // WHEN
            val lines = TextLines(min, Int.MAX_VALUE)
            logger.info { "[WHEN] lines=$lines" }

            // THEN
            assertIs<MultiTextLines>(lines)
            assertEquals(min, lines.min)
            assertEquals(Int.MAX_VALUE, lines.max)
            println()
        }
    }

    @Test
    fun `TextLines - 최대값이 더 작은 경우`() {
        for (i in 0..1000) {
            // GIVEN
            val max = Random.nextInt(1, Int.MAX_VALUE / 2)
            val min = Random.nextInt(max + 1, Int.MAX_VALUE)
            logger.info { "[GIVEN] min=$min, max=$max" }

            // WHEN
            val e = assertFails { TextLines(min, max) }
            logger.info { "[WHEN] e=$e" }

            // THEN
            assertNotNull(e)
            println()
        }
    }
}