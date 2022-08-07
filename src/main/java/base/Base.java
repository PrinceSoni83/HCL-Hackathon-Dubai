package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;


public class Base {
    public static WebDriver driver;
    public Properties properties;

    public WebDriver initialiseDriver() throws IOException {
        properties = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/configs/Configuration.properties");
        properties.load(fis);
        String browserName = properties.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            //Execute the tests in Chrome Browser
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            //Execute the tests in Firefox Browser
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            //Execute the tests in Edge Browser
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }else if (browserName.equalsIgnoreCase("safari")) {
            //Execute the tests in Safari Browser
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }

        /* Add Project Level Synchronisation page elements to load*/
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public void waitForElement(WebDriver driver, WebElement element) throws IOException {
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(40));
        wait.pollingEvery(Duration.ofSeconds(2));
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        TakesScreenshot ts = ((TakesScreenshot) driver);
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"/reports/"+testCaseName+".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }


}
