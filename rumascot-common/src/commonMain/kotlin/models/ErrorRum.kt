package ru.otus.otuskotlin.marketplace.common.models

data class ErrorRum(
    val code: String = "",
    val field: String = "",
    val message: String = "",
    val exception: Throwable? = null,
)
