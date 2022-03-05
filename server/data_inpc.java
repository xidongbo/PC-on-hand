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
    	 File [] root = File.listRoots(); //获取磁盘分区
    	 for(File file : root) 
    	 { 
    		 if(file.canRead())//不获取光驱
    		 {
    		 rootpath=file.getPath().substring(0, 2);//不获取反斜杠
    	     pw.println(rootpath); 
    		 pw.flush();
    		 }
    	 } 
    	 pw.println("over");//表示当前文件目录传输完毕
    	 pw.flush();
     }
     public void data_inpc1(String str)
     {
    	 File folder=new File(str);
    	 if(folder.isFile())//如果是文件就发送文件
    	 {
    		//发送文件到服务端
    	 }
    	 else//否则发送文件夹下目录
    	 {
    		 File folder2=new File(str+"\\");
	    	 File[] list=folder2.listFiles();//获取str\文件夹下的目录
	    	 for(File file:list)
	    	 {
	    		 if(file.isFile())
	    		 {
	    			 pw.println(">");//告诉客户端是文件，非文件夹,>不能出现在文件名中，故以>为标志，防止出错
	        		 pw.flush();
	        		 pw.println(file.getName());//发送文件名
	        		 pw.flush();
	        		 pw.println(file.length());//发送文件大小
	        		 pw.flush();
	    		 }
	    		 else{
	    		 pw.println(file.getName());//发送str文件夹下的目录到客户端
	    		 pw.flush();
	    		 }
	    	 }
	    	 pw.println("over");
	    	 pw.flush();
    	 }
     }
}
