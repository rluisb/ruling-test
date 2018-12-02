Feature: As a user, i wanna Create, Read, Update and Delete rulings, for manage this infos

  @Ruling
  Scenario: Create a new ruling
    Given a description this is a new ruling
    And a subject public voting
    And a title public voting
    When save this
    Then it must return the same information given before
    And a new generated id
    And status 200

  @Ruling
  Scenario: Update the state of an existing ruling
    Given an existing ruling
    And a description this has changed
    And a subject this subject has changed too
    When update ruling data
    Then it must return the same information given before
    And status 200

  Scenario: List all rulings from storage
    When user request all rulings
    Then it must return a list of rulings
    And status 200

  Scenario: List all rulings from storage
    Given an existing id
    When user request ruling by id
    Then it must return a valid ruling
    And status 200
