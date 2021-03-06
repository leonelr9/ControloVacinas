package com.example.controlovacinas

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.util.*

data class Vacina (var id: Long = -1, var num_lote: String, var data_vacinacao: Date, var idPaciente: Long, var idFabricante: Long, var nomePaciente: String? = null, var nomeFabricante: String? = null) {
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
            val colNomePaciente = cursor.getColumnIndex(TabelaVacina.CAMPO_EXTERNO_NOME_PACIENTE)
            val colNomeFabricante = cursor.getColumnIndex(TabelaVacina.CAMPO_EXTERNO_NOME_FABRICANTE)

            val id = cursor.getLong(colId)
            val num_lote = cursor.getString(colNum_Lote)
            val data_vacinacao = Date(cursor.getLong(colData))
            val idPaciente = cursor.getLong(colIdPaciente)
            val idFabricante = cursor.getLong(colIdFabricante)
            val nomePaciente = if (colNomePaciente != 1) cursor.getString(colNomePaciente) else null
            val nomeFabricante = if (colNomeFabricante != 1) cursor.getString(colNomeFabricante) else null

            return Vacina(id, num_lote, data_vacinacao, idPaciente, idFabricante, nomePaciente, nomeFabricante)
        }
    }
}