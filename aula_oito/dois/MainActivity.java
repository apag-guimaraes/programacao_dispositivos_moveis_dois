import androidx.appcompat.app.AppCompatActivity;


import android.app.AlarmManager;

import android.app.PendingIntent;

import android.content.Intent;

import android.os.Bundle;

import android.os.SystemClock;

import android.view.View;

import android.widget.Toast;


import java.util.Calendar;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

   private AlarmManager alarmManager;

   private PendingIntent pendingIntent;

   private Integer numeroGerado;


   @Override

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

       numeroGerado = gerarNumero();

       Toast.makeText(this, numeroGerado.toString(),

               Toast.LENGTH_SHORT).show();

   }


   private int gerarNumero(){

       Random random = new Random();

       int numeroAleatorio = random.nextInt(10);

       return numeroAleatorio;

   }


   public void disparar(View view) {

       if(view.getId() == R.id.textHello){

           Toast.makeText(this, "clicado", Toast.LENGTH_SHORT).show();

           alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

           Intent i = new Intent(this, MyBroadcast.class);

           i.putExtra("numeroGerado",numeroGerado);

           pendingIntent = PendingIntent.getBroadcast(this,

                   0,i,0);


           /*Calendar calendar = Calendar.getInstance();

           calendar.setTimeInMillis(System.currentTimeMillis());

           calendar.set(Calendar.HOUR_OF_DAY,18);

           calendar.set(Calendar.MINUTE,16);*/


           alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,

                   SystemClock.elapsedRealtime() +

                           60 * 1000, pendingIntent);

       }


   }

}
