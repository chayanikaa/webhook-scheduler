package com.webhookscheduler.TimerCreatorService.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.webhookscheduler.TimerCreatorService.configuration.RabbitMQConfig
import com.webhookscheduler.TimerCreatorService.dto.TimerDTO
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class RabbitMQSender(private val rabbitTemplate: RabbitTemplate) {

    private val objectMapper = jacksonObjectMapper()

    fun sendDelayed(timer: TimerDTO, delay: Int) {
        val messageProperties = MessageProperties()
        messageProperties.setHeader("x-delay", delay)
        val messageSerialized = objectMapper.writeValueAsString(timer)
        val message = Message(messageSerialized.toByteArray(), messageProperties)

        rabbitTemplate.send("myDelayedExchange", "yourRoutingKey", message)
    }
}
