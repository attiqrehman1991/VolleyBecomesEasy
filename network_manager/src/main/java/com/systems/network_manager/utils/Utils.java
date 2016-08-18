package com.systems.network_manager.utils;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Attiq ur Rehman on 4/18/2016.
 * <p>
 * Senior Software Engineer at Systems Ltd
 * attiq.ur.rehman1991@gmail.com
 * <p>
 * To perform common functionality for application wide variables
 */
public class Utils {

    public static void copyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (; ; ) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception ex) {
        }
    }
}