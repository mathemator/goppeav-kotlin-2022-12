package ru.otus.otuskotlin.marketplace.app.common

import ru.otus.goppeav.rumascot.api.v2.models.IRequest
import ru.otus.goppeav.rumascot.api.v2.models.IResponse
import ru.otus.goppeav.rumascot.mappers.v2.fromTransport
import ru.otus.goppeav.rumascot.mappers.v2.toTransport
import ru.otus.otuskotlin.marketplace.api.logs.mapper.toLog
import ru.otus.otuskotlin.marketplace.biz.RumAdProcessor
import ru.otus.otuskotlin.marketplace.logging.common.IMpLogWrapper

suspend inline fun <reified Q : IRequest, reified R : IResponse> processV2(
    processor: RumAdProcessor,
    request: Q,
    logger: IMpLogWrapper,
    logId: String,
): R  = processor.process(logger, logId,
        fromTransport = { fromTransport(request) },
        sendResponse = { toTransport() as R },
        toLog = { toLog(logId) },
)
