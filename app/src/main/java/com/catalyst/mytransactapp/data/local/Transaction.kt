package com.catalyst.mytransactapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "transaction_title")var transactionTitle: String,
    @ColumnInfo(name = "transaction_amount") var transactionAmount: String,
    @ColumnInfo(name = "date") var date: String
)
