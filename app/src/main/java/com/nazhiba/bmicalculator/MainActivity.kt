package com.nazhiba.bmicalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nilai_weight = findViewById<EditText>(R.id.inputwidth)
        val nilai_height = findViewById<EditText>(R.id.inputheight)
        val btn_kalkulasi = findViewById<Button>(R.id.button)

        btn_kalkulasi.setOnClickListener(){
            val weight = nilai_weight.text.toString()
            val height = nilai_height.text.toString()
            if (handelempty(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2digits = String.format("%.2f", bmi).toFloat()
                display_jawaban(bmi2digits)
            }

        }
    }

    private fun handelempty(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight tidak boleh kosong",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height tidak boleh kosong",Toast.LENGTH_LONG).show()
                return false
            }
            else -> return true
        }
    }

    private fun display_jawaban(bmi:Float){
        val jawabanindex = findViewById<TextView>(R.id.jawaban)
        val deskripsi_jawaban = findViewById<TextView>(R.id.tvresult)
        val info = findViewById<TextView>(R.id.info)

        jawabanindex.text = bmi.toString()
        info.text = "Normal range is 18 - 24"

        var opsi_jawaban = ""
        var color = 0
        when{
            bmi<18.00 ->{
                opsi_jawaban = "EPIC ABADI!"
                color = R.color.Surend
            }
            bmi in 18.00 .. 24.00 -> {
                opsi_jawaban = "BERSYUKUR!!"
                color = R.color.hijau
            }
            bmi in 24.00..29.00 ->{
                opsi_jawaban = "WHOA..(`o`)MANUSIA SILVER!"
                color = R.color.tombol
            }
            bmi >29 -> {
                opsi_jawaban = "OBESITAS TANPA BATAS!"
                color = R.color.white
            }

        }
        deskripsi_jawaban.setTextColor(ContextCompat.getColor(this,color))
        deskripsi_jawaban.text = opsi_jawaban
    }
}