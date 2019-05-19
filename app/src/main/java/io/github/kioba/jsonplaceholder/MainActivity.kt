package io.github.kioba.jsonplaceholder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import io.github.kioba.feed.FeedFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    supportFragmentManager.execute {
      replace(android.R.id.content, FeedFragment())
    }
  }

}

private fun FragmentManager.execute(transaction: FragmentTransaction.() -> Unit) {
  beginTransaction().apply(transaction).commit()
}
