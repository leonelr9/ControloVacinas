package com.example.controlovacinas

import android.database.sqlite.SQLiteDatabase

class TabelaVacina(db: SQLiteDatabase?) {
    private val db: SQLiteDatabase? = db

    fun cria(){
        db?.execSQL("CREATE TABLE vacina(_id INTEGER PRIMARY KEY AUTOINCREMENT, fabricante TEXT NOT NULL, Num_serie INTEGER NOT NULL, data_vacinacao DATE)")
    }
}