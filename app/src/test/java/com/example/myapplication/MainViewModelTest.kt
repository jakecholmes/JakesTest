package com.example.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // Subjects under test
    private lateinit var viewModelWithMocks: MainViewModel

    @Before
    fun setUp() {
        viewModelWithMocks = MainViewModel()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testValidCredentials() = runTest {
        viewModelWithMocks.login("test@test.com", "password")
        assertEquals(true, viewModelWithMocks.loginSuccess.value)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testInvalidCredentials() = runTest {
        viewModelWithMocks.login("", "")
        assertEquals(false, viewModelWithMocks.loginSuccess.value)
    }
}