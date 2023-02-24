import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Build;

import android.os.Bundle;

import android.provider.Telephony;

import android.view.View;

import android.widget.Button;


public class MainActivity extends AppCompatActivity {

   private Button buttonSms;

   @Override

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

       buttonSms = findViewById(R.id.buttonSms);


   }//onCreate


   public void clicar(View view){

       Intent intent = null;

       if(view.getId()==R.id.buttonSms){

           if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){

               String defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(getApplicationContext());  //obter o pacote de SMS padrão 

               intent = new Intent();

               intent.setAction(Intent.ACTION_SEND); //defini uma ação a ser enviada, ou seja envia algo para alguém, mas não especifica.

               intent.setType("text/plain");  //defini o tipo de dado

               intent.putExtra(Intent.EXTRA_TEXT,"hello"); //add informação extra

               intent.setPackage(defaultSmsPackage); //defina um nome de pacote de aplicativo explícito 

               startActivity(intent);

           }else{

               intent = new Intent();

               intent.setAction(Intent.ACTION_VIEW); //exiba os dados para o usuário

               intent.putExtra(Intent.EXTRA_TEXT,"hello2");

               intent.setType("vnd.android-dir/mms-sms");

               startActivity(intent);

           }

       }

   }//

}
