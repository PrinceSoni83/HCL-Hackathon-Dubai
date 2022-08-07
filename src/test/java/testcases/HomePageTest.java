package testcases;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;


public class HomePageTest extends Base {
    public WebDriver driver;

    @BeforeTest
    public void initialize() throws IOException {
        driver = initialiseDriver();
        driver.get(properties.getProperty("baseurl"));
    }

    @Test(description = "Verify Home Page Navigation and Home Page content")
    public void homepage_tc_01_02() throws IOException {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().maximize();
        Assert.assertEquals(driver.getTitle(), "Healthcare Marketing and Medical Advertising for Doctors");
        Assert.assertTrue(homePage.getHomePageLogo().isDisplayed());
    }


    @Test(description = "Verify the behavior of About us link")
    public void about_tc_01() throws IOException {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().maximize();
        Assert.assertTrue(homePage.getAboutLinkMenu().isDisplayed());
        Assert.assertTrue(homePage.getAboutLinkMenu().isEnabled());
    }

    @Test(description = "Mouse Hover the About link and Verify the Testimonial link")
    public void about_tc_02() throws IOException {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().maximize();
        Assert.assertTrue(homePage.getAboutLinkMenu().isDisplayed());
        Assert.assertTrue(homePage.getAboutLinkMenu().isEnabled());
        WebElement aboutMenuLink = homePage.getAboutLinkMenu();
        //Instantiating Actions class
        Actions actions = new Actions(driver);
        actions.moveToElement(aboutMenuLink);
        WebElement testimonialLink = homePage.getTestimonialSubMenuLink();
        actions.moveToElement(testimonialLink);
        actions.click().build().perform();
        Assert.assertTrue(homePage.getFirstTestimonialText().isDisplayed());
    }

    @Test(description = "Compare the screenshot for Testimonial")
    public void testimonials_tc_01() throws IOException, InterruptedException {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().maximize();
        //Get WebElement reference of logo
        WebElement headers = homePage.getHomePageHeaders();
        //Capture and store logo image
        Screenshot shot = new AShot().takeScreenshot(driver, headers);
        File file = new File(System.getProperty("user.dir")+"/reports/headersActual.png");
        System.out.println(file);
        ImageIO.write(shot.getImage(), "PNG", file);
        //Getting Expected Image
        BufferedImage expectedImg = ImageIO.read(file);
        //Getting Actual Image
        BufferedImage actualImg = shot.getImage();
        //Image Comparison
        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff dif = imgDiff.makeDiff(expectedImg, actualImg);
        if(dif.hasDiff()){
            System.out.println("Both images are different");
        }else {
            System.out.println("Both images are same");
        }
    }

    @Test(description = "Copy the Testimonial link and paste it into a text file")
    public void testimonials_tc_02() throws IOException {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().maximize();
        WebElement aboutMenuLink = homePage.getAboutLinkMenu();
        //Instantiating Actions class
        Actions actions = new Actions(driver);
        actions.moveToElement(aboutMenuLink);
        WebElement testimonialLink = homePage.getTestimonialSubMenuLink();
        actions.moveToElement(testimonialLink);
        actions.click().build().perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Assert.assertTrue(homePage.getFirstTestimonialText().isDisplayed());
        String sTestimonial = homePage.getFirstTestimonialText().getText();
        String TestFile = "/Users/princesoni/workspace/hclhackathonproject/reports/textFile1.txt";
        File FC = new File(TestFile);//Created object of java File class.
        FC.createNewFile();//Create file.
        //Writing In to file.
        //Create Object of java FileWriter and BufferedWriter class.
        FileWriter FW = new FileWriter(TestFile);
        BufferedWriter BW = new BufferedWriter(FW);
        BW.write(sTestimonial); //Writing In To File.
        BW.newLine();//To write next string on new line.
        BW.write("This Is Second Line."); //Writing In To File.
        BW.close();
    }


    @AfterTest
    public void tearDown() throws IOException {
        driver.quit();
    }

}
