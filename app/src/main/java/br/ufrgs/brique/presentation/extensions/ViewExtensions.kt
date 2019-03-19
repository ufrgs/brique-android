package br.ufrgs.brique.presentation.extensions

import android.view.View

fun View.hide(){
    this.visibility = View.GONE
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.isVisible() : Boolean = this.visibility == View.VISIBLE

