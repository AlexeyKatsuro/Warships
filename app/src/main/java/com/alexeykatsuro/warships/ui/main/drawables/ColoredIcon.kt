package com.alexeykatsuro.warships.ui.main.drawables

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import com.alexeykatsuro.warships.ui.main.view.MyDrawable
import com.alexeykatsuro.warships.ui.main.view.Utils


class ColoredIcon(@ColorInt fillColor: Int, @ColorInt strokeColor: Int) : MyDrawable() {

    private var _fillColor = fillColor
    private var _strokeColor = strokeColor
    private val paintFill = Paint().apply {
        style = Paint.Style.FILL
        this.color = _fillColor
    }

    private val paintStroke = Paint().apply {
        style = Paint.Style.STROKE
        this.color = _strokeColor
        strokeWidth = Utils.STROKE_WIDTH.toFloat()
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRect(bounds, paintFill)
        canvas.drawRect(bounds, paintStroke)
    }

}