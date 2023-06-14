package com.catalyst.mytransactapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TransactionDao {

    @Query("SELECT * FROM transaction_table ORDER BY date ASC")
    fun getAllTransactions(): LiveData<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTransaction(transactionItem: Transaction)

    @Update
    suspend fun updateTransaction(transactionItem: Transaction)

    @Delete
    suspend fun deleteTransaction(transactionItem: Transaction)

}