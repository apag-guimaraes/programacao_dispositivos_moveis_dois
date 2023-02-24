import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


   @Override

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

   }//onCreate


   public void start(View v){

       Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();

       Intent it = new Intent(this,

               MyService.class);

       startService(it);

   }//


   public void stop(View v){

       Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();

       Intent it = new Intent(this,

               MyService.class);

       stopService(it);

   }//


}//class
