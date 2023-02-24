import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.DatePicker;

import android.widget.TextView;


import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

   private TextView textViewDate;

   private DatePickerDialog datePickerDialog;


   @Override

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

       textViewDate=findViewById(R.id.textViewDate);

   }


   public void clicar(View view) {

       if(view.getId() == R.id.textViewDate){

           Calendar calendar = Calendar.getInstance();

           int dia = calendar.get(Calendar.DAY_OF_MONTH);

           int mes = calendar.get(Calendar.MONTH);

           int ano = calendar.get(Calendar.YEAR);

           datePickerDialog = new DatePickerDialog(MainActivity.this, this, ano,mes,dia);

           datePickerDialog.show();

       }

   }


   @Override

   public void onDateSet(DatePicker datePicker, int year, int month, int day) {

       String date = day+"/"+month+"/"+year;

       textViewDate.setText(date);

   }

}//class
