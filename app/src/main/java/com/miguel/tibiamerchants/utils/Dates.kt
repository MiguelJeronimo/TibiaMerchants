package com.miguel.tibiamerchants.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class Dates {
    lateinit var dateFormat: String
    fun format(date:String): Dates {
        val instant = Instant.parse(date)
        val zonedDateTime = instant.atZone(ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        dateFormat = zonedDateTime.format(formatter)
        return this
    }
    fun get(): String {
        return dateFormat
    }
}