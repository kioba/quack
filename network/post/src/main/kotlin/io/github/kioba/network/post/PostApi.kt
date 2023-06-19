package io.github.kioba.network.post

import arrow.core.Either
import io.github.kioba.network.post.model.PostResponse
import io.github.kioba.network.post.request.postRequest
import io.github.kioba.platform.network.NetworkScope

public suspend fun NetworkScope.fetchPosts(): Either<Throwable, List<PostResponse>> =
  postRequest { getPosts() }
    .mapLeft { Exception(it) }
