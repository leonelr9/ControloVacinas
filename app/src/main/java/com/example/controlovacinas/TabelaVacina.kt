package com.example.controlovacinas

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaVacina(db: SQLiteDatabase) {
    private val db: SQLiteDatabase = db

    fun cria(){
        db.execSQL("CREATE TABLE " + NOME_TABELA + "(" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NUM_LOTE + " TEXT NOT NULL, " +
                DATA_VACINACAO + " INTEGER NOT NULL, " +
                CAMPO_ID_PACIENTE + " INTEGER NOT NULL, " +
                CAMPO_ID_FABRICANTE + " INTEGER NOT NULL, " +
                " FOREIGN KEY(" + CAMPO_ID_PACIENTE + ") " +
                    "REFERENCES " + TabelaPaciente.NOME_TABELA +", "+
                " FOREIGN KEY(" + CAMPO_ID_FABRICANTE + ") " +
                    "REFERENCES " + TabelaFabricante.NOME_TABELA +")")
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

        var posColNomePaciente = -1 // -1 indica que a coluna não foi pedida
        //Acrescentar nova var
        var posColNomeFabricante = -1

        for (i in 0..ultimaColuna) {
            if (columns[i] == CAMPO_EXTERNO_NOME_PACIENTE) {
                posColNomePaciente = i
            }else if (columns[i] == CAMPO_EXTERNO_NOME_FABRICANTE) {
                posColNomeFabricante = i
            }
             //Tirar o break e fazer else if
        }

        //No prox if alterar posColNomePaciente ou posColNomeFabricante e depois fazer o return da query
        if (posColNomePaciente == -1 && posColNomeFabricante == -1 ) {
            return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy)
        }

        var colunas = ""
        for (i in 0..ultimaColuna) {
            if (i > 0) colunas += ","

            colunas += if (i == posColNomePaciente) {
                "${TabelaPaciente.NOME_TABELA}.${TabelaPaciente.NOME} AS $CAMPO_EXTERNO_NOME_PACIENTE"
            } else if (i == posColNomeFabricante) {
                "${TabelaFabricante.NOME_TABELA}.${TabelaFabricante.NOME} AS $CAMPO_EXTERNO_NOME_FABRICANTE"
            } else {
                "${NOME_TABELA}.${columns[i]}"
            }
        }

        val tabelas = "$NOME_TABELA INNER JOIN ${TabelaPaciente.NOME_TABELA} ON ${TabelaPaciente.NOME_TABELA}.${BaseColumns._ID}=${CAMPO_ID_PACIENTE} INNER JOIN ${TabelaFabricante.NOME_TABELA} ON ${TabelaFabricante.NOME_TABELA}.${BaseColumns._ID}=${CAMPO_ID_FABRICANTE}"

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
        const val NOME_TABELA = "vacina"
        const val NUM_LOTE = "numero_lote"
        const val DATA_VACINACAO = "data_vacinacao"
        const val CAMPO_ID_PACIENTE = "id_paciente"
        const val CAMPO_ID_FABRICANTE = "id_fabricante"
        const val CAMPO_EXTERNO_NOME_PACIENTE = "nome_paciente"
        const val CAMPO_EXTERNO_NOME_FABRICANTE = "nome_fabricante"


        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, NUM_LOTE, DATA_VACINACAO, CAMPO_ID_PACIENTE, CAMPO_ID_FABRICANTE, CAMPO_EXTERNO_NOME_PACIENTE, CAMPO_EXTERNO_NOME_FABRICANTE)
    }
}