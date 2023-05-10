package ru.otus.otuskotlin.marketplace.api.v1

import ru.otus.otuskotlin.marketplace.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseSerializationTest {
    private val response = AdCreateResponse(
        responseType = "create",
        requestId = "123",
        ad = AdResponseObject(
            id = "1",
            title = "ad title",
            description = "ad desc",
            ownerId = 1,
            dealSide = DealSide.PROPOSAL,
            tags = listOf(Tag(1, "tag")),
            visibility = AdVisibility.PUBLIC
        ),
        result = ResponseResult.SUCCESS
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(response)

        assertContains(json, Regex("\"title\":\\s*\"ad title\""))
        assertContains(json, Regex("\"responseType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(response)
        val obj = apiV1Mapper.readValue(json, IResponse::class.java) as AdCreateResponse

        assertEquals(response, obj)
    }
}
