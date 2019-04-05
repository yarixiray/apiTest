package apimethods;

import configuration.ApiUtils;
import configuration.HttpMethods;
import io.restassured.response.Response;

public class MarketApi {
    private HttpMethods httpMethods = new HttpMethods();
    private ApiUtils apiUtils = new ApiUtils();
    private String url = apiUtils.getApiUrl() + "/event";
    private Response response;

    public void eventEventIdAddMarketPost(Market body, Long eventId) {
        String path = url + "/" + eventId + "/addMarket";
        String bodyString = body + "";
        httpMethods.postRequest(path, bodyString);
        response = httpMethods.postRequest(path, bodyString);
    }

    public void eventEventIdMarketIdDelete(Long eventId, Long marketId) {
        httpMethods.deleteRequest(url + "/" + eventId + "/" + marketId);
        response = httpMethods.deleteRequest(url + "/" + eventId + "/" + marketId);
    }

    public Response getResponse() {
        return response;
    }
}
