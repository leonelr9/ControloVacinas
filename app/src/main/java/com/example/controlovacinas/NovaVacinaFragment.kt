package com.example.controlovacinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import com.example.controlovacinas.databinding.FragmentNovaVacinaBinding
import com.example.controlovacinas.databinding.FragmentNovoFabricanteBinding


/**
 * A simple [Fragment] subclass.
 * Use the [NovaVacinaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NovaVacinaFragment : Fragment() {

    private var _binding: FragmentNovaVacinaBinding? = null

    private lateinit var spinnerPaciente: Spinner
    private lateinit var spinnerFabricante: Spinner
    private lateinit var editTextNumLote: EditText
    private lateinit var calendarViewDataVacinacao: CalendarView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_nova_vacina

        _binding = FragmentNovaVacinaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinnerPaciente = view.findViewById(R.id.spinnerPaciente)
        spinnerFabricante = view.findViewById(R.id.spinnerFabricante)
        editTextNumLote = view.findViewById(R.id.editTextNumLote)
        calendarViewDataVacinacao = view.findViewById(R.id.calendarViewDataVacinacao)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navegaListaVacinas() {
        findNavController().navigate(R.id.action_novaVacinaFragment_to_listaVacinasFragment)
    }

    fun guardar() {
        // todo: guardar fabricante
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_nova_vacina -> guardar()
            R.id.action_cancelar_nova_vacina -> navegaListaVacinas()
            else -> return false
        }

        return true
    }
}