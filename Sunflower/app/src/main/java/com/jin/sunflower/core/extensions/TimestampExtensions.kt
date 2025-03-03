package com.jin.sunflower.core.extensions

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val systemZoneId = ZoneId.systemDefault()

fun Long.formatAsDate(format: String = "MMM dd, yyyy"): String {
    val formatter = DateTimeFormatter.ofPattern(format)
    return Instant.ofEpochMilli(this)
        .atZone(systemZoneId)
        .toLocalDate()
        .format(formatter)
}
