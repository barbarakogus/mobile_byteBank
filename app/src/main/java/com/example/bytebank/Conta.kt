package com.example.bytebank

class Conta (var saldo: Double, val agencia: Int, val tipoConta: String) {

    val listaTransacoes = mutableListOf<Pair<Int, String>>()

    fun depositar(valor: Double) {
        if(valor > 0){
            this.saldo += valor
            preencherListaTransacoes("depositar")
        }
    }
    fun sacar (valor: Double) {
        if(saldo > valor){
            this.saldo -= valor
            preencherListaTransacoes("sacar")
        }
    }
    fun transferir (conta: Conta, valor: Double) {
        this.sacar(valor)
        conta.depositar(valor)
        preencherListaTransacoes("transferir")
    }

    fun preencherListaTransacoes(tipoTransacao: String) {
        val totalTransacoes = listaTransacoes.size + 1
        //val pair = Pair(totalTransacoes, tipoTransacao)
        listaTransacoes.add(totalTransacoes to tipoTransacao)
    }


}