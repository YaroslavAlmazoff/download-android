package com.chatlog.download_android

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val field = findViewById<EditText>(R.id.field)
        var download = findViewById<TextView>(R.id.download)

        download.setOnClickListener {
            if(field.text.toString() == "") {
                Toast.makeText(this, R.string.hint, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            Thread{
                if(!Utils.isFileExists(field.text.toString())) {
                    runOnUiThread { Toast.makeText(this, R.string.hint, Toast.LENGTH_LONG).show() }
                    return@Thread
                }
            }.start()
            val path = field.text.toString().split("/")
            Utils.downloadFile(
                path[path.size - 1], field.text.toString(), this)
            { Toast.makeText(this, R.string.downloaded, Toast.LENGTH_LONG).show() }
        }
    }
}