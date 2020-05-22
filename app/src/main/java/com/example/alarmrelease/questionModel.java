package com.example.alarmrelease;

import com.google.gson.annotations.SerializedName;

public   class questionModel {

    @SerializedName("id")
    private int id;

    @SerializedName("question")
    private String question;

    @SerializedName("optionA")
    private String optionA;

    @SerializedName("optionB")
    private String optionB;

    @SerializedName("optionC")
    private String optionC;

    @SerializedName("optionD")
    private String optionD;

    @SerializedName("answer")
    private String answer;

    public questionModel(int id, String question, String optionA, String optionB, String optionC, String optionD, String answer) {

        this.id = id;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getAnswer() {
        return answer;
    }
}

