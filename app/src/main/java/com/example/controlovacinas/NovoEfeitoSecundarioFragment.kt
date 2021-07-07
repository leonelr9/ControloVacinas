package com.example.controlovacinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.controlovacinas.databinding.FragmentNovaVacinaBinding
import com.example.controlovacinas.databinding.FragmentNovoEfeitoSecundarioBinding

/**
 * A simple [Fragment] subclass.
 * Use the [NovoEfeitoSecundarioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NovoEfeitoSecundarioFragment : Fragment() {

    private var _binding: FragmentNovoEfeitoSecundarioBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_novo_efeito_secundario

        _binding = FragmentNovoEfeitoSecundarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navegaListaEfeitosSecundarios() {
        findNavController().navigate(R.id.action_novoEfeitoSecundarioFragment_to_listaEfeitosSecundariosFragment)
    }

    fun guardar() {
        // todo: guardar efeitos secundarios
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_novo_efeito_secundario -> guardar()
            R.id.action_cancelar_novo_efeito_secundario -> navegaListaEfeitosSecundarios()
            else -> return false
        }

        return true
    }
}