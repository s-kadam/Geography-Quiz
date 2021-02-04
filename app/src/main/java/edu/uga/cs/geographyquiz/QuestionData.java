package edu.uga.cs.geographyquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuestionData {

    private SQLiteDatabase db;
    private SQLiteOpenHelper quizDBHelper;
    private static final String[] allColumns = {
            QuizDBHelper.QUESTION_NUM,
            QuizDBHelper.COUNTRY,
            QuizDBHelper.CONTINENT,
    };

    public QuestionData( Context context ) {
        this.quizDBHelper = QuizDBHelper.getInstance( context );
    }

    public void open() {

        db = quizDBHelper.getWritableDatabase();
    }

    public void close() {
        if( quizDBHelper != null ) {
            quizDBHelper.close();
        }
    }

    public List<Question> getAllQuestions() {
        ArrayList<Question> quizArrayList = new ArrayList<>();
        Cursor cursor = null;

        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query( QuizDBHelper.TABLE_QUESTIONS, allColumns,
                    null, null, null, null, null );
            // collect all job leads into a List
            if( cursor.getCount() > 0 ) {
                while( cursor.moveToNext() ) {
                    long id = cursor.getLong( cursor.getColumnIndex( QuizDBHelper.QUESTION_NUM ) );
                    String country = String.valueOf(cursor.getString(cursor.getColumnIndex(QuizDBHelper.COUNTRY)));
                    String continent = String.valueOf(cursor.getString( cursor.getColumnIndex( QuizDBHelper.CONTINENT)));
                    Question question = new Question(id, country, continent);
                    question.setQuizNumber( id );
                    quizArrayList.add( question );
                }
            }
        }
        catch( Exception e ){

        }
        finally{
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        return quizArrayList;
    }

    public Question storeQuestions(Question question) {
        open();
        ContentValues values = new ContentValues();
        values.put(QuizDBHelper.QUESTION_NUM, question.getQuizNumber());
        values.put(QuizDBHelper.COUNTRY, question.getCountry());
        values.put(QuizDBHelper.CONTINENT, question.getContinent());
        db.insert(QuizDBHelper.TABLE_QUESTIONS, null, values);
        close();
        return question;
    }
}

