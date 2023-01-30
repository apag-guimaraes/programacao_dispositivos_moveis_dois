import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNome, editTextDisciplina,
            editTextNota;
    private Button buttonAdd, buttonGerar, buttonConsumir;
    private List<Estudante> lista;
    private TextView textViewResultado;
    private String retorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNome = findViewById(R.id.editTextNome);
        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        editTextNota = findViewById(R.id.editTextNota);
        textViewResultado = findViewById(R.id.textViewResultado);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonGerar = findViewById(R.id.buttonGerar);
        buttonConsumir = findViewById(R.id.buttonConsumir);
        lista = new ArrayList<>();
    }//onCreate

    public void criarLista(View v){
        lista.add(new Estudante(editTextNome.getText().toString(),
               editTextDisciplina.getText().toString(),
                Integer.parseInt(editTextNota.getText().toString())));
        Toast.makeText(getApplicationContext(),"item inserido",Toast.LENGTH_SHORT).show();
    }//

    public String criarJSON(List<Estudante> dados){
        Gson gson = new Gson();	//cria o objeto para acessar os recursos da biblioteca
        String stringJson = gson.toJson(dados);    //observe que é necessário apenas um método para converter a lista de objetos em uma String
        return stringJson;
    }//method

    public void gerarJSON(View v){
        retorno = criarJSON(lista);
        textViewResultado.setText(retorno);

    }//method

    public void abrirTela(View v){
        Intent it = new Intent(getApplicationContext(),SegundaActivity.class);
        it.putExtra("dados",retorno);
        startActivity(it);
    }//method

}//class
