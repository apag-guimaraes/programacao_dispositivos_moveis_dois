import android.app.IntentService;

import android.content.Intent;

import androidx.annotation.Nullable;


public class Service1 extends IntentService {

   public Service1() {

       super("Service 1");

   }


   @Override

   protected void onHandleIntent(@Nullable Intent intent) {

       System.out.println("serviço....");

   }

}
