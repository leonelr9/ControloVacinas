package com.example.controlovacinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.controlovacinas.databinding.FragmentNovoPacienteBinding


/**
 * A simple [Fragment] subclass.
 * Use the [NovoPacienteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NovoPacienteFragment : Fragment() {

    private var _binding: FragmentNovoPacienteBinding? = null

    private lateinit var editTextNome: EditText
    private lateinit var calendarViewDataNascimento: CalendarView
    private lateinit var editTextSexo: EditText
    private lateinit var editTextContacto: EditText

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.novoPacienteFragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_novo_paciente

        _binding = FragmentNovoPacienteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextNome = view.findViewById(R.id.editTextNome)
        calendarViewDataNascimento = view.findViewById(R.id.calendarViewDataNascimento)
        editTextSexo = view.findViewById(R.id.editTextSexo)
        editTextContacto = view.findViewById(R.id.editTextContacto)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navegaListaPacientes() {
        findNavController().navigate(R.id.action_novoPacienteFragment_to_ListaPacientesFragment)
    }

    fun guardar() {
        // todo: guardar pacientes
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_novo_paciente -> guardar()
            R.id.action_cancelar_novo_paciente -> navegaListaPacientes()
            else -> return false
        }

        return true
    }
}