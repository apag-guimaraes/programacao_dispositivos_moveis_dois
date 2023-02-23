import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private static final int CODIGO_SOLICITACAO = 1;
    private static final String PERMISSAO = Manifest.permission.CAMERA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.buttonHello);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicar();
            }
        });
    }//onCreate

    private void solicitarPermissao(){
        int temPermissao = ContextCompat.checkSelfPermission(this,
                PERMISSAO);
        if(temPermissao != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{PERMISSAO},CODIGO_SOLICITACAO);
        }//if
        else{
            chamarActivity();
        }

    }//method

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                chamarActivity();
            }else if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(
                        this, PERMISSAO)){
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Atenção")
                            .setMessage("A permissão é necessária para ...")
                            .setCancelable(false)
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ActivityCompat.requestPermissions(MainActivity.this,
                                            new String[]{PERMISSAO},CODIGO_SOLICITACAO);
                                }//onClick
                            })
                            .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getApplicationContext(),"É necessário" +
                                            " a permissão " +
                                            "para funcionar",Toast.LENGTH_SHORT).show();
                                    finish();

                                }//onClick
                            });
                    AlertDialog dialog =  builder.create();
                    dialog.show();
                }
                else{
                    finish();
                }
            }//else if
        }//if
    }//method

    public void clicar(){
        solicitarPermissao();
    }//

    private void chamarActivity() {
        Intent i = new Intent(getApplicationContext(),
                SegundaActivity.class);
        startActivity(i);
    }//method


}//class
