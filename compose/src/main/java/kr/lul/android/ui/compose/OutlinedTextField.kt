package kr.lul.android.ui.compose

import android.util.Log
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import kr.lul.android.ui.state.SingleTextLine
import kr.lul.android.ui.state.TextFieldState
import kr.lul.android.ui.state.hasTestTag
import kr.lul.android.ui.state.preview.TextFieldStateProvider

@Composable
fun OutlinedTextField(
    state: TextFieldState,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = TextFieldDefaults.shape,
    colors: TextFieldColors = TextFieldDefaults.colors()
) {
    Log.v(TAG, "#TextField args : state=$state")

    androidx.compose.material3.OutlinedTextField(
        value = state.value,
        onValueChange = onValueChange,
        modifier = if (modifier.hasTestTag()) {
            Log.w(TAG, "#TextField testTag already exists in the modifier : modifier=$modifier")
            modifier
        } else {
            modifier.testTag(state.testTag)
        },
        enabled = state.enabled,
        readOnly = state.readOnly,
        textStyle = state.textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        isError = state.error,
        visualTransformation = state.visualTransformation,
        keyboardOptions = state.keyboardOptions,
        keyboardActions = state.keyboardActions,
        singleLine = state.lines is SingleTextLine,
        maxLines = state.lines.max,
        minLines = state.lines.min,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors
    )
}

@Composable
@Preview(showBackground = true)
private fun PreviewOutlinedTextField(@PreviewParameter(TextFieldStateProvider::class) state: TextFieldState) {
    MaterialTheme {
        Box(Modifier.padding(8.dp)) {
            OutlinedTextField(state = state, onValueChange = {})
        }
    }
}