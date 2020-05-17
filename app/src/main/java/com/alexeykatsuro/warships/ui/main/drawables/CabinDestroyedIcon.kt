package com.alexeykatsuro.warships.ui.main.drawables

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.annotation.ColorInt
import com.alexeykatsuro.warships.ui.main.view.MyDrawable
import com.alexeykatsuro.warships.ui.main.view.Utils
import com.alexeykatsuro.warships.ui.main.view.Utils.STROKE_WIDTH

class CabinDestroyedIcon : MyDrawable() {

    private val path = Path()

    @ColorInt
    var fillColor: Int = Color.WHITE
    var borderColor: Int = Color.BLACK

    private val paintFill = Paint().apply {
        style = Paint.Style.FILL
        color = fillColor
    }
    private val paintStroke = Paint().apply {
        style = Paint.Style.STROKE
        color = borderColor
        strokeWidth  = Utils.STROKE_WIDTH.toFloat()
    }


    override fun draw(canvas: Canvas) {
        // Draw \
        path.moveTo(bounds.left.toFloat(), bounds.top.toFloat())
        path.lineTo(bounds.right.toFloat(), bounds.bottom.toFloat())
        // Draw /
        path.moveTo(bounds.right.toFloat(), bounds.top.toFloat())
        path.lineTo(bounds.left.toFloat(), bounds.bottom.toFloat())
        canvas.drawPath(path, paintStroke)
        canvas.drawRect(bounds, paintFill)
    }
}