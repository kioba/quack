package io.github.kioba.core

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * wraps the FragmentTransaction begin and commit behind a higher order function
 */
inline fun FragmentManager.execute(transaction: FragmentTransaction.() -> Unit) {
  beginTransaction().apply(transaction).commit()
}
