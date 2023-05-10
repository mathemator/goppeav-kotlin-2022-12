package ru.otus.otuskotlin.marketplace.mappers.v2

import ru.otus.otuskotlin.marketplace.api.v2.models.*
import ru.otus.otuskotlin.marketplace.common.ContextRum
import ru.otus.otuskotlin.marketplace.common.models.*
import ru.otus.otuskotlin.marketplace.mappers.v2.exceptions.UnknownCommandRum

fun ContextRum.toTransport(): IResponse = when (val cmd = command) {
    CommandRum.CREATE -> toTransportCreate()
    CommandRum.READ -> toTransportRead()
    CommandRum.UPDATE -> toTransportUpdate()
    CommandRum.DELETE -> toTransportDelete()
    CommandRum.SEARCH -> toTransportSearch()
    CommandRum.NONE -> throw UnknownCommandRum(cmd)
}

fun ContextRum.toTransportCreate() = AdCreateResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == StateRum.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    ad = adResponse.toTransport()
)

fun ContextRum.toTransportRead() = AdReadResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == StateRum.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    ad = adResponse.toTransport()
)

fun ContextRum.toTransportUpdate() = AdUpdateResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == StateRum.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    ad = adResponse.toTransport()
)

fun ContextRum.toTransportDelete() = AdDeleteResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == StateRum.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    ad = adResponse.toTransport()
)

fun ContextRum.toTransportSearch() = AdSearchResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == StateRum.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    ads = adsResponse.toTransportAds()
)

fun List<AdRum>.toTransportAds(): List<AdResponseObject>? = this
    .map { it.toTransport() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun AdRum.toTransport(): AdResponseObject = AdResponseObject(
    id = id.takeIf { it != AdIdRum.NONE }?.asString(),
    title = title.takeIf { it.isNotBlank() },
    description = description.takeIf { it.isNotBlank() },
    ownerId = ownerId?.asLong(),
    dealSide = dealSide.toTransport(),
    visibility = visibility.toTransport(),
    permissions = permissionsClient.toTransport(),
    tags = tags?.toTransport()
)

private fun Set<AdPermissionClientRum>.toTransport(): Set<AdPermissions>? = this
    .map { it.toTransport() }
    .toSet()
    .takeIf { it.isNotEmpty() }

private fun AdPermissionClientRum.toTransport() = when (this) {
    AdPermissionClientRum.READ -> AdPermissions.READ
    AdPermissionClientRum.UPDATE -> AdPermissions.UPDATE
    AdPermissionClientRum.DELETE -> AdPermissions.DELETE
}

private fun VisibilityRum.toTransport(): AdVisibility? = when (this) {
    VisibilityRum.VISIBLE_PUBLIC -> AdVisibility.PUBLIC
    VisibilityRum.VISIBLE_TO_GROUP -> AdVisibility.REGISTERED_ONLY
    VisibilityRum.NONE -> null
}

private fun DealSideRum.toTransport(): DealSide? = when (this) {
    DealSideRum.DEMAND -> DealSide.DEMAND
    DealSideRum.PROPOSAL -> DealSide.PROPOSAL
    DealSideRum.NONE -> null
}

private fun List<ErrorRum>.toTransportErrors(): List<Error>? = this
    .map { it.toTransport() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun ErrorRum.toTransport() = Error(
    code = code.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() },
)

private fun List<TagRum>.toTransport(): List<Tag>? = this
    .map { it.toTransport() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun TagRum.toTransport() = Tag(
    id = id,
    name = name,
)
