package edu.uga.cs.geographyquiz;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class NewQuiz extends AppCompatActivity {

    private ViewPager pager;
    private final String DEBUG_TAG = "NewQuiz";

    QuestionData questionData;

    private TextView textView;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    List<Question> questions;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiper);

        Log.d("NewQuiz", "IN NEW QUIZ" );

        textView = findViewById(R.id.textView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        pager = (ViewPager) findViewById(R.id.pager);
        QuizPagerAdapter adapter = new QuizPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        adapter.notifyDataSetChanged();






    }

    public List<Question> getAllQuestions(){

        questionData.close();
        return  questions;
    }

    private class QuestionDBReader extends AsyncTask<Void, Void, List<Question>> {

        protected List<Question> doInBackground(Void... params){
            Log.d("NEW QUIZ ASYNC", "currently in doInBackground");
            questionData = new QuestionData(getApplicationContext());
            List<Question> questionList;
            questionData.open();
            questionList = questionData.getAllQuestions();
            questionData.close();
            for (int i = 0; i < questionList.size(); i++) {
                Log.d("GOT ALL QUESTIONS"
                        , "Question: "
                                + questionList.get(i).getQuizNumber()
                                + " COUNTRY: " + questionList.get(i).getCountry()
                                + " CONTINENT " + questionList.get(i).getContinent()
                );
            }
            return questionList;

        }

        protected void onPostExecute(List<Question> questions){
            super.onPostExecute(questions);



        }
    }

    private class QuizPagerAdapter extends FragmentStatePagerAdapter {


        public QuizPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public Fragment getItem(int position){

            try {
                questions = new QuestionDBReader().execute().get();

            }catch (Exception e){
                Log.d("NEW QUIZ FRAGMENT", "Failed to execute QuestionDBReader in Fragment getItem(int position)");
            }
            Fragment fragment = new QuizFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            bundle.putString("questionNum", ("Question " + Integer.toString(position+1 )));
            bundle.putString("country", questions.get(position).getCountry());
            bundle.putString("continent", questions.get(position).getContinent());
            fragment.setArguments(bundle);

            return fragment;
        }

    }



}
