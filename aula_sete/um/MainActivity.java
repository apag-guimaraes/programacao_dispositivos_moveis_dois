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

        NotificationCompat.Builder builder =

                new NotificationCompat.Builder(this, CANAL_ID)  //o construtor precisa do contexto e o ID do canal

                        .setSmallIcon(R.mipmap.ic_launcher)   //defini uma imagem a ser visível

                        .setContentTitle("Meu título")     //defini um título para a notificação

                        .setContentText("Meu conteúdo")    //defini o conteúdo da notificação

                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);   //a prioridade da notificação

        NotificationManagerCompat nm = NotificationManagerCompat.from(this);

        nm.notify(NOTIFICACAO_ID, builder.build());  //para exibir a notificação

    }

}
