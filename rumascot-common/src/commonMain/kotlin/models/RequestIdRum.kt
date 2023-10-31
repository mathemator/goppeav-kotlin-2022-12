package ru.otus.goppeav.rumascot.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class RequestIdRum(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = RequestIdRum("")
    }
}
