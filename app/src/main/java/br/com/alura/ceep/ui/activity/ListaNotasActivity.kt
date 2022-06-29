package br.com.alura.ceep.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.ceep.R
import br.com.alura.ceep.dao.NotaDAO
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.ui.recyclerview.adapter.ListaNotasAdapter

class ListaNotasActivity : AppCompatActivity(), ListaNotaConstantes {
    private var adapter: ListaNotasAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)

        val todasNotas = pegaTodasNotas()
        configuraRecyclerView(todasNotas)
        configuraBotao()
    }

    private fun configuraBotao() {
        val botaoInsereNota = findViewById<TextView>(R.id.lista_notas_insere_nota)
        botaoInsereNota.setOnClickListener {
            vaiParaFormularioNotaActivity()
        }
    }

    private fun vaiParaFormularioNotaActivity() {
        val iniciaFormularioNota = Intent(
            this,
            FormularioNotaActivity::class.java
        )
        result.launch(iniciaFormularioNota)
    }

    private fun pegaTodasNotas(): List<Nota>? {
        val dao = NotaDAO()
        val todasNotas = dao.todos()
        return todasNotas
    }

    val result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val notaRecebida = it.data?.getSerializableExtra(CHAVE_NOTA) as Nota?
        adiciona(notaRecebida)
    }

    private fun adiciona(notaRecebida: Nota?) {
        NotaDAO().insere(notaRecebida)
        adapter!!.adiciona(notaRecebida!!)
    }

    private fun configuraRecyclerView(todasNotas: List<Nota>?) {
        val listaNotas = findViewById<RecyclerView>(R.id.lista_notas_recyclerview)
        configuraAdapter(todasNotas, listaNotas)
    }

    private fun configuraAdapter(todasNotas: List<Nota>?, listaNotas: RecyclerView) {
        adapter = ListaNotasAdapter(this, todasNotas as MutableList<Nota>)
        listaNotas.adapter = adapter
    }
}