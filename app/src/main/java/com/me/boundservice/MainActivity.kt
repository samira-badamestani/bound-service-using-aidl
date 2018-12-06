package com.me.boundservice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.service.google.AidlInterface
import android.content.Intent
import android.content.ComponentName
import android.os.IBinder
import android.content.ServiceConnection
import android.util.Log
import android.content.Context;
import android.os.RemoteException
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),ServiceConnection {

    private var mService: AidlInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initService(applicationContext,"com.bound.server")
        txt1.setOnClickListener {
          val version = getVersionCode()
            Toast.makeText(applicationContext,"$version",Toast.LENGTH_LONG).show()
        }
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
        if (bindService){
            Toast.makeText(applicationContext,"service bounded",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(applicationContext,"oppsss",Toast.LENGTH_LONG).show()
        }

    }

    private fun getVersionCode(): Int {
        try {
            val version = mService!!.version
            Log.d("version", ">>>>>>>> version: $version<<<<<<<<<<<")
            return version
        } catch (e: RemoteException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        return 0
    }


}
