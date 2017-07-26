package mailRu.cucumber.steps_definitions;


import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import mailRu.business_objects.letter.Letter;
import mailRu.business_objects.letter.LetterBuilder;
import mailRu.pages.HeaderMenuPage;
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
    public void send_letter_from_json(String jsonFile) {
        new HeaderMenuPage().clickNewLetterButton();
        letterList = jsonUtils.createTestData(jsonFile);
        for (Letter letter : letterList) {
            mailService.sendLetterWithAllFilledInputs(letter);
        }
    }

    @And("^User sends letter with only 'Addressee' filled as \"([^\"]*)\"$")
    public void send_letter_with_only_addressee_filled(String subject) {
        mailService.sendLetterWithOnlyAddressee(new Letter(subject));
        boolean isAlertContentExpected = mailService.isAlertMessageExpectedWhileSendingLetterWithBlankSubject();
        Assert.assertTrue(isAlertContentExpected, "Alert message doesn't match");
    }

    @And("^User moves letter with \"([^\"]*)\" 'Subject' to spam$")
    public void move_letter_to_spam(String subject) {
        mailService.moveLetterToSpam(new Letter(subject));
    }

    @When("^User removes letter with \"([^\"]*)\" 'Subject' from spam folder$")
    public void move_letter_from_spam(String subject) {
        mailService.moveLetterFromSpam(new Letter(subject));
    }

    @When("^User fills new Letter fields: 'Addressee' as \"([^\"]*)\", 'Subject' as \"([^\"]*)\", 'Body' as \"([^\"]*)\"$")
    public void send_letter_with_all_filled_inputs(String addressee, String subject, String body) {
        new HeaderMenuPage().clickNewLetterButton();
        mailService.sendLetterWithAllFilledInputs(new LetterBuilder(addressee).setSubject(subject).setBody(body).build());
    }
}