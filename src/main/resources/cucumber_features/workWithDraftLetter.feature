@actionsWithDrafts
Feature: Draft Letter Test
  # The user should be able to save draft letter with various combinations of parameters.
  # Draft Letter is saved in the draft folder by default.
  # The user should be able to delete draft letter from draft folder.
  # The user should be able to delete draft letter permanently from the trash folder.

  Background:
    Given   User opens MailRu Home Page
    And     User enters "volhakarzhova@mail.ru" and "1584624Qwe" and click submit button
    And     Check user is authorized successfully

  Scenario Outline: User is able to save draft letter
    When    User fills 'Addressee' with "<addressee>", fill 'Subject' with "<subject>", fill 'Body' with "<body>"
    And     Clicks 'Save As Draft' button
    Then    Letter with 'Subject' "<subject>" is present in draft folder
    And     User logOut from the mailbox
    Examples:
      | addressee               | subject    | body      |
      | volhakarzhova@gmail.com | test08     | test98576 |
      | olga158462              | mail87476  | mail      |
      | olga158462@gmail.com    | empty Body |           |

  Scenario:  User is able to delete draft letter
    And      User goes to the draft folder
    When     User deletes letter with 'Subject' "test08"
    Then     Letter with 'Subject' "test08" isn't visible in draft folder
    And      Letter with 'Subject' "test08" is visible in trash folder
    And      User logOut from the mailbox

  Scenario:  User is able to delete draft letter permanently
    And      User moves to the trash folder
    When     User deletes letter with 'Subject' "test08"
    Then     Letter with 'Subject' "test08" isn't visible in trash folder
    And      User logOut from the mailbox