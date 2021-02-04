package edu.uga.cs.geographyquiz;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;

public class PreviousQuizzes extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;

    private QuizData quiz;
    private List<Quiz> quizList;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_database );

        recyclerView = findViewById( R.id.recyclerView );

        // use a linear layout manager for the recycler view
        layoutManager = new LinearLayoutManager(this );
        recyclerView.setLayoutManager( layoutManager );

        // Create a JobLeadsData instance, since we will need to save a new JobLead to the dn.
        // Note that even though more activites may create their own instances of the JobLeadsData
        // class, we will be using a single instance of the JobLeadsDBHelper object, since
        // that class is a singleton class.
        quiz = new QuizData( this );

        // Execute the retrieval of the job leads in an asynchronous way,
        // without blocking the UI thread.
        new QuizDBReader().execute();

    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB reading of job leads, asynchronously.
    private class QuizDBReader extends AsyncTask<Void, Void, List<Quiz>> {

        // This method will run as a background process to read from db.
        @Override
        protected List<Quiz> doInBackground( Void... params ) {
            quiz.open();
            quizList = quiz.getAllQuizzes();
            return quizList;
        }

        // This method will be automatically called by Android once the db reading
        // background process is finished.  It will then create and set an adapter to provide
        // values for the RecyclerView.
        @Override
        protected void onPostExecute( List<Quiz> quizList ) {
            super.onPostExecute(quizList);
            recyclerAdapter = new QuizRecycler( quizList);
            recyclerView.setAdapter( recyclerAdapter );
        }
    }

}

