package com.example.apppermissao;

public class MainActivity extends AppCompatActivity {
    private Button buttonGerar, buttonDisparar;

    private ListView listViewNumeros;

    private ArrayAdapter<Integer> adapter;

    private ArrayList<Integer> dados;

    private int numeroAleatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonGerar = findViewById(R.id.buttonGerar);

        buttonDisparar = findViewById(R.id.buttonDisparar);

        listViewNumeros = findViewById(R.id.listViewNumeros);
        dados = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dados);

        listViewNumeros.setAdapter(adapter);

        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver(); //objeto da classe

        IntentFilter intentFilter = new IntentFilter();  //cria uma IntentFilter 

        intentFilter.addAction("com.example.appmybroadcastreceiver.MY_INTENT"); // defini as ações de transmissão. 

        registerReceiver(myBroadcastReceiver, intentFilter); //registra a classe que irá receber a mensagem do transmissor específico.
    }//onCreate

    public void clicar(View v) {

        switch (v.getId()) {

            case R.id.buttonGerar:

                Random random = new Random();

                numeroAleatorio = random.nextInt(10);

                Toast.makeText(this, "número gerado", Toast.LENGTH_SHORT).show();

                break;

            case R.id.buttonDisparar:

                dispararEvento();

                break;

        }//switch

    }//clicar

    private void dispararEvento() {

        Intent intent = new Intent();  //cria-se uma Intent

        intent.putExtra("numero", numeroAleatorio);  //adiciona-se a informação a ser passada

        intent.setAction("com.example.appmybroadcastreceiver.MY_INTENT"); //especifica-se o destinatário que recebe este dado extra

        sendBroadcast(intent); //envia a mensagem

    }//

    private class MyBroadcastReceiver extends BroadcastReceiver { //para tratar a mensagem, é necessário estender a classe BroadcastReceiver, pois assim, terá acesso ao método onReceive.

        @Override

        public void onReceive(Context context, Intent intent) {  //método capaz de tratar a mensagem disparada

            int num = intent.getIntExtra("numero", 0);  //obter o dado extra vindo da Intent

            dados.add(num); //adiciona o número no ArrayList

            adapter.notifyDataSetChanged();   //altera o adapter que preenche a ListView

        }//

    }//inner class

}//class

