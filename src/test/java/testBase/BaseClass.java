package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Master", "datadriven"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {
        //loading config.properties file
        //you can use FileReader class as well instead of Fileinputstram
        FileInputStream file = new FileInputStream("src/test/resources/config.properties");
        p = new Properties();//creating object of properties
        p.load(file);//loading file into properties object
        logger = LogManager.getLogger(this.getClass());//for log4j2 logging using log4j2.xml file
        // execution environment local or remote using config.properties file
        if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            //os
            if (os.equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("mac")) {
                capabilities.setPlatform(Platform.MAC);
            } else {
                System.out.println("No matching OS");
                return;
            }
            //browser
            switch (br.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("edge");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                default:
                    System.out.println("Invalid browser name....");
                    return;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444"), capabilities);
        }
        if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
            switch (br.toLowerCase()) {
//            case "chrome" : WebDriverManager.chromedriver().setup(); driver=new ChromeDriver(); break;
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("Invalid browser name....");
                    return;
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("baseURL")); //reading url from config file
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Master", "datadriven"})
    public void tearDown() {
        driver.quit();
    }

    //to generate random string for email
    public String randomString() {
        String generatestring = RandomStringUtils.randomAlphabetic(6);
        return generatestring;
    }

    // to generate random number for Telephone
    public String randomNumber() {
        String generateNumber = RandomStringUtils.randomNumeric(10);
        return generateNumber;
    }

    //to generate random string for PASSWORD
    public String randomAlphaNumber() {
        String generateString = RandomStringUtils.randomAlphabetic(5);
        String generateNumber = RandomStringUtils.randomNumeric(4);
        return (generateString + "@" + generateNumber);
    }

    //to capture screenshot when test case fails
    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());//timestamp
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;// type casting
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);//file format
        String targetFilePath = System.getProperty("user.dir") + ".\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);//rename the file
        return targetFilePath;
    }
}
