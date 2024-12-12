package dev.kioba.feature.feed.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.kioba.android.database.buildDatabaseScope
import dev.kioba.feature.feed.data.FeedEffects
import dev.kioba.feature.feed.data.feedAnchor
import dev.kioba.platform.android.compose.renderWith

public class FeedFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View =
    renderWith(scope = {
      feedAnchor(FeedEffects(buildDatabaseScope(requireContext())))
    }) { state ->
      FeedUi(state = state)
    }
}
