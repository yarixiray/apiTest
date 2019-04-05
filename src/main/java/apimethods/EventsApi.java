package apimethods;

import configuration.ApiUtils;
import configuration.HttpMethods;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class EventsApi {
    private HttpMethods httpMethods = new HttpMethods();
    private ApiUtils apiUtils = new ApiUtils();
    private String url = apiUtils.getApiUrl() + "/events";
    private Response response;

    public void eventsGet() {
        response = httpMethods.getRequest(url);
    }

    public Response getResponse() {
        eventsGet();
        return response;
    }

    public List<String> getTagList(String tag) {
        List<String> tagList = httpMethods.getRequest(url).jsonPath().getList(tag);
        return tagList;
    }

    @Override
    public String toString() {
        return getResponse().asString();
    }
}
