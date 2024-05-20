package com.webhookscheduler.TimerCreatorService.controller

import com.webhookscheduler.TimerCreatorService.dto.TimerDTO
import com.webhookscheduler.TimerCreatorService.dto.TimerRecord
import com.webhookscheduler.TimerCreatorService.repository.TimerRepository
import com.webhookscheduler.TimerCreatorService.service.RabbitMQSender
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/v1/timers")
class TimerController(
    private val rabbitMQSender: RabbitMQSender,
    private val timerRepository: TimerRepository
) {
    @PostMapping
    fun createTimer() {
        val randomSeconds = (0..59).random()
        val timer = TimerDTO(0, 0, randomSeconds, "http://localhost:8080/v1/greetings/World")

        // calculate delay in milliseconds and set trigger time
        val currentTime = Instant.now().toEpochMilli()
        val delay = randomSeconds * 1000
        val triggerTime = currentTime + delay

        // Create and save TimerInfo
        val timerInfo = TimerRecord(triggerTime.toString(), triggerTime, false)
        timerRepository.save(timerInfo)

        println("Timer created with delay: $delay ms, firing at $triggerTime")

        rabbitMQSender.sendDelayed(timer, delay)
    }

    @GetMapping
    fun getTimers(): List<TimerRecord> {
        return timerRepository.findAll()
    }
}