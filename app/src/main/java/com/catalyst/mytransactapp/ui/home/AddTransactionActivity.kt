package com.catalyst.mytransactapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.catalyst.mytransactapp.R
import com.catalyst.mytransactapp.data.local.Transaction
import com.catalyst.mytransactapp.databinding.ActivityAddTransactionBinding
import com.catalyst.mytransactapp.ui.TransactionActivity
import java.text.SimpleDateFormat
import java.util.*

class AddTransactionActivity : AppCompatActivity() {

    companion object {
        const val titleKey: String = "title_key"
        const val amountKey: String = "amount_key"
    }

    private lateinit var binding: ActivityAddTransactionBinding
    private val viewModel by viewModels<TransactionViewModel>()
    private var transaction: Transaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        populateTransactionDetails()


        binding.backArrow.setOnClickListener {
            val intent = Intent(this@AddTransactionActivity, TransactionActivity::class.java)
            startActivity(intent)
        }
        binding.idBtnAddUpdate.setOnClickListener {
            insertDataToDatabase()
        }

    }

    private fun populateTransactionDetails() {
        val title = intent.getStringExtra(titleKey)
        val amount = intent.getStringExtra(amountKey)

        if (!title.isNullOrEmpty() && !amount.isNullOrEmpty()) {
            binding.idBtnAddUpdate.text = getString(R.string.update_transaction)
            binding.idEtTransactionTitle.setText(title)
            binding.idEtTransactionAmount.setText(amount)
        }
        else {
            binding.idBtnAddUpdate.text = getString(R.string.add_transaction)
        }
    }

    private fun insertDataToDatabase() {
        val title = binding.idEtTransactionTitle.text.toString()
        val amount = binding.idEtTransactionAmount.text.toString()
        val dateFormatter = SimpleDateFormat("dd MMM, yyyy - HH:mm a", Locale.getDefault())

        if (inputCheck(title, amount)) {
            if (transaction == null) {
                // Create new transaction object
                val transaction = Transaction( 0, title, amount, dateFormatter.format(Date()))
                // Add data to the database
                viewModel.addTransaction(transaction)
                Toast.makeText(this, "Transaction successfully added", Toast.LENGTH_SHORT).show()
            } else {
                // Update existing transaction
                val transaction = Transaction(0, title, amount, dateFormatter.format(Date()))
                viewModel.updateTransaction(transaction)
                Toast.makeText(this, "Transaction successfully updated", Toast.LENGTH_SHORT).show()
            }
            // Navigate back to TransactionActivity
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title: String, amount: String): Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(amount))
    }
}