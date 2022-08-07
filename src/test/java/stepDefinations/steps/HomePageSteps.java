package stepDefinations.steps;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;



public class HomePageSteps {
    @Given("User is on the Homepage")
    public void user_is_on_the_homepage() {
        System.out.println("First Test");
    }

    @When("User clicks on the login button")
    public void user_clicks_on_the_login_button() {
        System.out.println("Second Test");
    }

    @Then("Login Page is displayed")
    public void login_page_is_displayed() {
        System.out.println("Third Test");
    }

}
