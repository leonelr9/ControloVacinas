package com.example.controlovacinas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.controlovacinas.databinding.FragmentListaFabricantesBinding


class ListaFabricantesFragment : Fragment() {

    private var _binding: FragmentListaFabricantesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListaFabricantesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}