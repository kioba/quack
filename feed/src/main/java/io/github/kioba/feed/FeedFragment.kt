package io.github.kioba.feed

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

  private val adapter = FeedAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View =
    inflater.inflate(R.layout.fragment_feed, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    feed_recycler.layoutManager = LinearLayoutManager(requireContext())
    feed_recycler.adapter = adapter
    feed_recycler.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))

    feed_bar.setNavigationOnClickListener {
      AboutBottomSheet().show(childFragmentManager, "AboutBottomSheet")
    }
  }
}

val Context.density: Float
  get() = resources.displayMetrics.density

fun Context.dip(dip: Int): Int {
  val scale = density
  return (dip * scale + 0.5f).toInt()
}
