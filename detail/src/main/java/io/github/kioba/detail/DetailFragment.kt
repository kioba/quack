package io.github.kioba.detail

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.kioba.placeholder.json_placeholder.network_models.Post
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

  private val adapter = DetailAdapter()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View = inflater.inflate(R.layout.fragment_detail, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    detail_recycler.layoutManager = LinearLayoutManager(requireContext())
    detail_recycler.adapter = adapter

    detail_content.alpha = 0f
    ObjectAnimator.ofFloat(detail_content, View.ALPHA, 0f, 1f).apply {
      startDelay = 150
      duration = 850
      start()
    }
  }

  companion object {
    fun post(post: Post): DetailFragment {
      return DetailFragment()
    }
  }
}

