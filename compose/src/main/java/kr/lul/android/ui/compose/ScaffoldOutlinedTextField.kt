package kr.lul.android.ui.compose

import android.util.Log
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import kr.lul.android.ui.state.ScaffoldTextFieldState
import kr.lul.android.ui.state.ScaffoldTextFieldStateProvider
import kr.lul.android.ui.state.TextFieldActionHandler
import kr.lul.android.ui.state.TextFieldActions
import kr.lul.android.ui.state.hasTestTag

/**
 * [ScaffoldTextFieldState]를 사용한 [OutlinedTextField]를 생성한다.
 *
 * @param state 텍스트필드 상태.
 * @param onValueChange 텍스트필드 값 변경 이벤트 처리.
 * @param modifier [Modifier].
 * @param interactionSource [MutableInteractionSource]. 호이스팅으로 상위 컴포넌트에서 관리할 때 사용.
 * @param shape 텍스트필드 형태.
 * @param colors 텍스트필드 색깔.
 * @param onChangeFocus 포커스 변경 이벤트 처리.
 */
@Composable
fun ScaffoldOutlinedTextField(
    state: ScaffoldTextFieldState,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = TextFieldDefaults.shape,
    colors: TextFieldColors = TextFieldDefaults.colors(),
    onChangeFocus: ((FocusState) -> Unit)? = null
) {
    Log.v(
        TAG, listOf(
            "state=$state",
            "onValueChange=$onValueChange",
            "modifier=$modifier",
            "interactionSource=$interactionSource",
            "shape=$shape",
            "colors=$colors",
            "onChangeFocus=$onChangeFocus"
        ).joinToString(", ", "#ScaffoldOutlinedTextField args : ")
    )

    var actualModifier = if (modifier.hasTestTag()) {
        modifier
    } else {
        modifier.testTag(state.testTag)
    }

    if (null != state.focusRequester) {
        actualModifier = actualModifier.focusRequester(state.focusRequester!!)
    }
    if (null != onChangeFocus) {
        actualModifier = actualModifier.onFocusChanged(onChangeFocus)
    }

    val label: (@Composable () -> Unit)? = if (null == state.label) {
        null
    } else {
        @Composable { Text(state.label!!) }
    }
    val placeholder: (@Composable () -> Unit)? = if (null == state.placeholder) {
        null
    } else {
        @Composable { Text(state.placeholder!!) }
    }
    val leadingIcon: (@Composable () -> Unit)? = if (null == state.leadingIcon) {
        null
    } else {
        @Composable { Icon(state.leadingIcon!!) }
    }
    val trailingIcon: (@Composable () -> Unit)? = if (null == state.trailingIcon) {
        null
    } else {
        @Composable { Icon(state.trailingIcon!!) }
    }
    val prefix: (@Composable () -> Unit)? = if (null == state.prefix) {
        null
    } else {
        @Composable { StateComponent(state.prefix!!) }
    }
    val suffix: (@Composable () -> Unit)? = if (null == state.suffix) {
        null
    } else {
        @Composable { StateComponent(state.suffix!!) }
    }
    val supportingText: (@Composable () -> Unit)? = if (null == state.supportingText) {
        null
    } else {
        @Composable { Text(state.supportingText!!) }
    }

    Column(modifier = actualModifier) {
        androidx.compose.material3.OutlinedTextField(
            value = state.value,
            onValueChange = onValueChange,
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
            singleLine = state.lines.singleLine,
            maxLines = state.lines.max,
            minLines = state.lines.min,
            interactionSource = interactionSource,
            shape = shape,
            colors = colors
        )
        if (null != state.errorText) {
            Text(state = state.errorText!!, modifier = Modifier.padding(8.dp, 4.dp))
        }
    }
}

/**
 * [ScaffoldTextFieldState]를 사용한 [OutlinedTextField]를 생성한다.
 *
 * @param state 텍스트필드 상태.
 * @param actions 텍스트필드 사용자 조작.
 * @param modifier [Modifier].
 * @param interactionSource [MutableInteractionSource]. 호이스팅으로 상위 컴포넌트에서 관리할 때 사용.
 * @param shape 텍스트필드 형태.
 * @param colors 텍스트필드 색깔.
 */
@Composable
fun ScaffoldOutlinedTextField(
    state: ScaffoldTextFieldState,
    actions: TextFieldActions,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = TextFieldDefaults.shape,
    colors: TextFieldColors = TextFieldDefaults.colors()
) {
    ScaffoldOutlinedTextField(
        state = state,
        onValueChange = actions.onValueChange,
        modifier = modifier,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
        onChangeFocus = actions.onFocusChange
    )
}

/**
 * [ScaffoldTextFieldState]를 사용한 [OutlinedTextField]를 생성한다.
 *
 * @param state 텍스트필드 상태.
 * @param handler 텍스트필드 사용자 조작.
 * @param modifier [Modifier].
 * @param interactionSource [MutableInteractionSource]. 호이스팅으로 상위 컴포넌트에서 관리할 때 사용.
 * @param shape 텍스트필드 형태.
 * @param colors 텍스트필드 색깔.
 */
@Composable
fun ScaffoldOutlinedTextField(
    state: ScaffoldTextFieldState,
    handler: TextFieldActionHandler,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = TextFieldDefaults.shape,
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    ScaffoldOutlinedTextField(
        state = state,
        onValueChange = handler::onValueChange,
        modifier = modifier,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
        onChangeFocus = handler::onFocusChange
    )
}

@Composable
@Preview(showBackground = true)
private fun PreviewScaffoldOutlinedTextField(
    @PreviewParameter(ScaffoldTextFieldStateProvider::class) state: ScaffoldTextFieldState
) {
    MaterialTheme {
        ScaffoldOutlinedTextField(state = state, onValueChange = {})
    }
}