package com.example.bytebank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {

    data class Cliente(val nome: String, val cpf: String, val conta: Conta)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val buttonCriarCliente = findViewById<Button>(R.id.buttonCriarCliente)
        val inputClienteNome = findViewById<TextInputLayout>(R.id.inputClienteNome)
        val inputClienteCpf = findViewById<TextInputLayout>(R.id.inputClienteCpf)

        buttonCriarCliente.setOnClickListener {

            val entradaNome = inputClienteNome.editText?.text.toString()
            val entradaCpf = inputClienteCpf.editText?.text.toString()

            if(entradaNome.isNotEmpty() && entradaCpf.isNotEmpty()) {
                Toast.makeText(this, "Cliente criado com sucesso", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "O campo nome e cpf sao obrigatórios.", Toast.LENGTH_SHORT).show()
            }

            val goToByteBank = Intent(this, MainActivity ::class.java)
            goToByteBank.putExtra("nomeCliente", entradaNome)
            goToByteBank.putExtra("cpfCliente", entradaCpf)
            startActivity(goToByteBank)
        }

    }
}