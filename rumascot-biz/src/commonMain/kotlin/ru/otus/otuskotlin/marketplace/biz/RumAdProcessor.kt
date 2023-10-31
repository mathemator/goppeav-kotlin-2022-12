package ru.otus.otuskotlin.marketplace.biz

import ru.otus.goppeav.rumascot.common.ContextRum
import ru.otus.goppeav.rumascot.common.models.*
import ru.otus.goppeav.rumascot.stubs.RumAdStub

class RumAdProcessor {
    suspend fun exec(ctx: ContextRum) {
        // TODO: Rewrite temporary stub solution with BIZ
        require(ctx.workMode == WorkModeRum.STUB) {
            "Currently working only in STUB mode."
        }

        ctx.state = StateRum.RUNNING
        when (ctx.command) {
            CommandRum.SEARCH -> {
                ctx.adsResponse.addAll(RumAdStub.prepareSearchList("Фигурка кошки", DealSideRum.DEMAND))
            }
            CommandRum.READ -> {
                ctx.adResponse = RumAdStub.prepareResult {
                    id = AdIdRum("id2")
                    title = "Фигурка Кошки"
                    description = "Выполненная из дерева фигурка кошки породы рэгдолл"
                }
            }
            else -> {
                ctx.adResponse = RumAdStub.get()
            }
        }
    }
}
