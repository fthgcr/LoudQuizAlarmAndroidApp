package com.example.alarmrelease;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    String BASE_URL = "http://192.168.56.1:9191/";

    @GET(" ")
    Call<List<questionModel>> getQuestions();


}
