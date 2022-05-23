package org.d3if2107.splitbill

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.d3if2107.splitbill.databinding.ActivityMainBinding
import org.d3if2107.splitbill.model.JumlahTagihan

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener { splitBill() }
        binding.batalbutton.setOnClickListener {
            binding.jumlahOrangInp.text?.clear()
            binding.totalBillInp.text?.clear()
            binding.radioGroup.clearCheck()
            binding.totalBillTextView.text=""
            binding.patunganTextView.text=""
        }
        viewModel.getJumlahTagihan().observe(this, { lihatHasil (it) })
    }

    private fun splitBill() {
        val jmlhOrang = binding.jumlahOrangInp.text.toString()
        if (TextUtils.isEmpty(jmlhOrang)) {
            Toast.makeText(this, R.string.orang_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val tagihan = binding.totalBillInp.text.toString()
        if (TextUtils.isEmpty(tagihan)) {
            Toast.makeText(this, R.string.tagihan_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == 0){
            Toast.makeText(this, R.string.pajak_invalid, Toast.LENGTH_LONG).show()
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