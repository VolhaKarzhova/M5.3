package mailRu.cucumber.steps_definitions;

import cucumber.api.java.en.When;
import mailRu.pages.LeftMenuPage;

public class MoveToFolderSteps {

    @When("^User (?:moves to|goes to) the trash folder$")
    public void user_goes_to_trash_folder() {
        new LeftMenuPage().openDeletedFolder();
    }

    @When("^User (?:moves to|goes to) the draft folder$")
    public void user_goes_to_draft_folder() {
        new LeftMenuPage().openDraftFolder();
    }
}