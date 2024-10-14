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
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import kr.lul.android.ui.state.SingleTextLine
import kr.lul.android.ui.state.TextFieldActionHandler
import kr.lul.android.ui.state.TextFieldActions
import kr.lul.android.ui.state.TextFieldState
import kr.lul.android.ui.state.hasTestTag
import kr.lul.android.ui.state.preview.TextFieldStateProvider

/**
 * 스테이트 홀더를 기반으로 하는 [androidx.compose.material3.OutlinedTextField] 확장.
 *
 * @param state 텍스트필드 상태.
 * @param onValueChange 텍스트필드 값 변경 이벤트 처리.
 * @param modifier [Modifier].
 * @param label 레이블.
 * @param placeholder 플레이스홀더.
 * @param leadingIcon 선행 아이콘.
 * @param trailingIcon 후행 아이콘.
 * @param prefix 접두사.
 * @param suffix 접미사.
 * @param supportingText 지원 텍스트.
 * @param interactionSource [MutableInteractionSource].
 * @param shape 텍스트필드 형태.
 * @param colors 텍스트필드 색깔.
 * @param onChangeFocus 포커스 변경 이벤트 처리.
 *
 * @see androidx.compose.material3.OutlinedTextField
 */
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
    colors: TextFieldColors = TextFieldDefaults.colors(),
    onChangeFocus: ((FocusState) -> Unit)? = null
) {
    Log.v(TAG, "#TextField args : state=$state, ...")

    var actualModifier = if (modifier.hasTestTag()) {
        Log.w(TAG, "#TextField testTag already exists in the modifier : modifier=$modifier")
        modifier
    } else {
        modifier.testTag(state.testTag)
    }
    if (null != onChangeFocus) {
        actualModifier = actualModifier.onFocusChanged(onChangeFocus)
    }

    androidx.compose.material3.OutlinedTextField(
        value = state.value,
        onValueChange = onValueChange,
        modifier = actualModifier,
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

/**
 * 스테이트 홀더를 기반으로 하는 [androidx.compose.material3.OutlinedTextField] 확장.
 *
 * @param state 텍스트필드 상태.
 * @param actions 텍스트필드 사용자 조작.
 * @param modifier [Modifier].
 * @param label 레이블.
 * @param placeholder 플레이스홀더.
 * @param leadingIcon 선행 아이콘.
 * @param trailingIcon 후행 아이콘.
 * @param prefix 접두사.
 * @param suffix 접미사.
 * @param supportingText 지원 텍스트.
 * @param interactionSource [MutableInteractionSource].
 * @param shape 텍스트필드 형태.
 * @param colors 텍스트필드 색깔.
 *
 * @see androidx.compose.material3.OutlinedTextField
 */
@Composable
fun OutlinedTextField(
    state: TextFieldState,
    actions: TextFieldActions,
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
    OutlinedTextField(
        state = state,
        onValueChange = actions.onValueChange,
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
        onChangeFocus = actions.onFocusChange
    )
}

/**
 * 스테이트 홀더를 기반으로 하는 [androidx.compose.material3.OutlinedTextField] 확장.
 *
 * @param state 텍스트필드 상태.
 * @param handler 텍스트필드 사용자 조작.
 * @param modifier [Modifier].
 * @param label 레이블.
 * @param placeholder 플레이스홀더.
 * @param leadingIcon 선행 아이콘.
 * @param trailingIcon 후행 아이콘.
 * @param prefix 접두사.
 * @param suffix 접미사.
 * @param supportingText 지원 텍스트.
 * @param interactionSource [MutableInteractionSource].
 * @param shape 텍스트필드 형태.
 * @param colors 텍스트필드 색깔.
 *
 * @see androidx.compose.material3.OutlinedTextField
 */
@Composable
fun OutlinedTextField(
    state: TextFieldState,
    handler: TextFieldActionHandler,
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
    OutlinedTextField(
        state = state,
        onValueChange = handler::onValueChange,
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
        onChangeFocus = handler::onFocusChange
    )
}

@Composable
@Preview(showBackground = true)
private fun PreviewOutlinedTextField(@PreviewParameter(TextFieldStateProvider::class) state: TextFieldState) {
    MaterialTheme {
        Box(Modifier.padding(8.dp)) {
            OutlinedTextField(state = state, TextFieldActions.EMPTY)
        }
    }
}