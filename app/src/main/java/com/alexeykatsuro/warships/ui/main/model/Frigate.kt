package com.alexeykatsuro.warships.ui.main.model

import androidx.annotation.IntRange

data class Frigate(val cabins: List<Cabin>) {

    val size: Int = cabins.size
    val isDestroyed = cabins.all { it.isDestroyed }

    class Builder(
        @IntRange(from = 0, to = 4)
        val frigateSize: Int
    ) {
        private val cabinList: MutableList<Cabin> = mutableListOf()

        fun add(cabin: Cabin) {
            cabinList.add(cabin)
            if (cabinList.size > 2) {
                check(cabinList.all { it.x == cabin.x } || cabinList.all { it.y == cabin.y }) {
                    "Cabins in Frigate should be lay sequentially"
                }
            }
        }

        fun build(): Frigate {
            check(frigateSize == cabinList.size) { "Frigate should has $frigateSize size" }
            return Frigate(cabinList)
        }

    }
}