package ru.otus.goppeav.rumascot.common.helpers

import ru.otus.goppeav.rumascot.common.ContextRum
import ru.otus.goppeav.rumascot.common.models.ErrorRum

fun Throwable.asRumError(
    code: String = "unknown",
    group: String = "exceptions",
    message: String = this.message ?: "",
) = ErrorRum(
    code = code,
    field = "",
    message = message,
    exception = this,
)

fun ContextRum.addError(vararg error: ErrorRum) = errors.addAll(error)
