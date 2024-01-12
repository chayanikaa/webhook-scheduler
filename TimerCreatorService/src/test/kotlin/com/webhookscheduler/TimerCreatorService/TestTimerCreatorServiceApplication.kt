package com.webhookscheduler.TimerCreatorService

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.boot.with
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.containers.RabbitMQContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestTimerCreatorServiceApplication {

	@Bean
	@ServiceConnection
	fun mongoDbContainer(): MongoDBContainer {
		return MongoDBContainer(DockerImageName.parse("mongo:latest"))
	}

	@Bean
	@ServiceConnection
	fun rabbitContainer(): RabbitMQContainer {
		return RabbitMQContainer(DockerImageName.parse("rabbitmq:latest"))
	}

}

fun main(args: Array<String>) {
	fromApplication<TimerCreatorServiceApplication>().with(TestTimerCreatorServiceApplication::class).run(*args)
}
