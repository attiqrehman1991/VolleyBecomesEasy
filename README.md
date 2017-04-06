# VolleyBecomesEasy

<img src="/ic_launcher.png"/>

## How to use
If you want use this library, you only need to add the gradle dependency, you have to add these lines in your build.gradle file:

```xml
repositories {
    maven {
        url 'https://dl.bintray.com/attiqrehman1991/maven'
    }
}

dependencies {
    compile 'com.systems.network_manager:network_manager:0.7'
}
```

#### To Generate GET Request request with index is 1
```java
String url = "http://www.someurl.com";
CustomRequest customRequest = new CustomRequest(1, Request.Method.GET, url, null, this, this);
requestQueue.add(customRequest);
```
#### To Generate POST Request request with index is 1
following snippet is using JSON data to pass, you can use any object to pass
```java
String url = "http://www.someurl.com";
JSONObject jsonObject = new JSONObject();
jsonObject.put("_UserID", user.getUserID());
CustomObjectRequest customRequest = new CustomObjectRequest(2, Request.Method.POST, url, jsonObject, MainFragment.this, MainFragment.this, null);
requestQueue.add(customRequest);
```
and just implement two interfaces "DataResponse, ErrorResponse"

### For Fragments
```java
public class MainFragment extends Fragment implements DataResponse, ErrorResponse {
}
```
### For Activity
```java
public class MainActivity extends Fragment implements DataResponse, ErrorResponse {
}
```

### Implementation of Interface calls
```java
    @Override
    public void onResponse(int index, Object object) {
        if (index == 1) { // index of the request
        // object is either JSONObject or JSONArray based upon your received object
        }
    }
    @Override
    public void onErrorResponse(int reqId, VolleyError error) {
        if (index == 1) { // index of the request
        }
    }
```


##### MIT License

##### Copyright (c) 2017 Attiq ur Rehman
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
