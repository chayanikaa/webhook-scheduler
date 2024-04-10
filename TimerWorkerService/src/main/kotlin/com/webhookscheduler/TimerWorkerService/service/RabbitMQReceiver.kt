package com.webhookscheduler.TimerWorkerService.service

import com.webhookscheduler.TimerWorkerService.configuration.RabbitMQConfig
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class RabbitMQReceiver {
    private val messages = mutableListOf<String>()

    @RabbitListener(queues = [RabbitMQConfig.QUEUE_NAME])
    fun receive(message: String) {
        println("Received message: $message")
        messages.add(message)
        // fire webhook
    }

    fun getMessages(): List<String> {
        return messages.toList() // Return a copy of the messages
    }

    fun clearMessages() {
        messages.clear()
    }
}
