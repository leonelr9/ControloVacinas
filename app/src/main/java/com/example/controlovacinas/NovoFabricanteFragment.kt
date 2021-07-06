package com.example.controlovacinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.controlovacinas.databinding.FragmentNovoFabricanteBinding
import com.google.android.material.snackbar.Snackbar


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
        val nome = editTextNome.text.toString()
        if (nome.isEmpty()) {
            editTextNome.setError(getString(R.string.introduza_nome_fabricante))
            editTextNome.requestFocus()
            return
        }

        val fabricante = Fabricante(nome = nome)

        val uri = activity?.contentResolver?.insert(
            ContentProviderControloVacinas.ENDERECO_FABRICANTE,
            fabricante.toContentValues()
        )

        if (uri == null) {
            Snackbar.make(
                editTextNome,
                getString(R.string.erro_inserir_fabricante),
                Snackbar.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(
            requireContext(),
            getString(R.string.fabricante_guardado),
            Toast.LENGTH_LONG
        ).show()
        navegaListaFabricante()
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