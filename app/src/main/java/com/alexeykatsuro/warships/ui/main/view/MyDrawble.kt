package com.alexeykatsuro.warships.ui.main.view

import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.annotation.CallSuper
import java.lang.ref.WeakReference
import kotlin.properties.Delegates

abstract class MyDrawable {

   // private var callbacks: Callbacks? = null

    protected val bounds: Rect = Rect()

    fun setBounds(left: Int, top: Int, right: Int, bottom: Int ){
        if (!bounds.equals(left, top, right, bottom)) {
            bounds.set(left, top, right, bottom)
            onBoundsChanged()
        }
    }


    open fun onBoundsChanged() {}


    abstract fun draw(canvas: Canvas)

   /* protected fun invalidateSelf(){
        callbacks?.invalidateSelf()
    }*/

   /* @CallSuper
    open fun setCallbacks(callbacks: Callbacks){
        this.callbacks = (callbacks)
    }*/

    interface Callbacks{
        fun invalidateSelf()

        companion object {
            inline operator fun invoke(crossinline action: () -> Unit) =
                object : MyDrawable.Callbacks {
                    override fun invalidateSelf() = action()
                }
        }
    }

}