package pageObjects;


import base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage  {
    /* Declare the WebDriver instance*/
    public WebDriver driver;

    /* Initialize the constructor to pass the driver instance */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /* Use Page Object with the Page Factory Find by Annotation*/
    @FindBy (css = "img[alt='HealthCare Success Logo']")
    WebElement logo;
    public WebElement getHomePageLogo() {
        return logo;
    }

    @FindBy (css = "a[href*='about']")
    WebElement aboutLink;
    public WebElement getAboutLinkMenu() {
        return aboutLink;
    }

    @FindBy (css = "a[href*='/about/testimonials']")
    WebElement testimonialLink;
    public WebElement getTestimonialSubMenuLink() {
        return testimonialLink;
    }

    @FindBy (id = "text_block-54-45916")
    WebElement firstTestimonial;
    public WebElement getFirstTestimonialText() {
        return firstTestimonial;
    }

    @FindBy (className = "oxy-header-center")
    WebElement homePageHeaders;
    public WebElement getHomePageHeaders() {
        return homePageHeaders;
    }

}
