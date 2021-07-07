package com.example.controlovacinas

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass.
 * Use the [EliminaEfeitosSecundariosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminaEfeitosSecundariosFragment : Fragment() {

    private lateinit var textViewNomePacienteVacina: TextView
    private lateinit var textViewNomeFabricanteVacinaEfeitosSecundarios: TextView
    private lateinit var textViewNumLoteEfeitos: TextView

    private lateinit var checkBoxFebre: CheckBox
    private lateinit var checkBoxFadiga: CheckBox
    private lateinit var checkBoxDorCabeca: CheckBox
    private lateinit var checkBoxDoresMoscular: CheckBox
    private lateinit var checkBoxCalafrios: CheckBox
    private lateinit var checkBoxDiarreia: CheckBox
    private lateinit var checkBoxDorBraco: CheckBox

    private lateinit var textViewOutrosSintomas: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_elimina_efeitos_secundarios

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_elimina_efeitos_secundarios, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewNomePacienteVacina = view.findViewById(R.id.textViewNomePacienteVacina)
        textViewNomeFabricanteVacinaEfeitosSecundarios = view.findViewById(R.id.textViewNomeFabricanteVacinaEfeitosSecundarios)
        textViewNumLoteEfeitos = view.findViewById(R.id.textViewNumLoteEfeitos)

        checkBoxFebre = view.findViewById(R.id.checkBoxFebre)
        checkBoxFebre.isClickable = false
        checkBoxFadiga = view.findViewById(R.id.checkBoxFadiga)
        checkBoxFadiga.isClickable = false
        checkBoxDorCabeca = view.findViewById(R.id.checkBoxDorCabeca)
        checkBoxDorCabeca.isClickable = false
        checkBoxDoresMoscular = view.findViewById(R.id.checkBoxDoresMosculares)
        checkBoxDoresMoscular.isClickable = false
        checkBoxCalafrios = view.findViewById(R.id.checkBoxCalafrios)
        checkBoxCalafrios.isClickable = false
        checkBoxDiarreia = view.findViewById(R.id.checkBoxDiarreia)
        checkBoxDiarreia.isClickable = false
        checkBoxDorBraco = view.findViewById(R.id.checkBoxDorBraco)
        checkBoxDorBraco.isClickable = false

        textViewOutrosSintomas = view.findViewById(R.id.textViewOutrosSintomas)

        val efeitosSecundarios = DadosApp.efeitosSecundariosSeleccionado!!
        textViewNomePacienteVacina.setText(efeitosSecundarios.nomePacienteVacina)
        textViewNomeFabricanteVacinaEfeitosSecundarios.setText(efeitosSecundarios.nomeFabricanteVacina)
        textViewNumLoteEfeitos.setText(efeitosSecundarios.numLote)

        if (efeitosSecundarios.febre){ (checkBoxFebre.isChecked) = true}
        if (efeitosSecundarios.fadiga){ (checkBoxFadiga.isChecked) = true}
        if (efeitosSecundarios.dor_cabeca){ (checkBoxDorCabeca.isChecked) = true}
        if (efeitosSecundarios.dores_mosculares){ (checkBoxDoresMoscular.isChecked) = true}
        if (efeitosSecundarios.calafrios){ (checkBoxCalafrios.isChecked) = true}
        if (efeitosSecundarios.diarreia){ (checkBoxDiarreia.isChecked) = true}
        if (efeitosSecundarios.dor_braco){ (checkBoxDorBraco.isChecked) = true}

        textViewOutrosSintomas.setText(efeitosSecundarios.outro)
    }

    fun navegaListaEfeitosSecundarios() {
        findNavController().navigate(R.id.action_eliminaEfeitosSecundariosFragment_to_listaEfeitosSecundariosFragment)
    }

    fun elimina() {
        val uriEfeitosSecundarios = Uri.withAppendedPath(
            ContentProviderControloVacinas.ENDERECO_EFEITOS_SECUNDARIOS,
            DadosApp.efeitosSecundariosSeleccionado!!.id.toString()
        )

        val registos = activity?.contentResolver?.delete(
            uriEfeitosSecundarios,
            null,
            null
        )

        if (registos != 1) {
            Toast.makeText(
                requireContext(),
                getString(R.string.erro_eliminar_efeitos_secundarios),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(
            requireContext(),
            getString(R.string.efeitos_secundarios_eliminados_sucesso),
            Toast.LENGTH_LONG
        ).show()
        navegaListaEfeitosSecundarios()
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_confirma_eliminar_efeitos_secundarios -> elimina()
            R.id.action_cancelar_eliminar_efeitos_secundarios -> navegaListaEfeitosSecundarios()
            else -> return false
        }

        return true
    }
}