package com.webhookscheduler.TimerCreatorService.dto

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class TimerRecord(
    @Id var id: String? = null,  // Allow MongoDB to auto-generate this ID
    var triggerTime: Long,
    var fired: Boolean = false
)