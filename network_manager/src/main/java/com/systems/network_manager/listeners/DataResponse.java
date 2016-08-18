package com.systems.network_manager.listeners;

/**
 * @author Attiq ur Rehman
 *         created on 4/10/2016.
 *         attiq.ur.rehman1991@gmail.com
 *
 *         Added functions are
 *         onResponse -- to handle successful request
 */

public interface DataResponse {
    public void onResponse(int reqId, Object object);

}
