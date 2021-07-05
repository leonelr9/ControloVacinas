package com.example.controlovacinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.controlovacinas.databinding.FragmentInicialBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class PaginaInicialFragment : Fragment() {

    private var _binding: FragmentInicialBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_main

        _binding = FragmentInicialBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonPacientes.setOnClickListener {
            findNavController().navigate(R.id.action_PaginaInicialFragment_to_ListaPacientesFragment)
        }

        binding.buttonFabricantes.setOnClickListener{
            findNavController().navigate(R.id.action_PaginaInicialFragment_to_ListaFabricantesFragment)
        }

        binding.buttonVacinas.setOnClickListener{
            findNavController().navigate(R.id.action_PaginaInicialFragment_to_listaVacinasFragment)
        }

        binding.buttonEfeitosSecundarios.setOnClickListener{
            findNavController().navigate(R.id.action_PaginaInicialFragment_to_listaEfeitosSecundariosFragment)
        }
    }


    fun processaOpcaoMenu(item: MenuItem): Boolean {
        return false
    }
}