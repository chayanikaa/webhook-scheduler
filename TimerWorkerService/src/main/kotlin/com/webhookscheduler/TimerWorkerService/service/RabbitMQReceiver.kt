package com.webhookscheduler.TimerWorkerService.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.webhookscheduler.TimerCreatorService.dto.TimerDTO
import com.webhookscheduler.TimerWorkerService.configuration.RabbitMQConfig
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class RabbitMQReceiver {
    private val objectMapper = jacksonObjectMapper()

    @RabbitListener(queues = [RabbitMQConfig.QUEUE_NAME])
    fun receive(message: String) {
        println("Received message: $message")

        val payload: TimerDTO = objectMapper.readValue(message, TimerDTO::class.java)
        println("Payload: $payload")

        // fire webhook
    }
}
