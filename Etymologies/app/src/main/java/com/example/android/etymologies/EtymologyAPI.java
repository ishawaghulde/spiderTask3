package com.example.android.etymologies;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface EtymologyAPI {
    @Headers({
            "Accept: application/json",
            "app_id: e74c59bf",
            "app_key: 887b5d6fac596d7cdd5031334db3cd67"
    })
    @GET("en-gb/{word_id}?fields=etymologies&strictMatch=false")
    Call<EtymologyPost> getEtymologyPosts(@Path("word_id") String lemma);
}
