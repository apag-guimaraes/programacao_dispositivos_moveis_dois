//Solução feita pelo estudante Luan Luz

import java.util.HashMap;
import java.util.Map;

public class Filme {
    public String titulo;
    public int nota = 0;

    public Filme() {}

    public Filme(String titulo, int nota) {
        this.titulo = titulo;
        this.nota = nota;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("titulo", titulo);
        result.put("nota", nota);

        return result;
    }
}
