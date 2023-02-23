public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;

    private PendingIntent pendingIntent;


    @Override

    protected void onStart() {

        super.onStart();

        disparar();

    }

    private void disparar() {

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent i = new Intent(this, MyBroadcast.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0, i, 0);


        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(Calendar.HOUR_OF_DAY, 18);

        calendar.set(Calendar.MINUTE, 42);


        alarmManager.set(AlarmManager.RTC_WAKEUP,

                calendar.getTimeInMillis() + 2 * 1000,

                pendingIntent);

    }//


}
