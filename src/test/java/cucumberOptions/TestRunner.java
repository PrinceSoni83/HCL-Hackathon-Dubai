package cucumberOptions;

import cucumber.api.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepDefinations.steps"
)
public class TestRunner extends AbstractTestNGCucumberTests {


}
