package com.example.stackoverlow

import app.cash.turbine.test
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.Assert
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class MyTest {

    private val scope = CoroutineScope(Dispatchers.Unconfined)
    private val mutableSharedFlow = MutableSharedFlow<Int>()

    @Test
    fun fails() = runBlocking {
        val sharedFlow = mutableSharedFlow
                .shareIn(scope, started = SharingStarted.WhileSubscribed())

        sharedFlow.test {
            expectNoEvents()

            mutableSharedFlow.emit(3)

            Assert.assertEquals(3, expectItem())
        }
    }
}