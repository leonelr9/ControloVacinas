package com.example.controlovacinas

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaPaciente (db: SQLiteDatabase?) {
    private val db: SQLiteDatabase? = db

    fun cria(){
        db?.execSQL("CREATE TABLE " + NOME_TABELA + "(" +
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

    companion object{
        const val NOME_TABELA = "paciente"
        const val NOME = "nome"
        const val IDADE = "idade"
        const val SEXO = "sexo"
        const val CONTACTO = "contacto"
        const val CAMPO_ID_VACINA = "id_vacina"
    }
}