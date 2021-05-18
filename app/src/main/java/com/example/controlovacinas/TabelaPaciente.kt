package com.example.controlovacinas

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaPaciente (db: SQLiteDatabase) {
    private val db: SQLiteDatabase = db

    fun cria(){
        db.execSQL("CREATE TABLE " + NOME_TABELA + "(" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME + " TEXT NOT NULL, " +
                IDADE + " INTEGER NOT NULL, " +
                SEXO + " TEXT NOT NULL, " +
                CONTACTO + " TEXT NOT NULL, " +
                CAMPO_ID_VACINA + " INTEGER NOT NULL, " +
                "FOREING KEY(" + CAMPO_ID_VACINA + ") " +
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
        const val NOME_TABELA = "paciente"
        const val NOME = "nome"
        const val IDADE = "idade"
        const val SEXO = "sexo"
        const val CONTACTO = "contacto"
        const val CAMPO_ID_VACINA = "id_vacina"
    }
}