package com.example.android_socket_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class data_inpc_activity extends ListActivity{
	MyHandler1 myHandler;
	String str="";
	String length="";
	String data_inpc="";
	BufferedReader in;
	PrintWriter pw;
	String path="";
	String pre_path="";//��ǰ·������һ��·��
	 List<Map<String, Object>> list= new ArrayList<Map<String, Object>>(); 
	 Map<String,Object> map;
	 public boolean onKeyDown(int keyCode, KeyEvent event)  
	    {  
	        if (keyCode == KeyEvent.KEYCODE_BACK )  //���ؼ������
	        {
	        	pre_path=getpre_path(path);
	        	if((path.length())!=0)//��ǰ���Ǹ�Ŀ¼
	        	{
	        	  path=pre_path;
	        	  if((pre_path.length())==0)
	        	  {
	        		  pw.println("root");//���ظ�Ŀ¼
		        	  pw.flush();
	        	  }
	        	  else
	        	  {
     	            pw.println(pre_path);//������һ��·��
	        	     pw.flush();
	        	     path+="\\";
	        	  } 
	        	  return_handler();
	        	}
	        	else
	        	{//�������˵�
	        		pw.println("return");
	        		pw.flush();
	        		Intent function_intent=new Intent(data_inpc_activity.this, Function_activity.class);
	        	    startActivity(function_intent);
	        	    finish();
	        	}
	        }
	        return false;
	    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_inpc);
		try {
			in = new BufferedReader(new InputStreamReader(my_socket_client.getsocket().getInputStream(),"gb2312"));
			pw = new PrintWriter(my_socket_client.getsocket().getOutputStream());
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ListView mylistview=(ListView)findViewById(android.R.id.list);
		mylistview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//��ȡ����ǰ������ļ���
				TextView file_name=(TextView)arg1.findViewById(R.id.file_name);
				String sfile_name=file_name.getText().toString();
				 //���ļ�����ӵ���ǰ·���﷢���������
				path+=sfile_name;
				pw.println(path);
				pw.flush();
				path+="\\";
				return_handler();
			}
		});
		Button return_root=(Button)findViewById(R.id.return_root);
		return_root.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				path="";
				pre_path="";
				pw.println("root");//���ظ�Ŀ¼
				pw.flush();
				return_handler();
			}
		});
		myHandler=new MyHandler1();
		return_handler();
	}
	public void return_handler(){//���շ���˷��������ݣ����ظ�handler�Ը���UI
		new Thread(){
			public void run(){
				try {
					list.clear();
					while((str=in.readLine())!=null)
					{
						if(str.equals("over"))//��ǰĿ¼�ѷ������
						{
							 Message msg = Message.obtain();          
			   	              Bundle data = new Bundle();  
			   	              data.putString("data", "ok"); 
			   	              msg.setData(data); 
							data_inpc_activity.this.myHandler.sendMessage(msg); //���͸�handle����UI
							break;//��ǰĿ¼�������
						}
						map=new HashMap<String,Object>();
						if(str.equals(">"))//���ļ�,����>���ܳ������ļ����У�����>Ϊ��־����ֹ���ļ�������
						{
		   	              str=in.readLine();//��ȡ�ļ���
						  length=in.readLine();//��ȡ�ļ���С
						  map.put("name",str);
						  map.put("size",getFileSize(length));
						  map.put("icon",R.drawable.file);
						  list.add(map);
						}
						else
						{
				   	          map.put("name",str);//�ļ�����
							   map.put("size","");
							   map.put("icon",R.drawable.folder);
							   list.add(map);
						}
					}	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}
	public String getFileSize(String length)
	  {//���ļ���С�任��λ
		  String show=null;
		  int sub_index=0;
		  long size=Long.parseLong(length);//String to long
		  if(size<1024) 
			{
			show=String.valueOf(length)+"B"; 
			} 
		  else  if(size<=1048576) 
			{ 
			sub_index=(String.valueOf((float)size/1024)).indexOf("."); 
			show=((float)size/1024+"000").substring(0,sub_index+3)+"KB"; 
			} 
		  else if(size<=1073741824) 
			{ 
			sub_index=(String.valueOf((float)size/1048576)).indexOf("."); 
			show=((float)size/1048576+"000").substring(0,sub_index+3)+"MB"; 
			} 
		  else
		  { 
			sub_index=String.valueOf((float)size/1073741824).indexOf("."); //��2��30�η����ҵ���.��������
			show=((float)size/1073741824+"000").substring(0,sub_index+3)+"GB"; //������λ
			} 
			return show; 
	  }
      public String getpre_path(String str)
      {//���㵱ǰ·������һ·��
    	     String[] name_str=str.split("\\\\");//java��\\��ʾ\������split�Ĳ����\\��ʾ\������\\\\��ʾ\\
    	     if(name_str.length==1)
    	    	 str="";
    	     else
    	    	 str=name_str[0];
    	     for(int i=1;i<=name_str.length-2;i++)
    	     {
    	    	 str+="\\"+name_str[i];
    	     }
    	    return str;
      }
	class MyHandler1 extends Handler {   
		SimpleAdapter list_adapter;
		 public MyHandler1() {
			 list_adapter=new SimpleAdapter(data_inpc_activity.this,list, R.layout.file_list, 
					   new String[]{"icon","name","size"},
					   new int[]{R.id.icon,R.id.file_name,R.id.file_size});
			   setListAdapter(list_adapter);//Ϊlistview����������
		 }
		   public MyHandler1(Looper L) {
				   super(L);	     
		   }
	   // ���������д�˷���,��������		      
		  @Override	    
		    public void handleMessage(Message msg) {		           
		 // TODO Auto-generated method stub		         
		   super.handleMessage(msg); 

		  TextView current_path=(TextView)findViewById(R.id.current_path);
		  current_path.setText(path);//��ʾ��ǰ·��
		    Bundle b = msg.getData();    
		    data_inpc= b.getString("data");
		    if(data_inpc.equals("ok"))
		    {
		    	list_adapter.notifyDataSetChanged();
		    }
		    
		  };
		  
		}
}
