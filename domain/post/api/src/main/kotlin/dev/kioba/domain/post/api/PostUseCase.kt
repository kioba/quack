package dev.kioba.domain.post.api

import arrow.core.Either
import arrow.core.right
import dev.kioba.domain.post.api.converter.toDomain
import dev.kioba.domain.post.api.converter.toEntity
import dev.kioba.domain.post.api.model.Comment
import dev.kioba.domain.post.api.model.Post
import dev.kioba.domain.post.api.model.PostId
import dev.kioba.network.post.fetchCommentsByPost
import dev.kioba.network.post.fetchPosts
import dev.kioba.persistence.post.insertComments
import dev.kioba.persistence.post.insertPosts
import dev.kioba.persistence.post.readCommentsByPost
import dev.kioba.persistence.post.readPosts
import dev.kioba.platform.domain.EffectContext
import dev.kioba.platform.domain.syncFirstStrategy
import dev.kioba.platform.domain.useCase


public suspend fun EffectContext.syncPosts(): Either<Throwable, List<Post>> = useCase {
  syncFirstStrategy(
    sync = { fetchPosts().map { it.map { response -> response.toDomain() } } },
    insert = { insertPosts(it.map { post -> post.toEntity() }) },
    read = { readPosts().map { it.toDomain() }.right() },
  )
}


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


