package com.example.controlovacinas

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass.
 * Use the [EditaFabricanteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditaFabricanteFragment : Fragment() {

    private lateinit var editTextNome: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_edita_fabricante

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edita_fabricante, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextNome = view.findViewById(R.id.editTextNome)

        editTextNome.setText(DadosApp.fabricanteSeleccionado!!.nome)
    }

    fun navegaListaFabricante() {
        findNavController().navigate(R.id.action_editaFabricanteFragment_to_listaFabricantesFragment)
    }

    fun guardar() {
        val nome = editTextNome.text.toString()
        if (nome.isEmpty()) {
            editTextNome.setError(getString(R.string.introduza_nome_fabricante))
            editTextNome.requestFocus()
            return
        }

        val fabricante = DadosApp.fabricanteSeleccionado!!
        fabricante.nome = nome

        val uriFabricante = Uri.withAppendedPath(
            ContentProviderControloVacinas.ENDERECO_FABRICANTE,
            fabricante.id.toString()
        )
        val registos = activity?.contentResolver?.update(
            uriFabricante,
            fabricante.toContentValues(),
            null,
            null
        )

        if (registos != 1) {
            Toast.makeText(
                requireContext(),
                getString(R.string.erro_alterar_fabricante),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(
            requireContext(),
            getString(R.string.fabricante_guardado_sucesso),
            Toast.LENGTH_LONG
        ).show()
        navegaListaFabricante()
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_edita_fabricante -> guardar()
            R.id.action_cancelar_edita_fabricante -> navegaListaFabricante()
            else -> return false
        }

        return true
    }
}