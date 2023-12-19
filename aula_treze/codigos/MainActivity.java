//Solução feita por Luan Luz

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText tituloText;
    private EditText notaText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private final String NOME_COLECAO = "filmes";
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        tituloText = findViewById(R.id.tituloText);
        notaText = findViewById(R.id.notaText);
        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<>()
        );

        listView.setAdapter(adapter);
        obterDados();
    }

    public void criar(View view) {

        String tituloTextValue = tituloText.getText().toString();
        String notaTextValue = notaText.getText().toString();

        if (tituloTextValue.isEmpty() || notaTextValue.isEmpty()) {
            return;
        }

        limparCampos();

        int nota = Integer.parseInt(notaTextValue);

        Filme filme = new Filme(tituloTextValue, nota);

        db.collection(NOME_COLECAO)
                .add(filme.toMap())
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Sucesso ao cadastrar filme", Toast.LENGTH_LONG).show();
                    obterDados();
                })
                .addOnFailureListener(e -> {
                    Log.w("Cadastro", "Erro ao cadastrar", e);
                });
    }

    private void obterDados() {
        db.collection(NOME_COLECAO)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<String> lista = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String titulo = document.getString("titulo");
                            int nota = Objects.requireNonNull(document.getLong("nota")).intValue();

                            lista.add(titulo + " - " + nota);

                            Log.d("Dado",document.getId() + " => " + document.getData());
                        }

                        adapter.clear();
                        adapter.addAll(lista);
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.d("Erro", "Erro ao obter dados: ", task.getException());
                    }
                });
    }


    private void limparCampos() {
        tituloText.setText("");
        notaText.setText("");
    }
}
