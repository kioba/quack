package dev.kioba.network.post

import arrow.core.Either
import dev.kioba.network.post.model.CommentResponse
import dev.kioba.network.post.model.PostResponse
import dev.kioba.network.post.request.postRequest
import dev.kioba.platform.network.NetworkScope

public suspend fun NetworkScope.fetchPosts(): Either<Throwable, List<PostResponse>> =
  postRequest { getPosts() }
    .mapLeft { Exception(it) }

public suspend fun NetworkScope.fetchCommentsByPost(
  postId: Long,
): Either<Throwable, List<CommentResponse>> =
  postRequest { getCommentsByPost(postId) }
    .mapLeft { Exception(it) }
