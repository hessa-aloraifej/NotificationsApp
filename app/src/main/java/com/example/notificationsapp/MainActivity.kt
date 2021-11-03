package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var etMessage=findViewById<EditText>(R.id.etMessage)
        var btn=findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            val channelId = "myapp.notifications"
            val description = "Notification App Example"
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
           val intent = Intent(this, MainActivity2::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var notificationChannel = NotificationChannel(channelId,description,
                    NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)
              var builder = Notification.Builder(this, channelId)
                    .setSmallIcon(R.drawable.imgnotcopy)
                  .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.imgnotcopy))
                    .setContentIntent(pendingIntent)
                    .setContentTitle("My Notification")
                    .setContentText(etMessage.text.toString())
                notificationManager.notify(1234, builder.build())

            } else {
               var builder = Notification.Builder(this)
                    .setSmallIcon(R.drawable.imgnotcopy)
                    .setContentIntent(pendingIntent)
                    .setContentTitle("My Notification")
                    .setContentText(etMessage.text.toString())
                notificationManager.notify(1234, builder.build())

            }



    }}
}