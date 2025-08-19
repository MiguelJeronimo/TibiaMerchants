package com.miguel.tibiamerchants.utils

import java.text.NumberFormat
import java.util.Locale

class Money {
    private lateinit var money: String
    fun format(money: Double): Money {
        this.money = NumberFormat.getCurrencyInstance(Locale.US).format(money)
        return this
    }
    fun get(): String {
        return money.replace("$", "").replace(".00", "")
    }
}