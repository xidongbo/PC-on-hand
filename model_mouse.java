package socket;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;


public class model_mouse {
	 public model_mouse(){}
	  public void model_mouse1(String str) throws AWTException, IOException{
		  Robot robot=new Robot();
		  switch(str)
		  	{
		  	case "singledown":
		  		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);//�������
		  		break;
		  	case "singleup":
		  		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);//�ͷ����
		  		break;
		  	case "right":
		  		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);//�����Ҽ�
		  		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);//�ͷ����
		  		break;
		  	case "up":
		  		robot.mouseWheel(-1);//���Ϲ���һ���̶�
		  		break;
		  	case "down":
		  		robot.mouseWheel(1);//���¹���һ���̶�
		  		break;
		  	default:
		  		String xy[]=str.split("\\+");//��+Ϊ��׼���ַ����ֳ�������X��Y
		  		Point point=MouseInfo.getPointerInfo().getLocation();//��ȡ��굱ǰ����
		  		point.x-=Integer.parseInt(xy[0]);//��ǰ�������ƫ����
		  		point.y-=Integer.parseInt(xy[1]);//��ǰ�������ƫ����
		  		robot.mouseMove(point.x, point.y);
		  		break;
	  	}
		  robot=null;
	  }
}
