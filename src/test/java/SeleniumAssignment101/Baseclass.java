package SeleniumAssignment101;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


public class Baseclass {

    private String username="";
    private String accesskey="";
    private String hubURL="hub.lambdatest.com/wd/hub";

    protected RemoteWebDriver driver;

    @Parameters({"platformname","browser","browserversion"})
    @BeforeTest
    public void setup(String platformname, String browser, String browserversion)
    {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("PlatformName",platformname);
        capabilities.setCapability("Browsername",browser);
        capabilities.setCapability("Browserversion",browserversion);

        HashMap<String,Object> exploreoptions=new HashMap<>();
        exploreoptions.put("username",username);  //username
        exploreoptions.put("accesskey",accesskey); //accesskey
        exploreoptions.put("visible",true); //screenshots
        exploreoptions.put("video",true); //video recording
        exploreoptions.put("network",true);//Network log
        exploreoptions.put("build","final-build"); //Build Name
        exploreoptions.put("project","TestMuAI");  //Project Name
        exploreoptions.put("name","Selenium Java TestMu AI"); //Test Name
        exploreoptions.put("tunnel",false); //Disabling the tunnel for anywhere accessible URI
        exploreoptions.put("console",true); //Console  log
        exploreoptions.put("w3c",true); //Enable w3c webdriver protocol for compatibility with the largest webdriver standards
        exploreoptions.put("terminal",true); //Terminal log

        //Options check in chromeoptions
        capabilities.setCapability("Exploreoptions",exploreoptions);

        try {
            driver=new RemoteWebDriver(new URL("https://"+ username + ":" + accesskey + "@" + hubURL),capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URl: "+e.getMessage());
            e.printStackTrace();
        } catch (Exception e)
        {
            System.out.println("Error while initializing driver: "+ e.getMessage());
            e.printStackTrace();
        }

    }

    @BeforeTest
    public void startup()
    {
        driver=new ChromeDriver();

    }

    @BeforeMethod
            public void SetURI()
    {
        if (driver!=null)
        {
            driver.manage().window().maximize();
            driver.get("https://www.testmuai.com/selenium-playground/");
        }
        else
        {
            System.out.println("Driver is not initialized");
        }
    }

    @AfterMethod
    public void takessonfailure(ITestResult testResult)
    {
        TakesScreenshot snap= (TakesScreenshot) driver;
        File source=snap.getScreenshotAs(OutputType.FILE);
        File destination=new File(System.getProperty("user.dir")+"/snaps/"+testResult.getName()+ ".png");



        try {
            FileHandler.copy(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void teardown()
    {
        if (driver !=null)
        {
            driver.quit();
        }

    }
}
