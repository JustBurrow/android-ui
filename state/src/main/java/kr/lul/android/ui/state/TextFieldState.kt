package kr.lul.android.ui.state

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import java.util.UUID

/**
 * 텍스트필드 상태
 *
 * @see androidx.compose.material3.TextField
 * @see androidx.compose.material3.OutlinedTextField
 */
@Immutable
open class TextFieldState(
    /**
     * 텍스트필드에 입력된 것드로 표시할 문자열.
     */
    val value: TextFieldValue = TextFieldValue(""),
    val enabled: Boolean = true,
    val readOnly: Boolean = false,
    val textStyle: TextStyle = TextStyle.Default,
    val error: Boolean = false,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    val keyboardActions: KeyboardActions = KeyboardActions.Default,
    val singleLine: Boolean = false,
    val lines: TextLines = DefaultTextLines,
    override val testTag: String = UUID.randomUUID().toString()
) : State {
    constructor(
        text: String = "",
        enabled: Boolean = true,
        readOnly: Boolean = false,
        textStyle: TextStyle = TextStyle.Default,
        error: Boolean = false,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        keyboardActions: KeyboardActions = KeyboardActions.Default,
        singleLine: Boolean = false,
        lines: TextLines = DefaultTextLines,
        testTag: String = UUID.randomUUID().toString()
    ) : this(
        TextFieldValue(text, TextRange(text.length)),
        enabled,
        readOnly,
        textStyle,
        error,
        visualTransformation,
        keyboardOptions,
        keyboardActions,
        singleLine,
        lines,
        testTag
    )

    fun copy(
        value: TextFieldValue = this.value,
        enabled: Boolean = this.enabled,
        readOnly: Boolean = this.readOnly,
        textStyle: TextStyle = this.textStyle,
        error: Boolean = this.error,
        visualTransformation: VisualTransformation = this.visualTransformation,
        keyboardOptions: KeyboardOptions = this.keyboardOptions,
        keyboardActions: KeyboardActions = this.keyboardActions,
        singleLine: Boolean = this.singleLine,
        lines: TextLines = this.lines
    ) = TextFieldState(
        value,
        enabled,
        readOnly,
        textStyle,
        error,
        visualTransformation,
        keyboardOptions,
        keyboardActions,
        singleLine,
        lines,
        testTag
    )

    fun copy(
        text: String = this.value.text,
        enabled: Boolean = this.enabled,
        readOnly: Boolean = this.readOnly,
        textStyle: TextStyle = this.textStyle,
        error: Boolean = this.error,
        visualTransformation: VisualTransformation = this.visualTransformation,
        keyboardOptions: KeyboardOptions = this.keyboardOptions,
        keyboardActions: KeyboardActions = this.keyboardActions,
        singleLine: Boolean = this.singleLine,
        lines: TextLines = this.lines,
    ): TextFieldState = if (text == this.value.text) {
        TextFieldState(
            value,
            enabled,
            readOnly,
            textStyle,
            error,
            visualTransformation,
            keyboardOptions,
            keyboardActions,
            singleLine,
            lines,
            testTag
        )
    } else {
        TextFieldState(
            TextFieldValue(text, TextRange(text.length)),
            enabled,
            readOnly,
            textStyle,
            error,
            visualTransformation,
            keyboardOptions,
            keyboardActions,
            singleLine,
            lines,
            testTag
        )
    }

    override fun equals(other: Any?) = this === other || (
            other is TextFieldState &&
                    value == other.value &&
                    enabled == other.enabled &&
                    readOnly == other.readOnly &&
                    textStyle == other.textStyle &&
                    error == other.error &&
                    visualTransformation == other.visualTransformation &&
                    keyboardOptions == other.keyboardOptions &&
                    keyboardActions == other.keyboardActions &&
                    singleLine == other.singleLine &&
                    lines == other.lines &&
                    testTag == other.testTag
            )

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + enabled.hashCode()
        result = 31 * result + readOnly.hashCode()
        result = 31 * result + textStyle.hashCode()
        result = 31 * result + error.hashCode()
        result = 31 * result + visualTransformation.hashCode()
        result = 31 * result + keyboardOptions.hashCode()
        result = 31 * result + keyboardActions.hashCode()
        result = 31 * result + singleLine.hashCode()
        result = 31 * result + lines.hashCode()
        result = 31 * result + testTag.hashCode()
        return result
    }

    override fun toString() = listOf(
        "value=$value",
        "enabled=$enabled",
        "readOnly=$readOnly",
        "textStyle=$textStyle",
        "error=$error",
        "visualTransformation=$visualTransformation",
        "keyboardOptions=$keyboardOptions",
        "keyboardActions=$keyboardActions",
        "singleLine=$singleLine",
        "lines=$lines",
        "testTag='$testTag'"
    ).joinToString(", ", "TextFieldState(", ")")
}
