package Frame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.mongodb.selector.ServerSelector;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Capabilities
{
	public static String appactivity;
	public static String apppackage;
	public static String devicename ;
	public static String platforname;
	
	public AppiumDriverLocalService service;
    //for starting the server
    public AppiumDriverLocalService startServer()
    {
        boolean flag = checkifserverisRunning(4723);//port number when we start the appium
        if(!flag)
        {
    service = AppiumDriverLocalService.buildDefaultService();//if the same service is avaiable
    service.start();
            }
        return service;//for returning service like we return 
    }
    
    public static boolean checkifserverisRunning(int port)
    {
        boolean isServerRunning=false;
        ServerSocket serversocket;
        try{
            serversocket = new ServerSocket(port);
            serversocket.close();
        }
        catch(IOException e)
        {
            isServerRunning = true;
        }
        finally {
            serversocket=null;
        }
        return isServerRunning;
    }
	
	public static void startemulator() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"/src/main/resources/extension.bat");
		Thread.sleep(5000);
	}
	
	public static AndroidDriver<AndroidElement> framecap(String appactivity,String apppackage,String devicename,String platformname) 
			throws IOException, InterruptedException {
		FileReader gp=new FileReader(System.getProperty("user.dir")+"/src/main/java/global.properties");
		Properties pro =new Properties();
		pro.load(gp);
		appactivity=pro.getProperty("appactivity");
		apppackage=pro.getProperty("apppackage");
		devicename=pro.getProperty("devicename");
		platformname=pro.getProperty("platformname");
		if(devicename.equals("Manu"))
		{
			startemulator();
		}
		
	DesiredCapabilities dc=new DesiredCapabilities();
	dc.setCapability(MobileCapabilityType.DEVICE_NAME, devicename);
	dc.setCapability(MobileCapabilityType.PLATFORM_NAME,platforname);
	//dc.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
    dc.setCapability(MobileCapabilityType. AUTOMATION_NAME,AutomationName.ANDROID_UIAUTOMATOR2);
	dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,apppackage);
	dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,appactivity);
	AndroidDriver<AndroidElement> driver=new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"),dc);
	return driver;
	
}
}