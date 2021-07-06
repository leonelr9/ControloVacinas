package com.example.controlovacinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.controlovacinas.databinding.FragmentNovoFabricanteBinding


/**
 * A simple [Fragment] subclass.
 * Use the [NovoFabricanteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NovoFabricanteFragment : Fragment() {

    private var _binding: FragmentNovoFabricanteBinding? = null

    private lateinit var editTextNome: EditText

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_novo_fabricante

        _binding = FragmentNovoFabricanteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextNome = view.findViewById(R.id.editTextNome)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navegaListaFabricante() {
        findNavController().navigate(R.id.action_novoFabricanteFragment_to_listaFabricantesFragment)
    }

    fun guardar() {
        // todo: guardar fabricante
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_novo_fabricante -> guardar()
            R.id.action_cancelar_novo_fabricante -> navegaListaFabricante()
            else -> return false
        }

        return true
    }

}