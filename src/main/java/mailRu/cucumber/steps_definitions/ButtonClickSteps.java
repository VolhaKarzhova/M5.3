package mailRu.cucumber.steps_definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import mailRu.pages.HeaderMenuPage;
import mailRu.pages.NewLetterPage;

public class ButtonClickSteps {

    NewLetterPage newLetterPage = new NewLetterPage();

    @When("^User clicks 'New Letter' button$")
    public void user_clicks_new_letter_button() {
        new HeaderMenuPage().clickNewLetterButton();
    }

    @And("^Clicks? 'Save As Draft' button$")
    public void save_letter_as_draft() {
        newLetterPage.saveDraftMail();
    }
}