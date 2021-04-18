package com.example.infs3605;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedAPI {

    String BASE_URL = "https://www.wired.com/";

    @GET("feed/category/security/latest/rss")
    Call<RSS> getFeed();
}
