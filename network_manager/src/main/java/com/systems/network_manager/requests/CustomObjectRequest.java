package com.systems.network_manager.requests;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.systems.network_manager.listeners.DataResponse;
import com.systems.network_manager.listeners.ErrorResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Attiq ur Rehman
 *         created on 4/10/2016.
 *         attiq.ur.rehman1991@gmail.com
 *
 *         Added functions are
 *         getParams      -- to add parameters in request
 *         getHeaders     -- to set header of request
 *         getRetryPolicy -- to manage retry policy
 */

public class CustomObjectRequest extends JsonObjectRequest {

    /** The default socket timeout in milliseconds */
    public static final int DEFAULT_TIMEOUT_MS = 20*1000;

    /** The default number of retries */
    public static final int DEFAULT_MAX_RETRIES = 1;

    /** The default backoff multiplier */
    public static final float DEFAULT_BACKOFF_MULT = 1f;

    private Map<String, String> params;
    public CustomObjectRequest(final int index, int method, String url, JSONObject jsonRequest, final DataResponse serverReponseListner, final ErrorResponse errorResponse, Map<String, String> params) {

        super(method, url, jsonRequest, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                serverReponseListner.onResponse(index, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorResponse.onErrorResponse(index, error);
            }
        });
        this.params = params;
        setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIMEOUT_MS,
                DEFAULT_MAX_RETRIES,
                DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected Map<String, String> getParams() {
        return params;
    };

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        return headers;
    }


    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        // here you can write a custom retry policy
        return super.getRetryPolicy();
    }

}
