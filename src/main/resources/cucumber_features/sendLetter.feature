@sendTest
Feature: Send Letter Test
  # The user should be able to send letter with valid addressee.
  # The user should not be able to send letter with invalid addressee or empty addressee
  # The user should be able to send letter with empty subject

  Background:
    Given   User opens MailRu Home Page
    And     User enters "volhakarzhova@mail.ru" and "1584624Qwe" and click submit button
    And     Check user is authorized successfully

  Scenario: User is able to send letter with all fields filled with valid data
    When    User sends letter with parameters from JSON
    """
   [{"addressee":"volhakarzhova@mail.ru", "subject":"Subject464647", "body":"wetyjiolkmn876re3"},{"addressee":"olga1584624@gmail.com", "subject":"5476576787", "body":"467589p0dfhkilpo"}]
    """
    Then   Letter with 'Subject' taken from JSON is visible in the sent folder
     """
   [{"addressee":"volhakarzhova@mail.ru", "subject":"Subject464647", "body":"wetyjiolkmn876re3"},{"addressee":"olga1584624@gmail.com", "subject":"5476576787", "body":"467589p0dfhkilpo"}]
    """
    And     User logOut from the mailbox