package ru.otus.goppeav.rumascot.common.helpers

import ru.otus.goppeav.rumascot.common.ContextRum
import ru.otus.goppeav.rumascot.common.models.CommandRum.*

fun ContextRum.isUpdatableCommand() =
    this.command in listOf(CREATE, UPDATE, DELETE)
