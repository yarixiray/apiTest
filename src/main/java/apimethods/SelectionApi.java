package apimethods;

import configuration.ApiUtils;
import configuration.HttpMethods;
import io.restassured.response.Response;

public class SelectionApi {
    private HttpMethods httpMethods = new HttpMethods();
    private ApiUtils apiUtils = new ApiUtils();
    private String url = apiUtils.getApiUrl() + "/event";
    private Response response;

    public void eventEventIdMarketMarketIdAddSelectionPost(Selection body, Long eventId, Long marketId) {
        String path = url + "/" + eventId + "/market/" + marketId + "/addSelection";
        String bodyString = body + "";
        response = httpMethods.postRequest(path, bodyString);
    }

    public void eventEventIdMarketMarketIdSelectionIdDelete(Long eventId, Long marketId, Long selectionId) {
        httpMethods.deleteRequest(url + "/" + eventId + "/market/" + marketId + "/" + selectionId);
        response = httpMethods.deleteRequest(url + "/" + eventId + "/market/" + marketId + "/" + selectionId);

    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return response.asString();
    }
}
