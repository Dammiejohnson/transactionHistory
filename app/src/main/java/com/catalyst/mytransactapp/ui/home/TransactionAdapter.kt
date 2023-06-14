package com.catalyst.mytransactapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catalyst.mytransactapp.R
import com.catalyst.mytransactapp.data.local.Transaction
import com.catalyst.mytransactapp.databinding.TransactionRvItemBinding

class TransactionAdapter(
    private var allTransactions: List<Transaction>,
    private val transactionClickInterface: TransactionClickInterface
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {


    inner class TransactionViewHolder(
        private val binding: TransactionRvItemBinding,
        private val transactionClickInterface: TransactionClickInterface
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transactionItem: Transaction) = with(binding) {
            tvType.text = transactionItem.transactionTitle
            tvAmount.text = transactionItem.transactionAmount.toString()
            tvDate.text = transactionItem.date
            ivDelete.setOnClickListener {
                transactionClickInterface.onDeleteIconClick(transactionItem)
            }
            root.setOnClickListener {
                transactionClickInterface.onItemClick(transactionItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            TransactionRvItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.transaction_rv_item, parent, false)
            ), transactionClickInterface
        )
    }

    override fun getItemCount(): Int {
        return allTransactions.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(allTransactions[position])

    }

}

