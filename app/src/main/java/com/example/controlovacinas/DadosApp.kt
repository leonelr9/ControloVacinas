package com.example.controlovacinas

class DadosApp {
    companion object {
        lateinit var activity: MainActivity
        var listaPacientesFragment: ListaPacientesFragment? = null
        var novoPacienteFragment: NovoPacienteFragment? = null

        var pacienteSeleccionado : Paciente? = null

    }
}