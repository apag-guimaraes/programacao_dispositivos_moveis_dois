import android.app.Service;

import android.content.Intent;

import android.os.IBinder;

import android.util.Log;

import android.widget.Toast;


import androidx.annotation.Nullable;


public class MyService extends Service {


   @Override

   public void onCreate() {

       super.onCreate();

       Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

   }


   @Override

   public int onStartCommand(Intent intent, int flags, int startId) {

       Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();

       return START_NOT_STICKY;

   }


   @Override

   public void onDestroy() {

       super.onDestroy();

       Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();

   }


   @Nullable

   @Override

   public IBinder onBind(Intent intent) {

       return null;

   }

}

