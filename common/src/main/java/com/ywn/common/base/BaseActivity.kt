package com.ywn.common.base

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.ywn.common.R
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    protected abstract val layoutId: Int

    fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        this.setContentView(layoutId)


//        add(HttpErrorEvent.subscribe(???({ this.handle(it) })))

        main(savedInstanceState)
    }



    /*private fun handle(event: HttpErrorEvent.Event) {
        //        Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
    }*/

    protected abstract fun main(savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }



    fun showSnack(
        message: String, actionMessage: Int, onClickListener: View.OnClickListener,
        duration: Int
    ) {
        val rootContent = findViewById<View>(android.R.id.content)
        Snackbar.make(rootContent, message, duration)
            .setAction(actionMessage, onClickListener)
            .setActionTextColor(resources.getColor(R.color.colorPrimary))
            .show()
    }

    fun showSnack(
        message: String, actionMessage: String, onClickListener: View.OnClickListener,
        duration: Int
    ) {
        val rootContent = findViewById<View>(android.R.id.content)
        Snackbar.make(rootContent, message, duration)
            .setAction(actionMessage, onClickListener)
            .setActionTextColor(resources.getColor(android.R.color.white))
            .show()
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}