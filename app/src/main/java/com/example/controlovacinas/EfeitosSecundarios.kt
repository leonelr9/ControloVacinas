package com.example.controlovacinas

data class EfeitosSecundarios (var id: Long, var febre: Boolean, var fadiga: Boolean, var dor_cabeca: Boolean, var dores_mosculares: Boolean, var calafrios: Boolean, var diarreia: Boolean, var dor_braco: Boolean, var outro: String, var idVacina: Long){
}