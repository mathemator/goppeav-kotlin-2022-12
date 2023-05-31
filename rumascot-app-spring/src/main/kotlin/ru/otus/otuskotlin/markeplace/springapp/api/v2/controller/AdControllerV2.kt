package ru.otus.goppeav.rumascot.springapp.api.v2.controller

import org.springframework.web.bind.annotation.*
import ru.otus.goppeav.rumascot.api.v2.models.*
import ru.otus.goppeav.rumascot.common.ContextRum
import ru.otus.goppeav.rumascot.mappers.v2.*
import ru.otus.goppeav.rumascot.stubs.RumAdStub

@RestController
@RequestMapping("v2/ad")
class AdControllerV2 {

    @PostMapping("create")
    fun createAd(@RequestBody request: AdCreateRequest): AdCreateResponse {
        val context = ContextRum()
        context.fromTransport(request)
        context.adResponse = RumAdStub.get()
        return context.toTransportCreate()
    }

    @PostMapping("read")
    fun readAd(@RequestBody request: AdReadRequest): AdReadResponse {
        val context = ContextRum()
        context.fromTransport(request)
        context.adResponse = RumAdStub.get()
        return context.toTransportRead()
    }

    @PostMapping("update")
    fun updateAd(@RequestBody request: AdUpdateRequest): AdUpdateResponse {
        val context = ContextRum()
        context.fromTransport(request)
        context.adResponse = RumAdStub.get()
        return context.toTransportUpdate()
    }

    @PostMapping("delete")
    fun deleteAd(@RequestBody request: AdDeleteRequest): AdDeleteResponse {
        val context = ContextRum()
        context.fromTransport(request)
        context.adResponse = RumAdStub.get()
        return context.toTransportDelete()
    }

    @PostMapping("search")
    fun searchAd(@RequestBody request: AdSearchRequest): AdSearchResponse {
        val context = ContextRum()
        context.fromTransport(request)
        context.adsResponse.add(RumAdStub.get())
        return context.toTransportSearch()
    }
}
