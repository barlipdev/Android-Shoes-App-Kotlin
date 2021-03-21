package com.barlipdev.fitrite.shareData

import android.content.Context

class ShareData {
    private constructor(context: Context)

    companion object {
        @Volatile private var mInstance: ShareData? = null

        public  fun get(context: Context): ShareData =
            mInstance ?: synchronized(this) {
                val newInstance = mInstance ?: ShareData(context).also { mInstance = it }
                newInstance
            }
    }
}