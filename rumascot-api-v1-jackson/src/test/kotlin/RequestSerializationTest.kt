package ru.otus.goppeav.rumascot.api.v1

import ru.otus.goppeav.rumascot.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestSerializationTest {
    private val request = AdCreateRequest(
        requestType = "create",
        requestId = "123",
        debug = AdDebug(
            mode = AdRequestDebugMode.STUB,
            stub = AdRequestDebugStubs.BAD_INFO
        ),
        ad = AdCreateObject(
            title = "ad title",
            description = "ad desc",
            ownerId = 1,
            dealSide = DealSide.PROPOSAL,
            tags = listOf(Tag(1, "tag")),
            visibility = AdVisibility.PUBLIC
        )
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(request)

        assertContains(json, Regex("\"title\":\\s*\"ad title\""))
        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"badInfo\""))
        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(request)
        val obj = apiV1Mapper.readValue(json, IRequest::class.java) as AdCreateRequest

        assertEquals(request, obj)
    }
}
