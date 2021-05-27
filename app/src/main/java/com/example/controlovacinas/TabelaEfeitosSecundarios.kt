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
        selection: String,
        selectionArgs: Array<String>,
        groupBy: String,
        having: String,
        orderBy: String
    ): Cursor? {
        return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy)
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
    }
}