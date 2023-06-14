package com.catalyst.mytransactapp.ui.home

import com.catalyst.mytransactapp.data.local.Transaction

interface TransactionClickInterface {
    fun onItemClick(transaction: Transaction)
    fun onDeleteIconClick(transaction: Transaction)
}