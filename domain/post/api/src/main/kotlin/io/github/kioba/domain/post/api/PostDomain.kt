package io.github.kioba.domain.post.api

import arrow.core.Either
import arrow.core.right
import io.github.kioba.domain.post.api.converter.toDomain
import io.github.kioba.domain.post.api.converter.toEntity
import io.github.kioba.domain.post.api.model.Post
import io.github.kioba.network.post.fetchPosts
import io.github.kioba.persistence.post.insertPosts
import io.github.kioba.persistence.post.readPosts
import io.github.kioba.persistence.post.streamPosts
import io.github.kioba.platform.domain.DomainScope
import io.github.kioba.platform.domain.cacheOnlyFlowableStrategy
import io.github.kioba.platform.domain.syncFirstStrategy
import io.github.kioba.platform.domain.useCase
import io.github.kioba.platform.domain.useCaseFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


public suspend fun DomainScope.syncPosts(): Either<Throwable, List<Post>> =
  useCase {
    syncFirstStrategy(
      sync = { fetchPosts().map { it.map { response -> response.toDomain() } } },
      insert = { insertPosts(it.map { post -> post.toEntity() }) },
      read = { readPosts().map { it.toDomain() }.right() },
    )
  }

public fun DomainScope.listenPosts(): Flow<List<Post>> =
  useCaseFlow {
    cacheOnlyFlowableStrategy {
      streamPosts()
        .map {
          it.map { entity -> entity.toDomain() }
        }
    }
  }
    .catch { emit(emptyList()) }
