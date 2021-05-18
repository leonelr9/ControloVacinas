package com.example.controlovacinas

import android.database.sqlite.SQLiteDatabase

class TabelaPaciente (db: SQLiteDatabase?) {
    private val db: SQLiteDatabase? = db

    fun cria(){
        db?.execSQL("CREATE TABLE paciente(_id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, idade INTEGER NOT NULL, sexo TEXT NOT NULL, contacto TEXT NOT NULL)")
    }
}