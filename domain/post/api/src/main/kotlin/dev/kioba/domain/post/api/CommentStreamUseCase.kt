package dev.kioba.domain.post.api

import dev.kioba.domain.post.api.converter.toDomain
import dev.kioba.domain.post.api.model.Comment
import dev.kioba.domain.post.api.model.PostId
import dev.kioba.persistence.post.streamCommentsByPost
import dev.kioba.platform.domain.EffectContext
import dev.kioba.platform.domain.cacheOnlyFlowableStrategy
import dev.kioba.platform.domain.useCaseFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

public fun EffectContext.listenCommentsByPost(
  id: PostId,
): Flow<List<Comment>> =
  useCaseFlow {
    cacheOnlyFlowableStrategy {
      streamCommentsByPost(id.value)
        .map { entities ->
          entities.map { entity -> entity.toDomain() }
        }
    }
  }
    .catch { emit(emptyList()) }
