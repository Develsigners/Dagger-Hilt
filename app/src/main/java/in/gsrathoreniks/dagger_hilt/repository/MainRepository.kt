package `in`.gsrathoreniks.dagger_hilt.repository

import `in`.gsrathoreniks.dagger_hilt.data.networkdata.BlogRetrofit
import `in`.gsrathoreniks.dagger_hilt.data.networkdata.NetworkMapper
import `in`.gsrathoreniks.dagger_hilt.data.room.BlogDao
import `in`.gsrathoreniks.dagger_hilt.data.room.CacheMapper
import `in`.gsrathoreniks.dagger_hilt.model.Blog
import `in`.gsrathoreniks.dagger_hilt.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.util.concurrent.Flow

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){
    suspend fun getBlogs(): kotlinx.coroutines.flow.Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try{
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for(blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}

















