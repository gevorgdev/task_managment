package com.gev.task.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    private var onBackPressedCallback: (() -> Unit)? = null

    fun registerBackPressed(onBackPressedCallback: () -> Unit) {
        this.onBackPressedCallback = onBackPressedCallback
    }

    fun unRegisterBackPressed() {
        mBackPressedCallback.remove()
        onBackPressedCallback = null
    }

    private val mBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            remove()
            onBackPressedCallback?.let {
                it()
            } ?: run {
                requireActivity().onBackPressed()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this, mBackPressedCallback)
    }

    protected fun showLoader(show: Boolean) {

    }
}