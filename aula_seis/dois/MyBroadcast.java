import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() == "android.intent.action.AIRPLANE_MODE"){  //verifica se a transmissão foi realizada por habilitar o modo avião do aparelho

            Intent i = new Intent(context,Activity2.class); //criar uma Intent, pois o objeto é quando o celular for colocado em modo avião, invoque a segunda tela.
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  //a tela a ser mostrada será iniciada em uma nova pilha
            context.startActivity(i); //inicia a segunda tela
        }
    }
}
