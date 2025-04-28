package dev.kioba.domain.post.api

import arrow.core.Either
import arrow.core.right
import dev.kioba.domain.post.api.converter.toDomain
import dev.kioba.domain.post.api.converter.toEntity
import dev.kioba.domain.post.api.model.Post
import dev.kioba.network.post.fetchPosts
import dev.kioba.persistence.post.insertPosts
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
