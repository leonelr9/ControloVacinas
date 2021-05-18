package com.example.controlovacinas

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaVacina(db: SQLiteDatabase?) {
    private val db: SQLiteDatabase? = db

    fun cria(){
        db?.execSQL("CREATE TABLE " + NOME_TABELA + "(" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FABRICANTE + " TEXT NOT NULL, " +
                NUM_SERIE + " INTEGER NOT NULL, " +
                DATA_VACINACAO + " DATE)")
    }

    companion object{
        const val NOME_TABELA = "vacina"
        const val FABRICANTE = "fabricante"
        const val NUM_SERIE = "numero_serie"
        const val DATA_VACINACAO = "data_vacinacao"
    }
}