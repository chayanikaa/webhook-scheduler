package com.webhookscheduler.TimerWorkerService.configuration

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.CustomExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {
    companion object {
        const val QUEUE_NAME = "timers" // this will also be in configuration
        const val DELAYED_EXCHANGE_NAME = "myDelayedExchange"
    }

    @Bean
    fun queue(): Queue {
        return Queue(QUEUE_NAME, true)
    }

    @Bean
    fun delayedExchange(): CustomExchange {
        val args = HashMap<String, Any>()
        args["x-delayed-type"] = "direct"

        return CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, args)
    }

    @Bean
    fun binding(queue: Queue, delayedExchange: CustomExchange): Binding {
        return BindingBuilder.bind(queue).to(delayedExchange).with("yourRoutingKey").noargs()
    }
}
