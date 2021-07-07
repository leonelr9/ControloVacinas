package com.example.controlovacinas

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass.
 * Use the [EditaEfeitosSecundariosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditaEfeitosSecundariosFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private lateinit var spinnerVacina: Spinner
    private lateinit var checkBoxFebre: CheckBox
    private lateinit var checkBoxFadiga: CheckBox
    private lateinit var checkBoxDorCabeca: CheckBox
    private lateinit var checkBoxDoresMoscular: CheckBox
    private lateinit var checkBoxCalafrios: CheckBox
    private lateinit var checkBoxDiarreia: CheckBox
    private lateinit var checkBoxDorBraco: CheckBox
    private lateinit var editTextOutros: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_edita_efeitos_secundarios
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edita_efeitos_secundarios, container, false)
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

        LoaderManager.getInstance(this).initLoader(ID_LOADER_MANAGER_VACINA, null, this)

        if (DadosApp.efeitosSecundariosSeleccionado!!.febre){ (checkBoxFebre.isChecked) = true}
        if (DadosApp.efeitosSecundariosSeleccionado!!.fadiga){ (checkBoxFadiga.isChecked) = true}
        if (DadosApp.efeitosSecundariosSeleccionado!!.dor_cabeca){ (checkBoxDorCabeca.isChecked) = true}
        if (DadosApp.efeitosSecundariosSeleccionado!!.dores_mosculares){ (checkBoxDoresMoscular.isChecked) = true}
        if (DadosApp.efeitosSecundariosSeleccionado!!.calafrios){ (checkBoxCalafrios.isChecked) = true}
        if (DadosApp.efeitosSecundariosSeleccionado!!.diarreia){ (checkBoxDiarreia.isChecked) = true}
        if (DadosApp.efeitosSecundariosSeleccionado!!.dor_braco){ (checkBoxDorBraco.isChecked) = true}
        editTextOutros.setText(DadosApp.efeitosSecundariosSeleccionado!!.outro)

    }

    fun navegaListaEfeitosSecundarios() {
        findNavController().navigate(R.id.action_editaEfeitosSecundariosFragment_to_listaEfeitosSecundariosFragment)
    }

    fun guardar() {
        val idVacina = spinnerVacina.selectedItemId

        val febre = checkBoxFebre.isChecked
        val fadiga = checkBoxFadiga.isChecked
        val dorCabeca = checkBoxDorCabeca.isChecked
        val doresMosculares = checkBoxDoresMoscular.isChecked
        val calafrios = checkBoxCalafrios.isChecked
        val diarreia = checkBoxDiarreia.isChecked
        val dorBraco = checkBoxDorBraco.isChecked

        val outros = editTextOutros.text.toString()

        val efeitosSecundarios = DadosApp.efeitosSecundariosSeleccionado!!
        efeitosSecundarios.idVacina = idVacina
        efeitosSecundarios.febre = febre
        efeitosSecundarios.fadiga = fadiga
        efeitosSecundarios.dor_cabeca = dorCabeca
        efeitosSecundarios.dores_mosculares =doresMosculares
        efeitosSecundarios.calafrios = calafrios
        efeitosSecundarios.diarreia = diarreia
        efeitosSecundarios.dor_braco = dorBraco
        efeitosSecundarios.outro = outros

        val uriEfeitosSecundarios = Uri.withAppendedPath(
            ContentProviderControloVacinas.ENDERECO_EFEITOS_SECUNDARIOS,
            efeitosSecundarios.id.toString()
        )

        val registos = activity?.contentResolver?.update(
            uriEfeitosSecundarios,
            efeitosSecundarios.toContentValues(),
            null,
            null
        )


        if (registos != 1) {
            Toast.makeText(
                requireContext(),
                "Erro ao alterar efeitos secundarios",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(
            requireContext(),
            "Efeitos secundarios alterados com sucesso",
            Toast.LENGTH_LONG
        ).show()

        navegaListaEfeitosSecundarios()
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_edita_efeito_secundario -> guardar()
            R.id.action_cancelar_edita_efeito_secundario -> navegaListaEfeitosSecundarios()
            else -> return false
        }

        return true
    }


    /**
     * Instantiate and return a new Loader for the given ID.
     *
     *
     * This will always be called from the process's main thread.
     *
     * @param id The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            requireContext(),
            ContentProviderControloVacinas.ENDERECO_VACINA,
            TabelaVacina.TODAS_COLUNAS,
            null, null,
            TabelaVacina.CAMPO_EXTERNO_NOME_PACIENTE
        )
    }

    /**
     * Called when a previously created loader has finished its load.  Note
     * that normally an application is *not* allowed to commit fragment
     * transactions while in this call, since it can happen after an
     * activity's state is saved.  See [ FragmentManager.openTransaction()][androidx.fragment.app.FragmentManager.beginTransaction] for further discussion on this.
     *
     *
     * This function is guaranteed to be called prior to the release of
     * the last data that was supplied for this Loader.  At this point
     * you should remove all use of the old data (since it will be released
     * soon), but should not do your own release of the data since its Loader
     * owns it and will take care of that.  The Loader will take care of
     * management of its data so you don't have to.  In particular:
     *
     *
     *  *
     *
     *The Loader will monitor for changes to the data, and report
     * them to you through new calls here.  You should not monitor the
     * data yourself.  For example, if the data is a [android.database.Cursor]
     * and you place it in a [android.widget.CursorAdapter], use
     * the [android.widget.CursorAdapter.CursorAdapter] constructor *without* passing
     * in either [android.widget.CursorAdapter.FLAG_AUTO_REQUERY]
     * or [android.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER]
     * (that is, use 0 for the flags argument).  This prevents the CursorAdapter
     * from doing its own observing of the Cursor, which is not needed since
     * when a change happens you will get a new Cursor throw another call
     * here.
     *  *  The Loader will release the data once it knows the application
     * is no longer using it.  For example, if the data is
     * a [android.database.Cursor] from a [android.content.CursorLoader],
     * you should not call close() on it yourself.  If the Cursor is being placed in a
     * [android.widget.CursorAdapter], you should use the
     * [android.widget.CursorAdapter.swapCursor]
     * method so that the old Cursor is not closed.
     *
     *
     *
     * This will always be called from the process's main thread.
     *
     * @param loader The Loader that has finished.
     * @param data The data generated by the Loader.
     */
    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        atualizaSpinnerVacina(data)
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     *
     * This will always be called from the process's main thread.
     *
     * @param loader The Loader that is being reset.
     */
    override fun onLoaderReset(loader: Loader<Cursor>) {
        atualizaSpinnerVacina(null)
    }

    private fun atualizaSpinnerVacina(data: Cursor?) {
        spinnerVacina.adapter = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaVacina.CAMPO_EXTERNO_NOME_PACIENTE),
            intArrayOf(android.R.id.text1),
            0
        )
    }

    companion object {
        const val ID_LOADER_MANAGER_VACINA = 0
    }

}