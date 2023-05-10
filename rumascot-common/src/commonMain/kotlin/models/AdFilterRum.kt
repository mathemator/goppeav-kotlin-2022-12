package ru.otus.otuskotlin.marketplace.common.models

data class AdFilterRum(
    var searchString: String = "",
    var ownerId: UserIdRum? = null,
    var dealSide: DealSideRum = DealSideRum.NONE,
)
