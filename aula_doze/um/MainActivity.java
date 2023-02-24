import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.DatePicker;

import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

   private Button buttonDate;

   private TextView textViewDate;

   private DatePicker datePicker;


   @Override

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

       datePicker=findViewById(R.id.datePicker);

       buttonDate=findViewById(R.id.buttonDate);

       textViewDate=findViewById(R.id.textViewDate);

   }


   private String obterData(){

       int dia=0,mes=0,ano=0;

       String date="";

       dia=datePicker.getDayOfMonth();

       mes=datePicker.getMonth();

       ano=datePicker.getYear();

       date=dia+"/"+mes+"/"+ano;

       return date;

   }


   public void clicar(View view) {

       textViewDate.setText(obterData());

   }

}//class
