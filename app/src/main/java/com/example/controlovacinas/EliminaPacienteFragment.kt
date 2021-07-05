package com.example.controlovacinas

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [EliminaPacienteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminaPacienteFragment : Fragment() {
    private lateinit var textViewNome: TextView
    private lateinit var textViewDataNascimento: TextView
    private lateinit var textViewSexo: TextView
    private lateinit var textViewContacto: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_elimina_paciente

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_elimina_paciente, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewNome = view.findViewById(R.id.textViewNome)
        textViewDataNascimento = view.findViewById(R.id.textViewDataNascimento)
        textViewSexo = view.findViewById(R.id.textViewSexo)
        textViewContacto = view.findViewById(R.id.textViewContacto)

        val paciente = DadosApp.pacienteSeleccionado!!
        textViewNome.setText(paciente.nome)
        textViewDataNascimento.setText(paciente.data_nascimento.toString())
        textViewSexo.setText(paciente.sexo)
        textViewContacto.setText(paciente.contacto)
    }


    fun navegaListaPacientes() {
        findNavController().navigate(R.id.action_eliminaPacienteFragment_to_ListaPacientesFragment)
    }

    fun elimina() {
        val uriPaciente = Uri.withAppendedPath(
            ContentProviderControloVacinas.ENDERECO_PACIENTE,
            DadosApp.pacienteSeleccionado!!.id.toString()
        )

        val registos = activity?.contentResolver?.delete(
            uriPaciente,
            null,
            null
        )

        if (registos != 1) {
            Toast.makeText(
                requireContext(),
                getString(R.string.erro_elimina_paciente),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(
            requireContext(),
            getString(R.string.paciente_eliminado_sucesso),
            Toast.LENGTH_LONG
        ).show()
        navegaListaPacientes()
        }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_confirma_eliminar_paciente -> elimina()
            R.id.action_cancelar_eliminar_paciente -> navegaListaPacientes()
            else -> return false
        }

        return true
    }

}