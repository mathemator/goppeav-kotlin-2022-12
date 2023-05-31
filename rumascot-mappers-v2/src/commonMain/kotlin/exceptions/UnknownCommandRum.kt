package ru.otus.goppeav.rumascot.mappers.v2.exceptions

import ru.otus.goppeav.rumascot.common.models.CommandRum

class UnknownCommandRum(command: CommandRum) : Throwable("Wrong command $command at mapping toTransport stage")
