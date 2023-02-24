package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectTestActivity extends AppCompatActivity {

    Button buttonStartQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_test);

        bindComponents();
        addListeners();
    }

    private void bindComponents(){
        this.buttonStartQuiz = findViewById(R.id.button_start_quiz);
    }

    private void addListeners(){
        this.buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), QuetionsActivity.class));
            }
        });
    }


}