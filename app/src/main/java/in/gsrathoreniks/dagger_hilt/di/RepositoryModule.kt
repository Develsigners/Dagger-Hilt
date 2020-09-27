package `in`.gsrathoreniks.dagger_hilt.di

import `in`.gsrathoreniks.dagger_hilt.data.networkdata.BlogRetrofit
import `in`.gsrathoreniks.dagger_hilt.data.networkdata.NetworkMapper
import `in`.gsrathoreniks.dagger_hilt.data.room.BlogDao
import `in`.gsrathoreniks.dagger_hilt.data.room.CacheMapper
import `in`.gsrathoreniks.dagger_hilt.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository{
        return MainRepository(blogDao, retrofit, cacheMapper, networkMapper)
    }
}