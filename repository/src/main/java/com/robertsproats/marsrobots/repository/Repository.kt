package com.robertsproats.marsrobots.repository

import android.os.Looper

abstract class Repository {

    protected fun checkThread(caller: String) {
        check(!Thread.currentThread().equals((Looper.getMainLooper().thread)),
                { "Error: $caller called on main thread." })
    }

}