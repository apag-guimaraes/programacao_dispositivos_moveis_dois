public class MainActivity extends AppCompatActivity {

    private static final String CANAL_ID = "2";  //ID do canal

    private static final int NOTIFICACAO_ID = 1;   //ID da notificação


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        criarCanal();

    }//

    private void criarCanal() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {  //verifica as versões do Android

            CharSequence nome = "canal1";

            String descricao = "descrição do canal 1";

            int importancia = NotificationManager.IMPORTANCE_DEFAULT; //define a prioridade

            NotificationChannel canal = new NotificationChannel(CANAL_ID, nome, importancia); //cria o objeto referente ao canal, passand o ID do canal, nome e prioridade.

            canal.setDescription(descricao);


//registrando o canal no sistema

            NotificationManager nm = getSystemService(NotificationManager.class);

            nm.createNotificationChannel(canal);

        }

    }//

    public void gerar(View view) {

//quando a notificação for tocada, será aberta uma outra Activity. A notificação responde ao toque do usuário.

        Intent i = new Intent(this, Activity2.class);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |

                Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);


        Bitmap bitmap = BitmapFactory

                .decodeResource(this.getResources(), R.drawable.image);  //criando um Bitmap com a imagem contida na pasta drawable


        Notification builder = new NotificationCompat  //configurando a estrutura da notificação

                .Builder(this, CANAL_ID)

                .setSmallIcon(R.mipmap.ic_launcher)

                .setContentTitle("Meu título")

                .setContentText("Meu conteúdo")

                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                .setContentIntent(pi)  //Passando o PendingIntent

                .setLargeIcon(bitmap)

                .setStyle(new NotificationCompat.BigTextStyle()

                        .bigText("fjasdlkfjadjkladlkajda\n" +

                                "dakslkdaçlsdkasçldsa\n" +

                                "kdasldkasçldlkasdsçal\n" +

                                "ldaskdçlasdkaçldkasl")).build();


        NotificationManagerCompat nm = NotificationManagerCompat.from(this);

        nm.notify(NOTIFICACAO_ID, builder);


    }//gerar


}
