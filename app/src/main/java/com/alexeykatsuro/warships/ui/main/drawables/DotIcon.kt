package com.alexeykatsuro.warships.ui.main.drawables

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.annotation.ColorInt
import com.alexeykatsuro.warships.ui.main.view.MyDrawable
import com.alexeykatsuro.warships.ui.main.view.Utils

class DotIcon : MyDrawable() {

    @ColorInt
    var fillColor: Int = Color.BLACK

    var radius = Utils.dpToPx(3f)

    private val paint = Paint().apply {
        style = Paint.Style.FILL
        color = fillColor
    }

    override fun draw(canvas: Canvas) {
        val centerX = bounds.centerX().toFloat()
        val centerY = bounds.centerY().toFloat()
        canvas.drawCircle(centerX, centerY, radius, paint)
    }
}