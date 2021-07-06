package com.example.controlovacinas

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController


/**
 * A simple [Fragment] subclass.
 * Use the [EliminaFabricanteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminaFabricanteFragment : Fragment() {

    private lateinit var textViewNome: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_elimina_fabricante

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_elimina_fabricante, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewNome = view.findViewById(R.id.textViewNome)

        val fabricante = DadosApp.fabricanteSeleccionado!!
        textViewNome.setText(fabricante.nome)
    }


    fun navegaListaFabricantes() {
        findNavController().navigate(R.id.action_eliminaFabricanteFragment_to_listaFabricantesFragment)
    }

    fun elimina() {
        val uriFabricante = Uri.withAppendedPath(
            ContentProviderControloVacinas.ENDERECO_FABRICANTE,
            DadosApp.fabricanteSeleccionado!!.id.toString()
        )

        val registos = activity?.contentResolver?.delete(
            uriFabricante,
            null,
            null
        )

        if (registos != 1) {
            Toast.makeText(
                requireContext(),
                getString(R.string.erro_eliminar_fabricante),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(
            requireContext(),
            getString(R.string.fabricante_eliminado_sucesso),
            Toast.LENGTH_LONG
        ).show()
        navegaListaFabricantes()
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_confirma_eliminar_fabricante -> elimina()
            R.id.action_cancelar_eliminar_fabricante -> navegaListaFabricantes()
            else -> return false
        }

        return true
    }
}