package io.arkstud.ejerciciokokonut.ui.common

import android.arch.lifecycle.Observer
import android.content.Context
import android.view.View
import android.widget.Toast

interface BaseView {

    fun loadingObserver(view: View) : Observer<Boolean> = Observer {
        view.visibility = if(it == true) View.VISIBLE else View.INVISIBLE
    }

    fun messageObserver(context: Context) : Observer<String?> = Observer {
        Toast.makeText(context, it ?: "", Toast.LENGTH_SHORT).show()
    }

}