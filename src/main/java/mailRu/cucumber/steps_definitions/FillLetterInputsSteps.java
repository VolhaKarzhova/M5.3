package mailRu.cucumber.steps_definitions;

import cucumber.api.java.en.And;
import mailRu.pages.NewLetterPage;

public class FillLetterInputsSteps {

    private NewLetterPage newLetterPage = new NewLetterPage();

    @And("^Fill 'Addressee' with \"([^\"]*)\", fill 'Subject' with \"([^\"]*)\", fill 'Body' with \"([^\"]*)\"$")
    public void fill_all_letter_inputs(String addressee, String subject, String body) {
        newLetterPage.fillAllLetterInputs(addressee, subject, body);
    }
}