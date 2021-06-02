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

//        assertNotEquals(-1, id)

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

}