import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;


public class MainActivity extends AppCompatActivity {

   private Button buttonWA;

   @Override

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

       buttonWA=findViewById(R.id.buttonWhatsApp);

   }


   public void clicar(View v){

       Intent intent = null;

       if(v.getId()==R.id.buttonWhatsApp){

           String text = "hello world";

           intent = new Intent();

           intent.setAction(Intent.ACTION_SEND);

           intent.putExtra(Intent.EXTRA_TEXT,text);

           intent.setType("text/plain");

           intent.setPackage("com.whatsapp");

           startActivity(intent);

       }


   }

}

