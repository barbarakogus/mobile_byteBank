package com.example.bytebank

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TransacaoAdapter (val listaTransacoes : List<Pair<Int, String>>) : RecyclerView.Adapter<TransacaoAdapter.TransacaoViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransacaoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_linha_transacao, parent, false)
        val transacaoViewHolder = TransacaoViewHolder(view)
        return transacaoViewHolder
    }

    override fun onBindViewHolder(holder: TransacaoViewHolder, position: Int) {
        var transacaoRealizada = listaTransacoes[position]
        holder.preencherDadosTransacao(transacaoRealizada.first, transacaoRealizada.second)
    }

    override fun getItemCount(): Int {
        return listaTransacoes.size
    }

    class TransacaoViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numeroTransacao = itemView.findViewById<TextView>(R.id.numero_transacao)
        val tipoTransacao = itemView.findViewById<TextView>(R.id.tipo_transacao)

        fun preencherDadosTransacao (numero: Int, transacao: String) {
            numeroTransacao.text = numero.toString()
            tipoTransacao.text = transacao
        }
    }
}


