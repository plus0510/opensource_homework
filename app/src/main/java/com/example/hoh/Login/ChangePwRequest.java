package com.example.hoh.Login;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ChangePwRequest extends StringRequest {
    final static private String URL = "https://cksrudzz.cafe24.com/updatePw.php";
    private Map<String, String> parameters;

    public ChangePwRequest(String userID, String userPwd, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPwd", userPwd);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}