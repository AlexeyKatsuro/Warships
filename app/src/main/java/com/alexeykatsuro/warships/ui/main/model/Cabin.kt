package com.alexeykatsuro.warships.ui.main.model

data class Cabin(
    val point: SeaPoint,
    var isDestroyed: Boolean = false
) {
    val x = point.x
    val y = point.y
}