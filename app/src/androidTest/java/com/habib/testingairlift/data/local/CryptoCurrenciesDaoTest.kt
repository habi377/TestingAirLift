package com.habib.testingairlift.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.habib.testingairlift.utils.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

//@RunWith(AndroidJUnit4::class) // Because we are in Android/Emulator Env not kotlin JVM
@SmallTest // Part of pyramid Testing, means unit test
@HiltAndroidTest
class CryptoCurrenciesDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule // To allow we want the async tasks line by line in this particular class
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: CryptoCurrenciesDatabase
    private lateinit var dao: CryptoCurrenciesDao

    @Before
    fun setup() {
        hiltRule.inject()
//        database =
//            Room.inMemoryDatabaseBuilder( // database in Ram only for this Test, Not actual persistent storage
//                ApplicationProvider.getApplicationContext(),
//                CryptoCurrenciesDatabase::class.java
//            ).allowMainThreadQueries().build()

        dao = database.cryptoCurrenciesDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertAndGetCryptoCurrenciesItemsList() = runBlockingTest {
        val listCryptoCurrenciesItems = mutableListOf(
            CryptoCurrenciesItem("BitCoin", "BTC", 14.44, "09-21-21",1),
            CryptoCurrenciesItem("BitCoin", "BTC", 14.44, "09-21-21",2),
            CryptoCurrenciesItem("BitCoin", "BTC", 14.44, "09-21-21",3)
        )

        dao.insertAll(listCryptoCurrenciesItems)

        val allCryptoCurrenciesItems = dao.getCryptoCurrencies().getOrAwaitValue()

        assertThat(allCryptoCurrenciesItems).isEqualTo(listCryptoCurrenciesItems)
    }
}