import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SegundaActivity extends AppCompatActivity {
    private String dadosJSON;
    private ListView listView;
    private List<Estudante> lista;
    private ArrayAdapter<Estudante> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        dadosJSON = getIntent().getStringExtra("dados");
        Toast.makeText(getApplicationContext(),dadosJSON,Toast.LENGTH_LONG).show();
        listView = findViewById(R.id.listaViewDados);
        lista = consumirJSON();
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                lista);
        listView.setAdapter(adapter);
    }//onCreate

    private List<Estudante> consumirJSON(){
        String resultado = "";
        List<Estudante> listaEstudantes = null;
        if(dadosJSON!=null){
            Gson gson = new Gson();  //cria o objeto para acessar os recursos da biblioteca
            Type type = new TypeToken<List<Estudante>>(){}.getType();  //classe genérica que possibilita obter os dados no mesmo tipo que foi definido em tempo de execução   
             listaEstudantes = gson.fromJson(dadosJSON, type);  //converse o arquivo JSON em uma lista de estudantes.
            Toast.makeText(getApplicationContext(),listaEstudantes.toString(),
                       Toast.LENGTH_LONG).show();

        }//if
        return listaEstudantes;
    }//method

}//class
