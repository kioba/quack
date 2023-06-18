package io.github.kioba.placeholder.converter

import io.github.kioba.placeholder.network.model.Comment
import io.github.kioba.placeholder.persistence.model.DatabaseComment
import io.github.kioba.platform.database.EntityConverter
import io.github.kioba.platform.network.NetworkConverter

object CommentListConverter :
  NetworkConverter<List<Comment>, List<Comment>>,
  EntityConverter<List<Comment>, List<DatabaseComment>> {
  override fun List<DatabaseComment>.fromEntityToDomain(): List<Comment> {
    TODO("Not yet implemented")
  }

  override fun List<Comment>.fromDomainToEntity(): List<DatabaseComment> {
    TODO("Not yet implemented")
  }

  override fun List<Comment>.fromNetworkToDomain(): List<Comment> =
    this
}