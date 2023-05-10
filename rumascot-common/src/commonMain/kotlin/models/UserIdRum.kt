package ru.otus.otuskotlin.marketplace.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class UserIdRum(private val id: Long) {
    fun asLong() = id
}
