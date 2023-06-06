package io.github.kioba.core

import android.view.View
import com.google.android.material.snackbar.Snackbar


fun View.gone() {
  visibility = View.GONE
}

fun View.hide() {
  visibility = View.INVISIBLE
}

fun View.show() {
  visibility = View.VISIBLE
}

fun View.snack(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
