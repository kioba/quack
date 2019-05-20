package io.github.kioba.detail

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class DetailFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View = inflater.inflate(R.layout.fragment_detail, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val content = view.findViewById<View>(R.id.content).also { it.alpha = 0f }

    ObjectAnimator.ofFloat(content, View.ALPHA, 0f, 1f).apply {
      startDelay = 50
      duration = 150
      start()
    }
  }
}
