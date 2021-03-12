package com.pablogarcia.marvel.ui.custom

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Custom recycler view.
 *
 * Checks when last position is reached by scrolling and invokes callback Unit if is initialized
 */
class LoadDataRecyclerView : RecyclerView {

    lateinit var callback: () -> Unit

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    /**
     * Set linear layout manager to recycler view
     * Check when recycler view reach the las position in order to execute callback function
     */
    private fun init() {

        layoutManager = LinearLayoutManager(context)
        addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!canScrollVertically(1)) {
                    if (::callback.isInitialized) {
                        callback.invoke()
                    }
                }
            }
        })
    }
}
