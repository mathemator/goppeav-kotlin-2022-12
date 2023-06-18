package ru.otus.goppeav.rumascot.common.models

data class TagRum(
    val id: Long? = null,
    val name: String? = ""
) {
    fun asString() = name
}