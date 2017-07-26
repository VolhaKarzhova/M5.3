package mailRu.cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import mailRu.webdriver.WebDriverSingleton;
import org.testng.annotations.AfterClass;

@CucumberOptions(strict = true, plugin = {"json:target/cucumber-report.json",
        "html:target/cucumber-report"}, tags = "~@NegativeTest", features = "src/main/resources/cucumber_features", glue = {
        "mailRu.cucumber.steps_definitions"})
public class PositiveCasesTestRunner extends AbstractTestNGCucumberTests {

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        WebDriverSingleton.kill();
    }
}