package com.webhookscheduler.TimerCreatorService.repository

import com.webhookscheduler.TimerCreatorService.dto.TimerRecord
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TimerRepository : MongoRepository<TimerRecord, String>
