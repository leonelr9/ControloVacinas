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
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [EditaPacienteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditaPacienteFragment : Fragment() {

    private lateinit var editTextNome: EditText
    private lateinit var calendarViewDataNascimento: CalendarView
    private lateinit var editTextSexo: EditText
    private lateinit var editTextContacto: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_edita_paciente

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edita__paciente_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextNome = view.findViewById(R.id.editTextNome)
        calendarViewDataNascimento = view.findViewById(R.id.calendarViewDataNascimento)
        editTextSexo = view.findViewById(R.id.editTextSexo)
        editTextContacto = view.findViewById(R.id.editTextContacto)
    }


    fun navegaListaPacientes() {
        findNavController().navigate(R.id.action_editaPacienteFragment_to_ListaPacientesFragment)
    }

    fun guardar() {
        val nome = editTextNome.text.toString()
        if (nome.isEmpty()) {
            editTextNome.setError(getString(R.string.introduza_nome_paciente))
            editTextNome.requestFocus()
            return
        }

        val data_nascimento = calendarViewDataNascimento.date.toLong()

        val sexo = editTextSexo.text.toString()
        if (sexo.isEmpty()) {
            editTextSexo.setError(getString(R.string.introduza_sexo))
            editTextSexo.requestFocus()
            return
        }

        val contacto = editTextContacto.text.toString()
        if (contacto.isEmpty()) {
            editTextContacto.setError(getString(R.string.introduza_contacto))
            editTextContacto.requestFocus()
            return
        }

        val paciente = DadosApp.pacienteSeleccionado!!
        paciente.nome = nome
        paciente.data_nascimento = Date(data_nascimento)
        paciente.sexo = sexo
        paciente.contacto = contacto

        val uriPaciente = Uri.withAppendedPath(
            ContentProviderControloVacinas.ENDERECO_PACIENTE,
            paciente.id.toString()
        )

        val registos = activity?.contentResolver?.update(
            uriPaciente,
            paciente.toContentValues(),
            null,
            null
        )

        if (registos != 1) {
            Toast.makeText(
                requireContext(),
                getString(R.string.erro_alterar_paciente),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(
            requireContext(),
            getString(R.string.paciente_guardado_sucesso),
            Toast.LENGTH_LONG
        ).show()
        navegaListaPacientes()
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_edita_paciente -> guardar()
            R.id.action_cancelar_edita_paciente -> navegaListaPacientes()
            else -> return false
        }

        return true
    }

}