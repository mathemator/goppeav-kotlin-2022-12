package ru.otus.goppeav.rumascot.mappers.v1.exceptions

import ru.otus.goppeav.rumascot.common.models.CommandRum

class UnknownRumCommand(command: CommandRum) : Exception("Wrong command $command at mapping toTransport stage")
