package ru.otus.goppeav.rumascot.stubs

import ru.otus.goppeav.rumascot.common.models.AdRum
import ru.otus.goppeav.rumascot.common.models.AdIdRum
import ru.otus.goppeav.rumascot.common.models.DealSideRum
import ru.otus.goppeav.rumascot.stubs.RumAdStubDuke.AD_DUKE_PROPOSAL
import ru.otus.goppeav.rumascot.stubs.RumAdStubDuke.AD_DUKE_DEMAND

object RumAdStub {
    fun get(): AdRum = AD_DUKE_PROPOSAL.copy()

    fun prepareResult(block: AdRum.() -> Unit): AdRum = get().apply(block)

    fun prepareSearchList(filter: String, type: DealSideRum) = listOf(
        rumAdDemand("id1", filter, type),
        rumAdDemand("id2", filter, type),
        rumAdDemand("id3", filter, type),
        rumAdDemand("id4", filter, type),
        rumAdDemand("id5", filter, type),
        rumAdDemand("id6", filter, type),
    )

    private fun rumAdDemand(id: String, filter: String, type: DealSideRum) =
        rumAd(AD_DUKE_DEMAND, id = id, filter = filter, type = type)

    private fun rumAdProposal(id: String, filter: String, type: DealSideRum) =
        rumAd(AD_DUKE_PROPOSAL, id = id, filter = filter, type = type)

    private fun rumAd(base: AdRum, id: String, filter: String, type: DealSideRum) = base.copy(
        id = AdIdRum(id),
        title = "$filter $id",
        description = "desc $filter $id",
        dealSide = type,
    )

}
