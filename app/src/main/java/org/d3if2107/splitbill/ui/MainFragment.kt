package org.d3if2107.splitbill.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if2107.splitbill.R
import org.d3if2107.splitbill.databinding.FragmentMainBinding
import org.d3if2107.splitbill.model.JumlahTagihan

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button1.setOnClickListener { splitBill() }
        binding.batalbutton.setOnClickListener {
            binding.jumlahOrangInp.text?.clear()
            binding.totalBillInp.text?.clear()
            binding.radioGroup.clearCheck()
            binding.totalBillTextView.text=""
            binding.patunganTextView.text=""
        }
        viewModel.getJumlahTagihan().observe(requireActivity(), { lihatHasil (it) })
    }

    private fun splitBill() {
        val jmlhOrang = binding.jumlahOrangInp.text.toString()
        if (TextUtils.isEmpty(jmlhOrang)) {
            Toast.makeText(context, R.string.orang_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val tagihan = binding.totalBillInp.text.toString()
        if (TextUtils.isEmpty(tagihan)) {
            Toast.makeText(context, R.string.tagihan_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1){
            Toast.makeText(context, R.string.pajak_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.hitungPatungan(
            jmlhOrang.toInt(),
            tagihan.toInt()
        )
    }

    private fun lihatHasil(result: JumlahTagihan?) {
        if (result == null) return
        binding.patunganTextView.text = getString(R.string.tagihan_perorang, result.patungan)
    }
}