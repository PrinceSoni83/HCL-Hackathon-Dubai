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
import java.io.*;
import static javax.imageio.ImageIO.read;


public class HomePageTest extends Base {
    public WebDriver driver;
    HomePage homePage;


    @BeforeTest
    public void initialize() throws IOException {
        driver = initialiseDriver();
        driver.get(properties.getProperty("baseurl"));
        homePage = new HomePage(driver);
        driver.manage().window().maximize();
    }

    @Test(description = "Verify Home Page Navigation and Home Page content")
    public void homepage_tc_01_02() throws IOException {
        Assert.assertEquals(driver.getTitle(), "Healthcare Marketing and Medical Advertising for Doctors");
        waitForElement(driver, homePage.getHomePageLogo());
        Assert.assertTrue(homePage.getHomePageLogo().isDisplayed());
    }


    @Test(description = "Verify the behavior of About us link")
    public void about_tc_01() throws IOException {
        Assert.assertTrue(homePage.getAboutLinkMenu().isDisplayed());
        Assert.assertTrue(homePage.getAboutLinkMenu().isEnabled());
    }

    @Test(description = "Mouse Hover the About link and Verify the Testimonial link")
    public void about_tc_02() throws IOException {
        Assert.assertTrue(homePage.getAboutLinkMenu().isDisplayed());
        Assert.assertTrue(homePage.getAboutLinkMenu().isEnabled());
        WebElement aboutMenuLink = homePage.getAboutLinkMenu();
        Actions actions = new Actions(driver);
        actions.moveToElement(aboutMenuLink);
        WebElement testimonialLink = homePage.getTestimonialSubMenuLink();
        actions.moveToElement(testimonialLink);
        actions.click().build().perform();
        waitForElement(driver, homePage.getFirstTestimonialText());
        Assert.assertTrue(homePage.getFirstTestimonialText().isDisplayed());
    }
//
//    @Test(description = "Compare the screenshot for Testimonial")
//    public void testimonials_tc_01() throws IOException, InterruptedException {
//        WebElement logoImage = homePage.getHomePageLogo();
//        File file = new File(System.getProperty("user.dir") + "/reports/logoImage.png");
//        FileInputStream fis = new FileInputStream(file);
//        BufferedImage expectedImage = ImageIO.read(fis);
//        Screenshot screenshot = new AShot().takeScreenshot(driver,logoImage);
//        BufferedImage actualImage = screenshot.getImage();
//        ImageDiffer imgDiff = new ImageDiffer();
//        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
//        Assert.assertFalse(diff.hasDiff(),"Images are Same");
//    }

    @Test(description = "Copy the Testimonial link and paste it into a text file")
    public void testimonials_tc_02() throws IOException {
        WebElement aboutMenuLink = homePage.getAboutLinkMenu();
        Actions actions = new Actions(driver);
        actions.moveToElement(aboutMenuLink);
        WebElement testimonialLink = homePage.getTestimonialSubMenuLink();
        actions.moveToElement(testimonialLink);
        actions.click().build().perform();
        waitForElement(driver, homePage.getFirstTestimonialText());
        Assert.assertTrue(homePage.getFirstTestimonialText().isDisplayed());
        String sTestimonial = homePage.getFirstTestimonialText().getText();
        System.out.println("Testimonials : " + sTestimonial);
        File file = new File(System.getProperty("user.dir")+"/reports/textFile.txt");
        FileWriter FW = new FileWriter(file);
        BufferedWriter BW = new BufferedWriter(FW);
        BW.write("**************************************");
        BW.newLine();
        BW.write(sTestimonial);
        BW.newLine();
        BW.write("**************************************");
        BW.close();
    }




    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
