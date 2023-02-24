package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class QuetionsActivity extends AppCompatActivity {

    RadioGroup radioGroupOptions;
    TextView textViewQuestion;
    GridView gridView;
    QuestionNoAdapter questionNoAdapter;
    List<Question> questionList;
    Button buttonNext, buttonPrev, buttonBookmark;
    int currentQues = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quetions);

        //startActivity(new Intent(getApplicationContext(), QuetionsActivity.class));

        bindComponents();
        questionList = JavaQuizQuestions.getQuiz();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String no = extras.getString("QUE_NO");
            currentQues = Integer.parseInt(no);
            Log.d("", "onCreate: "+currentQues);
            questionList = (List<Question>) extras.getSerializable("QUESTION_LIST");
            questionNoAdapter = new QuestionNoAdapter(this, questionList);
            gridView.setAdapter(questionNoAdapter);
            loadQuestion(currentQues);
            if(currentQues == 9){
                loadEndTestUI();
            }
        }else{
            loadQuestion(0);
            questionNoAdapter = new QuestionNoAdapter(this, questionList);
            gridView.setAdapter(questionNoAdapter);
        }

        addListeners();
    }

    private void loadQuestion(int i){

        currentQues = i;
        questionNoAdapter = new QuestionNoAdapter(this, questionList);
        gridView.setAdapter(questionNoAdapter);

        for(int k=0; k<10; k++){
            Log.d("", "loadQuestion: "+questionList.get(k).getQuestionNo()+"\t"+questionList.get(k).getStatus());
        }

        textViewQuestion.setText(questionList.get(i).getQuestion());
        for(int j=0; j<radioGroupOptions.getChildCount(); j++){
            ((RadioButton)radioGroupOptions.getChildAt(j)).setText(questionList.get(i).getOptions()[j]);
        }

        if(questionList.get(i).getStatus() == Question.DEFAULT)
            radioGroupOptions.clearCheck();
        if(questionList.get(i).getStatus() == Question.ANSWERED){
            RadioButton radioButton;
            switch (questionList.get(i).getAnswer()){
                case 1:
                    radioButton = findViewById(R.id.radio_op1);
                    radioButton.setChecked(true);
                    break;
                case 2:
                    radioButton = findViewById(R.id.radio_op2);
                    radioButton.setChecked(true);
                    break;
                case 3:
                    radioButton = findViewById(R.id.radio_op3);
                    radioButton.setChecked(true);
                    break;
                case 4:
                    radioButton = findViewById(R.id.radio_op4);
                    radioButton.setChecked(true);
                    break;
            }
        }
    }

    private void bindComponents(){
        textViewQuestion = findViewById(R.id.text_view_que);
        radioGroupOptions = findViewById(R.id.radio_group_options);
        gridView = findViewById(R.id.grid_view_ques);
        buttonNext = findViewById(R.id.button_next);
        buttonPrev = findViewById(R.id.button_prev);
        buttonBookmark = findViewById(R.id.button_bookmark);
    }

    private void addListeners(){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(QuetionsActivity.this, QuetionsActivity.class);
                intent.putExtra("QUE_NO",""+i);
                intent.putExtra("QUESTION_LIST", (Serializable) questionList);
                startActivity(intent);
            }
        });

        radioGroupOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                questionList.get(currentQues).setStatus(Question.ANSWERED);
                int radioButtonId = radioGroupOptions.getCheckedRadioButtonId();
                View radioButton = radioGroupOptions.findViewById(radioButtonId);
                int index = radioGroupOptions.indexOfChild(radioButton);
                index += 1;
                questionList.get(currentQues).setAnswer((short)index);
                //Toast.makeText(QuetionsActivity.this, ""+index, Toast.LENGTH_SHORT).show();
            }
        });

        buttonBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionList.get(currentQues).setStatus(Question.BOOKMARKED);
                questionNoAdapter = new QuestionNoAdapter(getApplicationContext(), questionList);
                gridView.setAdapter(questionNoAdapter);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentQues == 9){
                    submitQuiz();
                }
                else{
                    Intent intent = new Intent(QuetionsActivity.this, QuetionsActivity.class);
                    intent.putExtra("QUE_NO",""+(currentQues+1));
                    intent.putExtra("QUESTION_LIST", (Serializable) questionList);
                    startActivity(intent);
                }
                //Toast.makeText(QuetionsActivity.this, "next", Toast.LENGTH_SHORT).show();
            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentQues != 0){
                    Intent intent = new Intent(QuetionsActivity.this, QuetionsActivity.class);
                    intent.putExtra("QUE_NO",""+(currentQues-1));
                    intent.putExtra("QUESTION_LIST", (Serializable) questionList);
                    startActivity(intent);
                }
                //Toast.makeText(QuetionsActivity.this, "prev", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadEndTestUI(){
        buttonNext.setText("Submit");
        buttonNext.setBackgroundColor(Color.RED);
    }

    private void submitQuiz(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want submit Quiz?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getApplicationContext(), ShowScoreActivity.class);
                        intent.putExtra("QUESTION_LIST", (Serializable) questionList);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();    }
}