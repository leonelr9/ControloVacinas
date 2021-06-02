package com.example.controlovacinas

import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TestesBaseDados {
    private fun getAppContext() = InstrumentationRegistry.getInstrumentation().targetContext
    private fun getBdControloVacinasOpenHelper() = BdControloVacinasOpenHelper(getAppContext())


    private fun insereFabricante(tabela: TabelaFabricante, fabricante: Fabricante): Long {
        val id = tabela.insert(fabricante.toContentValues())
        assertNotEquals(-1, id)

        return id
    }

    private fun getFabricanteBaseDados(tabela: TabelaFabricante, id: Long): Fabricante {
        val cursor = tabela.query(
            TabelaFabricante.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf(id.toString()),
            null, null, null
        )

        assertNotNull(cursor)
        assert(cursor!!.moveToNext())

        return Fabricante.fromCursor(cursor)
    }


    private fun inserePaciente(tabela: TabelaPaciente, paciente: Paciente): Long {
        val id = tabela.insert(paciente.toContentValues())
        assertNotEquals(-1, id)

        return id
    }

    private fun getPacienteBaseDados(tabela: TabelaPaciente, id: Long): Paciente {
        val cursor = tabela.query(
            TabelaPaciente.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf(id.toString()),
            null, null, null
        )

        assertNotNull(cursor)
        assert(cursor!!.moveToNext())

        return Paciente.fromCursor(cursor)
    }

    @Before
    fun apagaBaseDados(){
        getAppContext().deleteDatabase(BdControloVacinasOpenHelper.NOME_BASE_DADOS)
    }

    @Test
    fun consegueAbrirBaseDados() {
        val openHelper = BdControloVacinasOpenHelper(getAppContext())
        val db = openHelper.readableDatabase
        assert(db.isOpen)
        db.close()
    }

    @Test
    fun consegueInserirFabricante() {
        val db = getBdControloVacinasOpenHelper().writableDatabase
        val tabelaFabricante= TabelaFabricante(db)

        val fabricante = Fabricante(nome = "AstraZeneca")
        fabricante.id = insereFabricante(tabelaFabricante, fabricante)

        assertEquals(fabricante, getFabricanteBaseDados(tabelaFabricante, fabricante.id))

        db.close()
    }

    @Test
    fun consegueAlterarFabricante() {
        val db = getBdControloVacinasOpenHelper().writableDatabase
        val tabelaFabricante= TabelaFabricante(db)

        val fabricante = Fabricante(nome = "Pfizer")
        fabricante.id = insereFabricante(tabelaFabricante, fabricante)

        fabricante.nome = "Pfizer-Biontech"

        val registosAlterados = tabelaFabricante.update(
            fabricante.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(fabricante.id.toString())
        )

        assertEquals(1, registosAlterados)

        assertEquals(fabricante, getFabricanteBaseDados(tabelaFabricante, fabricante.id))

        db.close()
    }


    @Test
    fun consegueEliminarFabricante() {
        val db = getBdControloVacinasOpenHelper().writableDatabase
        val tabelaFabricante= TabelaFabricante(db)

        val fabricante = Fabricante(nome = "Teste")
        fabricante.id = insereFabricante(tabelaFabricante, fabricante)


        val registosEliminados = tabelaFabricante.delete(
            "${BaseColumns._ID}=?",
            arrayOf(fabricante.id.toString())
        )

        assertEquals(1, registosEliminados)
        db.close()
    }

    @Test
    fun consegueLerFabricante() {
        val db = getBdControloVacinasOpenHelper().writableDatabase
        val tabelaFabricante= TabelaFabricante(db)

        val fabricante = Fabricante(nome = "Moderna")
        fabricante.id = insereFabricante(tabelaFabricante, fabricante)

        assertEquals(fabricante, getFabricanteBaseDados(tabelaFabricante, fabricante.id))

        db.close()
    }

    @Test
    fun consegueInserirPaciente() {
        val db = getBdControloVacinasOpenHelper().writableDatabase
        val tabelaPaciente= TabelaPaciente(db)

        val paciente = Paciente(nome = "José", data_nascimento = 4/7/1960, sexo = "Masculino", contacto = "961258976")
        paciente.id = inserePaciente(tabelaPaciente, paciente)

        assertEquals(paciente, getPacienteBaseDados(tabelaPaciente, paciente.id))

        db.close()
    }

    @Test
    fun consegueAlterarPaciente() {
        val db = getBdControloVacinasOpenHelper().writableDatabase
        val tabelaPaciente= TabelaPaciente(db)

        val paciente = Paciente(nome = "José", data_nascimento = 4/7/1960, sexo = "Masculino", contacto = "961258976")
        paciente.id = inserePaciente(tabelaPaciente, paciente)

        paciente.contacto = "927891593"
        paciente.data_nascimento = 5/5/1955

        val registosAlterados = tabelaPaciente.update(
            paciente.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(paciente.id.toString())
        )

        assertEquals(1, registosAlterados)

        assertEquals(paciente, getPacienteBaseDados(tabelaPaciente, paciente.id))

        db.close()
    }
}