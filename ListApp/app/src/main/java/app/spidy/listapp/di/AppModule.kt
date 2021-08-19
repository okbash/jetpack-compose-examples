package app.spidy.listapp.di

import android.content.Context
import app.spidy.listapp.repos.MainRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideMainRepo(@ApplicationContext appContext: Context): MainRepo = MainRepo(appContext)
}