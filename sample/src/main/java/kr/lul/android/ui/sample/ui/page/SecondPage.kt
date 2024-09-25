package kr.lul.android.ui.sample.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.lul.android.ui.navigation.compose.rememberBaseNavigator
import kr.lul.android.ui.sample.ui.navigator.SecondNavigator

@Composable
fun SecondPage(
    navigator: SecondNavigator
) {
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
        Text(text = "2nd Page", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.displayLarge)

        Button(onClick = navigator::back, modifier = Modifier.padding(16.dp)) {
            Text(text = "돌아가기")
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