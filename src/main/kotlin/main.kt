import data.data
import model.*
import model.Constantes.OPERATION_IN
import model.Constantes.OPERATION_OUT
import model.Constantes.STATUS_DONE
import model.Constantes.STATUS_PENDING
import model.Constantes.STATUS_REJECT

fun main(args: Array<String>) {
    val months = setMonths()
    val infoList = data.listData

    for (month in months){
        val infoFound = infoList.filterByMonth(month.key)
        printInfo(month.value,  infoFound)
    }
}

fun printInfo(nameMonth: String, infoFound: List<Operation>?) {
    infoFound?.let { list ->
        printDataForMonth(nameMonth, list)
    }
}

fun printDataForMonth(nameMonth : String , infoList: List<Operation>){
    val transactionsPending = infoList.totalByStatus(STATUS_REJECT)
    val transactionsBlocks = infoList.totalByStatus(STATUS_PENDING)

    val totalRevenue = infoList.totalByOperation(OPERATION_IN, STATUS_DONE)
    val totalExpenditures = infoList.totalByOperation(OPERATION_OUT, STATUS_DONE)

    val totalPercentageTransfers = infoList.totalPercentageByCategoryPerMonth(OPERATION_OUT)

    val textReport = StringBuffer()
    textReport.apply{
        appendLine("$nameMonth :")
        appendLine("        $transactionsPending transacciones pendientes")
        appendLine("        $transactionsBlocks  bloqueados")
            .appendLine()
        appendLine("        $$totalRevenue ingresos")
            .appendLine()
        appendLine("        $$totalExpenditures gastos")
            .appendLine()
        totalPercentageTransfers.forEach { itemPercentage ->
            appendLine("            ${itemPercentage.category}  %${itemPercentage.amountText}")
        }
    }
    println(textReport)
}

fun setMonths() : Map<Int, String>{
        return mapOf(
             1 to "Enero",
             2 to "Febrero",
             3 to "Marzo",
             4 to "Abril",
             5 to "Mayo",
             6 to "Junio",
             7 to "Julio",
             8 to "Agosto",
             9 to "Septiembre",
             10 to "Octubre",
             11 to "Noviembre",
             12 to "Dicembre",
        )
}
