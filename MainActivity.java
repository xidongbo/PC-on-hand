package com.example.android_socket_client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends Activity {      
	MyHandler myHandler;
	@Override  
	protected void onCreate(Bundle savedInstanceState) {   
		warn_dialog();
		super.onCreate(savedInstanceState);      
		setContentView(R.layout.activity_main);  
		Button connect_button=(Button)findViewById(R.id.button1);
		myHandler=new MyHandler();
		connect_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new Thread() {        
					public void run() {    
						connect();
					}     
				}.start();  
			}
		});	
		
	} 
	//��ʾ��
	protected void warn_dialog() {  
		 Dialog warn_dialog = new AlertDialog.Builder(this). 
         setTitle("��ʾ"). 
         setMessage("��ȷ��������ѿ��������ҿͻ��������˴���ͬһ������"). 
         setPositiveButton("ȷ��", new DialogInterface.OnClickListener() { 
             @Override 
             public void onClick(DialogInterface dialog, int which) { 
                 // TODO Auto-generated method stub  
            	 dialog.dismiss();
             } 
         }). 
         create(); 
        warn_dialog.show(); 
	}

	public void connect(){
		try {
			  EditText ip_edit=(EditText)(findViewById(R.id.editText1));
			  String ip=ip_edit.getText().toString();	
			  Socket socket_client=my_socket_client.getsocket();//my_socket_client���е�socket��Ϊȫ�ֱ���ʹ��
			  SocketAddress serveraddr= new InetSocketAddress(ip, 8426);
			  socket_client.connect(serveraddr);
			  if(socket_client!=null)
			  {
				 Message msg = new Message();          
	              Bundle data = new Bundle();  
	              data.putString("success", "ok");//success�Ǳ�ǩ,handleMessage��ʹ��  
	              msg.setData(data);  
	              MainActivity.this.myHandler.sendMessage(msg); 
			  }
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			      Message msg = new Message();          
	              Bundle data = new Bundle();  
	              data.putString("success", "error");//success�Ǳ�ǩ,handleMessage��ʹ��  
	              msg.setData(data);  
	              MainActivity.this.myHandler.sendMessage(msg); // ��Handler������Ϣ,����UI
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
                Message msg = Message.obtain();          
	               Bundle data = new Bundle();  
	              data.putString("success", "error");//success�Ǳ�ǩ,handleMessage��ʹ��  
	              msg.setData(data);  
	              MainActivity.this.myHandler.sendMessage(msg); 
			e.printStackTrace();
		} 
	}       

	class MyHandler extends Handler {   
		 public MyHandler() {}
		   public MyHandler(Looper L) {
				   super(L);	     
		   }
	   // ���������д�˷���,��������		      
		  @Override	    
		    public void handleMessage(Message msg) {		           
		 // TODO Auto-generated method stub		         
		   super.handleMessage(msg);
		    Bundle b = msg.getData();    
		    String success= b.getString("success");
		    if(success=="ok")
		    {
		    	dialog();
		    }
		    else if(success=="error")
		    {
		    	error_dialog();
		    }
		  };
				
	
	//�������ӳɹ���ʾ��
	protected void dialog() {  
		  Dialog alertDialog = new AlertDialog.Builder(MainActivity.this). 
                 setTitle("��ʾ"). 
                 setMessage("���ӷ���˳ɹ���"). 
                 setPositiveButton("ȷ��", new DialogInterface.OnClickListener() { 
                      
                     @Override 
                     public void onClick(DialogInterface dialog, int which) { 
                         // TODO Auto-generated method stub  
                    	 //���ضԻ���
                    	 dialog.dismiss();
                    	 //��ת������ҳ��
                    	 Intent function_activity=new Intent(MainActivity.this,Function_activity.class);
                    	 startActivity(function_activity);
                    	 finish();//�رյ�ǰҳ��
                     } 
                 }). 
                 create(); 
                alertDialog.show(); 
	}
	//���ӳ���
	protected void error_dialog() {  
		  Dialog error_dialog = new AlertDialog.Builder(MainActivity.this). 
                 setTitle("��ʾ"). 
                 setMessage("���ӷ�����ʧ�ܣ��������ã�"). 
                 setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {                      
                     @Override 
                     public void onClick(DialogInterface dialog, int which) { 
                         // TODO Auto-generated method stub  
                    	 //���ضԻ���
                    	 dialog.dismiss();
                     } 
                 }). 
                 create(); 
		           error_dialog.show(); 
	}
			public boolean onCreateOptionsMenu(Menu menu) { 
				// Inflate the menu; this adds items to the action bar if it is present.      
				getMenuInflater().inflate(R.menu.main, menu);       
				return true;    
				}   
			}
}