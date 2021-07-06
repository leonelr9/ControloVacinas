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
 * Use the [EliminaVacinaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminaVacinaFragment : Fragment() {

    private lateinit var textViewNomePaciente: TextView
    private lateinit var textViewNomeFabricante: TextView
    private lateinit var textViewNumLote: TextView
    private lateinit var textViewDataVacinacao: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_elimina_vacina

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_elimina_vacina, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewNomePaciente = view.findViewById(R.id.textViewNomePaciente)
        textViewNomeFabricante = view.findViewById(R.id.textViewNomeFabricante)
        textViewNumLote = view.findViewById(R.id.textViewNumLote)
        textViewDataVacinacao = view.findViewById(R.id.textViewDataVacinacao)

        val vacina = DadosApp.vacinaSeleccionada!!
        textViewNomePaciente.setText(vacina.nomePaciente)
        textViewNomeFabricante.setText(vacina.nomeFabricante)
        textViewNumLote.setText(vacina.num_lote)
        textViewDataVacinacao.setText(vacina.data_vacinacao.toString())
    }

    fun navegaListaVacinas() {
        findNavController().navigate(R.id.action_eliminaVacinaFragment_to_listaVacinasFragment)
    }

    fun elimina() {
        val uriVacina = Uri.withAppendedPath(
            ContentProviderControloVacinas.ENDERECO_VACINA,
            DadosApp.vacinaSeleccionada!!.id.toString()
        )

        val registos = activity?.contentResolver?.delete(
            uriVacina,
            null,
            null
        )

        if (registos != 1) {
            Toast.makeText(
                requireContext(),
                getString(R.string.erro_eliminar_vacina),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(
            requireContext(),
            getString(R.string.vacina_eliminada_sucesso),
            Toast.LENGTH_LONG
        ).show()
        navegaListaVacinas()
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_confirma_eliminar_vacina -> elimina()
            R.id.action_cancelar_eliminar_vacina -> navegaListaVacinas()
            else -> return false
        }

        return true
    }
}