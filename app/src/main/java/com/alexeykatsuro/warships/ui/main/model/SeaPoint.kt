package com.alexeykatsuro.warships.ui.main.model

import androidx.annotation.IntRange

data class SeaPoint(
    @IntRange(from = 0, to = 10)
    val x: Int,
    @IntRange(from = 0, to = 10)
    val y: Int
)

infix fun Int.at(y:Int) = SeaPoint(x = this, y = y)