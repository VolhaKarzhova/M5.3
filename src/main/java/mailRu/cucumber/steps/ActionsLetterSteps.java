package mailRu.cucumber.steps;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import mailRu.business_objects.letter.Letter;
import mailRu.business_objects.letter.LetterBuilder;
import mailRu.pages.HeaderMenuPage;
import mailRu.services.MailService;
import mailRu.utils.JsonUtils;
import org.testng.Assert;

public class ActionsLetterSteps {

    private MailService mailService = new MailService();
    private JsonUtils jsonUtils = new JsonUtils();

    @When("^User deletes letter with 'Subject' \"([^\"]*)\"$")
    public void delete_draft_letter(String subject) {
        mailService.deleteDraftLetter(new Letter(subject));
    }

    @When("^User deletes letter with 'Subject' \"([^\"]*)\" permanently$")
    public void delete_letter_from_trash(String subject) {
        mailService.permanentLetterDelete(new Letter(subject));
    }

    @And("^User sends letter with parameters from JSON$")
    public void send_letter_from_json(String jsonFile) {
        new HeaderMenuPage().clickNewLetterButton();
        Letter[] letterList = jsonUtils.getLetterParameters(jsonFile);
        for (Letter letter : letterList) {
            mailService.sendLetterWithAllFilledInputs(letter);
        }
    }

    @When("^User sends letter with only 'Addressee' filled as \"([^\"]*)\"$")
    public void send_letter_with_only_addressee_filled(String addressee) {
        mailService.sendLetterWithOnlyAddressee(new LetterBuilder(addressee).build());
    }

    @Then("^Alert message about blank subject is displayed$")
    public void alert_message_appears() {
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
        mailService.sendLetterWithAllFilledInputs(new LetterBuilder(addressee).setSubject(subject).setBody(body).build());
    }

    @And("^User confirms sending letter with empty subject$")
    public void confirm_on_alert_message(){
        mailService.confirmSendingLetterOnAlert();
    }

    @When("^User saves? letter as draft with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void save_draft_letter(String addressee, String subject, String body) {
        mailService.saveDraftLetter(new LetterBuilder(addressee).setSubject(subject).setBody(body).build());
    }

    @And("^Letter with 'Subject' \"([^\"]*)\" (?:is present|is visible) in draft folder$")
    public void check_letter_present_in_draft_folder(String subject) {
        Assert.assertTrue(mailService.isLetterVisibleInDraftFolder(new Letter(subject)), "Letter is not visible in draft folder");
    }

    @Then("^Letter with 'Subject' \"([^\"]*)\" (?:isn't present|isn't visible) in draft folder$")
    public void check_letter_is_not_visible_in_draft_folder(String subject) {
        Assert.assertFalse(mailService.isLetterVisibleInDraftFolder(new Letter(subject)), "letter with subject " + subject + " is still in the folder");
    }

    @Then("^Letter with 'Subject' \"([^\"]*)\" (?:isn't present|isn't visible) in trash folder$")
    public void check_letter_is_not_visible_in_trash_folder(String subject) {
        Assert.assertFalse(mailService.isLetterVisibleInTrashFolder(new Letter(subject)), "letter with subject " + subject + " is still in the folder");
    }

    @Then("^Letter with 'Subject' \"([^\"]*)\" (?:is present|is visible) in trash folder$")
    public void check_letter_present_in_trash_folder(String subject) {
        Assert.assertTrue(mailService.isLetterVisibleInTrashFolder(new Letter(subject)), "letter with subject " + subject + " is not in the folder");
    }

    @Then("^Letter with 'Subject' \"([^\"]*)\" (?:is present|is visible) in the sent folder$")
    public void check_letter_present_in_sent_folder(String subject) {
        Assert.assertTrue(mailService.isLetterVisibleInSentFolder(new Letter(subject)), "letter with subject " + subject + " is not in the folder");
    }

    @Then("^Letter with 'Subject' \"([^\"]*)\" (?:isn't present|isn't visible) in the inbox folder$")
    public void check_letter_is_not_visible_in_inbox_folder(String subject) {
        Assert.assertFalse(mailService.isLetterVisibleInInboxFolder(new Letter(subject)), "letter with subject " + subject + " is still in the folder");
    }

    @Then("^Letter with 'Subject' \"([^\"]*)\" (?:is present|is visible) in the inbox folder$")
    public void check_letter_is_visible_in_inbox_folder(String subject) {
        Assert.assertTrue(mailService.isLetterVisibleInInboxFolder(new Letter(subject)), "letter with subject " + subject + " is not in the folder");
    }

    @Then("^Letter with 'Subject' \"([^\"]*)\" (?:is present|is visible) in the spam folder$")
    public void check_letter_is_visible_in_spam_folder(String subject) {
        Assert.assertTrue(mailService.isLetterVisibleInSpamFolder(new Letter(subject)), "letter with subject " + subject + " is not in the folder");
    }

    @And("^Letter with 'Subject' \"([^\"]*)\" (?:isn't present|isn't visible) in the spam folder$")
    public void check_letter_is_not_visible_in_spam_folder(String subject) {
        Assert.assertFalse(mailService.isLetterVisibleInSpamFolder(new Letter(subject)), "letter with subject " + subject + " is still in the folder");
    }

    @Then("^Alert message is displayed$")
    public void alert_message_is_displayed() {
        Assert.assertTrue(mailService.isAlertMessageExpectedWhileSendingLetterWithInvalidAddressee());
    }

    @Then("^Letter with 'Subject' taken from JSON is visible in the sent folder$")
    public void check_letter_is_visible_in_sent_folder(String jsonFile) {
        Letter[] letterList = jsonUtils.getLetterParameters(jsonFile);
        for (Letter letter : letterList) {
            Assert.assertTrue(mailService.isLetterVisibleInSentFolder(letter), "letter with subject " + letter.getSubject() + " is not in the folder");
        }
    }
}