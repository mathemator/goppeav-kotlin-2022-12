package ru.otus.otuskotlin.marketplace.mappers.v2

import ru.otus.otuskotlin.marketplace.api.v2.models.*
import ru.otus.otuskotlin.marketplace.common.ContextRum
import ru.otus.otuskotlin.marketplace.common.models.*
import ru.otus.otuskotlin.marketplace.common.stubs.StubsRum
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MapperTest {
    @Test
    fun fromTransport() {
        val req = AdCreateRequest(
            requestId = "1234",
            debug = AdDebug(
                mode = AdRequestDebugMode.STUB,
                stub = AdRequestDebugStubs.SUCCESS,
            ),
            ad = AdCreateObject(
                title = "title",
                description = "desc",
                dealSide = DealSide.DEMAND,
                visibility = AdVisibility.PUBLIC,
                tags = mutableListOf(
                    Tag(
                        id = 1,
                        name = "thing"
                    ))
            ),
        )

        val context = ContextRum()
        context.fromTransport(req)

        assertEquals(StubsRum.SUCCESS, context.stubCase)
        assertEquals(WorkModeRum.STUB, context.workMode)
        assertEquals("title", context.adRequest.title)
        assertEquals(VisibilityRum.VISIBLE_PUBLIC, context.adRequest.visibility)
        assertEquals(DealSideRum.DEMAND, context.adRequest.dealSide)
        assertTrue(context.adRequest.tags?.contains(TagRum(1, "thing")) ?: false, "tags are wrong")
    }

    @Test
    fun toTransport() {
        val context = ContextRum(
            requestId = RequestIdRum("1234"),
            command = CommandRum.CREATE,
            adResponse = AdRum(
                title = "title",
                description = "desc",
                dealSide = DealSideRum.DEMAND,
                visibility = VisibilityRum.VISIBLE_PUBLIC,
                tags = mutableListOf(
                    TagRum(
                        id = 1,
                        name = "thing"
                    )
                )
            ),
            errors = mutableListOf(
                ErrorRum(
                    code = "err",
                    field = "title",
                    message = "wrong title",
                )
            ),
            state = StateRum.RUNNING,
        )

        val req = context.toTransport() as AdCreateResponse

        assertEquals("1234", req.requestId)
        assertEquals("title", req.ad?.title)
        assertEquals("desc", req.ad?.description)
        assertEquals(AdVisibility.PUBLIC, req.ad?.visibility)
        assertEquals(DealSide.DEMAND, req.ad?.dealSide)
        assertEquals(1, req.errors?.size)
        assertEquals("err", req.errors?.firstOrNull()?.code)
        assertEquals("title", req.errors?.firstOrNull()?.field)
        assertEquals("wrong title", req.errors?.firstOrNull()?.message)
        assertTrue(req.ad?.tags?.contains(Tag(1, "thing")) ?: false, "tags are wrong")
    }
}
