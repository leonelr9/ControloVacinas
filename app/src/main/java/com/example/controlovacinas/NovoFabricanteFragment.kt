package com.example.controlovacinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.controlovacinas.databinding.FragmentNovoFabricanteBinding


/**
 * A simple [Fragment] subclass.
 * Use the [NovoFabricanteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NovoFabricanteFragment : Fragment() {

    private var _binding: FragmentNovoFabricanteBinding? = null

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


}