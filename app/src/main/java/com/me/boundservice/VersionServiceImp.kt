package com.me.boundservice

import com.android.service.google.AidlInterface

class VersionServiceImp : AidlInterface.Stub(){
    override fun getVersion(): Int {
        return BuildConfig.VERSION_CODE
    }

}