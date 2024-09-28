package kr.lul.android.ui.state

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import java.util.UUID

/**
 * 텍스트 상태.
 */
@Immutable
class TextState(
    /**
     * 출력할 텍스트.
     */
    val text: AnnotatedString,
    /**
     * 텍스트 색상.
     */
    val color: Color = Color.Unspecified,
    /**
     * 텍스트 크기.
     */
    val fontSize: TextUnit = TextUnit.Unspecified,
    /**
     * 텍스트 스타일.
     */
    val fontStyle: FontStyle? = null,
    /**
     * 텍스트 굵기.
     */
    val fontWeight: FontWeight? = null,
    /**
     * 텍스트 글꼴.
     */
    val fontFamily: FontFamily? = null,
    /**
     * 글자 간격.
     */
    val letterSpacing: TextUnit = TextUnit.Unspecified,
    /**
     * 텍스트 장식.
     */
    val textDecoration: TextDecoration? = null,
    /**
     * 텍스트 정렬.
     */
    val textAlign: TextAlign? = null,
    /**
     * 줄 높이.
     */
    val lineHeight: TextUnit = TextUnit.Unspecified,
    /**
     * 텍스트 오버플로우 처리.
     */
    val overflow: TextOverflow = TextOverflow.Clip,
    /**
     * 텍스트 줄 바꿈.
     */
    val softWrap: Boolean = true,
    /**
     * 텍스트 줄 수. 텍스트가 최대 줄 수를 초과하면 [overflow]에 따라 처리된다.
     *
     * @see overflow
     */
    val textLines: TextLines = DefaultTextLines,
    /**
     * 텍스트 스타일.
     */
    val style: TextStyle = TextStyle.Default,
    override val testTag: String = UUID.randomUUID().toString()
) : State {
    constructor(
        text: String,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontStyle: FontStyle? = null,
        fontWeight: FontWeight? = null,
        fontFamily: FontFamily? = null,
        letterSpacing: TextUnit = TextUnit.Unspecified,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        textLines: TextLines = DefaultTextLines,
        style: TextStyle = TextStyle.Default,
        testTag: String = UUID.randomUUID().toString()
    ) : this(
        AnnotatedString(text),
        color,
        fontSize,
        fontStyle,
        fontWeight,
        fontFamily,
        letterSpacing,
        textDecoration,
        textAlign,
        lineHeight,
        overflow,
        softWrap,
        textLines,
        style,
        testTag
    )

    fun copy(
        text: AnnotatedString = this.text,
        color: Color = this.color,
        fontSize: TextUnit = this.fontSize,
        fontStyle: FontStyle? = this.fontStyle,
        fontWeight: FontWeight? = this.fontWeight,
        fontFamily: FontFamily? = this.fontFamily,
        letterSpacing: TextUnit = this.letterSpacing,
        textDecoration: TextDecoration? = this.textDecoration,
        textAlign: TextAlign? = this.textAlign,
        lineHeight: TextUnit = this.lineHeight,
        overflow: TextOverflow = this.overflow,
        softWrap: Boolean = this.softWrap,
        textLines: TextLines = this.textLines,
        style: TextStyle = this.style,
    ) = TextState(
        text,
        color,
        fontSize,
        fontStyle,
        fontWeight,
        fontFamily,
        letterSpacing,
        textDecoration,
        textAlign,
        lineHeight,
        overflow,
        softWrap,
        textLines,
        style,
        testTag
    )

    fun copy(
        text: String = this.text.text,
        color: Color = this.color,
        fontSize: TextUnit = this.fontSize,
        fontStyle: FontStyle? = this.fontStyle,
        fontWeight: FontWeight? = this.fontWeight,
        fontFamily: FontFamily? = this.fontFamily,
        letterSpacing: TextUnit = this.letterSpacing,
        textDecoration: TextDecoration? = this.textDecoration,
        textAlign: TextAlign? = this.textAlign,
        lineHeight: TextUnit = this.lineHeight,
        overflow: TextOverflow = this.overflow,
        softWrap: Boolean = this.softWrap,
        textLines: TextLines = this.textLines,
        style: TextStyle = this.style,
    ) = TextState(
        text,
        color,
        fontSize,
        fontStyle,
        fontWeight,
        fontFamily,
        letterSpacing,
        textDecoration,
        textAlign,
        lineHeight,
        overflow,
        softWrap,
        textLines,
        style,
        testTag
    )

    override fun equals(other: Any?) = other === this || (
            other is TextState &&
                    text == other.text &&
                    color == other.color &&
                    fontSize == other.fontSize &&
                    fontStyle == other.fontStyle &&
                    fontWeight == other.fontWeight &&
                    fontFamily == other.fontFamily &&
                    letterSpacing == other.letterSpacing &&
                    textDecoration == other.textDecoration &&
                    textAlign == other.textAlign &&
                    lineHeight == other.lineHeight &&
                    overflow == other.overflow &&
                    softWrap == other.softWrap &&
                    textLines == other.textLines &&
                    style == other.style &&
                    testTag == other.testTag
            )

    override fun hashCode(): Int {
        var result = text.hashCode()
        result = 31 * result + color.hashCode()
        result = 31 * result + fontSize.hashCode()
        result = 31 * result + (fontStyle?.hashCode() ?: 0)
        result = 31 * result + (fontWeight?.hashCode() ?: 0)
        result = 31 * result + (fontFamily?.hashCode() ?: 0)
        result = 31 * result + letterSpacing.hashCode()
        result = 31 * result + (textDecoration?.hashCode() ?: 0)
        result = 31 * result + (textAlign?.hashCode() ?: 0)
        result = 31 * result + lineHeight.hashCode()
        result = 31 * result + overflow.hashCode()
        result = 31 * result + softWrap.hashCode()
        result = 31 * result + textLines.hashCode()
        result = 31 * result + style.hashCode()
        result = 31 * result + testTag.hashCode()
        return result
    }

    override fun toString() = listOf(
        "text='$text'",
        "color=$color",
        "fontSize=$fontSize",
        "fontStyle=$fontStyle",
        "fontWeight=$fontWeight",
        "fontFamily=$fontFamily",
        "letterSpacing=$letterSpacing",
        "textDecoration=$textDecoration",
        "textAlign=$textAlign",
        "lineHeight=$lineHeight",
        "overflow=$overflow",
        "softWrap=$softWrap",
        "textLines=$textLines",
        "style=$style",
        "testTag='$testTag'"
    ).joinToString(", ", "TextState(", ")")
}
