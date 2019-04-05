Feature: The User can get detailed information about events
  As User
  I want to get all events from the site
  So that I can check the detailed information of events

  @AcceptanceCriteria
  Scenario: The User wants to retrieve all events
    When The User send request
    Then The User retrieves all events

  @AcceptanceCriteria
  Scenario: The User wants to verify a number of events
    When The User send request
    Then The number of events in response is "4"

  @AcceptanceCriteria
  Scenario Outline: The User wants to verify the response has all necessary Events
    When The User send request
    Then The response contains both events <Event1> and <Event2>
    Examples:
      | Event1 | Event2   |
      | Tennis | Football |

  @AcceptanceCriteria
  Scenario Outline: The User wants to create a new Selection
    When The User creates a new selection with <id>,<selection>,<price>,<status>,<result>,<eventId> in <marketId>
    Then The User checks the selection is created
    Examples:
      | id  | selection | price | status    | result | eventId | marketId |
      | 101 | ManU      | 1000  | Active    | Lost   | 1       | 121      |
      | 102 | Uve       | 1000  | Suspended | Won    | 2       | 123      |
      | 103 | Galaxy    | 1000  | Suspended | Lost   | 3       | 125      |
      | 104 | SuperFarm | 1000  | Active    | Lost   | 4       | 127      |

          # Method eventEventIdMarketIdDelete doesn't work
      # event/{eventId}/{marketId}
    #Error: Cannot delete /event/2/123
  @AcceptanceCriteria
  Scenario Outline: The User wants to delete all Tennis Events, markets, selections
    When The User is checking the <Category> events exist
    Then The User delete all <Category> events and check it
    Examples:
      | Category |
      | Tennis   |

  Scenario: The User wants to delete nonexistent event
    When The User send request to event <5>
    Then The User receives a response <400>