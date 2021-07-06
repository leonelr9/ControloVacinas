package com.example.controlovacinas

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.controlovacinas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var menu: Menu

    var menuAtual = R.menu.menu_main
        set(value) {
            field = value
            invalidateOptionsMenu()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        DadosApp.activity = this
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(menuAtual, menu)
        this.menu = menu

        if (menuAtual == R.menu.menu_lista_pacientes) {
            atualizaMenuListaPacientes(false)
        }
        if (menuAtual == R.menu.menu_lista_fabricantes) {
            atualizaMenuListaFabricantes(false)
        }
        if (menuAtual == R.menu.menu_lista_vacinas) {
            atualizaMenuListaVacinas(false)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val opcaoProcessada = when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, getString(R.string.versao), Toast.LENGTH_LONG).show()
                true
            }


            else -> when(menuAtual) {
                R.menu.menu_main -> (DadosApp.fragment as PaginaInicialFragment).processaOpcaoMenu(item)

                R.menu.menu_lista_pacientes -> (DadosApp.fragment as ListaPacientesFragment).processaOpcaoMenu(item)
                R.menu.menu_novo_paciente -> (DadosApp.fragment as NovoPacienteFragment).processaOpcaoMenu(item)
                R.menu.menu_edita_paciente -> (DadosApp.fragment as EditaPacienteFragment).processaOpcaoMenu(item)
                R.menu.menu_elimina_paciente -> (DadosApp.fragment as EliminaPacienteFragment).processaOpcaoMenu(item)

                R.menu.menu_lista_fabricantes -> (DadosApp.fragment as ListaFabricantesFragment).processaOpcaoMenu(item)
                R.menu.menu_novo_fabricante -> (DadosApp.fragment as NovoFabricanteFragment).processaOpcaoMenu(item)
                R.menu.menu_edita_fabricante -> (DadosApp.fragment as EditaFabricanteFragment).processaOpcaoMenu(item)
                R.menu.menu_elimina_fabricante -> (DadosApp.fragment as EliminaFabricanteFragment).processaOpcaoMenu(item)

                R.menu.menu_lista_vacinas -> (DadosApp.fragment as ListaVacinasFragment).processaOpcaoMenu(item)
                R.menu.menu_nova_vacina -> (DadosApp.fragment as NovaVacinaFragment).processaOpcaoMenu(item)
                else -> false
            }
        }
        return  if (opcaoProcessada) true else super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun atualizaMenuListaPacientes(mostraBotoesAlterarEliminar : Boolean) {
        menu.findItem(R.id.action_alterar_paciente).setVisible(mostraBotoesAlterarEliminar)
        menu.findItem(R.id.action_eliminar_paciente).setVisible(mostraBotoesAlterarEliminar)
    }

    fun atualizaMenuListaFabricantes(mostraBotoesAlterarEliminar : Boolean) {
        menu.findItem(R.id.action_alterar_fabricante).setVisible(mostraBotoesAlterarEliminar)
        menu.findItem(R.id.action_eliminar_fabricante).setVisible(mostraBotoesAlterarEliminar)
    }

    fun atualizaMenuListaVacinas(mostraBotoesAlterarEliminar : Boolean) {
        menu.findItem(R.id.action_alterar_vacina).setVisible(mostraBotoesAlterarEliminar)
        menu.findItem(R.id.action_eliminar_vacina).setVisible(mostraBotoesAlterarEliminar)
    }
}