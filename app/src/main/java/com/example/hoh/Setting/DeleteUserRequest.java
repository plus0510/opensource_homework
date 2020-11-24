package com.example.hoh.Setting;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteUserRequest extends StringRequest {
    final static private String URL = "https://cksrudzz.cafe24.com/UserDelete.php";
    private Map<String, String> parameters;

    public DeleteUserRequest(String userID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}