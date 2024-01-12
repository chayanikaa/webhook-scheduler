package com.webhookscheduler.TimerCreatorService.controller

import com.webhookscheduler.TimerCreatorService.dto.TimerDTO
import com.webhookscheduler.TimerCreatorService.service.RabbitMQReceiver
import com.webhookscheduler.TimerCreatorService.service.RabbitMQSender
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/timers")
class TimerController(private val rabbitMQSender: RabbitMQSender, private val rabbitMQReceiver: RabbitMQReceiver) {
    @PostMapping
    fun createTimer() {
        // create a timer with hours randomized between 0 and 59, minutes randomized between 0 and 59, seconds randomized between 0 and 59, and url set to http://localhost:8080/v1/greetings/World

        val timer = TimerDTO(
            (0..5).random(),
            (0..59).random(),
            (0..59).random(),
            "http://localhost:8080/v1/greetings/World")

        rabbitMQSender.sendDelayed(timer.toString(), 20000)
        println("Timer created")
    }

    @GetMapping
    fun getTimers(): List<String> {
        val messages = rabbitMQReceiver.getMessages()
        rabbitMQReceiver.clearMessages() // Clear the messages after retrieval
        return messages
    }
}