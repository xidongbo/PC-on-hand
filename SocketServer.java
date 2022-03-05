package socket;  

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class SocketServer implements Runnable{     
	    ServerSocket serverSocket;  
	    static Socket socket;
	    String ip=null;
	    public static Socket getsocket()
	    {
	    	return socket;
	    }
	    public SocketServer(){  
	        try {  
	            serverSocket=new ServerSocket(8426);  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();   
	            return;  
	        }  
	      try {
			 ip=InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	        JFrame f=new JFrame("欢迎使用xxxx服务端");
	        int screenwidth=(int)f.getToolkit().getScreenSize().getWidth();//获取屏幕宽度
			int screenheight=(int)f.getToolkit().getScreenSize().getHeight();//获取屏幕高度
			Dimension dm=f.getSize();
			int frameWidth=(int)dm.getWidth();//获取界面宽度
			int frameHeight=(int)dm.getHeight();//获取界面高度
			ImageIcon background=new ImageIcon("background.png");
			JLabel label = new JLabel(background); // 把背景图片显示在一个标签里
			label.setBounds(0,0,background.getIconWidth(),background.getIconHeight());//把标签的大小位置设置为图片刚好填充整个面 
			f.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE)); //添加图片到frame的第二层
			JPanel jp=(JPanel)f.getContentPane(); //获取frame的最上层面板为了设置其背景颜色（JPanel有设置透明的方法）
			jp.setOpaque(false);//设置透明以使背景图片显示
			
	        JMenuBar mb=new JMenuBar();
	        mb.setBackground(Color.orange);//设置背景颜色
	        JMenu function=new JMenu("功能介绍");
	        function.setFont(new Font("dialog", 1, 15));//设置字体、样式（粗细）、字号
	        JMenu use=new JMenu("使用方法");
	        use.setFont(new Font("dialog", 1, 15));
	        JMenu set=new JMenu("设置");
	        set.setFont(new Font("dialog", 1, 15));
	        JMenu about=new JMenu("关于");
	        about.setFont(new Font("dialog", 1, 15));
	        
	        JMenuItem data_inpc=new JMenuItem("电脑文件");
	        data_inpc.setFont(new Font("dialog", 1, 15));
	        JMenuItem model_mouse=new JMenuItem("模拟鼠标");
	        model_mouse.setFont(new Font("dialog", 1, 15));
	        JMenuItem model_touch=new JMenuItem("模拟触摸板");
	        model_touch.setFont(new Font("dialog", 1, 15));
	        JMenuItem camera=new JMenuItem("远程摄像头");
	        camera.setFont(new Font("dialog", 1, 15));
	        JMenuItem control=new JMenuItem("实时控制");
	        control.setFont(new Font("dialog", 1, 15));
	        JMenuItem power=new JMenuItem("电源");
	        power.setFont(new Font("dialog", 1, 15));
	        JMenuItem more=new JMenuItem("更多");
	        more.setFont(new Font("dialog", 1, 15));
	        JMenuItem start=new JMenuItem("开机启动");
	        start.setFont(new Font("dialog", 1, 15));
	        JMenuItem port=new JMenuItem("端口");
	        port.setFont(new Font("dialog", 1, 15));
	        
	        JLabel iptext=new JLabel("服务端IP："+ip);
	        JLabel connecttext=new JLabel("服务端已开启，等待客户端连接……");
	        JLabel all_function=new JLabel("具体功能:");
	        JLabel data_inpc_jlabel=new JLabel("电脑文件");
	        JLabel model_mouse_jlabel=new JLabel("模拟鼠标");
	        JLabel model_touch_jlabel=new JLabel("模拟触摸板");
	        JLabel camrea_jlabel=new JLabel("远程摄像头");
	        JLabel control_jlabel=new JLabel("实时控制");
	        JLabel power_jlabel=new JLabel("电源");
	        JLabel more_jlabel=new JLabel("更多");
	        
	        iptext.setBounds(30, 30, 300, 20);//设置位置
	        iptext.setFont(new Font("dialog", 1, 20));//设置字体、样式（粗细）、字号
	        connecttext.setBounds(30, 70, 400, 20);//设置位置
	        connecttext.setFont(new Font("dialog", 1, 20));//设置字体、样式（粗细）、字号
	        all_function.setBounds(10, 200, 200, 30);//设置位置
	        all_function.setFont(new Font("dialog", 1, 30));//设置字体、样式（粗细）、字号
	        data_inpc_jlabel.setBounds(130, 260, 200, 30);//设置位置
	        data_inpc_jlabel.setFont(new Font("dialog", 1, 25));//设置字体、样式（粗细）、字号
	        model_mouse_jlabel.setBounds(220, 330, 200, 30);//设置位置
	        model_mouse_jlabel.setFont(new Font("dialog", 1, 25));//设置字体、样式（粗细）、字号
	        model_touch_jlabel.setBounds(345, 330, 200, 30);//设置位置
	        model_touch_jlabel.setFont(new Font("dialog", 1, 25));//设置字体、样式（粗细）、字号
	        camrea_jlabel.setBounds(420, 260, 200, 30);//设置位置
	        camrea_jlabel.setFont(new Font("dialog", 1, 25));//设置字体、样式（粗细）、字号
	        control_jlabel.setBounds(280, 260, 200, 30);//设置位置
	        control_jlabel.setFont(new Font("dialog", 1, 25));//设置字体、样式（粗细）、字号
	        power_jlabel.setBounds(130, 330, 200, 30);//设置位置
	        power_jlabel.setFont(new Font("dialog", 1, 25));//设置字体、样式（粗细）、字号
	        more_jlabel.setBounds(500, 330, 200, 30);//设置位置
	        more_jlabel.setFont(new Font("dialog",1,25));
	        
	        f.getContentPane().setLayout(null);
	        f.getContentPane().add(iptext);
	        f.getContentPane().add(connecttext);
	        f.getContentPane().add(all_function);
	        f.getContentPane().add(data_inpc_jlabel);
	        f.getContentPane().add(model_mouse_jlabel);
	        f.getContentPane().add(model_touch_jlabel);
	        f.getContentPane().add(camrea_jlabel);
	        f.getContentPane().add(power_jlabel);
	        f.getContentPane().add(control_jlabel);
	        f.getContentPane().add(more_jlabel);
	        
			mb.add(function);
			mb.add(use);
			mb.add(set);
			mb.add(about);
			
			function.add(data_inpc);
			function.add(model_mouse);
			function.add(model_touch);
			function.add(camera);
			function.add(control);
			function.add(power);
			function.add(more);
			
			set.add(start);
			set.add(port);
			
			f.setJMenuBar(mb);
			f.setAlwaysOnTop(true);//总是在桌面的最前边
			f.setResizable(false);//不能改变窗口大小
			f.setSize(600, 490);
			f.setLocation((screenwidth-frameWidth)/4,(screenheight-frameHeight)/4);//设置界面位于屏幕中央
			f.setVisible(true);
		    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        try {  
	            if((socket=serverSocket.accept())!=null){   
	            	connecttext.setText("客户端连接成功");
	                new Thread(this).start();    //侦听到一个客户端的连接，开启一个工作线程 
	            }  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
    }  
	    public static void main(String[] args) {  
	        new SocketServer();  
	  
	    }  
	  
	    @Override  
	    public void run() {  
	        final Socket sk=socket;  
	        InputStream is=null;
	        OutputStream os=null;
	        BufferedReader br=null;
	        PrintWriter pw=null;
	        try {  
	            is=sk.getInputStream();
	            os=sk.getOutputStream();
	            br=new BufferedReader(new InputStreamReader(is,"Utf8"));  
	            pw=new PrintWriter(os);
	            while(!sk.isClosed())//当客户端没有关闭，则进行数据通信
	            {
	            	String str;
	            	while((str=br.readLine())!=null)//接收从服务端发来的字符串
	            	{
	            	    switch(str)
	            	    {
	            	    case "data_inpc":
	            	    	data_inpc file=new data_inpc();
	            	    	file.data_inpc_root();
	            	    	while((str=br.readLine())!=null)
	            	    	{
	            	    		if(str.equals("return"))
	            	    			break;
	            	    		else if(str.equals("root"))
	            	    			file.data_inpc_root();
	            	    		else
	            	    		   file.data_inpc1(str);
	            	    	}
	            	    	break;
	            	    case "camera":
	            	    	break;
	            	    case "model_mouse":
	            	    	model_mouse mm=new model_mouse();
	            	    	while((str=br.readLine())!=null)
	            	    	{
	            	    		if(str.equals("return"))
	            	    			break;
            	    			try {
									mm.model_mouse1(str);
								} catch (AWTException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	            	    	}
	            	    	break;
	            	    case "model_touch":
	            	    	model_touch mt=new model_touch();
	            	    	while((str=br.readLine())!=null)
	            	    	{
	            	    		if(str.equals("return"))
	            	    			break;
            	    			try {
									mt.model_touch1(str);
								} catch (AWTException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	            	    	}
	            	    	break;
	            	    case "control":
	            	    	break;
	            	    case "power":
	            	    	str=br.readLine();
	            	    	new power(str);
	            	    	break;
	            	    case "more":
	            	    	break;
	            	    case "close":
	            	    	serverSocket.close();
	            	    	socket.close();
	            	    	System.exit(0);
	            	    	break;
	            	    default:
	            	    break;
	            	    }
	            	}
	            	System.exit(0);//客户端断开，关闭程序
	            }
	            System.exit(0);//客户端断开，关闭程序
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            System.exit(0);  
	            return;  
	        }  
	            }      
	    }   