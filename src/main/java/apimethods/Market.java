package apimethods;

import org.json.simple.JSONObject;

public class Market {
    private JSONObject requestParams = new JSONObject();

    public Market(int id, String name, String status) {
        requestParams.put("id", id);
        requestParams.put("name", name);
        if (status.equals("Active") || status.equals("Suspended")) {
            requestParams.put("status", status);
        } else {
            throw new IllegalArgumentException("Should be Enum: Active or Suspended. Incorrect status: " + status);
        }
    }
}
