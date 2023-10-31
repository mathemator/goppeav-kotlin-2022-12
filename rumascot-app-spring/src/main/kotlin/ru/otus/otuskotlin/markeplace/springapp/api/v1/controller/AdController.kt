package ru.otus.goppeav.rumascot.springapp.api.v1.controller

import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.*
import ru.otus.goppeav.rumascot.api.v1.models.*
import ru.otus.goppeav.rumascot.common.ContextRum
import ru.otus.goppeav.rumascot.mappers.v1.*
import ru.otus.goppeav.rumascot.stubs.RumAdStub
import ru.otus.otuskotlin.markeplace.springapp.models.RumAppSettings
import ru.otus.otuskotlin.marketplace.app.common.processV1

@RestController
@RequestMapping("v1/ad")
class AdController (
    private  val appSettings: RumAppSettings
){

    @PostMapping("create")
    fun createAd(@RequestBody request: AdCreateRequest): AdCreateResponse = runBlocking {
        processV1<AdCreateRequest, AdCreateResponse>(
            processor = appSettings.processor,
            request = request,
            logger = appSettings.logger.logger(this::class.qualifiedName ?: "create"),
            logId = "create",
        )
    }

    @PostMapping("read")
    fun readAd(@RequestBody request: AdReadRequest): AdReadResponse = runBlocking {
        val context = ContextRum()
        context.fromTransport(request)
        appSettings.processor.exec(context)
        context.toTransportRead()
    }

    @RequestMapping("update", method = [RequestMethod.POST])
    fun updateAd(@RequestBody request: AdUpdateRequest): AdUpdateResponse = runBlocking {
        val context = ContextRum()
        context.fromTransport(request)
        appSettings.processor.exec(context)
        context.toTransportUpdate()
    }

    @PostMapping("delete")
    fun deleteAd(@RequestBody request: AdDeleteRequest): AdDeleteResponse = runBlocking {
        val context = ContextRum()
        context.fromTransport(request)
        appSettings.processor.exec(context)
        context.toTransportDelete()
    }

    @PostMapping("search")
    fun searchAd(@RequestBody request: AdSearchRequest): AdSearchResponse = runBlocking {
        val context = ContextRum()
        context.fromTransport(request)
        appSettings.processor.exec(context)
        context.toTransportSearch()
    }
}
