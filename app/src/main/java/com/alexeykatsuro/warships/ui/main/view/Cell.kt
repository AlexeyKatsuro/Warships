package com.alexeykatsuro.warships.ui.main.view

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.Half
import com.alexeykatsuro.warships.ui.main.view.Utils.STROKE_WIDTH
import com.alexeykatsuro.warships.ui.main.view.Utils.STROKE_WIDTH_HALF

data class Cell(
    private var x: Int,
    private var y: Int,
    private var size: Int,
    private var drawable: MyDrawable?
) {
    var bounds: Rect = Rect()

    init {
        updateBounds()
    }

    fun setPosition(x: Int, y: Int) {
        this.x = x
        this.y = y
        updateBounds()
    }

    fun getX() = x
    fun getY() = y
    fun getSize() = size

    fun setSize(size: Int) {
        this.size = size
        updateBounds()
    }
    fun setDrawable(drawable: MyDrawable){
        this.drawable = drawable
        updateBounds()
    }
    fun getDrawable(): MyDrawable? = drawable

    fun draw(canvas: Canvas) = drawable?.draw(canvas)

    private fun updateBounds() {
        val left = x * size
        val top = y * size
        val right = left + size
        val bottom = top + size
        // bounds for Drawable
        drawable?.setBounds(left, top, right, bottom)
        // bounds for Cell with additional space for drawable stroke width
        val halfWidth = STROKE_WIDTH_HALF
        bounds.set(left, top, right, bottom)
    }

}