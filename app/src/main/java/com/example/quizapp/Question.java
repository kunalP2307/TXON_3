package com.example.quizapp;

import java.io.Serializable;

public class Question implements Serializable {

    private int questionNo, answer, status;
    private String question, options[];
    public static short DEFAULT = 0, ANSWERED = 1, BOOKMARKED = 2;

    public Question() {
    }

    public Question(int questionNo, int answer,int status, String question, String[] options) {
        this.questionNo = questionNo;
        this.answer = answer;
        this.status = status;
        this.question = question;
        this.options = options;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
