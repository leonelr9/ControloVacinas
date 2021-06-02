package com.example.controlovacinas

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Fabricante(var id: Long = -1, var nome: String) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()
        valores.put(TabelaFabricante.NOME, nome)
        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Fabricante {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colNome = cursor.getColumnIndex(TabelaFabricante.NOME)

            val id = cursor.getLong(colId)
            val nome = cursor.getString(colNome)

            return Fabricante(id, nome)
        }
    }
}