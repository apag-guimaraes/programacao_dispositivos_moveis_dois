import android.content.BroadcastReceiver;

import android.content.Context;

import android.content.Intent;

import android.widget.Toast;


public class MyBroadcastReceiver extends BroadcastReceiver {

   @Override

   public void onReceive(Context context, Intent intent) {

       Integer num = intent.getIntExtra("numeroGerado",0);

       Toast.makeText(context,"número gerado: "+num.toString(),Toast.LENGTH_SHORT).show();

   }

}
