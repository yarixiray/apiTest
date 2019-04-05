package apimethods;

import org.json.simple.JSONObject;

public class Selection {

    private JSONObject requestParams = new JSONObject();

    public Selection(int id, String selection, int price, String status, String result) {
        requestParams.put("id", id);
        requestParams.put("selection", selection);
        requestParams.put("price", price);
        if (status.equals("Active") || status.equals("Suspended")) {
            requestParams.put("status", status);
        } else {
            throw new IllegalArgumentException("Should be Enum: Active or Suspended. Incorrect status: " + status);
        }
        if (result.equals("Lost") || result.equals("Won")) {
            requestParams.put("result", result);
        } else {
            throw new IllegalArgumentException("Should be Enum: Lost or Won. Incorrect result: " + result);
        }
    }

    @Override
    public String toString() {
        return requestParams.toJSONString();
    }
}