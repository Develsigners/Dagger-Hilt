package `in`.gsrathoreniks.dagger_hilt.di

import `in`.gsrathoreniks.dagger_hilt.data.networkdata.BlogNetworkEntity
import `in`.gsrathoreniks.dagger_hilt.data.networkdata.BlogRetrofit
import `in`.gsrathoreniks.dagger_hilt.data.room.BlogDao
import `in`.gsrathoreniks.dagger_hilt.data.room.BlogDatabase
import `in`.gsrathoreniks.dagger_hilt.model.Blog
import `in`.gsrathoreniks.dagger_hilt.util.EntityMapper
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): BlogDatabase {
        return Room
            .databaseBuilder(
                context,
                BlogDatabase::class.java,
                BlogDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(blogDatabase: BlogDatabase): BlogDao {
        return blogDatabase.blogDao()
    }
}