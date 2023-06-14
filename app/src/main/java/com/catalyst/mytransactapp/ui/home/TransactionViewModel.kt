package com.catalyst.mytransactapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.catalyst.mytransactapp.data.local.Transaction
import com.catalyst.mytransactapp.data.local.TransactionDatabase
import com.catalyst.mytransactapp.data.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: TransactionRepository
    val getAllTransactions: LiveData<List<Transaction>>

    init {
        val transactionDao = TransactionDatabase.getDatabase(application).transactionDao()
        repository = TransactionRepository(transactionDao)
        getAllTransactions = repository.getAllTransactions

    }

    fun addTransaction(transaction: Transaction) = viewModelScope.launch(Dispatchers.IO) {
        repository.addTransaction(transaction)
    }

    fun updateTransaction(transaction: Transaction) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateTransaction(transaction)
    }

    fun deleteTransaction(transaction: Transaction) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTransaction(transaction)
    }


}