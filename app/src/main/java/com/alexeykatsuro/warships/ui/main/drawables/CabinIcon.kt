package com.alexeykatsuro.warships.ui.main.drawables

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.annotation.ColorInt
import com.alexeykatsuro.warships.ui.main.view.MyDrawable
import com.alexeykatsuro.warships.ui.main.view.Utils
import com.alexeykatsuro.warships.ui.main.view.Utils.STROKE_WIDTH

class CabinIcon() : MyDrawable() {

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
        canvas.drawRect(bounds, paintFill)
        canvas.drawRect(bounds, paintStroke)
    }
}