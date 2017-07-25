package mailRu.cucumber.steps_definitions;


import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import mailRu.business_objects.letter.Letter;
import mailRu.business_objects.letter.LetterBuilder;
import mailRu.services.MailService;
import mailRu.utils.JsonUtils;

public class ActionsWithLetterSteps {

    private MailService mailService = new MailService();
    private Letter[] letterList;
    private JsonUtils jsonUtils = new JsonUtils();

    @When("^User deletes letter with 'Subject' \"([^\"]*)\"$")
    public void delete_letter(String subject) {
        mailService.deleteLetter(new LetterBuilder("volhakarxhova@mail.ru").setSubject(subject).build());
    }

    @And("^User sends letter with parameters from JSON$")
    public void send_letter(String jsonFile) {
        letterList = jsonUtils.crateTestData(jsonFile);
        for (Letter letter : letterList) {
            mailService.sendLetterWithAllFilledInputs(letter);
            System.out.println(letter.toString());
        }
    }
}