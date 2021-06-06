package com.example.controlovacinas

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.util.*


data class Paciente(var id:Long = -1, var nome: String, var data_nascimento: Date, var sexo: String, var contacto: String){
    fun toContentValues(): ContentValues {

        val valores = ContentValues().apply {
            put(TabelaPaciente.NOME, nome)
            put(TabelaPaciente.DATA_NASCIMENTO, data_nascimento.time)
            put(TabelaPaciente.SEXO, sexo)
            put(TabelaPaciente.CONTACTO, contacto)
        }

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Paciente {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colNome = cursor.getColumnIndex(TabelaPaciente.NOME)
            val colData = cursor.getColumnIndex(TabelaPaciente.DATA_NASCIMENTO)
            val colSexo = cursor.getColumnIndex(TabelaPaciente.SEXO)
            val colContacto = cursor.getColumnIndex(TabelaPaciente.CONTACTO)

            val id = cursor.getLong(colId)
            val nome = cursor.getString(colNome)
            val data = cursor.getLong(colData)
            val sexo = cursor.getString(colSexo)
            val contacto = cursor.getString(colContacto)


            return Paciente(id, nome, Date(data), sexo, contacto)
        }
    }
}