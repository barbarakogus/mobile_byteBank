package com.example.bytebank
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import java.lang.NumberFormatException

class transacoes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela2_transacoes)

        val saldoContaCorrente = findViewById<TextView>(R.id.saldoContaCorrente)
        val conta1 = Conta(saldoContaCorrente.text.toString().toDouble(), 1001, "contaCorrente")

        val saldoContaPoupanca = findViewById<TextView>(R.id.saldoContaPoupanca)
        val conta2 = Conta(saldoContaPoupanca.text.toString().toDouble(), 1002, "contaPoupanca")

        val nomeClienteExtra = intent.extras?.getString("nomeCliente")
        val cpfClienteExtra = intent.getStringExtra("cpfCliente")

        val recylerView = findViewById<RecyclerView>(R.id.rv_lista_transacoes)

        if(nomeClienteExtra?.isNotEmpty() == true && cpfClienteExtra?.isNotEmpty() == true){
            val clienteUm = criar_cliente.Cliente(
                nomeClienteExtra,
                cpfClienteExtra,
                conta = conta1
            )
            Log.d("barbara", clienteUm.nome)
            Log.d("barbara", clienteUm.cpf) //tira qdo vai para a prod.
        }else {
            finish()
        }

        val inputUsuario = findViewById<TextInputLayout>(R.id.inputUsuario)

        val buttonDepositar = findViewById<Button>(R.id.buttonDepositar)
        val buttonSacar = findViewById<Button>(R.id.buttonSacar)
        val buttonTransferir = findViewById<Button>(R.id.buttonTransferir)

        val buttonContaCorrente = findViewById<Button>(R.id.buttonContaCorrente)
        val buttonContaPoupanca = findViewById<Button>(R.id.buttonContaPoupanca)

        val nomeCliente = findViewById<TextView>(R.id.nomeCliente)
        nomeCliente.text = nomeClienteExtra

        var contaEscolhida = conta1
        var contaAlvo = conta2

        buttonContaCorrente.setOnClickListener {
            contaEscolhida = conta1
            contaAlvo = conta2
            Toast.makeText(this, "Voce está trabalhando com a Conta Corrente", Toast.LENGTH_SHORT).show()
        }
        buttonContaPoupanca.setOnClickListener {
            contaEscolhida = conta2
            contaAlvo = conta1
            Toast.makeText(this, "Voce está trabalhando com a Conta Poupanca", Toast.LENGTH_SHORT).show()
        }

        fun pegarEntrada (): Double {
            val entrada = inputUsuario.editText?.text.toString()
            try {
                val entradaValorNumerico = entrada.toDouble()
                return entradaValorNumerico
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "A entrada: $entrada nao é válida", Toast.LENGTH_SHORT).show()
                return 0.0
            }
        }

        fun depositar () : Boolean {
            val valor = pegarEntrada()
            if (valor > 0) {
                contaEscolhida.depositar(valor)
                Toast.makeText(this, "Operacao realizada com sucesso", Toast.LENGTH_SHORT).show()
                return true
            }else {
                Toast.makeText(this, "O valor nao pode ser negativo", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        fun sacar() : Boolean {
            val saldoAtual = contaEscolhida.saldo
            val valor = pegarEntrada()
            if (valor < saldoAtual) {
                contaEscolhida.sacar(valor)
                Toast.makeText(this, "Operacao realizada com sucesso", Toast.LENGTH_SHORT).show()
                return true
            }else {
                Toast.makeText(this, "Saldo näo disponível", Toast.LENGTH_SHORT).show()
                return false
            }
        }

        fun transferir() : Boolean {
            val saldoAtual = contaEscolhida.saldo
            //val saldoAlvo = contaAlvo.saldo
            val valor = pegarEntrada()
            if(saldoAtual > valor) {
                contaEscolhida.transferir(contaAlvo, valor)
                //val valorAtualizado = saldoAtual - valor
                //val valorTransferido = saldoAlvo + valor
                Toast.makeText(this, "Operacao realizada com sucesso", Toast.LENGTH_SHORT).show()
                //return valorAtualizado to valorTransferido
                return true
            }else {
                Toast.makeText(this, "Saldo näo disponível", Toast.LENGTH_SHORT).show()
                //return saldoAtual to valor
                return false
            }
        }

        fun atualizarDadosDaTela() {
            if(contaEscolhida.tipoConta == "contaCorrente"){
                saldoContaCorrente.text = contaEscolhida.saldo.toString()
                saldoContaPoupanca.text = contaAlvo.saldo.toString()
            }else {
                saldoContaPoupanca.text = contaEscolhida.saldo.toString()
                saldoContaCorrente.text = contaAlvo.saldo.toString()
            }
            inputUsuario.editText?.text?.clear()
        }

        fun efetuarRecyclerView(operacaoRealizadoComSucesso: Boolean) {
            if(operacaoRealizadoComSucesso) {

                val transacaoAdapter = TransacaoAdapter(contaEscolhida.listaTransacoes)
                recylerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                recylerView.adapter = transacaoAdapter
            }
        }

        buttonDepositar.setOnClickListener {
            val operacaoRealizadaComSucesso = depositar()
            atualizarDadosDaTela()

            efetuarRecyclerView(operacaoRealizadaComSucesso)
        }
        buttonSacar.setOnClickListener {
            val operacaoRealizadaComSucesso = sacar()
            atualizarDadosDaTela()

           efetuarRecyclerView(operacaoRealizadaComSucesso)
        }
        buttonTransferir.setOnClickListener {
            val operacapRealizadaComSucesso = transferir()
            //val valorAtualizado = transferir()
            //contaEscolhida.saldo = valorAtualizado.first
            //contaAlvo.saldo = valorAtualizado.second
            atualizarDadosDaTela()
            efetuarRecyclerView(operacapRealizadaComSucesso)
        }
    }
}
