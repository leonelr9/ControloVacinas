package com.example.controlovacinas

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaEfeitosSecundarios(db: SQLiteDatabase) {
    private val db: SQLiteDatabase = db

    fun cria(){
        db.execSQL("CREATE TABLE " + NOME_TABELA + "(" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FEBRE + " BOOLEAN, " +
                FADIGA +" BOOLEAN, " +
                DOR_CABECA + " BOOLEAN, " +
                DORES_MOSCULARES + " BOOLEAN, " +
                CALAFRIOS + " BOOLEAN, " +
                DIARREIA + " BOOLEAN, " +
                DOR_BRACO + " BOOLEAN, " +
                OUTRO + " TEXT, " +
                CAMPO_ID_VACINA + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + CAMPO_ID_VACINA + ") " +
                    "REFERENCES " + TabelaVacina.NOME_TABELA +
                ")")
    }

    fun insert(values: ContentValues): Long {
        return db.insert(NOME_TABELA, null, values)
    }

    fun update(values: ContentValues, whereClause: String, whereArgs: Array<String>): Int {
        return db.update(NOME_TABELA, values, whereClause, whereArgs)
    }

    fun delete(whereClause: String, whereArgs: Array<String>): Int {
        return db.delete(NOME_TABELA, whereClause, whereArgs)
    }

    fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor? {
        val ultimaColuna = columns.size - 1

        var posColNumLote = -1 // -1 indica que a coluna não foi pedida
        var posColNomePacienteVacina = -1
        var posColNomeFabricanteVacina = -1

        for (i in 0..ultimaColuna) {
            if (columns[i] == CAMPO_EXTERNO_NUM_LOTE) {
                posColNumLote = i
            }else if (columns[i] == CAMPO_EXTERNO_NOME_PACIENTE_VACINA){
                posColNomePacienteVacina = i
            }else if (columns[i] == CAMPO_EXTERNO_NOME_FABRICANTE_VACINA) {
                posColNomeFabricanteVacina = i
            }
        }

        if (posColNumLote == -1 && posColNomePacienteVacina == -1 && posColNomeFabricanteVacina == -1) {
            return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy)
        }

        var colunas = ""
        for (i in 0..ultimaColuna) {
            if (i > 0) colunas += ","

            colunas += if (i == posColNumLote) {
                "${TabelaVacina.NOME_TABELA}.${TabelaVacina.NUM_LOTE} AS $CAMPO_EXTERNO_NUM_LOTE"
            } else if (i == posColNomePacienteVacina) {
                "${TabelaPaciente.NOME_TABELA}.${TabelaPaciente.NOME} AS $CAMPO_EXTERNO_NOME_PACIENTE_VACINA"
            } else if (i == posColNomeFabricanteVacina) {
                "${TabelaFabricante.NOME_TABELA}.${TabelaFabricante.NOME} AS $CAMPO_EXTERNO_NOME_FABRICANTE_VACINA"
            } else {
                "${NOME_TABELA}.${columns[i]}"
            }
        }

        val tabelas = "$NOME_TABELA INNER JOIN ${TabelaVacina.NOME_TABELA} ON ${TabelaVacina.NOME_TABELA}.${BaseColumns._ID}=$CAMPO_ID_VACINA INNER JOIN ${TabelaPaciente.NOME_TABELA} ON ${TabelaPaciente.NOME_TABELA}.${BaseColumns._ID}=${TabelaVacina.CAMPO_ID_PACIENTE} INNER JOIN ${TabelaFabricante.NOME_TABELA} ON ${TabelaFabricante.NOME_TABELA}.${BaseColumns._ID}=${TabelaVacina.CAMPO_ID_FABRICANTE}"

        var sql = "SELECT $colunas FROM $tabelas"

        if (selection != null) sql += " WHERE $selection"

        if (groupBy != null) {
            sql += " GROUP BY $groupBy"
            if (having != null) " HAVING $having"
        }

        if (orderBy != null) sql += " ORDER BY $orderBy"

        return db.rawQuery(sql, selectionArgs)
    }

    companion object{
        const val NOME_TABELA = "efeitos_secundarios"
        const val FEBRE = "febre"
        const val FADIGA = "fadiga"
        const val DOR_CABECA = "dor_de_cabeca"
        const val DORES_MOSCULARES = "dores_mosculares"
        const val CALAFRIOS = "calafrios"
        const val DIARREIA = "diarreia"
        const val DOR_BRACO = "dor_no_braco"
        const val OUTRO = "outro"
        const val CAMPO_ID_VACINA = "id_vacina"
        const val CAMPO_EXTERNO_NUM_LOTE = "num_lote"
        const val CAMPO_EXTERNO_NOME_PACIENTE_VACINA = "nome_paciente_vacina"
        const val CAMPO_EXTERNO_NOME_FABRICANTE_VACINA = "nome_fabricante_vacina"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, FEBRE, FADIGA, DOR_CABECA, DORES_MOSCULARES, CALAFRIOS, DIARREIA, DOR_BRACO, OUTRO, CAMPO_ID_VACINA, CAMPO_EXTERNO_NUM_LOTE, CAMPO_EXTERNO_NOME_PACIENTE_VACINA, CAMPO_EXTERNO_NOME_FABRICANTE_VACINA)
    }
}