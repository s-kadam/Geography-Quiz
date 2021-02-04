package edu.uga.cs.geographyquiz;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private Button quizButton, dbButton;
    private NewQuiz newQuiz;
    private ImageView globePic;
    private QuestionData questionData;

    public static final String DEBUG_TAG = "MainActivity";
    public static final String COMMA = ",";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        globePic = findViewById(R.id.globePic);
        int pictureStream = getResources().getIdentifier("globe","raw", getPackageName());
        globePic.setImageResource(pictureStream);
        quizButton = findViewById(R.id.newQuizButton);
        dbButton = findViewById(R.id.pastQuizButton);
        quizButton.setOnClickListener(new QuizButtonListener());
        dbButton.setOnClickListener(new DataButtonListener());
    }
    private class QuizButtonListener implements View.OnClickListener{
        @Override
        public void onClick (View view)
        {

            Question question = new Question();
            new LoadAllCountries().execute();

            Intent intent = new Intent(view.getContext(), NewQuiz.class);
            view.getContext().startActivity(intent);

        }
    }
    private class DataButtonListener implements View.OnClickListener{
        @Override
        public void onClick (View view)
        {

            Intent intent = new Intent(view.getContext(), PreviousQuizzes.class);
            view.getContext().startActivity(intent);
        }
    }

    private class LoadAllCountries extends AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            ArrayList<String> questions = new ArrayList<String>(200);
            String[] csv;
            Resources res = getResources();
            InputStream in_s = res.openRawResource(R.raw.country_continent);
            CSVReader inputStream;

            try {
                inputStream = new CSVReader(new InputStreamReader(in_s));

                while ((csv = inputStream.readNext()) != null) {
                    for(int counter = 0; counter < csv.length; counter++)
                    {
                        questions.add(csv[counter]);
                        Log.d("MAIN ACTIVITY", "COUNTRY ADDED: " + csv[counter]);
                    }
                }


            } catch (Exception e) {
                Log.d(DEBUG_TAG, "IOException when trying to read csv file: " + e.toString());
            }



            return questions;
        }

        @Override
        protected void onPostExecute(ArrayList<String> questionList) {
            Log.d("POST EXECUTE", "In Post Execute");
            ArrayList<Question> questions = new ArrayList<>();
            super.onPostExecute(questionList);
            try {

                for (int i = 0; i < questionList.size(); i++) {

                    if (i % 2 == 0) {
                        Question question = new Question(
                                i / 2,
                                questionList.get(i),
                                questionList.get(i + 1)
                        );
                        questions.add(question);
                        Log.d("PARSING QUESTION LIST", "Added: " + question.getCountry() + " " + question.getContinent());
                    }
                }
            } catch (Exception e) {
                Log.d("PARSING QUESTION LIST", "error when parsing list: " + e.toString());

            }
            try{
                questionData = new QuestionData(getApplicationContext());
                questions.trimToSize();
                Collections.shuffle(questions);
                for (int i = 1; i <= 6; i++) {
                    questionData.storeQuestions(questions.get(i));
                    Log.d("MAIN ACTIVITY", "csv stored in db" + questions.get(i).getCountry() + questions.get(i).getContinent());
                }
            } catch (Exception e){
                Log.d(DEBUG_TAG, "Error when trying to store questions: " + e.toString());
            }
        }
    }

}