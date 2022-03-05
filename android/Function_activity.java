package com.example.android_socket_client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Function_activity extends Activity{
	public void sendmsg(String msg){
		Socket socket_client=my_socket_client.getsocket();
		try {
			PrintWriter out=new PrintWriter(socket_client.getOutputStream());
			out.println(msg);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void showDialog(String str)
	     {
	         new AlertDialog.Builder(Function_activity.this).setMessage(str).show();
	     }
	 @SuppressLint("HandlerLeak") Handler myhandler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch(msg.what)
			{
			case 1:
				finish();
			}
		}
	};

	 public boolean onKeyDown(int keyCode, KeyEvent event)  
	    {  
	        if (keyCode == KeyEvent.KEYCODE_BACK )  //返回键被点击
	        {
	        	AlertDialog.Builder dialog=new AlertDialog.Builder(Function_activity.this);
	        	dialog.setTitle("确定要退出？");
	        	dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						sendmsg("close");
						try {
							my_socket_client.getsocket().close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						finish();
					}
				});
	        	dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						
					}
				});
	        	dialog.show();
	        }
	        return false;
	    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function_activity);
		ImageButton data_inpc=(ImageButton)findViewById(R.id.data_inpc);
		ImageButton camera=(ImageButton)findViewById(R.id.camera);
		ImageButton model_mouse=(ImageButton)findViewById(R.id.model_mouse);
		ImageButton model_touch=(ImageButton)findViewById(R.id.model_touch);
		ImageButton control=(ImageButton)findViewById(R.id.control);
		ImageButton power=(ImageButton)findViewById(R.id.power);
		ImageButton more=(ImageButton)findViewById(R.id.more);
				//查看电脑文件
		data_inpc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub  
			sendmsg("data_inpc");
			Intent  data_inpc_activity=new Intent(Function_activity.this,data_inpc_activity.class);
			startActivity(data_inpc_activity);//切换页面
			finish();

			}
		});
		
		//打开电脑摄像头
		camera.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sendmsg("camera");
				Intent  camera_activity=new Intent(Function_activity.this,camera_activity.class);
				startActivity(camera_activity);//切换页面
				finish();

			}
		});
		
		//模拟鼠标
		model_mouse.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sendmsg("model_mouse");
				Intent  model_mouse_activity=new Intent(Function_activity.this,model_mouse_activity.class);
				startActivity(model_mouse_activity);//切换页面
				finish();
			}
		});
		
		//模拟触摸板
		model_touch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sendmsg("model_touch");
				Intent  model_touch_activity=new Intent(Function_activity.this,model_touch_activity.class);
				startActivity(model_touch_activity);//切换页面
				finish();
			}
		});
		
		//实时控制电脑
		control.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sendmsg("control");
				Intent  control_activity=new Intent(Function_activity.this,control_activity.class);
				startActivity(control_activity);//切换页面
				finish();

			}
		});
		
		//电源
		power.setOnClickListener(new View.OnClickListener() {
			int select;
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sendmsg("power");
				AlertDialog.Builder power_dialog = new AlertDialog.Builder(Function_activity.this);
				power_dialog.setTitle("POWER");
				final String[]  m_Items={"锁屏","关机","重启"};
				power_dialog.setSingleChoiceItems(m_Items, 0, new DialogInterface.OnClickListener()
				         {
				             public void onClick(DialogInterface dialog, int whichButton)
				             {
				                 showDialog("服务端即将" + m_Items[whichButton]);
				                 select=whichButton;
				             }
				         });
				power_dialog.setPositiveButton("取消", new DialogInterface.OnClickListener()
				         {
				             public void onClick(DialogInterface dialog, int whichButton)
				             {
				               sendmsg("cancle");
				             }
				         });
				power_dialog.setNegativeButton("确定", new DialogInterface.OnClickListener()
				         {
				             public void onClick(DialogInterface dialog, int whichButton)
				             { 
				            	  switch(select)
					                 {
					                 case 0:
					                	 sendmsg("lock");
					                	 break;
					                 case 1:
					                	 Toast.makeText(getApplicationContext(), "将于5秒后退出",Toast.LENGTH_LONG).show();
					                	 new Thread()
					     				{
					     					public void run() {
					     						 sendmsg("shutdown");
					     						 try {
													Thread.sleep(5000);
													Message message = new Message();  
										            message.what = 1;  
										            myhandler.sendMessage(message);  
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
					     					}
					     				}.start();
					                	 
					                	 break;
					                 case 2:
					                	 Toast.makeText(getApplicationContext(), "将于5秒后退出",Toast.LENGTH_LONG).show();
					                	 new Thread()
					     				{
					     					public void run() {
					     						 sendmsg("reset");
					     						 try {
													Thread.sleep(5000);
													Message message = new Message();  
										            message.what = 1;  
										            myhandler.sendMessage(message);  
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
					     					}
					     				}.start();
					                	 break;
					                 }
				             }
				         });
				power_dialog.create();
				power_dialog.show();
			}
		});
		
		//更多功能
		more.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sendmsg("more");
				Intent  more_activity=new Intent(Function_activity.this,more_activity.class);
				startActivity(more_activity);//切换页面
				finish();

			}
		});
		
}
}
