package android.template.di

import android.app.Application
import android.template.data.datasource.MyApplicationDatabase
import android.template.data.repository.MyModelRepositoryImpl
import android.template.domain.repository.MyModelRepository
import android.template.domain.usecases.MyModelUseCases
import android.template.domain.usecases.mymodel.DeleteMyModel
import android.template.domain.usecases.mymodel.GetMyModelById
import android.template.domain.usecases.mymodel.GetMyModels
import android.template.domain.usecases.mymodel.UpsertMyModel
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesMyApplicationDatabase(app: Application): MyApplicationDatabase {
        return Room.databaseBuilder(
            app,
            MyApplicationDatabase::class.java,
            MyApplicationDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideMyModelRepository(db: MyApplicationDatabase): MyModelRepository {
        return MyModelRepositoryImpl(db.myModelDao)
    }

    @Provides
    @Singleton
    fun provideMyModelUseCases(repository: MyModelRepository): MyModelUseCases {
        return MyModelUseCases(
            getMyModels = GetMyModels(repository),
            getMyModelById = GetMyModelById(repository),
            upsertMyModel = UpsertMyModel(repository),
            deleteMyModel = DeleteMyModel(repository)
        )
    }
}