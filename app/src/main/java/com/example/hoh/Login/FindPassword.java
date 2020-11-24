package com.example.hoh.Login;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FindPassword extends StringRequest {
    final static private String URL = "https://cksrudzz.cafe24.com/UserFindPassword.php";
    private Map<String, String> parameters;

    public FindPassword(String userID, String userName, String userPwdHint, String userPwdHintAnswer, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userName", userName);
        parameters.put("userPwdHint", userPwdHint);
        parameters.put("userPwdHintAnswer", userPwdHintAnswer);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}