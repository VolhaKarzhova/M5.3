package mailRu.cucumber.steps_definitions;

import cucumber.api.java.en.And;
import mailRu.business_objects.letter.Letter;
import mailRu.pages.NewLetterPage;
import mailRu.utils.JsonUtils;

public class FillLetterInputsSteps {

    private NewLetterPage newLetterPage = new NewLetterPage();
    private Letter[] letterList;
    private JsonUtils jsonUtils = new JsonUtils();

    @And("^Fill 'Addressee' with \"([^\"]*)\", fill 'Subject' with \"([^\"]*)\", fill 'Body' with \"([^\"]*)\"$")
    public void fill_all_letter_inputs(String addressee, String subject, String body) {
        newLetterPage.fillAllLetterInputs(addressee, subject, body);
    }

    @And("^User fills letter inputs with parameters from JSON$")
    public void fill_letter_inputs_from_json(String jsonFile) {
        letterList = jsonUtils.createTestData(jsonFile);
        for (Letter letter : letterList) {
            newLetterPage.fillAllLetterInputs(letter.getAddressee(), letter.getSubject(), letter.getBody());
        }
    }
}