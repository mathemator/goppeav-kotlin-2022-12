package ru.otus.goppeav.rumascot.common.models

data class AdFilterRum(
    var searchString: String = "",
    var ownerId: UserIdRum? = null,
    var dealSide: DealSideRum = DealSideRum.NONE,
)
