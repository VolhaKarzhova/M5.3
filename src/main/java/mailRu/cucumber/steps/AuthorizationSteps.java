package mailRu.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import mailRu.business_objects.user.User;
import mailRu.services.AuthorizationService;
import org.testng.Assert;

import static mailRu.tests.LoginTest.VALID_USER_ACCOUNT;

public class AuthorizationSteps {

    private AuthorizationService authorizationService = new AuthorizationService();

    @Given("^User (?:navigates to|opens) MailRu Home Page$")
    public void user_navigates_to_malRu_home_page() {
        authorizationService.openHomePage();
    }

    @And("^User enters? \"([^\"]*)\" and \"([^\"]*)\" and click submit button$")
    public void do_login_to_mailbox(String login, String password) {
        authorizationService.doLogin(new User(login, password));
    }

    @Then("^Check user is authorized successfully$")
    public void is_authorization_successful() {
        Assert.assertTrue(authorizationService.isUserLoginAfterAuthorizationExpected(VALID_USER_ACCOUNT),
                "Login wasn't successful");
    }

    @And("^User logOut from the mailbox$")
    public void do_logOut() {
        authorizationService.doLogout();
    }

    @Then("^MailRu home page is displayed$")
    public void is_logout_successful() {
        Assert.assertTrue(authorizationService.isLogOutSuccessfull(), "User didn't logOut from MailBox");
    }

    @Then("^\"([^\"]*)\" message is displayed$")
    public void is_alert_message_expected(String alert) {
        authorizationService.isInvalidCredentialsErrorMessageExpected(alert);
    }
}