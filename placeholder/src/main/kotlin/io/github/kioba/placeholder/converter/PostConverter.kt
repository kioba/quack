package io.github.kioba.placeholder.converter

import io.github.kioba.placeholder.post.DatabasePost
import io.github.kioba.placeholder.post.Post
import io.github.kioba.platform.database.EntityConverter
import io.github.kioba.platform.network.NetworkConverter

object PostConverter :
  NetworkConverter<Post, Post>,
  EntityConverter<Post, DatabasePost> {
  override fun DatabasePost.fromEntityToDomain(): Post {
    TODO("Not yet implemented")
  }

  override fun Post.fromDomainToEntity(): DatabasePost {
    TODO("Not yet implemented")
  }

  override fun Post.fromNetworkToDomain(): Post =
    this

}
