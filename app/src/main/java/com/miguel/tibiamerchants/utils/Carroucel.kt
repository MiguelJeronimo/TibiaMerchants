package com.miguel.tibiamerchants.utils

import com.miguel.tibiamerchants.BuildConfig

class Carroucel {
    private lateinit var array: MutableList<String>
    private var index: Int = 0
    fun generateFloorImage(screenshotCount: Int, tibiaId: Int): Carroucel {
        array = mutableListOf()
        for (i in 1..screenshotCount){
            array.add("${BuildConfig.API_TIBIA_TRADE}images/house/screenshot/$tibiaId/$i")
        }
        return this
    }

    fun floor(): MutableList<String> {
        return array
    }
}