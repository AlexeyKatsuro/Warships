package com.alexeykatsuro.warships.ui.main.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.alexeykatsuro.warships.R
import kotlin.math.max
import kotlin.math.min


class FieldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    companion object {
        const val TAG = "FieldView"
    }

    private val gridDrawble: Grid

    private val cellGrid: CellGrid

    private var fieldSize: Int = 0

    private var onCellClickListener: OnCellClickListener? = null

    init {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.FieldView, defStyle, 0
        )

        gridDrawble = Grid(cellSize = 50, color = Color.GRAY)

        cellGrid = CellGrid(11, 11, 50)

        a.recycle()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val w = right - left
        val h = bottom - top
        fieldSize = min(w, h)
        val cellSize = fieldSize / cellGrid.xSize
        cellGrid.updateCellSize(cellSize)
        gridDrawble.cellSize = cellSize
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom
        Log.e("TAG", "desired width $desiredWidth")
        Log.e("TAG", "desired height $desiredHeight")

        val resolveWidth = resolveSize(desiredWidth, widthMeasureSpec)
        val resolveHeight = resolveSize(desiredHeight, heightMeasureSpec)
        val size = if (MeasureSpec.getSize(widthMeasureSpec) > MeasureSpec.getSize(heightMeasureSpec)){
            resolveHeight
        } else resolveWidth
        setMeasuredDimension(size, size)
    }

    override fun getSuggestedMinimumHeight(): Int {
        val height = cellGrid.ySize * cellGrid.getCellSize()
        return max(super.getSuggestedMinimumHeight(), height)
    }

    override fun getSuggestedMinimumWidth(): Int {
        val width = cellGrid.xSize * cellGrid.getCellSize()
        return max(super.getSuggestedMinimumWidth(), width)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.v(TAG,"onDraw")
       /* gridDrawble.setBounds(
            0, 0, fieldSize, fieldSize
        )*/
        cellGrid.draw(canvas)
      //  gridDrawble.draw(canvas)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        val xP = event.x.toInt()
        val yP = event.y.toInt()
        val cell: Cell? = getCrossedCell(xP, yP)
        if (cell != null) {
            Log.d(TAG,"onTouchEvent cell $cell")
            onCellClickListener?.onClick(cell)
        }
        return super.onTouchEvent(event)
    }

    private fun getCrossedCell(x: Int, y: Int): Cell? {
        return cellGrid.getCells().find {
            it.bounds.contains(x, y)
        }
    }

    fun setOnCellClickListener(listener: OnCellClickListener?) {
        onCellClickListener = listener
    }



    interface OnCellClickListener {
        fun onClick(cell: Cell)

        companion object {
            inline operator fun invoke(crossinline onClick: (cell: Cell) -> Unit) =
                object : OnCellClickListener {
                    override fun onClick(cell: Cell) = onClick(cell)
                }
        }
    }
}
