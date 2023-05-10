package ru.otus.otuskotlin.marketplace.common.models

data class TagRum(
    val id: Long? = null,
    val name: String? = ""
) {
    fun asString() = name
}