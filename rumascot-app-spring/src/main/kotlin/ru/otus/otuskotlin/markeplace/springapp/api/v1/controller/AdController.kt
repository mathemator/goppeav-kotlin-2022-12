package ru.otus.goppeav.rumascot.springapp.api.v1.controller

import org.springframework.web.bind.annotation.*
import ru.otus.goppeav.rumascot.api.v1.models.*
import ru.otus.goppeav.rumascot.common.ContextRum
import ru.otus.goppeav.rumascot.mappers.v1.*
import ru.otus.goppeav.rumascot.stubs.RumAdStub

@RestController
@RequestMapping("v1/ad")
class AdController {

    @PostMapping("create")
    fun createAd(@RequestBody request: AdCreateRequest): AdCreateResponse {
        val context = ContextRum()
        context.fromTransport(request)
        //todo logic
        context.adResponse = RumAdStub.get()
        return context.toTransportCreate()
    }

    @PostMapping("read")
    fun readAd(@RequestBody request: AdReadRequest): AdReadResponse {
        val context = ContextRum()
        context.fromTransport(request)
        //todo logic
        context.adResponse = RumAdStub.get()
        return context.toTransportRead()
    }

    @RequestMapping("update", method = [RequestMethod.POST])
    fun updateAd(@RequestBody request: AdUpdateRequest): AdUpdateResponse {
        val context = ContextRum()
        context.fromTransport(request)
        //todo logic
        context.adResponse = RumAdStub.get()
        return context.toTransportUpdate()
    }

    @PostMapping("delete")
    fun deleteAd(@RequestBody request: AdDeleteRequest): AdDeleteResponse {
        val context = ContextRum()
        context.fromTransport(request)
        //todo logic
        return context.toTransportDelete()
    }

    @PostMapping("search")
    fun searchAd(@RequestBody request: AdSearchRequest): AdSearchResponse {
        val context = ContextRum()
        context.fromTransport(request)
        //todo logic
        context.adsResponse.add(RumAdStub.get())
        return context.toTransportSearch()
    }
}
