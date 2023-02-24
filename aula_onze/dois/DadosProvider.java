import android.content.ContentProvider;

import android.content.ContentUris;

import android.content.ContentValues;

import android.content.Context;

import android.content.UriMatcher;

import android.database.Cursor;

import android.database.SQLException;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

import android.database.sqlite.SQLiteQueryBuilder;

import android.net.Uri;

import android.util.Log;


import androidx.annotation.NonNull;

import androidx.annotation.Nullable;


public class DadosProvider extends ContentProvider {

   private static final int BANCO_VERSAO = 1;

   private static final String BANCO_NOME = "meu_banco";

   private static final String BANCO_TABELA = "contato";

   private static final String ID = "_id";

   private static final String NOME = "nome";

   private static final String TELEFONE = "telefone";


   private static final String CRIA_TABELA =

           "CREATE TABLE "+BANCO_TABELA +

                   "("+ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +

                   NOME+" TEXT, "+TELEFONE+" TEXT"+")";


   private static final String DELETA_TABELA = "DROP TABLE IF EXISTS "+

           BANCO_TABELA;


   private SQLiteDatabase banco;

   private static final String TAG = DadosProvider.class.getName();

   private static final String NOME_PROVIDER="com.example.appbancosqlite.DadosProvider"; //definição da autoridade (nome simbólico)

   private static final String URL = "content://"+NOME_PROVIDER+"/dados"; // definição da autoridade e da tabela a ser acessada

   private static final Uri URI_CONTEUDO = Uri.parse(URL); // converte a String em uma URI

   private static final int CODIGO_URI_TODOS = 1;

   private static final UriMatcher URI_MATCHER;


   static

   {

       //Classe de utilitária para auxiliar na correspondência de URIs em provedores de conteúdo.

       URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

       // acessa todos os registros da tabela

       URI_MATCHER.addURI(NOME_PROVIDER, "dados/", CODIGO_URI_TODOS);


   }

  @Override

   public boolean onCreate()  // acessa o banco de dados no modo escrita

   {

       Context context = getContext();

       BancoHelper helper = new BancoHelper(context);

       banco = helper.getWritableDatabase();

       if(banco!=null){

           Log.i(TAG,"provedor criado");

           return true;

       }

       return false;

   }//onCreate


   @Nullable

   @Override

   public Cursor query(Uri uri, String[] projection, String selection,

                       String[] selectionArgs, String sortOrder)

   {


       SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();

       sqb.setTables(BANCO_TABELA);

       Cursor cursor =  sqb.query(banco,projection,selection,selectionArgs

               ,null,null,null);   //obtém os dados da tabela e devolve em um Cursor

       cursor.setNotificationUri(getContext().getContentResolver(), uri); //para notificar as alterações.

       Log.i(TAG,"obtém dados");

       return cursor;

   }

   @Override

   public String getType(Uri uri) //defini o MIME TYPE

   {

       switch (URI_MATCHER.match(uri))

       {

           case CODIGO_URI_TODOS:

               return "vnd.android.cursor.dir/dados";


           default:

               throw new IllegalArgumentException("ERROR uri: " + uri);

       }

   }//getType


   @Nullable

   @Override

   public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

       long id = banco.insert(BANCO_TABELA,null,contentValues); //inseri os dados na tabela

       if (id >0)

       {

           Uri uriID = ContentUris.withAppendedId(URI_CONTEUDO,id); //obtém a URI com o ID retornado

           getContext().getContentResolver().notifyChange(uriID, null); //Notifique os observadores registrados que uma linha foi atualizada e tente sincronizar as alterações na rede.

           Log.i(TAG,"dados inseridos");

           return uriID;

       }

       throw new SQLException("ERROR: insert ");


   }//insert


   @Override

   public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {


       int count=0;

       switch (URI_MATCHER.match(uri))

       {

           case CODIGO_URI_TODOS:

               count = banco.delete(BANCO_TABELA, selection, selectionArgs); //deleta os dados da tabela

               Log.i(TAG,"dados deletados");

               break;


           default:

               throw new IllegalArgumentException

                   ("ERROR: delete ");

       }


       getContext().getContentResolver().notifyChange(uri, null);


       return count;

   }



   @Override

   public int update(@NonNull Uri uri, @Nullable ContentValues contentValues,

                     @Nullable String selection, @Nullable String[] selectionArgs) {

       int count = 0;

       switch (URI_MATCHER.match(uri))

       {

           case CODIGO_URI_TODOS:

               count =  banco.update   //altera os dados da tabela

                       (BANCO_TABELA,contentValues,selection,selectionArgs);

               Log.i(TAG,"dados alterados");

               break;


           default:

               throw new IllegalArgumentException

                   ("ERROR: update" );

       }


       getContext().getContentResolver().notifyChange(uri, null);


       return count;


   }



   private static class BancoHelper extends SQLiteOpenHelper

   {

       BancoHelper(Context context)

       {

           super(context, BANCO_NOME, null, BANCO_VERSAO);

       }


       @Override

       public void onCreate(SQLiteDatabase sqLiteDatabase)

       {

           sqLiteDatabase.execSQL(CRIA_TABELA);

       }


       @Override

       public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion,

                             int newVersion)

       {

           sqLiteDatabase.execSQL(DELETA_TABELA);

           onCreate(sqLiteDatabase);

       }

   }//inner class

}//class
