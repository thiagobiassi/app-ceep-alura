package br.com.alura.ceep.model

import java.io.Serializable

class Nota(private val titulo: String, private val descricao: String) : Serializable {
    fun getTitulo(): String {
        return titulo
    }

    fun getDescricao(): String {
        return descricao
    }
}