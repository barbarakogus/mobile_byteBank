package com.example.bytebank

class Conta (var saldo: Double, val agencia: Int, val tipoConta: String) {

    fun depositar(valor: Double) {
        if(valor > 0){
            this.saldo += valor
        }
    }
    fun sacar (valor: Double) {
        if(saldo > valor){
            this.saldo -= valor
        }
    }
    fun transferir (conta: Conta, valor: Double) {
        this.sacar(valor)
        conta.depositar(valor)
    }
}