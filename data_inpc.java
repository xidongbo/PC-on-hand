package socket;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class data_inpc {
	PrintWriter pw=null;
	String rootpath="";
	public data_inpc(){}
     public void data_inpc_root(){
    	 try {
             pw=new PrintWriter(SocketServer.getsocket().getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 File [] root = File.listRoots(); //��ȡ���̷���
    	 for(File file : root) 
    	 { 
    		 if(file.canRead())//����ȡ����
    		 {
    		 rootpath=file.getPath().substring(0, 2);//����ȡ��б��
    	     pw.println(rootpath); 
    		 pw.flush();
    		 }
    	 } 
    	 pw.println("over");//��ʾ��ǰ�ļ�Ŀ¼�������
    	 pw.flush();
     }
     public void data_inpc1(String str)
     {
    	 File folder=new File(str);
    	 if(folder.isFile())//������ļ��ͷ����ļ�
    	 {
    		//�����ļ��������
    	 }
    	 else//�������ļ�����Ŀ¼
    	 {
    		 File folder2=new File(str+"\\");
	    	 File[] list=folder2.listFiles();//��ȡstr\�ļ����µ�Ŀ¼
	    	 for(File file:list)
	    	 {
	    		 if(file.isFile())
	    		 {
	    			 pw.println(">");//���߿ͻ������ļ������ļ���,>���ܳ������ļ����У�����>Ϊ��־����ֹ����
	        		 pw.flush();
	        		 pw.println(file.getName());//�����ļ���
	        		 pw.flush();
	        		 pw.println(file.length());//�����ļ���С
	        		 pw.flush();
	    		 }
	    		 else{
	    		 pw.println(file.getName());//����str�ļ����µ�Ŀ¼���ͻ���
	    		 pw.flush();
	    		 }
	    	 }
	    	 pw.println("over");
	    	 pw.flush();
    	 }
     }
}
