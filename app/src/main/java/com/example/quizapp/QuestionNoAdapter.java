package com.example.quizapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class QuestionNoAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Question> questionList = new ArrayList<>();

    public QuestionNoAdapter(Context context, List<Question> questionList){
        this.context = context;
        this.questionList = questionList;
    }

    @Override
    public int getCount() {
        return questionList.size();
    }

    @Override
    public Object getItem(int i) {
        return questionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null){
            view = layoutInflater.inflate(R.layout.question_no_item, null);
        }


        TextView textViewQueNo = view.findViewById(R.id.text_view_que_no);
        Question question  = (Question) getItem(i);
        textViewQueNo.setText(String.valueOf(question.getQuestionNo()));

        //Log.d("", "getView: "+question.getStatus()+"\t"+question.getQuestionNo());
        if(question.getStatus() == Question.ANSWERED)
            textViewQueNo.setBackgroundColor(Color.GREEN);
        else if(question.getStatus() == Question.DEFAULT)
            textViewQueNo.setBackgroundColor(Color.RED);
        else
            textViewQueNo.setBackgroundColor(Color.BLUE);


        return view;
    }
}
