package ru.otus.otuskotlin.marketplace.mappers.v2.exceptions

import ru.otus.otuskotlin.marketplace.common.models.CommandRum

class UnknownCommandRum(command: CommandRum) : Throwable("Wrong command $command at mapping toTransport stage")
