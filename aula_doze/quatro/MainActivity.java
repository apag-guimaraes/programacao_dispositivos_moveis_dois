import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;

import android.app.TimePickerDialog;

import android.os.Bundle;

import android.view.View;

import android.widget.CalendarView;

import android.widget.TextView;

import android.widget.TimePicker;


import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements

       TimePickerDialog.OnTimeSetListener{

   private TextView textViewTime;

   private TimePickerDialog timePickerDialog;


   @Override

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

       textViewTime = findViewById(R.id.textViewTime);

   }


   public void clicar(View view) {

       if (view.getId() == R.id.textViewTime);

       Calendar calendar = Calendar.getInstance();

       int hora = calendar.get(Calendar.HOUR);

       int minutos = calendar.get(Calendar.MINUTE);


       timePickerDialog = new TimePickerDialog(MainActivity.this,

               this,

               hora,

               minutos,

               true);

       timePickerDialog.show();

   }


   @Override

   public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

       String time = hourOfDay+":"+minute+"h";

       textViewTime.setText(time);


   }

}
