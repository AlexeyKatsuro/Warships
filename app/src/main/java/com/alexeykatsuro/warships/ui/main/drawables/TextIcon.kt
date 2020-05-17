package com.alexeykatsuro.warships.ui.main.drawables

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.util.Log
import androidx.annotation.ColorInt
import com.alexeykatsuro.warships.ui.main.view.MyDrawable
import kotlin.math.abs
import kotlin.math.min

class TextIcon() : MyDrawable() {

    @ColorInt
    var fillColor: Int = Color.BLACK


    private val paintText = Paint().apply {
        isAntiAlias = true
        textSize = 10f
        color = fillColor
        style = Paint.Style.FILL
    }
    private val paintFill = Paint().apply {
        isAntiAlias = true
        textSize = 10f
        color = Color.GRAY
        style = Paint.Style.FILL
    }

    var text: String = ""
        set(value) {
            field = value
            resizeToFillBounds(field)
            measureText(field)
        }

    override fun onBoundsChanged() {
        Log.e("TAG", "onBoundsChanged ${bounds.toShortString()}")
        resizeToFillBounds(text)
        measureText(text)
    }

    private var textWidth = 0
    private var textHeight = 0

    private fun resizeToFillBounds(text: String) {
        paintText.textSize = getFitTextSize(
            paintText,
            bounds.width().toFloat(),
            bounds.height().toFloat() ,
            text
        )
    }

    private fun measureText(text: String) {
        textWidth = paintText.measureText(text).toInt() // Use measureText to calculate width
        textHeight = calcHeight(); // Use height from getTextBounds()
    }

    private fun getFitTextSize(
        paint: Paint,
        width: Float,
        height: Float,
        text: String
    ): Float {
        paint.textSize = 10f
        val maxSizeToFitWidth =
            (height / calcHeight() * paint.textSize)
        return min(maxSizeToFitWidth, width)
    }

    override fun draw(canvas: Canvas) {
        val centerX = bounds.centerX().toFloat()
        val centerY = bounds.centerY().toFloat()


        canvas.drawText(
            text,
            0,
            text.length,
            centerX - (textWidth/2f) ,
            bounds.top + textHeight.toFloat() - paintText.fontMetricsInt.bottom,
            paintText
        )
    }

    fun calcHeight() = paintText.fontMetricsInt.run {
        abs(top - bottom  - leading)
    }
}