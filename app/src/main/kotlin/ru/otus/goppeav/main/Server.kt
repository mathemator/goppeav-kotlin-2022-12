package ru.otus.goppeav.main

import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory

class Server {
    val logger = LoggerFactory.getLogger(this::class.java.canonicalName)

    suspend fun run(){
        while(true) {
            logger.info("This is info log")
            println("Hello World!!")
            delay(5000)
        }
    }
}