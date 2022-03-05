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
	        JFrame f=new JFrame("��ӭʹ��xxxx�����");
	        int screenwidth=(int)f.getToolkit().getScreenSize().getWidth();//��ȡ��Ļ���
			int screenheight=(int)f.getToolkit().getScreenSize().getHeight();//��ȡ��Ļ�߶�
			Dimension dm=f.getSize();
			int frameWidth=(int)dm.getWidth();//��ȡ������
			int frameHeight=(int)dm.getHeight();//��ȡ����߶�
			ImageIcon background=new ImageIcon("background.png");
			JLabel label = new JLabel(background); // �ѱ���ͼƬ��ʾ��һ����ǩ��
			label.setBounds(0,0,background.getIconWidth(),background.getIconHeight());//�ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ���������� 
			f.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE)); //���ͼƬ��frame�ĵڶ���
			JPanel jp=(JPanel)f.getContentPane(); //��ȡframe�����ϲ����Ϊ�������䱳����ɫ��JPanel������͸���ķ�����
			jp.setOpaque(false);//����͸����ʹ����ͼƬ��ʾ
			
	        JMenuBar mb=new JMenuBar();
	        mb.setBackground(Color.orange);//���ñ�����ɫ
	        JMenu function=new JMenu("���ܽ���");
	        function.setFont(new Font("dialog", 1, 15));//�������塢��ʽ����ϸ�����ֺ�
	        JMenu use=new JMenu("ʹ�÷���");
	        use.setFont(new Font("dialog", 1, 15));
	        JMenu set=new JMenu("����");
	        set.setFont(new Font("dialog", 1, 15));
	        JMenu about=new JMenu("����");
	        about.setFont(new Font("dialog", 1, 15));
	        
	        JMenuItem data_inpc=new JMenuItem("�����ļ�");
	        data_inpc.setFont(new Font("dialog", 1, 15));
	        JMenuItem model_mouse=new JMenuItem("ģ�����");
	        model_mouse.setFont(new Font("dialog", 1, 15));
	        JMenuItem model_touch=new JMenuItem("ģ�ⴥ����");
	        model_touch.setFont(new Font("dialog", 1, 15));
	        JMenuItem camera=new JMenuItem("Զ������ͷ");
	        camera.setFont(new Font("dialog", 1, 15));
	        JMenuItem control=new JMenuItem("ʵʱ����");
	        control.setFont(new Font("dialog", 1, 15));
	        JMenuItem power=new JMenuItem("��Դ");
	        power.setFont(new Font("dialog", 1, 15));
	        JMenuItem more=new JMenuItem("����");
	        more.setFont(new Font("dialog", 1, 15));
	        JMenuItem start=new JMenuItem("��������");
	        start.setFont(new Font("dialog", 1, 15));
	        JMenuItem port=new JMenuItem("�˿�");
	        port.setFont(new Font("dialog", 1, 15));
	        
	        JLabel iptext=new JLabel("�����IP��"+ip);
	        JLabel connecttext=new JLabel("������ѿ������ȴ��ͻ������ӡ���");
	        JLabel all_function=new JLabel("���幦��:");
	        JLabel data_inpc_jlabel=new JLabel("�����ļ�");
	        JLabel model_mouse_jlabel=new JLabel("ģ�����");
	        JLabel model_touch_jlabel=new JLabel("ģ�ⴥ����");
	        JLabel camrea_jlabel=new JLabel("Զ������ͷ");
	        JLabel control_jlabel=new JLabel("ʵʱ����");
	        JLabel power_jlabel=new JLabel("��Դ");
	        JLabel more_jlabel=new JLabel("����");
	        
	        iptext.setBounds(30, 30, 300, 20);//����λ��
	        iptext.setFont(new Font("dialog", 1, 20));//�������塢��ʽ����ϸ�����ֺ�
	        connecttext.setBounds(30, 70, 400, 20);//����λ��
	        connecttext.setFont(new Font("dialog", 1, 20));//�������塢��ʽ����ϸ�����ֺ�
	        all_function.setBounds(10, 200, 200, 30);//����λ��
	        all_function.setFont(new Font("dialog", 1, 30));//�������塢��ʽ����ϸ�����ֺ�
	        data_inpc_jlabel.setBounds(130, 260, 200, 30);//����λ��
	        data_inpc_jlabel.setFont(new Font("dialog", 1, 25));//�������塢��ʽ����ϸ�����ֺ�
	        model_mouse_jlabel.setBounds(220, 330, 200, 30);//����λ��
	        model_mouse_jlabel.setFont(new Font("dialog", 1, 25));//�������塢��ʽ����ϸ�����ֺ�
	        model_touch_jlabel.setBounds(345, 330, 200, 30);//����λ��
	        model_touch_jlabel.setFont(new Font("dialog", 1, 25));//�������塢��ʽ����ϸ�����ֺ�
	        camrea_jlabel.setBounds(420, 260, 200, 30);//����λ��
	        camrea_jlabel.setFont(new Font("dialog", 1, 25));//�������塢��ʽ����ϸ�����ֺ�
	        control_jlabel.setBounds(280, 260, 200, 30);//����λ��
	        control_jlabel.setFont(new Font("dialog", 1, 25));//�������塢��ʽ����ϸ�����ֺ�
	        power_jlabel.setBounds(130, 330, 200, 30);//����λ��
	        power_jlabel.setFont(new Font("dialog", 1, 25));//�������塢��ʽ����ϸ�����ֺ�
	        more_jlabel.setBounds(500, 330, 200, 30);//����λ��
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
			f.setAlwaysOnTop(true);//�������������ǰ��
			f.setResizable(false);//���ܸı䴰�ڴ�С
			f.setSize(600, 490);
			f.setLocation((screenwidth-frameWidth)/4,(screenheight-frameHeight)/4);//���ý���λ����Ļ����
			f.setVisible(true);
		    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        try {  
	            if((socket=serverSocket.accept())!=null){   
	            	connecttext.setText("�ͻ������ӳɹ�");
	                new Thread(this).start();    //������һ���ͻ��˵����ӣ�����һ�������߳� 
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
	            while(!sk.isClosed())//���ͻ���û�йرգ����������ͨ��
	            {
	            	String str;
	            	while((str=br.readLine())!=null)//���մӷ���˷������ַ���
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
	            	System.exit(0);//�ͻ��˶Ͽ����رճ���
	            }
	            System.exit(0);//�ͻ��˶Ͽ����رճ���
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            System.exit(0);  
	            return;  
	        }  
	            }      
	    }   