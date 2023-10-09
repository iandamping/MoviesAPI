package com.ian.app.muviepedia.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseFragmentViewBinding<out VB : ViewBinding> : Fragment() {
    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    private fun getCoroutineErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.e("TAG 2", "An error happened: ${e.message}")
    }

    protected fun consumeSuspend(func: suspend CoroutineScope.() -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch(getCoroutineErrorHandler()) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                func.invoke(this)
            }
        }
    }

    protected fun overrideFragmentBackPressed(func: () -> Unit) {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() { // Handle the back button event
                    func.invoke()
                }
            }
        )
    }

    abstract fun injectingDagger()

    abstract fun initView()

    abstract fun viewCreated()

    override fun onAttach(context: Context) {
        injectingDagger()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
