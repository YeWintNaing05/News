package com.ywn.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.ywn.common.R
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment() {

    private var compositeDisposable: CompositeDisposable? = null

    @get:LayoutRes
    abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compositeDisposable = CompositeDisposable()

    }


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResource, container, false)
    }


    fun showSnack(
        message: String, actionMessage: Int, onClickListener: View.OnClickListener,
        duration: Int
    ) {
        val rootContent = activity?.findViewById<View>(android.R.id.content)
        Snackbar.make(rootContent!!, message, duration)
            .setAction(actionMessage, onClickListener)
            .setActionTextColor(resources.getColor(R.color.colorPrimary))
            .show()
    }

    fun showSnack(
        message: String, actionMessage: String, onClickListener: View.OnClickListener,
        duration: Int
    ) {
        val rootContent = activity?.findViewById<View>(android.R.id.content)
        Snackbar.make(rootContent!!, message, duration)
            .setAction(actionMessage, onClickListener)
            .setActionTextColor(resources.getColor(R.color.colorPrimary))
            .show()
    }

    fun hideKeyboard() {
        val view = activity?.currentFocus
        if (view != null) {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun add(disposable: Disposable) {
        if (compositeDisposable != null) {
            compositeDisposable!!.add(disposable)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.clear()
        }


    }
}