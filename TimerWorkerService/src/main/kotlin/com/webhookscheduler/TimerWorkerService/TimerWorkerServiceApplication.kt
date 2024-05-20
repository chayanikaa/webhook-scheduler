package com.webhookscheduler.TimerWorkerService

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TimerWorkerServiceApplication

fun main(args: Array<String>) {
	runApplication<TimerWorkerServiceApplication>(*args)
}
