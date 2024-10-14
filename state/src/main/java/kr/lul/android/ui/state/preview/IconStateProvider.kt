package kr.lul.android.ui.state.preview

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color.BLUE
import android.graphics.Color.GREEN
import android.graphics.Color.RED
import android.graphics.Paint
import android.graphics.Paint.Style.FILL
import android.graphics.Paint.Style.FILL_AND_STROKE
import android.graphics.Paint.Style.STROKE
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathBuilder
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import kr.lul.android.ui.state.IconState

private val IMAGE_VECTOR = ImageVector.Builder(
    name = "SampleVector - Star",
    defaultWidth = 24.0.dp,
    defaultHeight = 24.0.dp,
    viewportWidth = 24.0f,
    viewportHeight = 24.0f
).apply {
    addPath(
        pathData = PathBuilder().apply {
            moveTo(12.0f, 2.0f)
            lineTo(15.09f, 8.26f)
            lineTo(22.0f, 9.27f)
            lineTo(17.0f, 14.14f)
            lineTo(18.18f, 21.02f)
            lineTo(12.0f, 17.77f)
            lineTo(5.82f, 21.02f)
            lineTo(7.0f, 14.14f)
            lineTo(2.0f, 9.27f)
            lineTo(8.91f, 8.26f)
            close()
        }.nodes,
        fill = Brush.linearGradient(listOf(Color.White, Color.Black))
    )
}.build()

private val IMAGE_BITMAP: Bitmap
    get() {
        val width = 100
        val height = 100
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint().apply {
            color = listOf(RED, GREEN, BLUE).random()
            style = setOf(FILL, STROKE, FILL_AND_STROKE).random()
        }
        canvas.drawCircle(width / 2f, height / 2f, width / 2f, paint)
        return bitmap
    }

private val IMAGE_PAINTER: Painter
    get() = BitmapPainter(IMAGE_BITMAP.asImageBitmap())

/**
 * TODO `preview` 패키지를 다른 모듈로 분리.
 */
class IconStateProvider : PreviewParameterProvider<IconState> {
    override val values = sequenceOf(
        IconState(imageVector = IMAGE_VECTOR, tint = tint),
        IconState(bitmap = IMAGE_BITMAP.asImageBitmap(), tint = tint),
        IconState(painter = IMAGE_PAINTER, tint = tint),
        IconState(drawable = android.R.drawable.checkbox_off_background, tint = tint)
    )

    private val tint: Color
        get() = listOf(Color.Red, Color.Green, Color.Blue).random()
}