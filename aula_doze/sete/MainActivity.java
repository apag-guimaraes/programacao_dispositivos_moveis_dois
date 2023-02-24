import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;


public class MainActivity extends AppCompatActivity {

   private Button buttonGmail;


   @Override

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

       buttonGmail=findViewById(R.id.buttonGmail);

   }


   public void clicar(View v){

       Intent intent = null;

       if(v.getId()==R.id.buttonGmail){

           String email = "apag.guimaraes@gmail.com";

           String subject = "MyTitle";

           String text = "MyText";

           intent = new Intent(Intent.ACTION_SEND);

           intent.putExtra(Intent.EXTRA_EMAIL,email);

           intent.putExtra(Intent.EXTRA_SUBJECT,subject);

           intent.putExtra(Intent.EXTRA_TEXT,text);

           intent.setType("text/plain");

           intent.setPackage("com.google.android.gm");

           startActivity(intent);

     }

   }

}

