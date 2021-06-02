package com.example.controlovacinas

import android.content.ContentValues

data class Fabricante(var id: Long = -1, var nome: String) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()
        valores.put(TabelaFabricante.NOME, nome)
        return valores
    }
}