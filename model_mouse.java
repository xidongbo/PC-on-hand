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
		  		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);//单机左键
		  		break;
		  	case "singleup":
		  		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);//释放左键
		  		break;
		  	case "right":
		  		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);//单机右键
		  		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);//释放左键
		  		break;
		  	case "up":
		  		robot.mouseWheel(-1);//向上滚动一个刻度
		  		break;
		  	case "down":
		  		robot.mouseWheel(1);//向下滚动一个刻度
		  		break;
		  	default:
		  		String xy[]=str.split("\\+");//以+为标准将字符串分成两部分X和Y
		  		Point point=MouseInfo.getPointerInfo().getLocation();//获取鼠标当前坐标
		  		point.x-=Integer.parseInt(xy[0]);//当前坐标加上偏移量
		  		point.y-=Integer.parseInt(xy[1]);//当前坐标加上偏移量
		  		robot.mouseMove(point.x, point.y);
		  		break;
	  	}
		  robot=null;
	  }
}
