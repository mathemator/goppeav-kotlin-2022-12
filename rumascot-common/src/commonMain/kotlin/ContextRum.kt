package ru.otus.otuskotlin.marketplace.common

import kotlinx.datetime.Instant
import ru.otus.otuskotlin.marketplace.common.models.*
import ru.otus.otuskotlin.marketplace.common.stubs.StubsRum

data class ContextRum(
    var command: CommandRum = CommandRum.NONE,
    var state: StateRum = StateRum.NONE,
    val errors: MutableList<ErrorRum> = mutableListOf(),

    var workMode: WorkModeRum = WorkModeRum.PROD,
    var stubCase: StubsRum = StubsRum.NONE,

    var requestId: RequestIdRum = RequestIdRum.NONE,
    var timeStart: Instant = Instant.NONE,
    var adRequest: AdRum = AdRum(),
    var adFilterRequest: AdFilterRum = AdFilterRum(),
    var adResponse: AdRum = AdRum(),
    var adsResponse: MutableList<AdRum> = mutableListOf(),
)
