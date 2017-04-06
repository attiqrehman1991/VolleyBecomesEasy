/*
 *
 *  * MIT License
 *  *
 *  * Copyright (c) 2017 Attiq ur Rehman
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *
 *
 */

package com.systems.network_manager.requests;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.systems.network_manager.listeners.DataResponse;
import com.systems.network_manager.listeners.ErrorResponseImage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownload extends AsyncTask<Void, Context, String> {

    private int index;
    private String name = null;
    private DataResponse dataResponse;
    private String urlLink;
    private ErrorResponseImage errorListener;

    public ImageDownload(int index, String name, String url, DataResponse dataResponse, final ErrorResponseImage errorListener) {
        this.index = index;
        this.name = name;
        this.urlLink = url;
        this.dataResponse = dataResponse;
        this.errorListener = errorListener;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            return doFileDownload();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            dataResponse.onResponse(index, result);
        } catch (Exception e) {
            e.printStackTrace();
            errorListener.onErrorResponse(index, result);
        }
    }

    private String doFileDownload() {
        String result = "";

        HttpURLConnection conn;
        DataOutputStream dos;

        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        int bytesRead, bytesAvailable, bufferSize;

        byte[] buffer;

        int maxBufferSize = 1024 * 1024;

        try {
            // ------------------ CLIENT REQUEST
            // open a URL connection to the Servlet
            URL url = new URL(urlLink);

            // Open a HTTP connection to the URL
            conn = (HttpURLConnection) url.openConnection();

            // Allow Inputs
            conn.setDoInput(true);

            // Allow Outputs
            conn.setDoOutput(true);

            // Don't use a cached copy.
            conn.setUseCaches(false);

            // Use a post method.
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Connection", "Keep-Alive");

            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=\"" + boundary + "\"");

            dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Type: application/octet-stream" + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + name + "\""
                    + lineEnd);
            dos.writeBytes("Content-Transfer-Encoding: binary" + lineEnd + lineEnd);

            Log.e("MediaPlayer", "Headers are written");

            // create a buffer of maximum size

            // read file and write it into form...

            // send multipart form data necessary after file data...
            dos.writeBytes(lineEnd + twoHyphens + boundary + twoHyphens);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            in.close();
            result = stringBuilder.toString();

            Log.d("MediaPlayer", "File is written");
            dos.flush();
            dos.close();
            return result;
        } catch (MalformedURLException ex) {
            Log.e("MediaPlayer", "error: " + ex.getMessage(), ex);
        } catch (IOException ioe) {
            Log.e("File Not Found", "error: " + ioe.getMessage(), ioe);
        }
        return result;
    }
}
