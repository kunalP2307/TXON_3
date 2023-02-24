package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ShowScoreActivity extends AppCompatActivity {

    TextView textViewScore;
    Button buttonBackToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);

        bindComponents();
        calculateScore();
    }

    private void calculateScore(){
        int score = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            List<Question> questionList =  (List<Question>) extras.getSerializable("QUESTION_LIST");
            for(int i=0; i<JavaQuizQuestions.getQuiz().size(); i++){
                if(questionList.get(i).getAnswer() == JavaQuizQuestions.getQuiz().get(i).getAnswer())
                    score += 1;
            }
        }
        textViewScore.setText(""+score);
    }
    private void bindComponents(){
        textViewScore = findViewById(R.id.text_view_score);
        buttonBackToHome = findViewById(R.id.button_back_to_home);

        buttonBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SelectTestActivity.class));
            }
        });
    }
}
