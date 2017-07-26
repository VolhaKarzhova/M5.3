package mailRu.cucumber.steps_definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import mailRu.pages.HeaderMenuPage;
import mailRu.pages.LeftMenuPage;
import mailRu.pages.NewLetterPage;

public class ButtonClickSteps {

    private NewLetterPage newLetterPage = new NewLetterPage();

    @And("^Clicks? 'Save As Draft' button$")
    public void save_letter_as_draft() {
        newLetterPage.saveDraftMail();
    }

    @And("^User clicks 'Send' button$")
    public void user_clicks_send_button() {
        newLetterPage.sendMail();
    }

    @And("^User confirms alert message$")
    public void user_confirms_alert_message() {
        newLetterPage.handleAlertMessage();
    }
}