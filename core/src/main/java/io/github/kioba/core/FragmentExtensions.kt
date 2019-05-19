package io.github.kioba.core

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


fun FragmentManager.execute(transaction: FragmentTransaction.() -> Unit) {
  beginTransaction().apply(transaction).commit()
}
