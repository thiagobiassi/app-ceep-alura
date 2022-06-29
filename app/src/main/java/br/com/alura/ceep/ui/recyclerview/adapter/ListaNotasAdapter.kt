package br.com.alura.ceep.ui.recyclerview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.ceep.R
import br.com.alura.ceep.model.Nota

class ListaNotasAdapter(val context: Context, val notas: MutableList<Nota>) : RecyclerView.Adapter<ListaNotasAdapter.NotaViewHolder>() {

    private var qntd : Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        qntd++
        val viewCriada = LayoutInflater.from(context)
            .inflate(R.layout.item_nota, parent, false)
        Log.i("RecylerView adapter", "Quantidade viewHolder : $qntd")
        return NotaViewHolder(viewCriada)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        holder.vincula(nota)
    }

    override fun getItemCount(): Int {
        return notas.size
    }

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo = itemView.findViewById<TextView>(R.id.item_nota_titulo)
        val descricao = itemView.findViewById<TextView>(R.id.item_nota_descricao)

        fun vincula(nota : Nota){
            preencheCampos(nota)
        }

        private fun preencheCampos(nota: Nota) {
            titulo.text = nota.getTitulo()
            descricao.text = nota.getDescricao()
        }
    }

    fun adiciona(nota : Nota){
        notas.add(nota)
        notifyDataSetChanged()
    }

}
