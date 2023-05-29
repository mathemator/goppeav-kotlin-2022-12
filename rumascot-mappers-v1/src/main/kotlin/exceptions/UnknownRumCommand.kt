package ru.otus.otuskotlin.marketplace.mappers.v1.exceptions

import ru.otus.otuskotlin.marketplace.common.models.CommandRum

class UnknownRumCommand(command: CommandRum) : Exception("Wrong command $command at mapping toTransport stage")
