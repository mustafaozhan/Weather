package mustafaozhan.github.com.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.test.runBlockingTest

fun <T> SharedFlow<T>.before(
    function: () -> Unit
) = onSubscription {
    function()
}

@ExperimentalCoroutinesApi
fun <T> Flow<T>.after(function: (T?) -> Unit) = runBlockingTest {
    delay(300)
    firstOrNull {
        function(it)
        true
    }
}
