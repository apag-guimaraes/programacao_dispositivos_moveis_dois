public class MainActivity extends AppCompatActivity {

   private static final String CHANNEL_ID = "2";

   private int notificationId = 1;



   @Override

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

       criarCanalNotificacao();

   }//


   public void gerar(View view){

       RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.notification_small);


       Notification builder = new NotificationCompat.Builder(this,

               CHANNEL_ID)

               .setSmallIcon(R.mipmap.ic_launcher)

               .setStyle(new NotificationCompat.DecoratedCustomViewStyle())

               .setCustomContentView(notificationLayout)

               .build();


       NotificationManagerCompat nm = NotificationManagerCompat.from(this);

       nm.notify(notificationId,builder);

   }//


   private void criarCanalNotificacao(){

       if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

           CharSequence nome = "canal1";

           String descricao = "descrição do canal 1";

           int importance = NotificationManager.IMPORTANCE_DEFAULT;

           NotificationChannel canal = new NotificationChannel(CHANNEL_ID, nome, importance);

           canal.setDescription(descricao);


           NotificationManager nm = getSystemService(NotificationManager.class);

           nm.createNotificationChannel(canal);

       }//if

   }//method

}
