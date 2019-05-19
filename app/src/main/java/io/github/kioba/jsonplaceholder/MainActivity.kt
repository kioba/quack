package io.github.kioba.jsonplaceholder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.github.kioba.core.execute
import io.github.kioba.feed.FeedFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidInjection.inject(this)

    supportFragmentManager.execute {
      replace(android.R.id.content, FeedFragment())
    }
  }

}
