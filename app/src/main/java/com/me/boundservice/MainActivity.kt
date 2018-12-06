package com.me.boundservice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.service.google.AidlInterface
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ComponentName
import android.os.IBinder
import android.content.ServiceConnection
import android.util.Log
import android.content.Context;


class MainActivity : AppCompatActivity(),ServiceConnection {

    private var mService: AidlInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initService(applicationContext,"com.bound.server")
    }



    override fun onServiceDisconnected(name: ComponentName?) {
        mService = null
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        mService = AidlInterface.Stub.asInterface(service)
    }
    private fun initService(context: Context, packageName: String) {
        val intent = Intent("com.android.service.google.VersionService")
        intent.setPackage(packageName)

        val bindService = context.bindService(intent, this, Context.BIND_AUTO_CREATE)
        Log.d("bindService", "bindService: $bindService")
    }


}
