package kr.lul.android.ui.sample.ui.page

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.lul.android.ui.compose.Text
import kr.lul.android.ui.navigation.compose.rememberBaseNavigator
import kr.lul.android.ui.sample.ui.navigator.SecondNavigator
import kr.lul.android.ui.sample.viewmodel.SecondViewModel
import kr.lul.android.ui.state.TextState
import kr.lul.android.ui.viewmodel.compose.baseViewModel

@Composable
fun SecondPage(
    navigator: SecondNavigator,
    viewModel: SecondViewModel = baseViewModel()
) {
    Log.v(TAG, "#SecondPage args : navigator=$navigator, viewModel=$viewModel")
    SecondPageContent(navigator)
}

@Composable
private fun SecondPageContent(
    navigator: SecondNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            TextState(text = "2nd Page", style = MaterialTheme.typography.displayLarge),
            modifier = Modifier.padding(16.dp)
        )

        Button(onClick = navigator::back, modifier = Modifier.padding(16.dp)) {
            Text(TextState("돌아가기"))
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun PreviewFirstPageContent() {
    MaterialTheme {
        SecondPageContent(SecondNavigator(rememberBaseNavigator()))
    }
}