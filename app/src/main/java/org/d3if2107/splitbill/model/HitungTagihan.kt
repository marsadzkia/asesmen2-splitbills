package org.d3if2107.splitbill.model

import org.d3if2107.splitbill.db.SplitBillsEntity

fun SplitBillsEntity.splitBill(): JumlahTagihan {
    val jmlhOrgF = jmlhOrang.toFloat()
    val ppn = tagihan.toFloat() * (11 / 100)
    val patungan = ((tagihan.toFloat() + ppn) / jmlhOrgF).toString()
    return JumlahTagihan(patungan)
}