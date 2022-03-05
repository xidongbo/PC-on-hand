package socket;

import java.io.IOException;

public class power {
   public power(){}
   public power(String str){
	   switch(str)
		{
		case "cancle":
			break;
		case "lock":
		try {
			Runtime.getRuntime().exec("RunDll32.exe user32.dll,LockWorkStation");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			break;
		case "shutdown":
		try {
			
			Runtime.getRuntime().exec("shutdown -s -t 00");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			break;
		case "reset":
		try {
			Runtime.getRuntime().exec("shutdown -r -t 00");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			break;
			default:
				break;
   	}
   }
}
