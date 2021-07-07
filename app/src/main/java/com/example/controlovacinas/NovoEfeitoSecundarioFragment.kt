package com.example.controlovacinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
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

    private lateinit var spinnerVacina: Spinner
    private lateinit var checkBoxFebre: CheckBox
    private lateinit var checkBoxFadiga: CheckBox
    private lateinit var checkBoxDorCabeca: CheckBox
    private lateinit var checkBoxDoresMoscular: CheckBox
    private lateinit var checkBoxCalafrios: CheckBox
    private lateinit var checkBoxDiarreia: CheckBox
    private lateinit var checkBoxDorBraco: CheckBox
    private lateinit var editTextOutros: EditText


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinnerVacina = view.findViewById(R.id.spinnerVacina)
        checkBoxFebre = view.findViewById(R.id.checkBoxFebre)
        checkBoxFadiga = view.findViewById(R.id.checkBoxFadiga)
        checkBoxDorCabeca = view.findViewById(R.id.checkBoxDorCabeca)
        checkBoxDoresMoscular = view.findViewById(R.id.checkBoxDoresMosculares)
        checkBoxCalafrios = view.findViewById(R.id.checkBoxCalafrios)
        checkBoxDiarreia = view.findViewById(R.id.checkBoxDiarreia)
        checkBoxDorBraco = view.findViewById(R.id.checkBoxDorBraco)
        editTextOutros = view.findViewById(R.id.editTextOutros)
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