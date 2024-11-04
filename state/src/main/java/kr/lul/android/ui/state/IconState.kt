package kr.lul.android.ui.state

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * 아이콘 상태
 *
 * @see androidx.compose.material3.Icon
 */
@Immutable
@OptIn(ExperimentalUuidApi::class)
open class IconState(
    val imageVector: ImageVector? = null,
    val bitmap: ImageBitmap? = null,
    val painter: Painter? = null,
    @DrawableRes val drawable: Int? = null,
    val contentDescription: String? = null,
    val tint: Color = Color.Unspecified,
    override val key: Any = Uuid.random(),
    override val testTag: String = key.toString()
) : State {
    init {
        require(1 == listOfNotNull(imageVector, bitmap, painter, drawable).size) {
            "enable only one resource : imageVector=$imageVector, bitmap=$bitmap, painter=$painter, drawable=$drawable"
        }
    }

    open fun copy(
        imageVector: ImageVector? = this.imageVector,
        bitmap: ImageBitmap? = this.bitmap,
        painter: Painter? = this.painter,
        drawable: Int? = this.drawable,
        contentDescription: String? = this.contentDescription,
        tint: Color = this.tint
    ) = IconState(imageVector, bitmap, painter, drawable, contentDescription, tint, key, testTag)

    override fun equals(other: Any?) = this === other || (
            other is IconState &&
                    imageVector == other.imageVector &&
                    bitmap == other.bitmap &&
                    painter == other.painter &&
                    drawable == other.drawable &&
                    contentDescription == other.contentDescription &&
                    tint == other.tint &&
                    key == other.key &&
                    testTag == other.testTag
            )

    override fun hashCode(): Int {
        var result = imageVector?.hashCode() ?: 0
        result = 31 * result + (bitmap?.hashCode() ?: 0)
        result = 31 * result + (painter?.hashCode() ?: 0)
        result = 31 * result + (drawable ?: 0)
        result = 31 * result + (contentDescription?.hashCode() ?: 0)
        result = 31 * result + tint.hashCode()
        result = 31 * result + key.hashCode()
        result = 31 * result + testTag.hashCode()
        return result
    }

    override fun toString() = listOf(
        "imageVector=$imageVector",
        "bitmap=$bitmap",
        "painter=$painter",
        "drawable=$drawable",
        "contentDescription=$contentDescription",
        "tint=$tint",
        "key=$key",
        "testTag=$testTag"
    ).joinToString(", ", "IconState(", ")")
}