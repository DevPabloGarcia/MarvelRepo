package com.pablogarcia.marvel.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
    }

    /**
     * Called when view is created in order to bind views
     */
    abstract fun bindViews(view: View)
}
