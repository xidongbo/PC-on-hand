package com.example.android_socket_client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

public class model_touch_activity extends Activity implements OnTouchListener, OnGestureListener{
	public void sendmsg(String msg){//��msg���͵������
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
	    public boolean onKeyDown(int keyCode, KeyEvent event)  
	    {  
	        if (keyCode == KeyEvent.KEYCODE_BACK )  //���ؼ������
	        {
	        	sendmsg("return");
	           Intent function_activity=new Intent(model_touch_activity.this,Function_activity.class);
	        	startActivity(function_activity);
	        	finish();
	        }
	        return false;
	    }
    GestureDetector gestureDetector=null;
    int x;
    int y;
    String xy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.model_touch);
		ImageButton single=(ImageButton)findViewById(R.id.single);
		ImageButton right=(ImageButton)findViewById(R.id.right);
		ImageButton touch=(ImageButton)findViewById(R.id.touch);
		touch.setLongClickable(true);
		single.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction())
				{
				case KeyEvent.ACTION_DOWN:
					sendmsg("singledown");//��������
					break;
				case KeyEvent.ACTION_UP:
					sendmsg("singleup");//�����ɿ�
					break;
				default:
					break;
				}
				return false;
			}
		});
		
		right.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sendmsg("right");//�һ�
			}
		});
		gestureDetector=new GestureDetector(this,this);
		touch.setOnTouchListener(new View.OnTouchListener() {
			//��touch��������ʶ��
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				gestureDetector.onTouchEvent(event);
				return true;
			}
		});
		

	}
	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distancex,
			float distancey) {
		// TODO Auto-generated method stub
		x=(int) distancex;//����Ϊ��
		y=(int)distancey;//����Ϊ��
		xy=String.valueOf(x)+"+"+String.valueOf(y);//ת��ΪX+Y����ʽ����
		sendmsg(xy);
		return false;
	}
	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		sendmsg("singledown");
		sendmsg("singleup");
		return false;
	}
	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
