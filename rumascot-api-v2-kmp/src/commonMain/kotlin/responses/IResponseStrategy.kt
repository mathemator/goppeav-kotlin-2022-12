package ru.otus.goppeav.rumascot.api.v2.requests

import ru.otus.goppeav.rumascot.api.v2.IApiStrategy
import ru.otus.goppeav.rumascot.api.v2.models.IResponse

sealed interface IResponseStrategy: IApiStrategy<IResponse> {
    companion object {
        val members = listOf(
            CreateResponseStrategy,
            ReadResponseStrategy,
            UpdateResponseStrategy,
            DeleteResponseStrategy,
            SearchResponseStrategy,
        )
        val membersByDiscriminator = members.associateBy { it.discriminator }
        val membersByClazz = members.associateBy { it.clazz }
    }
}
