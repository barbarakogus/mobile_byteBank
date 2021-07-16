package com.example.bytebank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saldoContaCorrente = findViewById<TextView>(R.id.saldoContaCorrente)
        val inputUsuario = findViewById<TextInputLayout>(R.id.inputUsuario)
        val buttonDepositar = findViewById<Button>(R.id.buttonDepositar)
        val buttonSacar = findViewById<Button>(R.id.buttonSacar)

        buttonDepositar.setOnClickListener {
            val entrada = inputUsuario.editText?.text.toString()
            val entradaValorNumerico = entrada.toDouble()
            val saldoAtual = saldoContaCorrente.text.toString().toDouble()
            val total = entradaValorNumerico + saldoAtual

            saldoContaCorrente.text = total.toString()
            inputUsuario.editText?.text?.clear()
            Toast.makeText(this, "Operacao realizada com sucesso", Toast.LENGTH_SHORT).show()
        }

        buttonSacar.setOnClickListener {
            val entrada = inputUsuario.editText?.text.toString()
            val entradaValorNumerico = entrada.toDouble()
            val saldoAtual = saldoContaCorrente.text.toString().toDouble()
            val saldoAtualizado = sacar(entradaValorNumerico, saldoAtual)
            saldoContaCorrente.text = saldoAtualizado.toString()
            inputUsuario.editText?.text?.clear()
        }
    }

    fun sacar(entradaValorNumerico: Double, saldoAtual: Double) : Double {
        if (entradaValorNumerico < saldoAtual) {
            val total = saldoAtual - entradaValorNumerico
            Toast.makeText(this, "Operacao realizada com sucesso", Toast.LENGTH_SHORT).show()
            return total
        }else {
            Toast.makeText(this, "Saldo näo disponível", Toast.LENGTH_SHORT).show()
            return saldoAtual
        }
    }
}
