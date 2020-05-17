package com.alexeykatsuro.warships.ui.main.view

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.annotation.Px
import kotlin.random.Random

object Utils {

    const val STROKE_WIDTH = 6
    const val STROKE_WIDTH_HALF = 3

    @Px
    fun dpToPx(dip: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dip, Resources.getSystem().displayMetrics
        )
    }

    @ColorInt
    fun randomColor(): Int {
        val rnd = Random.Default
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}

fun Rect.equals(left: Int, top: Int, right: Int, bottom: Int) =
    this.left == left && this.top == top && this.right == right && this.bottom == bottom