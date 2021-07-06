package com.example.controlovacinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.controlovacinas.databinding.FragmentNovaVacinaBinding
import com.example.controlovacinas.databinding.FragmentNovoFabricanteBinding


/**
 * A simple [Fragment] subclass.
 * Use the [NovaVacinaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NovaVacinaFragment : Fragment() {

    private var _binding: FragmentNovaVacinaBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNovaVacinaBinding.inflate(inflater, container, false)
        return binding.root
    }

}