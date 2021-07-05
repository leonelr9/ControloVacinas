package com.example.controlovacinas

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class EfeitosSecundarios (var id: Long = -1, var febre: Boolean, var fadiga: Boolean, var dor_cabeca: Boolean, var dores_mosculares: Boolean, var calafrios: Boolean, var diarreia: Boolean, var dor_braco: Boolean, var outro: String, var idVacina: Long, var numLote: String? = null, var nomePacienteVacina: String? = null, var nomeFabricanteVacina: String? = null){
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply {
            put(TabelaEfeitosSecundarios.FEBRE, if(febre) 1 else 0)
            put(TabelaEfeitosSecundarios.FADIGA, if(fadiga) 1 else 0)
            put(TabelaEfeitosSecundarios.DOR_CABECA, if(dor_cabeca) 1 else 0)
            put(TabelaEfeitosSecundarios.DORES_MOSCULARES, if(dores_mosculares) 1 else 0)
            put(TabelaEfeitosSecundarios.CALAFRIOS, if(calafrios) 1 else 0)
            put(TabelaEfeitosSecundarios.DIARREIA, if(diarreia) 1 else 0)
            put(TabelaEfeitosSecundarios.DOR_BRACO, if(dor_braco) 1 else 0)
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
            val colNumLote = cursor.getColumnIndex(TabelaEfeitosSecundarios.CAMPO_EXTERNO_NUM_LOTE)
            val colNomePacienteVacina = cursor.getColumnIndex(TabelaEfeitosSecundarios.CAMPO_EXTERNO_NOME_PACIENTE_VACINA)
            val colNomeFabricanteVacina = cursor.getColumnIndex(TabelaEfeitosSecundarios.CAMPO_EXTERNO_NOME_FABRICANTE_VACINA)

            val id = cursor.getLong(colId)
            val febre = if (cursor.getInt(colFebre)== 1) true else false
            val fadiga = if (cursor.getInt(colFadiga)== 1) true else false
            val dor_cabeca = if (cursor.getInt(colDor_Cabeca)== 1) true else false
            val dores_mosculares = if (cursor.getInt(colDores_Mosculares)== 1) true else false
            val calafrios = if (cursor.getInt(colCalafrios)== 1) true else false
            val diarreia = if (cursor.getInt(colDiarreia)== 1) true else false
            val dor_braco = if (cursor.getInt(colDor_Braco)== 1) true else false
            val outro = cursor.getString(colOutro)
            val idVacina = cursor.getLong(colIdVacina)
            val numLote = if (colNumLote != -1) cursor.getString(colNumLote) else null
            val nomePacienteVacina = if (colNomePacienteVacina != -1) cursor.getString(colNomePacienteVacina) else null
            val nomeFabricanteVacina = if (colNomeFabricanteVacina != -1) cursor.getString(colNomeFabricanteVacina) else null

            return EfeitosSecundarios(id, febre, fadiga, dor_cabeca, dores_mosculares, calafrios, diarreia, dor_braco, outro, idVacina, numLote, nomePacienteVacina, nomeFabricanteVacina)
        }
    }
}