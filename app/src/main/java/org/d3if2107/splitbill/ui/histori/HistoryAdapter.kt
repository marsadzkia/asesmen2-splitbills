package org.d3if2107.splitbill.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3if2107.splitbill.R
import org.d3if2107.splitbill.databinding.HistoryItemBinding
import org.d3if2107.splitbill.db.SplitBillsEntity
import org.d3if2107.splitbill.model.splitBill
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter :
    ListAdapter<SplitBillsEntity, HistoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<SplitBillsEntity>() {
                override fun areItemsTheSame(
                    oldItem: SplitBillsEntity,
                    newItem: SplitBillsEntity
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: SplitBillsEntity,
                    newItem: SplitBillsEntity
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HistoryItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind((getItem(position)))
    }

    class ViewHolder(
        private val binding: HistoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
        Locale("id", "ID"))

        fun bind(item: SplitBillsEntity) = with(binding) {
            val splitBills = item.splitBill()

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            tagihanTextView.text = root.context.getString(R.string.data_tagihan)

        }
    }
}