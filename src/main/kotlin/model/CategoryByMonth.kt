package model

import java.text.DecimalFormat
import java.text.NumberFormat

class CategoryByMonth(
    val amount : Double,
    val category : String,
){
    private val format: NumberFormat = DecimalFormat("#.#")
    val amountText : String
    get() {
        return format.format(amount)
    }
}



