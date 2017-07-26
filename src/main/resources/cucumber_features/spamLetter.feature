@spamLetter
Feature: Spam Letter Test
  # The user should be able to mark letter as spam.
  # Spam Letter is moved to spam folder.
  # The user should be able to mark letter as no spam.

  Background:
    Given   User opens MailRu Home Page
    And     User enters "volhakarzhova@mail.ru" and "1584624Qwe" and click submit button

  Scenario: User is able to move letter to the spam folder
    When    User sends letter with parameters from JSON
    """
   [{"addressee":"volhakarzhova@mail.ru", "subject":"SpamTest", "body":"spam Test"}]
    """
    And     User moves letter with "SpamTest" 'Subject' to spam
    Then    Letter with 'Subject' "SpamTest" isn't visible in the inbox folder
    And     Letter with 'Subject' "SpamTest" is present in the spam folder
    And     User logOut from the mailbox

  Scenario: User is able to restore letter from spam folder
    When    User sends letter with parameters from JSON
    """
   [{"addressee":"volhakarzhova@mail.ru", "subject":"SpamRestoreTest", "body":"spam restore Test"}]
    """
    And     User moves letter with "SpamRestoreTest" 'Subject' to spam
    And     User removes letter with "SpamRestoreTest" 'Subject' from spam folder
    Then    Letter with 'Subject' "SpamRestoreTest" isn't visible in the spam folder
    And     Letter with 'Subject' "SpamRestoreTest" is visible in the inbox folder
    And     User logOut from the mailbox