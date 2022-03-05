package com.example.android_socket_client;
import java.net.Socket;
import android.app.Application;
public class my_socket_client extends Application{
      private static Socket socket_client=new Socket();
      public static Socket getsocket(){
    	  return socket_client;
      }
      public static void setsocket(Socket s){
    	  socket_client=s;
      }
      @Override  
         public void onCreate(){  
            super.onCreate();  
        }
}
