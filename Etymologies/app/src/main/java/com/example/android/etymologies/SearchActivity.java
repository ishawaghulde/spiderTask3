package com.example.android.etymologies;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    public static final String KEY__1 = "KEY1";
    private TypeWriter textView;
    private LemmatronAPI lemmatronAPI;
    private EtymologyAPI etymologyAPI;
    private String lemma;
    private String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        textView = findViewById(R.id.textView);
        TextView head = findViewById(R.id.head);
        Bundle bundle = getIntent().getExtras();
        word = bundle.getString(KEY__1);
        head.setText(word);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://od-api.oxforddictionaries.com/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        lemmatronAPI = retrofit.create(LemmatronAPI.class);

        getLemmaPosts();
    }

    private void getLemmaPosts(){
        Call<LemmaPost> call = lemmatronAPI.getLemmaPosts(word);

        call.enqueue(new Callback<LemmaPost>() {
            @Override
            public void onResponse(@NonNull Call<LemmaPost> call,@NonNull Response<LemmaPost> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(SearchActivity.this, "Data Not Found!", Toast.LENGTH_LONG).show();
                    return;
                }

                LemmaPost lemmaPost = response.body();
                lemma = lemmaPost.getResult();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://od-api.oxforddictionaries.com/api/v2/entries/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                etymologyAPI = retrofit.create(EtymologyAPI.class);
                getEtymologyPosts();
            }

            @Override
            public void onFailure(@NonNull Call<LemmaPost> call, @NonNull Throwable t) {
                Toast.makeText(SearchActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });
    }

    private void getEtymologyPosts(){

        Call<EtymologyPost> call = etymologyAPI.getEtymologyPosts(lemma);

        call.enqueue(new Callback<EtymologyPost>() {
            @Override
            public void onResponse(@NonNull Call<EtymologyPost> call, @NonNull Response<EtymologyPost> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(SearchActivity.this, "Data Not Found!", Toast.LENGTH_LONG).show();
                    return;
                }

                EtymologyPost etymologyPost = response.body();
                String origin = etymologyPost.getResults();
                textView.animateText(origin);

            }

            @Override
            public void onFailure(@NonNull Call<EtymologyPost> call, @NonNull Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }

}
