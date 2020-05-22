package com.example.alarmrelease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionActivity extends AppCompatActivity {

    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Start music
        Intent intent = new Intent (QuestionActivity.this, MusicService.class);
        startService(intent);

        final TextView textView = findViewById(R.id.questionText);
        final Button optionA_button = findViewById(R.id.optionA_button);
        final Button optionB_button = findViewById(R.id.optionB_button);
        final Button optionC_button = findViewById(R.id.optionC_button);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsonPlaceHolderApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi api = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<questionModel>>  call = api.getQuestions();

        call.enqueue(new Callback<List<questionModel>>() {
            @Override
            public void onResponse(Call<List<questionModel>> call, Response<List<questionModel>> response) {

                final List<questionModel> questions = response.body();
                final int randomQuestion = (int)(12.0 * Math.random());
                textView.setText(questions.get(randomQuestion).getQuestion());
                optionA_button.setText(questions.get(randomQuestion).getOptionA());
                optionB_button.setText(questions.get(randomQuestion).getOptionB());
                optionC_button.setText(questions.get(randomQuestion).getOptionC());

                optionA_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (questions.get(randomQuestion).getOptionA().equals(questions.get(randomQuestion).getAnswer())){
                            correctOption ();
                        }
                        else{
                            wrongOption ();
                        }
                    }
                });

                optionB_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (questions.get(randomQuestion).getOptionB().equals(questions.get(randomQuestion).getAnswer())){
                            correctOption ();
                        }
                        else{
                            wrongOption ();
                        }
                    }
                });

                optionC_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (questions.get(randomQuestion).getOptionC().equals(questions.get(randomQuestion).getAnswer())){
                            correctOption ();
                        }
                        else{
                            wrongOption ();
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<List<questionModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void openQuestionsLayoutAgain () {
        Intent intent = getIntent();
        startActivity(intent);
    }

    public void openMainActiviy () {
        Intent intent = new Intent (QuestionActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void correctOption (){
        Toast.makeText(getApplicationContext(), "Alarm Closed", Toast.LENGTH_LONG).show();
        Intent intent = new Intent (QuestionActivity.this, MusicService.class);
        stopService(intent);
        openMainActiviy ();
    }
    public void wrongOption (){
        Intent intent = new Intent (QuestionActivity.this, MusicService.class);
        stopService(intent);
        openQuestionsLayoutAgain ();
    }



}
