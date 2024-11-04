package kr.lul.android.ui.state

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Immutable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import java.util.UUID

/**
 * 텍스트필드 상태를 좀 더 자세하게 다루는 상태.
 *
 * @see androidx.compose.material3.TextField
 * @see androidx.compose.material3.OutlinedTextField
 */
@Immutable
class ScaffoldTextFieldState(
    /**
     * 텍스트필드에 입력된 것드로 표시할 문자열.
     */
    override val value: TextFieldValue = TextFieldValue(""),
    override val enabled: Boolean = true,
    override val readOnly: Boolean = false,
    override val textStyle: TextStyle = TextStyle.Default,
    val label: TextState? = null,
    val placeholder: TextState? = null,
    val leadingIcon: IconState? = null,
    val trailingIcon: IconState? = null,
    val prefix: State? = null,
    val suffix: State? = null,
    val supportingText: TextState? = null,
    val errorText: TextState? = null,
    override val visualTransformation: VisualTransformation = VisualTransformation.None,
    override val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    override val keyboardActions: KeyboardActions = KeyboardActions.Default,
    override val lines: TextLines = DefaultTextLines,
    override val focusRequester: FocusRequester? = null,
    override val key: Any = UUID.randomUUID(),
    override val testTag: String = key.toString()
) : TextFieldState(
    value,
    enabled,
    readOnly,
    textStyle,
    null != errorText,
    visualTransformation,
    keyboardOptions,
    keyboardActions,
    lines,
    focusRequester,
    key,
    testTag
) {
    constructor(
        text: String = "",
        enabled: Boolean = true,
        readOnly: Boolean = false,
        textStyle: TextStyle = TextStyle.Default,
        label: TextState? = null,
        placeholder: TextState? = null,
        leadingIcon: IconState? = null,
        trailingIcon: IconState? = null,
        prefix: State? = null,
        suffix: State? = null,
        supportingText: TextState? = null,
        errorText: TextState? = null,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        keyboardActions: KeyboardActions = KeyboardActions.Default,
        lines: TextLines = DefaultTextLines,
        focusRequester: FocusRequester? = null,
        key: Any = UUID.randomUUID(),
        testTag: String = key.toString()
    ) : this(
        TextFieldValue(text, TextRange(text.length)),
        enabled,
        readOnly,
        textStyle,
        label,
        placeholder,
        leadingIcon,
        trailingIcon,
        prefix,
        suffix,
        supportingText,
        errorText,
        visualTransformation,
        keyboardOptions,
        keyboardActions,
        lines,
        focusRequester,
        key,
        testTag
    )

    fun copy(
        value: TextFieldValue = this.value,
        enabled: Boolean = this.enabled,
        readOnly: Boolean = this.readOnly,
        textStyle: TextStyle = this.textStyle,
        label: TextState? = this.label,
        placeholder: TextState? = this.placeholder,
        leadingIcon: IconState? = this.leadingIcon,
        trailingIcon: IconState? = this.trailingIcon,
        prefix: State? = this.prefix,
        suffix: State? = this.suffix,
        supportingText: TextState? = this.supportingText,
        errorText: TextState? = this.errorText,
        visualTransformation: VisualTransformation = this.visualTransformation,
        keyboardOptions: KeyboardOptions = this.keyboardOptions,
        keyboardActions: KeyboardActions = this.keyboardActions,
        lines: TextLines = this.lines
    ) = ScaffoldTextFieldState(
        value,
        enabled,
        readOnly,
        textStyle,
        label,
        placeholder,
        leadingIcon,
        trailingIcon,
        prefix,
        suffix,
        supportingText,
        errorText,
        visualTransformation,
        keyboardOptions,
        keyboardActions,
        lines,
        focusRequester,
        key,
        testTag
    )

    fun copy(
        text: String = this.value.text,
        enabled: Boolean = this.enabled,
        readOnly: Boolean = this.readOnly,
        textStyle: TextStyle = this.textStyle,
        label: TextState? = this.label,
        placeholder: TextState? = this.placeholder,
        leadingIcon: IconState? = this.leadingIcon,
        trailingIcon: IconState? = this.trailingIcon,
        prefix: State? = this.prefix,
        suffix: State? = this.suffix,
        supportingText: TextState? = this.supportingText,
        errorText: TextState? = this.errorText,
        visualTransformation: VisualTransformation = this.visualTransformation,
        keyboardOptions: KeyboardOptions = this.keyboardOptions,
        keyboardActions: KeyboardActions = this.keyboardActions,
        lines: TextLines = this.lines,
    ): ScaffoldTextFieldState = if (text == this.value.text) {
        ScaffoldTextFieldState(
            value,
            enabled,
            readOnly,
            textStyle,
            label,
            placeholder,
            leadingIcon,
            trailingIcon,
            prefix,
            suffix,
            supportingText,
            errorText,
            visualTransformation,
            keyboardOptions,
            keyboardActions,
            lines,
            focusRequester,
            key,
            testTag
        )
    } else {
        ScaffoldTextFieldState(
            TextFieldValue(text, TextRange(text.length)),
            enabled,
            readOnly,
            textStyle,
            label,
            placeholder,
            leadingIcon,
            trailingIcon,
            prefix,
            suffix,
            supportingText,
            errorText,
            visualTransformation,
            keyboardOptions,
            keyboardActions,
            lines,
            focusRequester,
            key,
            testTag
        )
    }

    override fun equals(other: Any?) = this === other || (
            other is ScaffoldTextFieldState &&
                    super.equals(other) &&
                    leadingIcon == other.leadingIcon &&
                    trailingIcon == other.trailingIcon &&
                    prefix == other.prefix &&
                    suffix == other.suffix &&
                    supportingText == other.supportingText &&
                    errorText == other.errorText
            )

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (leadingIcon?.hashCode() ?: 0)
        result = 31 * result + (trailingIcon?.hashCode() ?: 0)
        result = 31 * result + (prefix?.hashCode() ?: 0)
        result = 31 * result + (suffix?.hashCode() ?: 0)
        result = 31 * result + (supportingText?.hashCode() ?: 0)
        return result
    }

    override fun toString() = listOf(
        "value=$value",
        "enabled=$enabled",
        "readOnly=$readOnly",
        "textStyle=$textStyle",
        "label=$label",
        "placeholder=$placeholder",
        "leadingIcon=$leadingIcon",
        "trailingIcon=$trailingIcon",
        "prefix=$prefix",
        "suffix=$suffix",
        "supportingText=$supportingText",
        "errorText=$errorText",
        "visualTransformation=$visualTransformation",
        "keyboardOptions=$keyboardOptions",
        "keyboardActions=$keyboardActions",
        "lines=$lines",
        "focusRequester=$focusRequester",
        "key=$key",
        "testTag='$testTag'"
    ).joinToString(", ", "ScaffoldTextFieldState(", ")")
}
