package com.barlipdev.fitrite.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.barlipdev.fitrite.database.getDatabase
import com.barlipdev.fitrite.repository.BrandsRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params){

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = BrandsRepository(database)
        return try{
            repository.refreshBrands()
            Result.success()
        } catch (e: HttpException){
            Result.retry()
        }
    }

}