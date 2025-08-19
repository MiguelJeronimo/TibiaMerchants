package com.miguel.tibiamerchants.domain.models

import com.miguel.tibiamerchants.utils.Money

/**
 * Currence type: 0; price in gold; converted_price in TC
 * Currence type: 1; price in TC; converted_price in gold; aveces no tiene converted_price o viene en cero
 * currence type: 2; not prices
 *
 * */
class ConverterPriceModel {
    private var converter: Converter = Converter("", "")
    fun convert(price: Long, convertedPrice: Long, currencyType: Int): ConverterPriceModel {
        converter =  when (currencyType) {
            0 -> Converter(price= "$${Money().format(price.toDouble()).get()} golds", converter = "${Money().format(convertedPrice.toDouble()).get()} TCs")
            1 -> {
                if (price == 0L && convertedPrice == 0L){
                    Converter(price= "Talking Offers", converter = "N/A")
                }else{
                    Converter(price= "${Money().format(price.toDouble()).get()} TCs", converter = "${Money().format(convertedPrice.toDouble()).get()} golds")
                }
            }
            2 -> Converter(price = "Talking Offers", converter = "N/A")
            else -> {
                Converter(price = "Talking Offers", converter = "N/A")
            }
        }
        return this
    }
    fun get(): Converter {
        return converter
    }
}

data class Converter(
    val price: String,
    val converter: String
)