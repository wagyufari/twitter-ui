package com.mayburger.twitter.base

import android.app.Application
import android.content.Context
import android.graphics.Typeface
import androidx.multidex.MultiDex
import com.mayburger.twitter.utils.textfont.TypeFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        MultiDex.install(this)
        super.onCreate()
        instance = this
    }

    private var mFontFactory: TypeFactory? = null
    fun getTypeFace(type: Int): Typeface? {
        mFontFactory = TypeFactory(this)
        mFontFactory?.let {
            return when (type) {
                Constants.MEDIUM -> mFontFactory?.medium
                Constants.BOLD -> mFontFactory?.bold
                Constants.BLACK -> mFontFactory?.black
                else -> mFontFactory?.regular
            }
        }
        return null
    }

    private interface Constants {
        companion object {
            const val MEDIUM = 1
            const val BOLD = 2
            const val BLACK = 3
        }
    }

    companion object {
        @get:Synchronized
        var instance: BaseApplication? = null
            private set
    }
}
