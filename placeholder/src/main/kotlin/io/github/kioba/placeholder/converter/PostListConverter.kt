package io.github.kioba.placeholder.converter

import io.github.kioba.placeholder.post.DatabasePost
import io.github.kioba.placeholder.post.NetworkPost
import io.github.kioba.placeholder.post.Post
import io.github.kioba.platform.database.EntityConverter
import io.github.kioba.platform.network.NetworkConverter

object PostListConverter :
  NetworkConverter<List<Post>, List<NetworkPost>>,
  EntityConverter<List<Post>, List<DatabasePost>> {
  override fun List<DatabasePost>.fromEntityToDomain(): List<Post> {
    TODO("Not yet implemented")
  }

  override fun List<Post>.fromDomainToEntity(): List<DatabasePost> {
    TODO("Not yet implemented")
  }

  override fun List<NetworkPost>.fromNetworkToDomain(): List<Post> =
    map {
      Post(
        body = it.body,
        id = it.id,
        title = it.title,
        userId = it.userId,
      )
    }

}
