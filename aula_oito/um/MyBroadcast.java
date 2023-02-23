public class MyBroadcast extends BroadcastReceiver {

   @Override

   public void onReceive(Context context, Intent intent) {

       Toast.makeText(context,"hora de estudar",Toast.LENGTH_SHORT).show();

   }

}
