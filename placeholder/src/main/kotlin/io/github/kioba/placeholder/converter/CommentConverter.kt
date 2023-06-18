package io.github.kioba.placeholder.converter

import io.github.kioba.placeholder.network.model.Comment
import io.github.kioba.placeholder.persistence.model.DatabaseComment
import io.github.kioba.platform.database.EntityConverter
import io.github.kioba.platform.network.NetworkConverter

object CommentConverter :
  NetworkConverter<Comment, Comment>,
  EntityConverter<Comment, DatabaseComment> {
  override fun DatabaseComment.fromEntityToDomain(): Comment {
    TODO("Not yet implemented")
  }

  override fun Comment.fromDomainToEntity(): DatabaseComment {
    TODO("Not yet implemented")
  }

  override fun Comment.fromNetworkToDomain(): Comment =
    this

}

