package ru.otus.otuskotlin.marketplace.common.models

data class AdRum(
    var id: AdIdRum = AdIdRum.NONE,
    var title: String = "",
    var description: String = "",
    var ownerId: UserIdRum? = null,
    val dealSide: DealSideRum = DealSideRum.NONE,
    var visibility: VisibilityRum = VisibilityRum.NONE,
    val tags: List<TagRum>? = mutableListOf(),
    val permissionsClient: MutableSet<AdPermissionClientRum> = mutableSetOf()
)
