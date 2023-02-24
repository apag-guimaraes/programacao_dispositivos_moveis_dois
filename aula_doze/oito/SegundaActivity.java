import androidx.appcompat.app.AppCompatActivity;


import android.net.Uri;

import android.os.Build;

import android.os.Bundle;

import android.speech.tts.TextToSpeech;

import android.util.Log;

import android.view.View;

import android.widget.Button;

import android.widget.Toast;

import android.widget.VideoView;


import java.util.Locale;


public class Activity2 extends AppCompatActivity implements

       TextToSpeech.OnInitListener {   //interface para fornecer o método de suporte


   private VideoView videoView;

   private Button buttonPlay,buttonPause, buttonStop;

   private Uri uri;

   private TextToSpeech textToSpeech;

   private String msg;


   @Override

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_2);


       String nome = getIntent().getStringExtra("nome");

       msg = nome+" seu vídeo está pronto";


       textToSpeech =  new TextToSpeech(getApplicationContext(),this);

       videoView = findViewById(R.id.videoView);

       buttonPlay = findViewById(R.id.buttonPlay);

       buttonPause = findViewById(R.id.buttonPause);

       buttonStop = findViewById(R.id.buttonStop);


       uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video);   //local de onde se encontra o recurso

       videoView.setVideoURI(uri);  //configura o recurso no VideoView


   }//onCreate


   public void click(View view){

       if(view.getId() == R.id.buttonPlay){

           int duracao = videoView.getDuration();

           Toast.makeText(getApplicationContext(),"Tempo total: "+duracao, Toast.LENGTH_SHORT).show();

           videoView.start();  //começa o vídeo

       }


       if(view.getId() == R.id.buttonPause){

           videoView.pause();

           int posicao = videoView.getCurrentPosition();  //obtém a posição atual do vídeo

           Toast.makeText(getApplicationContext(),"Tempo atual: "+posicao, Toast.LENGTH_SHORT).show();

       }


       if(view.getId() == R.id.buttonStop){

           videoView.stopPlayback();  //para o vídeo

           Toast.makeText(getApplicationContext(),"Vídeo não pode ser mais reproduzido ", Toast.LENGTH_SHORT).show();

           finish();

       }

   }//click


   @Override

   public void onInit(int i) {

       if(i == TextToSpeech.SUCCESS){

           Locale locale = new Locale("pt","br");  //define o idioma

           int result = textToSpeech.setLanguage(locale);  //define o idioma no TextToSpeech

           textToSpeech.setSpeechRate(0.5f);  //define a velocidade da fala

           if(result == TextToSpeech.LANG_MISSING_DATA ||

           result == TextToSpeech.LANG_NOT_SUPPORTED){   //verifica se existe suporte ao idioma

               Log.e("problemasI","problemas com o idioma");

           }else{

               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){  //verifica as versões do Android

                   textToSpeech.speak(msg,TextToSpeech.QUEUE_FLUSH,  //passa a mensagem para o método. Os textos passados aqui serão pronunciados.

                           null,null);

               }else{

                   textToSpeech.speak(msg, TextToSpeech.QUEUE_FLUSH,

                           null); //passa a mensagem para o método. Os textos passados aqui serão pronunciados.

               }

           }

       }else{

           Log.e("problemasT","problemas com o textToSpeech");

       }

   }//onInit

}//class

