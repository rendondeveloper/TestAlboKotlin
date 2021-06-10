package model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Operation(
    val uuid : Int,
    val description : String,
    val category : String,
    val operation : String,
    val amount : Double,
    val status : String,
    val creation_date : String
){
    val numberMonth : Int
    get() {
        val dateLocal = LocalDate.parse(creation_date,DateTimeFormatter.ofPattern("MM/dd/yyyy"))
        return dateLocal.month.value
    }
}



