package ru.otus.goppeav.rumascot.mappers.v1.exceptions

class UnknownRequestClass(clazz: Class<*>) : RuntimeException("Class $clazz cannot be mapped to ContextRum")
