package com.alexeykatsuro.warships.ui.main.view

import android.graphics.*
import android.graphics.PixelFormat.OPAQUE
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.Px
import com.alexeykatsuro.warships.ui.main.view.Utils.STROKE_WIDTH

class Grid @JvmOverloads constructor(
    @Px var cellSize: Int,
    @ColorInt var color: Int = Color.GRAY,
    @Px var strokeWidth: Int = STROKE_WIDTH
) : Drawable() {

    val paint = Paint().also {
        it.flags = Paint.ANTI_ALIAS_FLAG
        it.style = Paint.Style.STROKE
        it.strokeWidth = strokeWidth.toFloat()
        it.color = color

    }
    private val path = Path()

    override fun draw(canvas: Canvas) {
        setUpGridPath()
        canvas.drawPath(path, paint)
    }

    private fun setUpGridPath() {
        val width = bounds.width().toFloat()
        val height = bounds.height().toFloat()

        var x = bounds.left.toFloat()
        var y = bounds.top.toFloat()
        while (x <= width) {
            path.moveTo(+x, 0f)
            path.lineTo(x, height)
            x += cellSize
        }
        while (y <= height) {
            path.moveTo(0f, y)
            path.lineTo(width, y)
            y += cellSize
        }
    }

    override fun setAlpha(alpha: Int) = Unit

    override fun getOpacity(): Int = OPAQUE

    override fun setColorFilter(colorFilter: ColorFilter?) = Unit
}