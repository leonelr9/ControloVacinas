package com.example.controlovacinas

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.util.*

data class Vacina (var id: Long = -1, var num_lote: String, var data_vacinacao: Date, var idPaciente: Long, var idFabricante: Long) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply {
            put(TabelaVacina.NUM_LOTE, num_lote)
            put(TabelaVacina.DATA_VACINACAO, data_vacinacao.time)
            put(TabelaVacina.CAMPO_ID_PACIENTE, idPaciente)
            put(TabelaVacina.CAMPO_ID_FABRICANTE, idFabricante)
        }

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Vacina {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colNum_Lote = cursor.getColumnIndex(TabelaVacina.NUM_LOTE)
            val colData = cursor.getColumnIndex(TabelaVacina.DATA_VACINACAO)
            val colIdPaciente = cursor.getColumnIndex(TabelaVacina.CAMPO_ID_PACIENTE)
            val colIdFabricante = cursor.getColumnIndex(TabelaVacina.CAMPO_ID_FABRICANTE)

            val id = cursor.getLong(colId)
            val num_lote = cursor.getString(colNum_Lote)
            val data_vacinacao = Date(cursor.getLong(colData))
            val idPaciente = cursor.getLong(colIdPaciente)
            val idFabricante = cursor.getLong(colIdFabricante)

            return Vacina(id, num_lote, data_vacinacao, idPaciente, idFabricante)
        }
    }
}