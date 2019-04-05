package restapi;

import apimethods.*;
import com.google.gson.*;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;

import java.util.List;

import static org.junit.Assert.*;
import static org.apache.http.HttpStatus.*;

@Slf4j
public class ApiTestSteps {
    private EventApi oneEventApi = new EventApi();
    private EventsApi eventsApi = new EventsApi();
    private SelectionApi selectionApi = new SelectionApi();
    private JsonPath responseJsonPath = new JsonPath(eventsApi.toString()).setRoot("events");
    private JSONObject jsonObject = new JSONObject();
    private MarketApi marketApi = new MarketApi();

    @When("^The User send request$")
    public void theUserSendRequest() {
        eventsApi.getResponse().then().statusCode(SC_OK);
    }

    @Then("^The User retrieves all events$")
    public void theUserRetrievesAllEvents() {
        eventsApi.getResponse().then().statusCode(SC_OK);
    }

    @Then("^The number of events in response is \"([^\"]*)\"$")
    public void the_number_of_events_in_response_is(int numberOfEvents) {
        List<Integer> eventIds = responseJsonPath.get("id");
        assertEquals(numberOfEvents, eventIds.size());
    }

    @Then("^The response contains both events (.*) and (.*)$")
    public void the_response_contains_both_events_Tennis_and_Football(String e1, String e2) {
        List<String> categories = responseJsonPath.get("category");
        assertTrue("Categoty " + e1 + " not found", categories.contains(e1));
        assertTrue("Categoty " + e2 + " not found", categories.contains(e2));
    }

    @When("^The User creates a new selection with (.*),(.*),(.*),(.*),(.*),(.*) in (.*)$")
    public void the_User_creates_a_new_selection_with_ManU_Active_super(int id, String selection, int price, String status, String result, Long eventID, Long marketId) {
        jsonObject.remove("eventID");
        jsonObject.put("eventID", eventID);
        jsonObject.remove("marketId");
        jsonObject.put("marketId", marketId);
        jsonObject.remove("id");
        jsonObject.put("id", id);
        jsonObject.remove("selection");
        jsonObject.put("selection", selection);
        jsonObject.remove("price");
        jsonObject.put("price", price);
        jsonObject.remove("status");
        jsonObject.put("status", status);
        jsonObject.remove("result");
        jsonObject.put("result", result);
        Selection selectionM = new Selection(id, selection, price, status, result);
        selectionApi.eventEventIdMarketMarketIdAddSelectionPost(selectionM, eventID, marketId);

        selectionApi.getResponse().then().statusCode(SC_CREATED);
    }

    @Then("^The User checks the selection is created$")
    public void the_User_checks_the_selection_is_created() {

        JsonPath selectionString = new JsonPath(eventsApi.toString()).setRoot("events");
        List<Object> spec = selectionString.get("markets.selections");

        assertTrue(spec.toString().contains(jsonObject.get("id").toString()));
        assertTrue(spec.toString().contains(jsonObject.get("selection").toString()));
        assertTrue(spec.toString().contains(jsonObject.get("price").toString()));
        assertTrue(spec.toString().contains(jsonObject.get("status").toString()));
        assertTrue(spec.toString().contains(jsonObject.get("result").toString()));

        Long eventId = Long.valueOf(jsonObject.get("eventID").toString());
        Long marketId = Long.valueOf(jsonObject.get("marketId").toString());
        Long id = Long.valueOf(jsonObject.get("id").toString());

        selectionApi.eventEventIdMarketMarketIdSelectionIdDelete(eventId, marketId, id);
    }

    @When("^The User is checking the ([^\"]*) events exist$")
    public void the_User_is_checking_the_events_exist(String category) {
        JsonPath resp = new JsonPath(eventsApi.toString()).setRoot("events");
        List<Integer> listOfIds = resp.get("id");
        for (Integer l : listOfIds) {
            oneEventApi.eventGet(String.valueOf(l));
            if (oneEventApi.toString().contains(category)) {
                Gson gson = new GsonBuilder().create();
                String stringJson = oneEventApi.toString();
                JsonObject jsonObject = gson.fromJson(stringJson, JsonObject.class);
                JsonElement eventID = jsonObject.get("id");
                Long longEventId = Long.valueOf(eventID.toString());
                JsonArray listOfmarkets = jsonObject.getAsJsonArray("markets");

                listOfmarkets.forEach(s -> {
                    JsonObject jsonObject1 = gson.fromJson(s, JsonObject.class);
                    JsonElement marketID = jsonObject1.get("id");
                    Long longMarketId = Long.valueOf(marketID.toString());
                    JsonArray listOfSelections = jsonObject1.getAsJsonArray("selections");

                    listOfSelections.forEach(k -> {
                        JsonObject jsonObject2 = gson.fromJson(k, JsonObject.class);
                        JsonElement selectionID = jsonObject2.get("id");
                        Long longSelectionId = Long.valueOf(selectionID.toString());
                        selectionApi.eventEventIdMarketMarketIdSelectionIdDelete(longEventId, longMarketId, longSelectionId);
                    });
                    marketApi.eventEventIdMarketIdDelete(longEventId, longMarketId);
                });
            }
        }
    }

    @Then("^The User delete all ([^\"]*) events and check it$")
    public void the_User_delete_all_tennis_events(String category) throws Exception {
        List<Integer> listOfIds = responseJsonPath.get("id");
        for (Integer l : listOfIds) {
            oneEventApi.eventGet(String.valueOf(l));
            if (oneEventApi.toString().contains(category)) {
                throw new Exception("The " + category + " category still persists at eventId: " + l);
            }
        }
    }

    @When("^The User send request to event <(\\d+)>$")
    public void the_User_send_request_to_event(int eventId) {
        Long event = Long.valueOf(eventId);
        oneEventApi.eventEventIdDelete(event);
    }

    @Then("^The User receives a response <(\\d+)>$")
    public void the_User_receives_a_response(int errorCode) {
        assertEquals(errorCode, oneEventApi.getResponse().getStatusCode());
    }
}