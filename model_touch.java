package socket;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
public class model_touch {
  public model_touch(){}
  public void model_touch1(String str) throws AWTException, IOException{
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
	  	default:
	  		String xy[]=str.split("\\+");//��+Ϊ��׼���ַ����ֳ�������X��Y
	  		Point point=MouseInfo.getPointerInfo().getLocation();//��ȡ��굱ǰ����
	  		point.x-=Integer.parseInt(xy[1]);//�������Ӧת��
	  		point.y+=Integer.parseInt(xy[0]);//�������Ӧת��
	  		robot.mouseMove(point.x, point.y);
	  		break;
	  		
  	     }
	     robot=null;
  }

}
