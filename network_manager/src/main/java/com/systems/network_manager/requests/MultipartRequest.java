package com.systems.network_manager.requests;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.JsonObject;
import com.systems.network_manager.listeners.DataResponse;
import com.systems.network_manager.listeners.ErrorResponse;

import java.util.Map;

/**
 * A request for making a Multi Part request
 */
public abstract class MultipartRequest extends Request<JsonObject> {

    /**
     * The default socket timeout in milliseconds
     */
    public static final int DEFAULT_TIMEOUT_MS = 20*1000;

    /**
     * The default number of retries
     */
    public static final int DEFAULT_MAX_RETRIES = 1;

    /**
     * The default backoff multiplier
     */
    public static final float DEFAULT_BACKOFF_MULT = 1f;
    public static final int TIMEOUT_MS = 30000;
    private static final String PROTOCOL_CHARSET = "utf-8";
    private Map<String, MultiPartParam> params = null;
    private boolean isFixedStreamingMode;
    private DataResponse listener;
    private int index;

    /**
     * Creates a new request with the given method.
     *
     * @param method        the request {@link Method} to use
     * @param url           URL to fetch the string at
     * @param listener      Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public MultipartRequest(final int index, int method, String url, Map<String, MultiPartParam> params, DataResponse listener, final ErrorResponse errorListener) {
        super(method, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorListener.onErrorResponse(index, error);
            }
        });
        this.listener = listener;
        this.index = index;
        this.params = params;
        setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIMEOUT_MS,
                DEFAULT_MAX_RETRIES,
                DEFAULT_BACKOFF_MULT
        ));

    }

    /**
     * Add a parameter to be sent in the multipart request
     *
     * @param name        The name of the paramter
     * @param contentType The content type of the paramter
     * @param value       the value of the paramter
     * @return The Multipart request for chaining calls
     */
    public MultipartRequest addMultipartParam(String name, String contentType, String value) {
        params.put(name, new MultiPartParam(contentType, value));
        return this;
    }

    /**
     * Add a string parameter to be sent in the multipart request
     *
     * @param name  The name of the paramter
     * @param value the value of the paramter
     * @return The Multipart request for chaining calls
     */
    public MultipartRequest addStringParam(String name, String value) {
        params.put(name, new MultiPartParam("text/plain", value));
        return this;
    }

    @Override
    protected void deliverResponse(JsonObject response) {
        // TODO Auto-generated method stub
        if (listener == null) {
            return;
        }
        listener.onResponse(index, response);
    }

    /**
     * Get all the multipart params for this request
     *
     * @return A map of all the multipart params NOT including the file uploads
     */
    public Map<String, MultiPartParam> getMultipartParams() {
        return params;
    }

    /**
     * Get the protocol charset
     */
    public String getProtocolCharset() {
        return PROTOCOL_CHARSET;
    }
//
//    /**
//     * Get all the files to be uploaded for this request
//     *
//     * @return A map of all the files to be uploaded for this request
//     */
//    public Map<String, String> getFilesToUpload() {
//        return mFileUploads;
//    }

    public boolean isFixedStreamingMode() {
        return isFixedStreamingMode;
    }

    public void setFixedStreamingMode(boolean isFixedStreamingMode) {
        this.isFixedStreamingMode = isFixedStreamingMode;
    }

    /**
     * A representation of a MultiPart parameter
     */
    public static final class MultiPartParam {

        public String contentType;
        public String value;

        /**
         * Initialize a multipart request param with the value and content type
         *
         * @param contentType The content type of the param
         * @param value       The value of the param
         */
        public MultiPartParam(String contentType, String value) {
            this.contentType = contentType;
            this.value = value;
        }
    }
}