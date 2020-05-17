package com.alexeykatsuro.warships.ui.main.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.res.use
import com.alexeykatsuro.warships.R
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class MyTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {


    var text = "A"

    @ColorInt
    var fillColor: Int = Color.BLACK


    private val paintText = Paint().apply {
        isAntiAlias = true
        textSize = 100f
        color = fillColor
        style = Paint.Style.FILL
    }
    private val paintFill = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor("#80000000")
        style = Paint.Style.FILL
    }

    private val paintStroke = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor("#8000FF00")
        style = Paint.Style.STROKE
        strokeWidth = 20f
    }

    private var textWidth = 0
    private var textHeight = 0

    init {
        context.obtainStyledAttributes(attrs, R.styleable.MyTextView).use {
            text = it.getString(R.styleable.MyTextView_android_text).orEmpty()
            paintText.textSize = it.getDimensionPixelSize(R.styleable.MyTextView_android_textSize,100).toFloat()
        }
    }

    private fun measureText(text: String) {
        val textBounds = Rect()
        paintText.getTextBounds(text, 0, text.length, textBounds)
        textWidth = paintText.measureText(text).toInt() // Use measureText to calculate width
        val fm = paintText.fontMetricsInt
        textHeight = calcHeight()
        Log.e("TAG", "measureText ${paintText.measureText(text)}")
        Log.e("TAG", "getTextBounds ${textBounds.width()}")

    }

    private fun resizeToFillBounds(text: String, width: Float, height: Float) {
        paintText.textSize = getFitTextSize(
            paintText,
            width,
            height,
            text
        )
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

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        resizeToFillBounds(text, (right - left).toFloat(), (bottom-top).toFloat())
        measureText(text)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        //canvas.drawARGB(100,100,100,100)
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintFill)
        val xC = width/2f
        canvas.drawText(
            text,
            0,
            text.length,
            xC - (textWidth/2f) + paddingStart,
            textHeight.toFloat() - paintText.fontMetricsInt.bottom,
            paintText
        )
       // canvas.drawLine(paintText.fontMetricsInt.ascent.toFloat())
       // canvas.drawLine(paintText.fontMetricsInt.descent.toFloat())
    }

    fun Canvas.drawLine(dy: Float) {
        drawLine(
            0f,
            textHeight.toFloat() - paintText.fontMetricsInt.descent + dy,
            textWidth.toFloat(),
            textHeight.toFloat() - paintText.fontMetricsInt.descent + dy,
            paintStroke
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.e("TAG", "onMeasure ----------------------")
        Log.e("onMeasure w", MeasureSpec.toString(widthMeasureSpec));
        Log.e("onMeasure h", MeasureSpec.toString(heightMeasureSpec));
        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)
        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)

        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom
        Log.e("TAG", "desired width $desiredWidth")
        Log.e("TAG", "desired height $desiredHeight")

        val resolveWidth = resolveSize(desiredWidth, widthMeasureSpec)
        val resolveHeight = resolveSize(desiredHeight, heightMeasureSpec)
        Log.e("TAG", "resolve width $resolveWidth")
        Log.e("TAG", "resolve height $resolveHeight")
        setMeasuredDimension(resolveWidth, resolveHeight)
    }

    override fun getSuggestedMinimumHeight(): Int {
        return max(super.getSuggestedMinimumHeight(), calcHeight())
    }

    override fun getSuggestedMinimumWidth(): Int {
        return max(super.getSuggestedMinimumWidth(), paintText.measureText(text).toInt())
    }

    fun calcHeight() = paintText.fontMetricsInt.run {
        abs(top - bottom  - leading)
    }
}