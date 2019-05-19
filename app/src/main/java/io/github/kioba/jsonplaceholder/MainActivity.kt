package io.github.kioba.jsonplaceholder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.kioba.core.execute
import io.github.kioba.feed.FeedFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    supportFragmentManager.execute {
      replace(android.R.id.content, FeedFragment())
    }
  }

}
