@sendTest
Feature: Send Letter Test
  # The user should be able to send letter with valid addressee.
  # The user should be able to send letter with empty subject

  Background:
    Given   User opens MailRu Home Page
    And     User enters "volhakarzhova@mail.ru" and "1584624Qwe" and click submit button

  Scenario: User is able to send letter
    When    User sends letter with parameters from JSON
    """
   [{"addressee":"volhakarzhova@mail.ru", "subject":"Subject464647", "body":"wetyjiolkmn876re3"},{"addressee":"olga1584624@gmail.com", "subject":"5476576787", "body":"467589p0dfhkilpo"}]
    """
    Then   Letter with 'Subject' taken from JSON is visible in the sent folder
     """
   [{"addressee":"volhakarzhova@mail.ru", "subject":"Subject464647", "body":"wetyjiolkmn876re3"},{"addressee":"olga1584624@gmail.com", "subject":"5476576787", "body":"467589p0dfhkilpo"}]
    """
    And     User logOut from the mailbox

    Scenario: User is able to send letter with only addressee filled
      When    User sends letter with only 'Addressee' filled as "volhakarzhova@mail.ru"
      And     Alert message about blank subject is displayed
      And     User confirms sending letter with empty subject
      Then    Letter with 'Subject' "<Без темы>" is visible in the sent folder