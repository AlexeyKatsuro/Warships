package com.alexeykatsuro.warships.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alexeykatsuro.warships.R
import com.alexeykatsuro.warships.ui.main.drawables.CabinDestroyedIcon
import com.alexeykatsuro.warships.ui.main.drawables.CabinIcon
import com.alexeykatsuro.warships.ui.main.drawables.ColoredIcon
import com.alexeykatsuro.warships.ui.main.drawables.DotIcon
import com.alexeykatsuro.warships.ui.main.view.FieldView
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        field.apply {
            setOnCellClickListener(FieldView.OnCellClickListener { cell ->

                val drawable = when (cell.getDrawable()) {
                    is ColoredIcon -> DotIcon()
                    is DotIcon -> CabinIcon()
                    is CabinIcon -> CabinDestroyedIcon()
                    is CabinDestroyedIcon -> DotIcon()
                    else -> null
                }
                if (drawable!=null) {
                    cell.setDrawable(drawable)
                    field.invalidate()
                }
            })
        }
        // TODO: Use the ViewModel
    }

}