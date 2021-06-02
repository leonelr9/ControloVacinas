package com.example.controlovacinas

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

}