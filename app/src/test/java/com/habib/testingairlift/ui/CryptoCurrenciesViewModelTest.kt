package com.habib.testingairlift.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.habib.testingairlift.utils.MainCoroutineRule
import com.habib.testingairlift.utils.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import com.habib.testingairlift.repositories.FakeCryptoCurrenciesRepository
import com.habib.testingairlift.ui.fragments.CryptoCurrenciesViewModel
import com.habib.testingairlift.utils.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CryptoCurrenciesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: CryptoCurrenciesViewModel

    @Before
    fun setup() {
        viewModel = CryptoCurrenciesViewModel(FakeCryptoCurrenciesRepository())
    }

    @Test
    fun `insert cryptoCurrencies List status, returns Success`() {

        viewModel.refreshDataFromRepository()

        val value = viewModel.eventMessage

        assertThat(value.value).isEqualTo(Constants.SUCCESS)
    }

    @Test
    fun `insert cryptoCurrencies List, returns True`() {

        viewModel.refreshDataFromRepository()

        val value = viewModel.cryptoCurrenciesList.getOrAwaitValueTest()

        assertThat(value.isNotEmpty()).isTrue()
    }
}