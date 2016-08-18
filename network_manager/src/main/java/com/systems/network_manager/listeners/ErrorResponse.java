package com.systems.network_manager.listeners;

import com.android.volley.VolleyError;

/**
 * @author Attiq ur Rehman
 *         created on 4/10/2016.
 *         attiq.ur.rehman1991@gmail.com
 *
 *         Added functions are
 *         onErrorResponse -- to handle error request
 */

public interface ErrorResponse {

    public void onErrorResponse(int reqId, VolleyError error);
}
