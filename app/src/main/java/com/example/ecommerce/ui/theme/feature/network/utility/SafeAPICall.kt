import com.example.ecommerce.ui.theme.core.errors.AppErrors
import com.example.ecommerce.ui.theme.feature.network.models.response.error.CustomResponseError
import com.example.ecommerce.ui.theme.feature.network.utility.ApiResult
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ApiResult<T> {

    try {
        return ApiResult.SuccessApiResult(
            data = apiCall()
        )

    } catch (e: IOException) {

        return ApiResult.ErrorApiResult(
            errors = AppErrors.NetworkError()
        )

    } catch (e: HttpException) {

        val json = e.response()?.errorBody()?.string()

        val errorClass = CustomResponseError::class.java

        val error = Gson().fromJson(
            json,
            errorClass
        )

        return ApiResult.ErrorApiResult(
            errors = AppErrors.ServerError(
                message = error.message ?: "Server error"
            )
        )

    } catch (e: Throwable) {

        return ApiResult.ErrorApiResult(
            errors = AppErrors.UnKmpwnError(
                message = e.localizedMessage ?: "Something went wrong"
            )
        )
    }
}