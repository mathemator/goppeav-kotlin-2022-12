package ru.otus.goppeav.rumascot.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class UserIdRum(private val id: Long) {
    fun asLong() = id
}
