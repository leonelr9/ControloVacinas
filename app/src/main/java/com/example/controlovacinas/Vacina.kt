package com.example.controlovacinas

import java.util.*

data class Vacina (var id: Long, var num_lote: String, var data_vacinacao: Date, var idPaciente: Long, var idFabricante: Long) {
}