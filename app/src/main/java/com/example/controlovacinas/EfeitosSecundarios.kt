package com.example.controlovacinas

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class EfeitosSecundarios (var id: Long, var febre: Boolean, var fadiga: Boolean, var dor_cabeca: Boolean, var dores_mosculares: Boolean, var calafrios: Boolean, var diarreia: Boolean, var dor_braco: Boolean, var outro: String, var idVacina: Long){
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply {
            put(TabelaEfeitosSecundarios.FEBRE, febre)
            put(TabelaEfeitosSecundarios.FADIGA, fadiga)
            put(TabelaEfeitosSecundarios.DOR_CABECA, dor_cabeca)
            put(TabelaEfeitosSecundarios.DORES_MOSCULARES, dores_mosculares)
            put(TabelaEfeitosSecundarios.CALAFRIOS, calafrios)
            put(TabelaEfeitosSecundarios.DIARREIA, diarreia)
            put(TabelaEfeitosSecundarios.DOR_BRACO, dor_braco)
            put(TabelaEfeitosSecundarios.OUTRO, outro)
            put(TabelaEfeitosSecundarios.CAMPO_ID_VACINA, idVacina)
        }

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): EfeitosSecundarios {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colFebre = cursor.getColumnIndex(TabelaEfeitosSecundarios.FEBRE)
            val colFadiga = cursor.getColumnIndex(TabelaEfeitosSecundarios.FADIGA)
            val colDor_Cabeca = cursor.getColumnIndex(TabelaEfeitosSecundarios.DOR_CABECA)
            val colDores_Mosculares = cursor.getColumnIndex(TabelaEfeitosSecundarios.DORES_MOSCULARES)
            val colCalafrios = cursor.getColumnIndex(TabelaEfeitosSecundarios.CALAFRIOS)
            val colDiarreia = cursor.getColumnIndex(TabelaEfeitosSecundarios.DIARREIA)
            val colDor_Braco = cursor.getColumnIndex(TabelaEfeitosSecundarios.DOR_BRACO)
            val colOutro = cursor.getColumnIndex(TabelaEfeitosSecundarios.OUTRO)
            val colIdVacina = cursor.getColumnIndex(TabelaEfeitosSecundarios.CAMPO_ID_VACINA)

            val id = cursor.getLong(colId)
            val febre = cursor.equals(colFebre)
            val fadiga = cursor.equals(colFadiga)
            val dor_cabeca = cursor.equals(colDor_Cabeca)
            val dores_mosculares = cursor.equals(colDores_Mosculares)
            val calafrios = cursor.equals(colCalafrios)
            val diarreia = cursor.equals(colDiarreia)
            val dor_braco = cursor.equals(colDor_Braco)
            val outro = cursor.getString(colOutro)
            val idVacina = cursor.getLong(colIdVacina)

            return EfeitosSecundarios(id, febre, fadiga, dor_cabeca, dores_mosculares, calafrios, diarreia, dor_braco, outro, idVacina)
        }
    }
}