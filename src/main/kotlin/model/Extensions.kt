package model

import model.Constantes.OPERATION_OUT
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun List<Operation>.filterByMonth(month: Int) : List<Operation>{
    val list: MutableList<Operation> = mutableListOf()
    this.forEach { item ->
        if (item.numberMonth == month) {
            list.add(item)
        }
    }
    return list
}

fun List<Operation>.totalByStatus(status : String) : Int{
    return this.filter { item -> item.status == status }.size
}

fun List<Operation>.totalByOperation(operation : String, statusOperation: String): String {
    var total = 0.0
    filter { itemFilter ->
        itemFilter.operation == operation &&
                itemFilter.status == statusOperation }
        .forEach { item ->
            total += item.amount }
    return DecimalFormat("#.##").format(total)
}

fun List<Operation>.totalPercentageByCategoryPerMonth(operation : String): List<CategoryByMonth>{
    val listTotalGeneral = filter { itemFilter -> itemFilter.operation == operation}
        .groupBy { itemGroup -> itemGroup.category}
        .map { item -> CategoryByMonth( amount = item.value.toList().totalAmountOperation(), category = item.key ) }

    val total = listTotalGeneral.totalAmountCategory()
    return listTotalGeneral.map { item -> CategoryByMonth(item.amount/total, category = item.category ) }
}

fun List<Operation>.totalAmountOperation(): Double {
    var total = 0.0
    this.forEach { item -> total += item.amount }
    return total
}

fun List<CategoryByMonth>.totalAmountCategory(): Double {
    var total = 0.0
    this.forEach { item -> total += item.amount }
    return total
}



