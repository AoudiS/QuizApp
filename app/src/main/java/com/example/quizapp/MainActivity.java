package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestions ;
    TextView question ;
    Button ansA , ansB , ansC , ansD ;
    Button submitBtn ;

    int score = 0;
    int nbrQuestion = QuestionReponse.question.length;
    int currentQuestIndex = 0 ;
    String selectedAnswer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestions = findViewById(R.id.total_question);
        question = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_c);
        ansD = findViewById(R.id.ans_d);
        submitBtn = findViewById(R.id.submit_btn);


        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);

        totalQuestions.setText("Total questions :" + totalQuestions);

        loadNewQuestion();


    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickButton = (Button) view ;
        if(clickButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(QuestionReponse.reponseJuste[currentQuestIndex])){
                score++;
            }
            currentQuestIndex++ ;
            loadNewQuestion();


        }
        else{
            selectedAnswer = clickButton.getText().toString();
            clickButton.setBackgroundColor(Color.MAGENTA);
        }

    }
    void loadNewQuestion(){
        if(currentQuestIndex == nbrQuestion){
            finishQuiz();
            return;
        }
        question.setText(QuestionReponse.question[currentQuestIndex]);
        ansA.setText(QuestionReponse.choix[currentQuestIndex][0]);
        ansB.setText(QuestionReponse.choix[currentQuestIndex][1]);
        ansC.setText(QuestionReponse.choix[currentQuestIndex][2]);
        ansD.setText(QuestionReponse.choix[currentQuestIndex][3]);


    }
    void finishQuiz(){
        String passStatus = "";
        if (score > nbrQuestion*0.60){
            passStatus = "Great !";
        }
        else {
            passStatus = "Failed";

        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage(" ton score :"+score+"sur"+totalQuestions)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }
    void restartQuiz(){
        score =0 ;
        currentQuestIndex = 0 ;
        loadNewQuestion();
    }
}