package Frame;

import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import freemarker.core.StopException;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class test_case extends Capabilities
{
	AndroidDriver<AndroidElement> driver;
@BeforeTest
public void bt() throws IOException, InterruptedException
{
	service = startServer();
	driver = framecap(appactivity, apppackage, devicename, platforname);
}
@Test
public void tc1() throws InterruptedException
{
	//service = startServer();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.findElement(MobileBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
	Thread.sleep(3000);
	service.stop();
}
}
