package ru.otus.goppeav.rumascot.stubs

import ru.otus.goppeav.rumascot.common.models.*

object RumAdStubDuke {
    val AD_DUKE_PROPOSAL: AdRum
        get() = AdRum(
            id = AdIdRum("id1"),
            title = "Фигурка Java Duke",
            description = "Выполненная из бука фигурка маскота, покрытая красками и лаком",
            ownerId = UserIdRum(1),
            dealSide = DealSideRum.PROPOSAL,
            visibility = VisibilityRum.VISIBLE_PUBLIC,
            tags = mutableListOf(TagRum(1, "маскот"), TagRum(2, "дерево")),
            permissionsClient = mutableSetOf(
                AdPermissionClientRum.READ,
                AdPermissionClientRum.UPDATE,
                AdPermissionClientRum.DELETE,
            )
        )
    val AD_DUKE_DEMAND = AD_DUKE_PROPOSAL.copy(dealSide = DealSideRum.DEMAND)
}
