package dev.kioba.domain.post.api.converter

import dev.kioba.domain.placeholder.user.model.Email
import dev.kioba.domain.placeholder.user.model.UserName
import dev.kioba.domain.post.api.model.Comment
import dev.kioba.domain.post.api.model.CommentId
import dev.kioba.domain.post.api.model.PostId
import dev.kioba.network.post.model.CommentResponse
import dev.kioba.persistence.comment.CommentEntity

internal fun CommentResponse.toDomain(): Comment =
  Comment(
    id = CommentId(id),
    postId = PostId(postId),
    userName = UserName(name),
    userEmail = Email(email),
    body = body,
  )


internal fun CommentEntity.toDomain(): Comment =
  Comment(
    id = CommentId(id),
    postId = PostId(postId),
    body = body,
    userName = UserName(userName),
    userEmail = Email(userEmail),
  )

internal fun Comment.toEntity(): CommentEntity =
  CommentEntity(
    id = id.value,
    postId = postId.value,
    body = body,
    userName = userName.value,
    userEmail = userEmail.value,
  )
