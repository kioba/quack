package dev.kioba.domain.post.api

import arrow.core.Either
import arrow.core.right
import dev.kioba.domain.post.api.converter.toDomain
import dev.kioba.domain.post.api.converter.toEntity
import dev.kioba.domain.post.api.model.Comment
import dev.kioba.domain.post.api.model.PostId
import dev.kioba.network.post.fetchCommentsByPost
import dev.kioba.persistence.post.insertComments
import dev.kioba.persistence.post.readCommentsByPost
import dev.kioba.platform.domain.EffectContext
import dev.kioba.platform.domain.syncFirstStrategy
import dev.kioba.platform.domain.useCase


public suspend fun EffectContext.syncComments(
  postId: PostId,
): Either<Throwable, List<Comment>> = useCase {
  syncFirstStrategy(
    sync = {
      fetchCommentsByPost(postId.value)
        .map { comments -> comments.map { response -> response.toDomain() } }
    },
    insert = { insertComments(it.map { comment -> comment.toEntity() }) },
    read = { readCommentsByPost(postId.value).map { entity -> entity.toDomain() }.right() },
  )
}


