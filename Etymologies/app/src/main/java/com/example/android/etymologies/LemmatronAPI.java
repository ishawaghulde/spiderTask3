package com.example.android.etymologies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface LemmatronAPI {
    @Headers({
            "Accept: application/json",
            "app_id: e74c59bf",
            "app_key: 887b5d6fac596d7cdd5031334db3cd67"
    })
    @GET("lemmas/en/{word_id}")
    Call<LemmaPost> getLemmaPosts(@Path("word_id") String word);
}
