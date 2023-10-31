package ru.otus.otuskotlin.markeplace.springapp.models

import ru.otus.otuskotlin.marketplace.biz.RumAdProcessor
import ru.otus.otuskotlin.marketplace.logging.common.MpLoggerProvider

open class RumAppSettings(
    val appUrls: List<String> = emptyList(),
    val processor: RumAdProcessor = RumAdProcessor(),
    val logger: MpLoggerProvider
)
