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
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [EditaVacinaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditaVacinaFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private lateinit var spinnerPacienteEdita: Spinner
    private lateinit var spinnerFabricanteEdita: Spinner
    private lateinit var editTextNumLote: EditText
    private lateinit var calendarViewDataVacinacao: CalendarView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_edita_vacina

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edita_vacina, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinnerPacienteEdita = view.findViewById(R.id.spinnerPacienteEdita)
        spinnerFabricanteEdita = view.findViewById(R.id.spinnerFabricanteEdita)
        editTextNumLote = view.findViewById(R.id.editTextNumLote)
        calendarViewDataVacinacao = view.findViewById(R.id.calendarViewDataVacinacao)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_MANAGER_PACIENTES, null, this)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_MANAGER_FABRICANTES, null, this)

        editTextNumLote.setText(DadosApp.vacinaSeleccionada!!.num_lote)
    }

    fun navegaListaVacinas() {
        findNavController().navigate(R.id.action_editaVacinaFragment_to_listaVacinasFragment)
    }

    fun guardar() {
        val idPaciente = spinnerPacienteEdita.selectedItemId

        val idFabricante = spinnerFabricanteEdita.selectedItemId


        val numLote = editTextNumLote.text.toString()
        if (numLote.isEmpty()) {
            editTextNumLote.setError(getString(R.string.erro_introduzir_lote))
            editTextNumLote.requestFocus()
            return
        }

        val data_vacinacao = calendarViewDataVacinacao.date.toLong()

        val vacina = DadosApp.vacinaSeleccionada!!
        vacina.idPaciente = idPaciente
        vacina.idFabricante = idFabricante
        vacina.num_lote = numLote
        vacina.data_vacinacao = Date(data_vacinacao)

        val uriVacina = Uri.withAppendedPath(
            ContentProviderControloVacinas.ENDERECO_VACINA,
            vacina.id.toString()
        )

        val registos = activity?.contentResolver?.update(
            uriVacina,
            vacina.toContentValues(),
            null,
            null
        )

        if (registos != 1) {
            Toast.makeText(
                requireContext(),
                getString(R.string.erro_alterar_vacina),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(
            requireContext(),
            getString(R.string.vacina_guardada_sucesso),
            Toast.LENGTH_LONG
        ).show()
        navegaListaVacinas()
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_edita_vacina -> guardar()
            R.id.action_cancelar_edita_vacina -> navegaListaVacinas()
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
        when (id) {
            ID_LOADER_MANAGER_PACIENTES -> return CursorLoader(
                requireContext(),
                ContentProviderControloVacinas.ENDERECO_PACIENTE,
                TabelaPaciente.TODAS_COLUNAS,
                null, null,
                TabelaPaciente.NOME
            )
            ID_LOADER_MANAGER_FABRICANTES -> return CursorLoader(
                requireContext(),
                ContentProviderControloVacinas.ENDERECO_FABRICANTE,
                TabelaFabricante.TODAS_COLUNAS,
                null, null,
                TabelaFabricante.NOME
            )

        }
        return CursorLoader(requireContext())
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
        when(loader.id){
            ID_LOADER_MANAGER_PACIENTES -> atualizaSpinnerPacientes(data)
            ID_LOADER_MANAGER_FABRICANTES -> atualizaSpinnerFabricantes(data)
        }
        atualizaPacienteSelecionado()
        atualizaFabricanteSelecionado()
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
        atualizaSpinnerPacientes(null)
        atualizaSpinnerFabricantes(null)
    }

    private fun atualizaSpinnerPacientes(data: Cursor?) {
        spinnerPacienteEdita.adapter = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaPaciente.NOME),
            intArrayOf(android.R.id.text1),
            0
        )
    }

    private fun atualizaSpinnerFabricantes(data: Cursor?) {
        spinnerFabricanteEdita.adapter = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaFabricante.NOME),
            intArrayOf(android.R.id.text1),
            0
        )
    }

    private fun atualizaPacienteSelecionado() {
        val idPaciente = DadosApp.vacinaSeleccionada!!.idPaciente

        val ultimoPaciente = spinnerPacienteEdita.count - 1
        for (i in 0..ultimoPaciente) {
            if (idPaciente == spinnerPacienteEdita.getItemIdAtPosition(i)) {
                spinnerPacienteEdita.setSelection(i)
                return
            }
        }
    }

    private fun atualizaFabricanteSelecionado() {
        val idFabricante = DadosApp.vacinaSeleccionada!!.idFabricante

        val ultimoFabricante = spinnerFabricanteEdita.count - 1
        for (i in 0..ultimoFabricante) {
            if (idFabricante == spinnerFabricanteEdita.getItemIdAtPosition(i)) {
                spinnerFabricanteEdita.setSelection(i)
                return
            }
        }
    }

    companion object {
        const val ID_LOADER_MANAGER_PACIENTES = 0
        const val ID_LOADER_MANAGER_FABRICANTES = 1
    }

}