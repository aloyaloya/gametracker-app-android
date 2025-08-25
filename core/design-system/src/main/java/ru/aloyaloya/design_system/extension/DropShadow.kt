package ru.aloyaloya.design_system.extension

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Applies a customizable drop shadow effect to the composable.
 *
 * This modifier draws a shadow behind the content with configurable color, blur, offset, and spread.
 * It uses a software-based blur effect and supports any [Shape] for the shadow outline.
 *
 * @param shape The [Shape] of the shadow outline. Typically matches the content's shape.
 * @param color The color of the shadow. Defaults to semi-transparent black.
 * @param blur The blur radius of the shadow. Higher values create softer shadows.
 * @param offsetX The horizontal offset of the shadow. Positive values move right.
 * @param offsetY The vertical offset of the shadow. Positive values move down.
 * @param spread The amount to expand the shadow beyond the content bounds.
 * @return A [Modifier] with the applied shadow effect.
 */
fun Modifier.dropShadow(
    shape: Shape,
    color: Color = Color.Black.copy(0.5f),
    blur: Dp = 8.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 4.dp,
    spread: Dp = 0.dp,
) = this.drawBehind {

    val spreadPx = spread.toPx()
    val shadowSize = Size(
        width = size.width + spreadPx * 2,
        height = size.height + spreadPx * 2
    )

    val shadowOutline = shape.createOutline(
        size = shadowSize,
        layoutDirection = layoutDirection,
        density = this
    )

    val paint = Paint().apply {
        this.color = color
        if (blur.toPx() > 0) {
            asFrameworkPaint().maskFilter = BlurMaskFilter(
                blur.toPx(),
                BlurMaskFilter.Blur.NORMAL
            )
        }
    }

    if (blur.toPx() > 0) {
        paint.asFrameworkPaint().apply {
            maskFilter = BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL)
        }
    }

    drawIntoCanvas { canvas ->
        canvas.save()
        canvas.translate(offsetX.toPx(), offsetY.toPx())
        canvas.drawOutline(shadowOutline, paint)
        canvas.restore()
    }
}