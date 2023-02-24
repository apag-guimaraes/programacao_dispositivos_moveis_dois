import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

   private Button button;

   private EditText editText;


   @Override

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

       button = findViewById(R.id.buttonProximo);

       editText = findViewById(R.id.editTextNome);

   }//onCreate


   public void clicar(View view) {

       if(view.getId() == R.id.buttonProximo){

           String nome = editText.getText().toString();

           Intent intent = new Intent(getApplicationContext(),Activity2.class);

           intent.putExtra("nome",nome);

           startActivity(intent);

       }

   }//clicar

}
