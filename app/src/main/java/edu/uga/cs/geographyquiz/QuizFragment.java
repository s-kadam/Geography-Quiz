package edu.uga.cs.geographyquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class QuizFragment extends Fragment {

    private final String[] CONTINENTS = {"Europe", "Asia", "North America", "South America", "Oceania", "Africa"};
    private Quiz quiz = new Quiz();

    private TextView textView;
    private TextView questionNum;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private QuizData quizData;
    private int score = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_question, container, false);
        textView = view.findViewById(R.id.textView);
        questionNum = view.findViewById(R.id.questionNum);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton1 = view.findViewById(R.id.button1);
        radioButton2 = view.findViewById(R.id.button2);
        radioButton3 = view.findViewById(R.id.button3);
        quizData = new QuizData(getContext());

        int position = getArguments().getInt("position");
        String questionNumber = getArguments().getString("questionNum");
        String question = "What Continent is " + getArguments().getString("country") + " located in?";
        String answer = getArguments().getString("continent");

        String wrong1 = "";
        String wrong2 = "";
        int randomizer = (int) Math.floor(Math.random()*Math.floor(5));
        int randomizer2 = (int) Math.floor(Math.random()*Math.floor(5));
        for(int i = 0; i<10; i++) {
            if(CONTINENTS[randomizer].equalsIgnoreCase(answer)|| randomizer == randomizer2) {
                randomizer = (int) Math.floor(Math.random()*Math.floor(5));

            }
            else if(CONTINENTS[randomizer2].equalsIgnoreCase(answer)|| randomizer == randomizer2) {
                randomizer2 = (int) Math.floor(Math.random()*Math.floor(5));
            }
            else {
                break;
            }
        }

        questionNum.setText(questionNumber);
        textView.setText(question);

        int random = new Random().nextInt(3);

        switch(random){
            case 0:
                radioButton1.setText(answer);
                radioButton2.setText(CONTINENTS[randomizer]);
                radioButton3.setText(CONTINENTS[randomizer2]);
                break;
            case 1:
                radioButton1.setText(CONTINENTS[randomizer]);
                radioButton2.setText(answer);
                radioButton3.setText(CONTINENTS[randomizer2]);
                break;
            case 2:
                radioButton1.setText(CONTINENTS[randomizer]);
                radioButton2.setText(CONTINENTS[randomizer2]);
                radioButton3.setText(answer);
                break;
        }

        switch (position){
            case 0:
                quiz.setQuestion1(question + " " + answer);
                break;
            case 1:
                quiz.setQuestion2(question + " " + answer);
                break;
            case 2:
                quiz.setQuestion3(question + " " + answer);
                break;
            case 3:
                quiz.setQuestion4(question + " " + answer);
                break;
            case 4:
                quiz.setQuestion5(question + " " + answer);
                break;
            case 5:
                quiz.setQuestion6(question + " " + answer);
                break;
        }

        int selected = radioGroup.getCheckedRadioButtonId();
        String selection = "";

        if (selected != -1){
            View radioButton = radioGroup.findViewById(selected);
            int radioId = radioGroup.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);
            selection = (String) btn.getText();
        }

        if (answer == selection ){
            score++;
        }

        if (position == 6){
            String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            quiz.setDate(date);
            quiz.setScore(score);
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            view.getContext().startActivity(intent);
            quizData.storeQuizzes(quiz);
        }
        return view;
    }

}
