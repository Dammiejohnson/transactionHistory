package com.catalyst.mytransactapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catalyst.mytransactapp.data.local.Transaction
import com.catalyst.mytransactapp.databinding.ActivityTransactionBinding
import com.catalyst.mytransactapp.ui.home.AddTransactionActivity
import com.catalyst.mytransactapp.ui.home.TransactionAdapter
import com.catalyst.mytransactapp.ui.home.TransactionClickInterface
import com.catalyst.mytransactapp.ui.home.TransactionViewModel

class TransactionActivity : AppCompatActivity(), TransactionClickInterface {
    private lateinit var binding: ActivityTransactionBinding
    private lateinit var recyclerView: RecyclerView
    private val viewModel by viewModels<TransactionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.transactionRV
        setUpRecyclerView()

        binding.idFAB.setOnClickListener {
            val intent = Intent(this@TransactionActivity, AddTransactionActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onItemClick(transaction: Transaction) {
        val intent = Intent(this, AddTransactionActivity::class.java)
        intent.putExtra(AddTransactionActivity.titleKey, transaction.transactionTitle)
        intent.putExtra(AddTransactionActivity.amountKey, transaction.transactionAmount)
        startActivity(intent)
    }

    override fun onDeleteIconClick(transaction: Transaction) {
        showDeleteDialog(transaction)
    }

    private fun showDeleteDialog(transaction: Transaction) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Delete Transaction")
        alertDialogBuilder.setMessage("Are you sure you want to delete this transaction?")
        alertDialogBuilder.setPositiveButton("Delete") { dialog, _ ->
            viewModel.deleteTransaction(transaction)
            Toast.makeText(this, "Transaction successfully deleted", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun setUpRecyclerView() {
        viewModel.getAllTransactions.observe(this) {
            // Set the layout manager
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = TransactionAdapter(it, this)
            binding.emptyView.isVisible = it.isEmpty()
        }
    }
}