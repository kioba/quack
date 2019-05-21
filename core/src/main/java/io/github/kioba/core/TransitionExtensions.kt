package io.github.kioba.core

import android.view.animation.Interpolator
import androidx.transition.TransitionSet

fun TransitionSet.setTransitionInterpolator(interpolator: Interpolator): TransitionSet {
  (0 until transitionCount)
    .map(::getTransitionAt)
    .forEach { transition -> transition.interpolator = interpolator }

  return this
}
