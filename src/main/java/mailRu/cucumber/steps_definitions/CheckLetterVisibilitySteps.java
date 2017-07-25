package mailRu.cucumber.steps_definitions;


import cucumber.api.java.en.And;
import mailRu.business_objects.letter.LetterBuilder;
import mailRu.services.MailService;
import org.testng.Assert;

public class CheckLetterVisibilitySteps {

    private MailService mailService = new MailService();

    @And("^Letter with 'Subject' \"([^\"]*)\" (?:is present|is visible) in draft folder$")
    public void check_letter_present_in_draft_folder(String subject) {
        boolean isLetterVisible = mailService.isLetterVisibleInDraftFolder(new LetterBuilder("volhakarzhova@mail.ru").setSubject(subject).build());
        Assert.assertTrue(isLetterVisible, "Letter is not visible in draft folder");
    }

    @And("^Letter with 'Subject' \"([^\"]*)\" (?:isn't present|isn't visible) in draft folder$")
    public void check_letter_is_not_visible_in_draft_folder(String subject) {
        Assert.assertFalse(mailService.isLetterVisibleInDraftFolder(new LetterBuilder("volhakarzhova@mail.ru").setSubject(subject).build()));
    }

    @And("^Letter with 'Subject' \"([^\"]*)\" (?:isn't present|isn't visible) in trash folder$")
    public void check_letter_is_not_visible_in_trash_folder(String subject) {
        Assert.assertFalse(mailService.isLetterVisibleInTrashFolder(new LetterBuilder("volhakarzhova@mail.ru").setSubject(subject).build()));
    }

    @And("^Letter with 'Subject' \"([^\"]*)\" (?:is present|is visible) in trash folder$")
    public void check_letter_present_in_trash_folder(String subject) {
        Assert.assertTrue(mailService.isLetterVisibleInTrashFolder(new LetterBuilder("volhakarxhova@mail.ru").setSubject(subject).build()));
    }

    @And("^Letter with 'Subject' \"([^\"]*)\" (?:is present|is visible) in the sent folder$")
    public void check_letter_present_in_sent_folder(String subject) {
        Assert.assertTrue(mailService.isLetterVisibleInTrashFolder(new LetterBuilder("volhakarzhova@mail.ru").setSubject(subject).build()));
    }
}