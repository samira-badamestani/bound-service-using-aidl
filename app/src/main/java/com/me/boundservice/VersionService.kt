package com.me.boundservice

import android.app.Service
import android.content.Intent
import android.os.IBinder

class VersionService : Service(){
    override fun onBind(intent: Intent?): IBinder? {
        return VersionServiceImp()
    }

}