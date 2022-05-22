package org.d3if2107.splitbill


import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.d3if2107.splitbill.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener { hitungPpn() }
        binding.batalbutton.setOnClickListener {
            binding.jumlahOrangInp.text?.clear()
            binding.totalBillInp.text?.clear()
            binding.radioGroup.clearCheck()
            binding.totalBillTextView.text=""
            binding.patunganTextView.text=""
        }
    }

    private fun hitungPpn() {
        val jmlhOrang = binding.jumlahOrangInp.text.toString()
        if (TextUtils.isEmpty(jmlhOrang)) {
            Toast.makeText(this, R.string.orang_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val harga = binding.totalBillInp.text.toString()
        if (TextUtils.isEmpty(harga)) {
            Toast.makeText(this, R.string.tagihan_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1){
            Toast.makeText(this, R.string.pajak_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val jmlhOrgF = jmlhOrang.toFloat()
        val ppn = harga.toFloat() * 11 / 100
        val patungan = ((harga.toFloat() + ppn) / jmlhOrgF).toString()
        binding.totalBillTextView.text = getString(R.string.total_tagihan, harga)
        binding.patunganTextView.text = getString(R.string.tagihan_perorang, patungan)
    }
}