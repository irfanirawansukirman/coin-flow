package com.irfanirawansukirman.composecrypto.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.io.IOException

/**
 * Sealed class type-restricts the result of IO calls to success and failure. The type
 * <T> represents the model class expected from the API call in case of a success
 * In case of success, the result will be wrapped around the OnSuccessResponse class
 * In case of error, the throwable causing the error will be wrapped around OnErrorResponse class
 * @author Prasan
 * @since 1.0
 */
sealed class IOTaskResult<out DTO : Any> {
    data class OnSuccess<out DTO : Any>(val data: DTO) : IOTaskResult<DTO>()
    data class OnFailed(val throwable: Throwable) : IOTaskResult<Nothing>()
}

/**
 * Lets the UI act on a controlled bound of states that can be defined here
 * @author Prasan
 * @since 1.0
 */
sealed class Resource<out T : Any?> {

    /**
     * Represents UI state where the UI should be showing a loading UX to the user
     * @param isLoading will be true when the loading UX needs to display, false when not
     */
    data class Loading(val isLoading: Boolean) : Resource<Nothing>()

    /**
     * Represents the UI state where the operation requested by the UI has been completed successfully
     * and the output of type [T] as asked by the UI has been provided to it
     * @param data result object of [T] type representing the fruit of the successful operation
     */
    data class Success<out T : Any?>(val data: T?) : Resource<T>()

    /**
     * Represents the UI state where the operation requested by the UI has failed to complete
     * either due to a IO issue or a service exception and the same is conveyed back to the UI
     * to be shown the user
     * @param throwable [Throwable] instance containing the root cause of the failure in a [String]
     */
    data class Error(val error: String) : Resource<Nothing>()
}

/**
 * Util method that takes a suspend function returning a [Flow] of [IOTaskResult] as input param and returns a
 * [Flow] of [Resource], which emits [Resource.Loading] with true prior to performing the IO Task. If the
 * IO operation results a [IOTaskResult.OnSuccess], the result is mapped to a [Resource.Success] instance and emitted,
 * else a [IOTaskResult.OnFailed] is mapped to a [Resource.Error] instance and emitted.
 * The flowable is then completed by emitting a [Resource.Loading] with false
 */
@ExperimentalCoroutinesApi
suspend fun <T : Any> getViewStateFlowForNetworkCall(ioOperation: suspend () -> Flow<IOTaskResult<T>>) =
    flow {
        emit(Resource.Loading(true))
        ioOperation().map {
            when (it) {
                is IOTaskResult.OnSuccess -> Resource.Success(it.data)
                is IOTaskResult.OnFailed -> Resource.Error(
                    it.throwable.localizedMessage ?: "There's something wrong"
                )
            }
        }.collect {
            emit(it)
        }
    }.flowOn(Dispatchers.IO)

/**
 * Readable naming convention for Network call lambda
 * @since 1.0
 */
typealias NetworkAPIInvoke<T> = suspend () -> Response<T>

/**
 * Utility function that works to perform a Retrofit API call and return either a success model
 * instance or an error message wrapped in an [Exception] class
 * @param messageInCaseOfError Custom error message to wrap around [IOTaskResult.OnFailed]
 * with a default value provided for flexibility
 * @param networkApiCall lambda representing a suspend function for the Retrofit API call
 * @return [IOTaskResult.OnSuccess] object of type [T], where [T] is the success object wrapped around
 * [IOTaskResult.OnSuccess] if network call is executed successfully, or [IOTaskResult.OnFailed]
 * object wrapping an [Exception] class stating the error
 * @since 1.0
 */
@ExperimentalCoroutinesApi
suspend fun <T : Any> performSafeNetworkApiCall(
    messageInCaseOfError: String = "Network error",
    allowRetries: Boolean = true,
    numberOfRetries: Int = 2,
    networkApiCall: NetworkAPIInvoke<T>
): Flow<IOTaskResult<T>> {
    var delayDuration = 1000L
    val delayFactor = 2
    return flow {
        val response = networkApiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(IOTaskResult.OnSuccess(it))
            }
                ?: emit(IOTaskResult.OnFailed(IOException("API call successful but empty response body")))
            return@flow
        }
        emit(
            IOTaskResult.OnFailed(
                IOException(
                    "API call failed with error - ${
                        response.errorBody()
                            ?.string() ?: messageInCaseOfError
                    }"
                )
            )
        )
        return@flow
    }.catch { e ->
        emit(IOTaskResult.OnFailed(IOException("Exception during network API call: ${e.message}")))
        return@catch
    }.retryWhen { cause, attempt ->
        if (!allowRetries || attempt > numberOfRetries || cause !is IOException) return@retryWhen false
        delay(delayDuration)
        delayDuration *= delayFactor
        return@retryWhen true
    }.flowOn(Dispatchers.IO)
}