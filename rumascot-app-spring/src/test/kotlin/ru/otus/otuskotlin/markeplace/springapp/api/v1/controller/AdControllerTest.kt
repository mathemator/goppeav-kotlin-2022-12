package ru.otus.goppeav.rumascot.springapp.api.v1.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ru.otus.goppeav.rumascot.api.v1.models.*
import ru.otus.goppeav.rumascot.common.ContextRum
import ru.otus.goppeav.rumascot.mappers.v1.*
import ru.otus.goppeav.rumascot.stubs.RumAdStub

// Temporary simple test with stubs
@WebMvcTest(AdController::class)
internal class AdControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Test
    fun createAd() = testStubAd(
        "/v1/ad/create",
        AdCreateRequest(),
        ContextRum().apply { adResponse = RumAdStub.get() }.toTransportCreate()
    )

    @Test
    fun readAd() = testStubAd(
        "/v1/ad/read",
        AdReadRequest(),
        ContextRum().apply { adResponse = RumAdStub.get() }.toTransportRead()
    )

    @Test
    fun updateAd() = testStubAd(
        "/v1/ad/update",
        AdUpdateRequest(),
        ContextRum().apply { adResponse = RumAdStub.get() }.toTransportUpdate()
    )

    @Test
    fun deleteAd() = testStubAd(
        "/v1/ad/delete",
        AdDeleteRequest(),
        ContextRum().toTransportDelete()
    )

    @Test
    fun searchAd() = testStubAd(
        "/v1/ad/search",
        AdSearchRequest(),
        ContextRum().apply { adsResponse.add(RumAdStub.get()) }.toTransportSearch()
    )

    private fun <Req : Any, Res : Any> testStubAd(
        url: String,
        requestObj: Req,
        responseObj: Res,
    ) {
        val request = mapper.writeValueAsString(requestObj)
        val response = mapper.writeValueAsString(responseObj)

        mvc.perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(content().json(response))
    }
}
