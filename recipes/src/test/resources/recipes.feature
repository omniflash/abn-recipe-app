Feature: getting list or recipes

  Scenario: client makes call to GET /index
    Given the client is authenticated
    When the client calls /index
    Then the client receives status code of 200
    And the client is redirected to the index page

  Scenario: client makes call to GET /index
    Given the client is NOT authenticated
    When the client calls /index
    Then the client receives status code of 401
    And the client is redirected to login page

Feature: opening the new recipe page

  Scenario: client makes call to GET /addnewrecipe
    Given the client is authenticated
    When the client calls /addnewrecipe
    Then the client receives status code of 200
    And the recipe creation page is opened

  Scenario: client makes call to GET /addnewrecipe
    Given the client is NOT authenticated
    When the client calls /addnewrecipe
    Then the client receives status code of 401
    And the client is redirected to login page

Feature: saving a new recipe

  Scenario: client makes call to POST /saverecipe
    Given the client is authenticated
    When the client calls /saverecipe
    Then the client receives status code of 200
    And the recipe is added
    And the index page is opened

  Scenario: client makes call to POST /saverecipe
    Given the client is NOT authenticated
    When the client calls /external/getallrecipes
    Then the client receives status code of 401
    And the client is redirected to login page

Feature: opening the update page

  Scenario: client makes call to GET /editrecipe/{id}
    Given the client is authenticated
    When the client calls /editrecipe/{id}
    Then the client receives status code of 200
    And the edit recipe page is opened

  Scenario: client makes call to GET /editrecipe/{id}
    Given the client is NOT authenticated
    When the client calls /editrecipe/{id}
    Then the client receives status code of 401
    And the client is redirected to login page

Feature: updating a recipe

  Scenario: client makes call to POST /editrecipe/{id}
    Given the client is authenticated
    When the client calls /editrecipe/{id}
    Then the client receives status code of 200
    And the recipe is updated
    And the client is redirected to index page

  Scenario: client makes call to POST /editrecipe/{id}
    Given the client is NOT authenticated
    When the client calls /editrecipe/{id}
    Then the client receives status code of 401
    And the client is redirected to login page

Feature: calling external

  Scenario: client makes call to GET /external/getallrecipes
    Given the client is authenticated and has ADMIN role
    When the client calls /external/getallrecipes
    Then the client receives status code of 200
    And the client receives a JSON of all available recipes

  Scenario: client makes call to GET /external/getallrecipes
    Given the client is NOT authenticated
    When the client calls /external/getallrecipes
    Then the client receives status code of 401

  Scenario: client makes call to GET /external/getallrecipes
    Given the client is authenticated and has role USER
    When the client calls /external/getallrecipes
    Then the client receives status code of 403

Feature: deleting a recipe externally

  Scenario: client makes call to DELETE /delete/{id}
    Given the client is authenticated and has ADMIN role
    When the client calls /delete/{id}
    Then the client receives status code of 200
    And the client is deleted with given id (if exists)

Feature: deleting a recipe internally

  Scenario: client makes call to GET /delete/{id}
    Given the client is authenticated
    When the client calls /delete/{id}
    Then the client receives status code of 200
    And the client is deleted with given id (if exists)
    And the user is returned to index page