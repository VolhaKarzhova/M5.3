package mailRu.cucumber.steps_definitions;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import mailRu.business_objects.letter.Letter;
import mailRu.services.MailService;
import mailRu.utils.JsonUtils;
import org.testng.Assert;

public class VisibilityOfElementsSteps {

    private MailService mailService = new MailService();
    private Letter[] letterList;
    private JsonUtils jsonUtils = new JsonUtils();

    @And("^Letter with 'Subject' \"([^\"]*)\" (?:is present|is visible) in draft folder$")
    public void check_letter_present_in_draft_folder(String subject) {
        boolean isLetterVisible = mailService.isLetterVisibleInDraftFolder(new Letter(subject));
        Assert.assertTrue(isLetterVisible, "Letter is not visible in draft folder");
    }

    @And("^Letter with 'Subject' \"([^\"]*)\" (?:isn't present|isn't visible) in draft folder$")
    public void check_letter_is_not_visible_in_draft_folder(String subject) {
        Assert.assertFalse(mailService.isLetterVisibleInDraftFolder(new Letter(subject)));
    }

    @And("^Letter with 'Subject' \"([^\"]*)\" (?:isn't present|isn't visible) in trash folder$")
    public void check_letter_is_not_visible_in_trash_folder(String subject) {
        Assert.assertFalse(mailService.isLetterVisibleInTrashFolder(new Letter(subject)));
    }

    @And("^Letter with 'Subject' \"([^\"]*)\" (?:is present|is visible) in trash folder$")
    public void check_letter_present_in_trash_folder(String subject) {
        Assert.assertTrue(mailService.isLetterVisibleInTrashFolder(new Letter(subject)));
    }

    @And("^Letter with 'Subject' \"([^\"]*)\" (?:is present|is visible) in the sent folder$")
    public void check_letter_present_in_sent_folder(String subject) {
        Assert.assertTrue(mailService.isLetterVisibleInSentFolder(new Letter(subject)));
    }

    @Then("^Alert message is displayed$")
    public void alert_message_is_displayed() {
        Assert.assertTrue(mailService.isAlertMessageExpectedWhileSendingLetterWithInvalidAddressee());
    }

    @Then("^Letter with 'Subject' taken from JSON is visible in the sent folder$")
    public void check_letter_is_visible_in_sent_folder(String jsonFile) {
        letterList = jsonUtils.createTestData(jsonFile);
        for (Letter letter : letterList) {
            mailService.isLetterVisibleInSentFolder(letter);
            System.out.println(letter.toString()+" visibility checked in folder");
        }
    }
}