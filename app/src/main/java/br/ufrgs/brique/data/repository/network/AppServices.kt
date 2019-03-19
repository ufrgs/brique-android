package br.ufrgs.brique.data.repository.network

import br.ufrgs.brique.data.repository.network.request.EditBemBody
import br.ufrgs.brique.data.repository.network.response.MuralResponse
import br.ufrgs.cpd.coresdk.network.models.ApiAnswer
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.*
import java.io.File

interface AppServices {

    @GET("v1/muralbens")
    fun getMuralBens(@Query("page") page: Int): Deferred<ApiAnswer<MuralResponse>>

    @GET("v1/muralbens/item/oferecidos")
    fun getBensOfertados(@Query("page") page: Int): Deferred<ApiAnswer<MuralResponse>>

    @GET("v1/muralbens/item/{id}/foto/raw")
    fun getItemImage(@Path("id") id: String): Deferred<ResponseBody>

    @PUT("v1/muralbens/item/{id}")
    fun editItem(@Path("id") id: String, @Body body: EditBemBody): Deferred<ResponseBody>

}