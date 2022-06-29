package br.com.alura.ceep.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.ceep.R
import br.com.alura.ceep.model.Nota

class FormularioNotaActivity : AppCompatActivity(), ListaNotaConstantes {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_nota)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_formulario_nota_salva, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (verificaSeEhMenuSalvaNota(item)) {
            val notaCriada = criaNota()
            retornaNota(notaCriada)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    private val CODIGO_RESULTADO_NOTA_CRIADA = 2

    private fun retornaNota(notaCriada: Nota) {
        val resultadoInsercao = Intent()
        resultadoInsercao.putExtra(CHAVE_NOTA, notaCriada)
        setResult(CODIGO_RESULTADO_NOTA_CRIADA, resultadoInsercao)
    }

    private fun criaNota(): Nota {
        val titulo = findViewById<EditText>(R.id.formulario_nota_titulo)
        val descricao = findViewById<EditText>(R.id.formulario_nota_descricao)
        val notaCriada = Nota(titulo.text.toString(), descricao.text.toString())
        return notaCriada
    }

    private fun verificaSeEhMenuSalvaNota(item: MenuItem) =
        item.itemId == R.id.menu_formulario_nota_ic_salva

}