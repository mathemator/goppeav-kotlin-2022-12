package ru.otus.otuskotlin.marketplace.app.common

import kotlinx.datetime.Clock
import ru.otus.goppeav.rumascot.common.ContextRum
import ru.otus.otuskotlin.marketplace.biz.RumAdProcessor
import ru.otus.otuskotlin.marketplace.logging.common.IMpLogWrapper

suspend fun <T> RumAdProcessor.process(
    logger: IMpLogWrapper,
    logId: String,
    fromTransport: suspend ContextRum.() -> Unit,
    sendResponse: suspend ContextRum.() -> T,
    toLog: ContextRum.(logId: String) -> Any): T {

    val ctx = ContextRum(
        timeStart = Clock.System.now(),
    )
    return logger.doWithLogging(id = logId) {
        ctx.fromTransport()
        logger.info(
            msg = "${ctx.command} request is got",
            data = ctx.toLog("${logId}-got")
        )
        exec(ctx)
        logger.info(
            msg = "${ctx.command} request is handled",
            data = ctx.toLog("${logId}-handled")
        )
        ctx.sendResponse()
    }
}
