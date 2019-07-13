package com.example.android.etymologies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String KEY__1 = "KEY1";
    private EditText wordText;
    private DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordText = findViewById(R.id.wordText);
        Button search_button = findViewById(R.id.search_button);
        dbHandler = new DbHandler(MainActivity.this);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = wordText.getText().toString();
                if(word.equals(""))
                    Toast.makeText(MainActivity.this, "Please Enter a word", Toast.LENGTH_SHORT).show();
                wordText.setText("");
                boolean exists = dbHandler.GetUserBySearch(word);
                if(!exists)
                    dbHandler.insertUserDetails(word);


                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(KEY__1,word);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }


}
