package mailRu.cucumber.steps_definitions;


import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import mailRu.business_objects.letter.Letter;
import mailRu.services.MailService;
import mailRu.utils.JsonUtils;
import org.testng.Assert;

public class ActionsWithLetterSteps {

    private MailService mailService = new MailService();
    private Letter[] letterList;
    private JsonUtils jsonUtils = new JsonUtils();

    @When("^User deletes letter with 'Subject' \"([^\"]*)\"$")
    public void delete_letter(String subject) {
        mailService.deleteLetter(new Letter(subject));
    }

    @And("^User sends letter with parameters from JSON$")
    public void send_letter(String jsonFile) {
        letterList = jsonUtils.createTestData(jsonFile);
        for (Letter letter : letterList) {
            mailService.sendLetterWithAllFilledInputs(letter);
        }
    }

    @And("^User sends letter with only 'Addressee' filled as \"([^\"]*)\"$")
    public void user_sends_letter_with_only_addressee_Filled(String subject) {
        mailService.sendLetterWithOnlyAddressee(new Letter(subject));
        boolean isAlertContentExpected = mailService.isAlertMessageExpectedWhileSendingLetterWithBlankSubject();
        Assert.assertTrue(isAlertContentExpected, "Alert message doesn't match");
    }

}