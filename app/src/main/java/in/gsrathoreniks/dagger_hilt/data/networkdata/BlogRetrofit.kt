package `in`.gsrathoreniks.dagger_hilt.data.networkdata
import retrofit2.http.GET

interface BlogRetrofit {

    @GET("blogs")
    suspend fun get():List<BlogNetworkEntity>
}