package ru.otus.otuskotlin.marketplace.mappers.v1.exceptions

import ru.otus.otuskotlin.marketplace.common.models.CommandRum

class UnknownMkplCommand(command: CommandRum) : Throwable("Wrong command $command at mapping toTransport stage")
