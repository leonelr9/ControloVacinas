package com.example.controlovacinas

import androidx.fragment.app.Fragment

class DadosApp {
    companion object {
        lateinit var activity: MainActivity
        lateinit var fragment: Fragment

        var pacienteSeleccionado : Paciente? = null
        var fabricanteSeleccionado : Fabricante? = null
        var vacinaSeleccionada : Vacina? = null
        var efeitosSecundariosSeleccionado : EfeitosSecundarios? = null
    }
}