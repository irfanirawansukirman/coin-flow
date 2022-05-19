package com.irfanirawansukirman.composecrypto.presentation.coins

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.irfanirawansukirman.composecrypto.common.Resource
import com.irfanirawansukirman.composecrypto.domain.model.Coins
import com.irfanirawansukirman.composecrypto.domain.usecase.CoinsUseCase
import com.irfanirawansukirman.composecrypto.util.DataFactory
import com.irfanirawansukirman.composecrypto.util.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CoinsViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var useCase: CoinsUseCase
    private lateinit var viewModel: CoinsViewModel

    @Before
    fun setUp() {
        useCase = mockk(relaxed = true)
        viewModel = CoinsViewModel(useCase)
    }

    @Test
    fun `successfully get coin list`() = runTest {
        val expected = DataFactory.coins
        coEvery { useCase() } returns flow { emit(Resource.Success<List<Coins>>(expected)) }

        viewModel.getCoins()

        coVerify(exactly = 2) {
            useCase()

            val actual = viewModel.state.value.coins
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `successfully get coin list but list is empty`() = runTest {
        val expected = DataFactory.emptyCoins
        coEvery { useCase() } returns flow { emit(Resource.Success(expected)) }

        viewModel.getCoins()

        coVerify(exactly = 2) {
            useCase()

            val actual = viewModel.state.value.coins
            assertEquals(expected.size, actual?.size)
        }
    }

    @Test
    fun `successfully get coin list but list is null`() = runTest {
        val expected = null
        coEvery { useCase() } returns flow { emit(Resource.Success(expected)) }

        viewModel.getCoins()

        coVerify(exactly = 2) {
            useCase()

            val actual = viewModel.state.value.coins
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `failed get coin list and throw error message`() = runTest {
        val expected = DataFactory.error
        coEvery { useCase() } returns flow { emit(Resource.Error(expected)) }

        viewModel.getCoins()

        coVerify(exactly = 2) {
            useCase()

            val actual = viewModel.state.value.error
            assertEquals(expected, actual)
        }
    }
}