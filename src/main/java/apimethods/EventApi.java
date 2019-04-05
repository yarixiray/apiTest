package apimethods;

import configuration.ApiUtils;
import configuration.HttpMethods;
import io.restassured.response.Response;

public class EventApi {
    private HttpMethods httpMethods = new HttpMethods();
    private ApiUtils apiUtils = new ApiUtils();
    private String url = apiUtils.getApiUrl() + "/event/";
    private Response response;

    public void eventGet(String eventId) {
        httpMethods.getRequest(url + eventId);
        response = httpMethods.getRequest(url + eventId);
    }

    public void eventEventIdDelete(Long eventId) {
        httpMethods.deleteRequest(url + eventId);
        response = httpMethods.deleteRequest(url + eventId);
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return response.asString();
    }
}
