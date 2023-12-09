package com.robonouveau.nbc.takehome.interactors

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class ReadFileInteractor @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun readLocalFile(filename: String): Result<String> {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open(filename)
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            return Result.failure(ioException)
        }
        return Result.success(jsonString)
    }
}
