package com.example.controlovacinas

import android.database.sqlite.SQLiteDatabase

class TabelaEfeitosSecundarios(db: SQLiteDatabase?) {
    private val db: SQLiteDatabase? = db

    fun cria(){
        db?.execSQL("CREATE TABLE efeitos_secundarios(_id INTEGER PRIMARY KEY AUTOINCREMENT, febre BOOLEAN, fadiga BOOLEAN, dor_de_cabeca BOOLEAN, dores_mosculares BOOLEAN, calafrios BOOLEAN, diarreia BOOLEAN, dor_no_braco BOOLEAN)")
    }
}