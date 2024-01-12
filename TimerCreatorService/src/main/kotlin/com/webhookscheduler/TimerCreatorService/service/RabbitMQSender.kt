package com.webhookscheduler.TimerCreatorService.service

import com.webhookscheduler.TimerCreatorService.configuration.RabbitMQConfig
import com.webhookscheduler.TimerCreatorService.dto.TimerDTO
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class RabbitMQSender(private val rabbitTemplate: RabbitTemplate) {

    fun send(timerString: String) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, timerString)
    }

    fun sendDelayed(message: String, delay: Int) {
        val messageProperties = MessageProperties()
        messageProperties.setHeader("x-delay", delay)
        val message = Message(message.toByteArray(), messageProperties)

        rabbitTemplate.send("myDelayedExchange", "yourRoutingKey", message)
    }
}
