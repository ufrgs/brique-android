package br.ufrgs.brique.presentation.extensions

import android.util.Log
import br.ufrgs.brique.BuildConfig

fun logD(message: String) {
    if (BuildConfig.DEBUG) {
        Log.d("UFRGS", message)
    }
}

fun logE(message: String) {
    if (BuildConfig.DEBUG) {
        Log.d("UFRGS", message)
    }
}