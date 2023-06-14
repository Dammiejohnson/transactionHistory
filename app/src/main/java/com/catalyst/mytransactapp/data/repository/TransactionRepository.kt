package com.catalyst.mytransactapp.data.repository

import androidx.lifecycle.LiveData
import com.catalyst.mytransactapp.data.local.Transaction
import com.catalyst.mytransactapp.data.local.TransactionDao

class TransactionRepository(private val transactionDao: TransactionDao) {

    val getAllTransactions: LiveData<List<Transaction>> = transactionDao.getAllTransactions()

    suspend fun addTransaction(transaction: Transaction) {
        transactionDao.addTransaction(transaction)
    }

    suspend fun updateTransaction(transaction: Transaction) {
        transactionDao.updateTransaction(transaction)
    }

    suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.deleteTransaction(transaction)
    }

}