package com.alexeykatsuro.warships.ui.main.view

import android.graphics.Canvas
import android.graphics.Color
import androidx.core.graphics.withClip
import androidx.core.graphics.withSave
import com.alexeykatsuro.warships.ui.main.drawables.ColoredIcon
import com.alexeykatsuro.warships.ui.main.drawables.TextIcon
import org.w3c.dom.Text

class CellGrid(val xSize: Int, val ySize: Int, private var cellSize: Int) : MyDrawable() {
    private val letters =  "АБВГДЕЖЗИК"
    private val cellCount = xSize * ySize
    private val list: MutableList<Cell> = MutableList(cellCount) {
        val x = it % xSize
        val y = it / ySize
        val drawable = if (x == 0 && y!=0) {
            TextIcon().apply {text = "$y"}
        } else if (y == 0 && x!=0) {
            TextIcon().apply { text = letters[x-1].toString() }
        } else if (x == 0 && y == 0){
            null
        } else {
            ColoredIcon(Color.LTGRAY,Color.WHITE)
        }
        Cell(
            x,
            y,
            cellSize,
            drawable
        )
    }

    fun updateCellSize(size: Int) {
        cellSize = size
        list.forEach {
            it.setSize(cellSize)
        }
    }

    fun getCellSize() = cellSize

    operator fun get(x: Int, y: Int): Cell {
        return list[xSize * y + x]
    }

    override fun draw(canvas: Canvas) {
        list.forEach {
            canvas.withClip(it.bounds) {
                it.draw(canvas)
            }
        }
    }

    /*  override fun setCallbacks(callbacks: Callbacks) {
          super.setCallbacks(callbacks)
          list.forEach { it.setCallbacks(callbacks) }
      }*/

    fun getCells(): List<Cell> = list

}