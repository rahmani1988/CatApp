package com.reza.database

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * Custom AndroidJUnitRunner that uses [HiltTestApplication] to provide Hilt dependency injection during tests.
 */
class HiltTestRunner : AndroidJUnitRunner() {

    /**
     * Creates a new [HiltTestApplication] instance for testing.
     *
     * @param cl The class loader.
     * @param className The name of the application class.
     * @param context The application context.
     * @return A new [HiltTestApplication] instance.
     */
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}