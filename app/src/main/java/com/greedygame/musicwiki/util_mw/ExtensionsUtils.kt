package com.greedygame.musicwiki.util_mw

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment


fun logEGlobal(message: String, classObj: Any? = null) {
    if (classObj != null) {
        Log.e(classObj::class.java.simpleName, message.toString())
    } else {
        Log.e("defaultTag", message.toString())
    }
}

fun Activity.toastActivity(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toastFragment(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}
