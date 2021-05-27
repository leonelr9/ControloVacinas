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
                DATA_VACINACAO + " DATE NOT NULL" +
                CAMPO_ID_PACIENTE + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + CAMPO_ID_PACIENTE + ") " +
                "REFERENCES " + TabelaPaciente.NOME_TABELA +
                CAMPO_ID_FABRICANTE + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + CAMPO_ID_FABRICANTE + ") " +
                "REFERENCES " + TabelaFabricante.NOME_TABELA +
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
        const val NOME_TABELA = "vacina"
        const val NUM_LOTE = "numero_lote"
        const val DATA_VACINACAO = "data_vacinacao"
        const val CAMPO_ID_PACIENTE = "id_paciente"
        const val CAMPO_ID_FABRICANTE = "id_fabricante"
    }
}