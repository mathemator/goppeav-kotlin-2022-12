package ru.otus.otuskotlin.marketplace.mappers.v1

import ru.otus.otuskotlin.marketplace.api.v1.models.*
import ru.otus.otuskotlin.marketplace.common.ContextRum
import ru.otus.otuskotlin.marketplace.common.models.*
import ru.otus.otuskotlin.marketplace.common.models.WorkModeRum
import ru.otus.otuskotlin.marketplace.common.stubs.StubsRum
import ru.otus.otuskotlin.marketplace.mappers.v1.exceptions.UnknownRequestClass

fun ContextRum.fromTransport(request: IRequest) = when (request) {
    is AdCreateRequest -> fromTransport(request)
    is AdReadRequest -> fromTransport(request)
    is AdUpdateRequest -> fromTransport(request)
    is AdDeleteRequest -> fromTransport(request)
    is AdSearchRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request.javaClass)
}

private fun String?.toAdId() = this?.let { AdIdRum(it) } ?: AdIdRum.NONE
private fun String?.toAdWithId() = AdRum(id = this.toAdId())
private fun IRequest?.requestId() = this?.requestId?.let { RequestIdRum(it) } ?: RequestIdRum.NONE

private fun AdDebug?.transportToWorkMode(): WorkModeRum = when (this?.mode) {
    AdRequestDebugMode.PROD -> WorkModeRum.PROD
    AdRequestDebugMode.TEST -> WorkModeRum.TEST
    AdRequestDebugMode.STUB -> WorkModeRum.STUB
    null -> WorkModeRum.PROD
}

private fun AdDebug?.transportToStubCase(): StubsRum = when (this?.stub) {
    AdRequestDebugStubs.SUCCESS -> StubsRum.SUCCESS
    AdRequestDebugStubs.NOT_FOUND -> StubsRum.NOT_FOUND
    AdRequestDebugStubs.BAD_ID -> StubsRum.BAD_ID
    AdRequestDebugStubs.BAD_INFO -> StubsRum.BAD_INFO
    AdRequestDebugStubs.CANNOT_DELETE -> StubsRum.CANNOT_DELETE
    AdRequestDebugStubs.BAD_SEARCH_STRING -> StubsRum.BAD_SEARCH_STRING
    null -> StubsRum.NONE
}

fun ContextRum.fromTransport(request: AdCreateRequest) {
    command = CommandRum.CREATE
    requestId = request.requestId()
    adRequest = request.ad?.toInternal() ?: AdRum()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun ContextRum.fromTransport(request: AdReadRequest) {
    command = CommandRum.READ
    requestId = request.requestId()
    adRequest = request.ad?.id.toAdWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun ContextRum.fromTransport(request: AdUpdateRequest) {
    command = CommandRum.UPDATE
    requestId = request.requestId()
    adRequest = request.ad?.toInternal() ?: AdRum()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun ContextRum.fromTransport(request: AdDeleteRequest) {
    command = CommandRum.DELETE
    requestId = request.requestId()
    adRequest = request.ad?.id.toAdWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun ContextRum.fromTransport(request: AdSearchRequest) {
    command = CommandRum.SEARCH
    requestId = request.requestId()
    adFilterRequest = request.adFilter.toInternal()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun AdSearchFilter?.toInternal(): AdFilterRum = AdFilterRum(
    searchString = this?.searchString ?: ""
)

private fun AdCreateObject.toInternal(): AdRum = AdRum(
    title = this.title ?: "",
    description = this.description ?: "",
    dealSide = this.dealSide.fromTransport(),
    visibility = this.visibility.fromTransport(),
    tags = this.tags?.fromTransport()
)

private fun AdUpdateObject.toInternal(): AdRum = AdRum(
    id = this.id.toAdId(),
    title = this.title ?: "",
    description = this.description ?: "",
    dealSide = this.dealSide.fromTransport(),
    visibility = this.visibility.fromTransport(),
    tags = this.tags?.fromTransport()
)

private fun AdVisibility?.fromTransport(): VisibilityRum = when (this) {
    AdVisibility.PUBLIC -> VisibilityRum.VISIBLE_PUBLIC
    AdVisibility.REGISTERED_ONLY -> VisibilityRum.VISIBLE_TO_GROUP
    null -> VisibilityRum.NONE
}

private fun DealSide?.fromTransport(): DealSideRum = when (this) {
    DealSide.DEMAND -> DealSideRum.DEMAND
    DealSide.PROPOSAL -> DealSideRum.PROPOSAL
    null -> DealSideRum.NONE
}


private fun List<Tag>.fromTransport(): List<TagRum>? = this
    .map { it.fromTransport() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun Tag.fromTransport() = TagRum(
    id = id,
    name = name,
)

