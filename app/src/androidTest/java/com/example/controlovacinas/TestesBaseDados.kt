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

    private fun insereVacina(tabela: TabelaVacina, vacina: Vacina): Long {
        val id = tabela.insert(vacina.toContentValues())
        assertNotEquals(-1, id)

        return id
    }

    private fun getVacinaBaseDados(tabela: TabelaVacina, id: Long): Vacina {
        val cursor = tabela.query(
            TabelaVacina.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf(id.toString()),
            null, null, null
        )

        assertNotNull(cursor)
        assert(cursor!!.moveToNext())

        return Vacina.fromCursor(cursor)
    }

    private fun insereEfeitosSecundarios(tabela: TabelaEfeitosSecundarios, efeitosSecundarios: EfeitosSecundarios): Long {
        val id = tabela.insert(efeitosSecundarios.toContentValues())
        assertNotEquals(-1, id)

        return id
    }

    private fun getEfeitosSecundariosBaseDados(tabela: TabelaEfeitosSecundarios, id: Long): EfeitosSecundarios {
        val cursor = tabela.query(
            TabelaEfeitosSecundarios.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf(id.toString()),
            null, null, null
        )

        assertNotNull(cursor)
        assert(cursor!!.moveToNext())

        return EfeitosSecundarios.fromCursor(cursor)
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

        val paciente = Paciente(nome = "Maria", data_nascimento = 20/4/1955, sexo = "Feminino", contacto = "911898944")
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

    @Test
    fun consegueEliminarPaciente() {
        val db = getBdControloVacinasOpenHelper().writableDatabase
        val tabelaPaciente= TabelaPaciente(db)

        val paciente = Paciente(nome = "Marco", data_nascimento = 7/7/1968, sexo = "Masculino", contacto = "961478523")
        paciente.id = inserePaciente(tabelaPaciente, paciente)


        val registosEliminados = tabelaPaciente.delete(
            "${BaseColumns._ID}=?",
            arrayOf(paciente.id.toString())
        )

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueLerPaciente() {
        val db = getBdControloVacinasOpenHelper().writableDatabase
        val tabelaPaciente= TabelaPaciente(db)

        val paciente = Paciente(nome = "Rute", data_nascimento = 1/5/1990, sexo = "Feminino", contacto = "961258976")
        paciente.id = inserePaciente(tabelaPaciente, paciente)

        assertEquals(paciente, getPacienteBaseDados(tabelaPaciente, paciente.id))

        db.close()
    }


    @Test
    fun consegueInserirVacina() {
        val db = getBdControloVacinasOpenHelper().writableDatabase

        val tabelaFabricante = TabelaFabricante(db)
        val fabricante = Fabricante(nome = "AstraZeneca")
        fabricante.id = insereFabricante(tabelaFabricante, fabricante)

        val tabelaPaciente = TabelaPaciente(db)
        val paciente = Paciente(nome = "Alberto", data_nascimento = 4/10/1951, sexo = "Masculino", contacto = "961258976")
        paciente.id = inserePaciente(tabelaPaciente, paciente)

        val tabelaVacina = TabelaVacina(db)
        val vacina = Vacina(num_lote = "AB1257", data_vacinacao = 1/6/2021, idPaciente = paciente.id, idFabricante = fabricante.id)
        vacina.id = insereVacina(tabelaVacina, vacina)

        assertEquals(vacina, getVacinaBaseDados(tabelaVacina, vacina.id))

        db.close()
    }

    @Test
    fun consegueAlterarVacina() {
        val db = getBdControloVacinasOpenHelper().writableDatabase

        val tabelaFabricante = TabelaFabricante(db)

        val fabricanteModerna = Fabricante(nome = "Moderna")
        fabricanteModerna.id = insereFabricante(tabelaFabricante, fabricanteModerna)

        val fabricantePfizer = Fabricante(nome = "Pfizer")
        fabricantePfizer.id = insereFabricante(tabelaFabricante, fabricantePfizer)

        val tabelaPaciente = TabelaPaciente(db)

        val pacienteJonas = Paciente(nome = "Jonas", data_nascimento = 9/1/1974, sexo = "Masculino", contacto = "961258976")
        pacienteJonas.id = inserePaciente(tabelaPaciente, pacienteJonas)

        val pacienteRute = Paciente(nome = "Rute", data_nascimento = 1/5/1990, sexo = "Feminino", contacto = "961258976")
        pacienteRute.id = inserePaciente(tabelaPaciente, pacienteRute)


        val tabelaVacina = TabelaVacina(db)
        val vacina = Vacina(num_lote = "?", data_vacinacao = 1 , idPaciente = pacienteJonas.id, idFabricante = fabricanteModerna.id)
        vacina.id = insereVacina(tabelaVacina, vacina)

        vacina.num_lote = "MO5158L"
        vacina.data_vacinacao = 1/6/2021
        vacina.idPaciente = pacienteRute.id
        vacina.idFabricante = fabricantePfizer.id

        val registosAlterados = tabelaVacina.update(
            vacina.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(vacina.id.toString())
        )

        assertEquals(1, registosAlterados)

        assertEquals(vacina, getVacinaBaseDados(tabelaVacina, vacina.id))

        db.close()
    }

    @Test
    fun consegueEliminarVacina() {
        val db = getBdControloVacinasOpenHelper().writableDatabase

        val tabelaFabricante = TabelaFabricante(db)
        val fabricante = Fabricante(nome = "johnson")
        fabricante.id = insereFabricante(tabelaFabricante, fabricante)

        val tabelaPaciente = TabelaPaciente(db)
        val paciente = Paciente(nome = "Rute", data_nascimento = 1/5/1990, sexo = "Feminino", contacto = "961258976")
        paciente.id = inserePaciente(tabelaPaciente, paciente)


        val tabelaVacina = TabelaVacina(db)
        val vacina = Vacina(num_lote = "PP1257", data_vacinacao = 23/5/2021, idPaciente = paciente.id, idFabricante = fabricante.id)
        vacina.id = insereVacina(tabelaVacina, vacina)

        val registosEliminados = tabelaVacina.delete(
            "${BaseColumns._ID}=?",
            arrayOf(vacina.id.toString())
        )

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueLerVacina() {
        val db = getBdControloVacinasOpenHelper().writableDatabase

        val tabelaFabricante = TabelaFabricante(db)
        val fabricante = Fabricante(nome = "Curevac")
        fabricante.id = insereFabricante(tabelaFabricante, fabricante)

        val tabelaPaciente = TabelaPaciente(db)
        val paciente = Paciente(nome = "Bruno", data_nascimento = 4/5/1970, sexo = "Masculino", contacto = "911252973")
        paciente.id = inserePaciente(tabelaPaciente, paciente)

        val tabelaVacina = TabelaVacina(db)
        val vacina = Vacina(num_lote = "UC12897", data_vacinacao = 5/5/2021, idPaciente = paciente.id, idFabricante = fabricante.id)
        vacina.id = insereVacina(tabelaVacina, vacina)

        assertEquals(vacina, getVacinaBaseDados(tabelaVacina, vacina.id))

        db.close()
    }

    @Test
    fun consegueInserirEfeitosSecundarios() {
        val db = getBdControloVacinasOpenHelper().writableDatabase

        val tabelaFabricante = TabelaFabricante(db)
        val fabricante = Fabricante(nome = "AstraZeneca")
        fabricante.id = insereFabricante(tabelaFabricante, fabricante)

        val tabelaPaciente = TabelaPaciente(db)
        val paciente = Paciente(nome = "Alberto", data_nascimento = 4/10/1951, sexo = "Masculino", contacto = "961258976")
        paciente.id = inserePaciente(tabelaPaciente, paciente)

        val tabelaVacina = TabelaVacina(db)
        val vacina = Vacina(num_lote = "AB1257", data_vacinacao = 1/6/2021, idPaciente = paciente.id, idFabricante = fabricante.id)
        vacina.id = insereVacina(tabelaVacina, vacina)

        val tabelaEfeitosSecundarios = TabelaEfeitosSecundarios(db)
        val efeitosSecundarios = EfeitosSecundarios(febre = false, fadiga = true, dor_cabeca = true, dores_mosculares = true, calafrios = false, diarreia = false, dor_braco = false, outro = "", idVacina = vacina.id)
        efeitosSecundarios.id = insereEfeitosSecundarios(tabelaEfeitosSecundarios, efeitosSecundarios)

        assertEquals(fabricante, getFabricanteBaseDados(tabelaFabricante, fabricante.id))

        db.close()
    }
}