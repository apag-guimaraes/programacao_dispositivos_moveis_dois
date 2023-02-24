import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;

import android.os.Build;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.DatePicker;

import android.widget.TextView;

import android.widget.TimePicker;

import android.widget.Toast;


import java.util.Calendar;


public class MainActivity extends AppCompatActivity  {

   private TimePicker timePicker;

   private Button button;



   @Override

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

       timePicker = findViewById(R.id.timePicker);

       timePicker.setIs24HourView(true);

       button = findViewById(R.id.button);


   }//onCreate


   private void obter(){

       int hora =0,minuto=0;

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

           hora = timePicker.getHour();

           minuto = timePicker.getMinute();

       }else{

           hora = timePicker.getCurrentHour();

           minuto = timePicker.getCurrentMinute();

       }

       String tempo = hora+":"+minuto;


       Toast.makeText(getApplicationContext(),tempo,Toast.LENGTH_SHORT).show();


   }



   public void clicar(View view) {

       obter();

   }//method



}//class

