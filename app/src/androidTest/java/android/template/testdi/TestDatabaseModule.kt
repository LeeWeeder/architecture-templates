package android.template.testdi

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
object TestAppModule {

    @Provides
    @Singleton
    fun provideMyApplicationDatabase(app: Application): MyApplicationDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            MyApplicationDatabase::class.java,
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