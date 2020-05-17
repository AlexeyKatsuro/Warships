package com.alexeykatsuro.warships.ui.main.utils

import android.graphics.Rect

inline class Bounds(val rect: Rect= Rect(0,0,0,0)) {

    val left: Int get() = rect.left
    val top: Int get() = rect.top
    val right: Int get() = rect.right
    val bottom: Int get() = rect.bottom

}