public class MainActivity extends AppCompatActivity {


   private static final String CHANNEL_ID = "2"; //ID do canal

   private static final int NOTIFICATION_ID = 1;  //ID da notificação


   @Override

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);


       criarCanal();

   }


   public void gerar(View view) {

      //construindo a notificação

       NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)

               .setSmallIcon(R.mipmap.ic_launcher)

               .setContentTitle("Download File")

               .setContentText("Download in progress")

               .setPriority(NotificationCompat.PRIORITY_DEFAULT);


       NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

       int PROGRESS_MAX = 100;


       new Thread(

               new Runnable() {

                   @Override

                   public void run() {

                       int PROGRESS_CURRENT;

                       for (PROGRESS_CURRENT = 0; PROGRESS_CURRENT <= PROGRESS_MAX; PROGRESS_CURRENT += 20) {

                           builder.setProgress(100, PROGRESS_CURRENT, false);

                           notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());

                           try {

                               Thread.sleep(1 * 1000);

                           } catch (InterruptedException e) {

                               Log.d("TAG", "sleep failure");

                           }

                       }


                       builder.setContentText("Download completed")

                               .setProgress(0, 0, false);

                       notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());

                   }

               }

       ).start();


   }


   private void criarCanal() {

    //compara as versões, pois a classe NotificationChannel está disponível na API 26 e superior

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

           CharSequence name = "canal";

           String descricao = "descricao do canal 1";

           int importancia = NotificationManager.IMPORTANCE_DEFAULT;

           NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importancia); //registra o canal e a prioridade

           channel.setDescription(descricao);

           NotificationManager notificationManager = getSystemService(NotificationManager.class);

           notificationManager.createNotificationChannel(channel);

       }

   }

}

