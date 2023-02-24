import androidx.appcompat.app.AppCompatActivity;


import android.Manifest;

import android.content.ContentResolver;

import android.content.pm.PackageManager;

import android.database.Cursor;

import android.net.Uri;

import android.os.Build;

import android.os.Bundle;

import android.provider.ContactsContract;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.ListView;

import android.widget.Toast;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements

       AdapterView.OnItemClickListener {


   private ListView lista;

   private ArrayAdapter<Contato> dadosAdapter;

   private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;


   @Override

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

       lista = findViewById(R.id.lista);

       dadosAdapter = new ArrayAdapter<>(getApplicationContext(),

               android.R.layout.simple_list_item_1, obterDados());

       lista.setAdapter(dadosAdapter);

       lista.setOnItemClickListener(this);

   }


   public ArrayList<Contato> obterDados(){

       String projection[] = null;

       String select = null;

       String selectArgs[] = null;

       String sortOrder = null;


       Uri uri = null;

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

               && checkSelfPermission(Manifest.permission.READ_CONTACTS)

               != PackageManager.PERMISSION_GRANTED) {

           requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},

                   PERMISSIONS_REQUEST_READ_CONTACTS);

           uri = ContactsContract.CommonDataKinds.Contactables.CONTENT_URI;

       } else {

           uri = ContactsContract.Data.CONTENT_URI;

       }


       ContentResolver contentResolver = getContentResolver();

       Cursor cursor = contentResolver.query(uri, projection, select, selectArgs,

               sortOrder);

       ArrayList<Contato> dados = new ArrayList<>();


       while (cursor.moveToNext()){

           int id = cursor.getInt(cursor.

                   getColumnIndex(ContactsContract.Data.CONTACT_ID));

           String nome = cursor.getString(cursor.getColumnIndex(

                   ContactsContract.Contacts.DISPLAY_NAME));

           String data = cursor.getString(cursor.getColumnIndex(

                   ContactsContract.CommonDataKinds.Contactables.DATA));

           dados.add(new Contato((int) id, nome, data));

       }

       return dados;

   }



   @Override

   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       Contato item = (Contato) parent.getItemAtPosition(position);

       Toast.makeText(MainActivity.this, item.toString(),

               Toast.LENGTH_SHORT).show();


   }

}
