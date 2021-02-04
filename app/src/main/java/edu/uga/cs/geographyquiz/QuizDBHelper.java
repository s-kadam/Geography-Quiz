package edu.uga.cs.geographyquiz;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "quizhistory.db";
    private static final int DB_VER = 1;
    private SQLiteDatabase db;


    public static final String TABLE_QUIZ = "quizhistory";
    public static final String QUIZ_NUM = "_id";
    public static final String DATETIME = "datetime";
    public static final String QUIZ_Q1 = "question1";
    public static final String QUIZ_Q2 = "question2";
    public static final String QUIZ_Q3 = "question3";
    public static final String QUIZ_Q4 = "question4";
    public static final String QUIZ_Q5 = "question5";
    public static final String QUIZ_Q6 = "question6";
    public static final String SCORE = "score";

    public static final String TABLE_QUESTIONS = "questions";
    public static final String QUESTION_NUM = "_id";
    public static final String COUNTRY = "country";
    public static final String CONTINENT = "continent";


    private static QuizDBHelper helperInstance;

    private static final String CREATE_QUIZ =
            "create table " + TABLE_QUIZ + " ("
            + QUIZ_NUM + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + QUIZ_Q1 + " TEXT, "
            + QUIZ_Q2 + " TEXT, "
            + QUIZ_Q3 + " TEXT, "
            + QUIZ_Q4 + " TEXT, "
            + QUIZ_Q5 + " TEXT, "
            + QUIZ_Q6 + " TEXT,"
            + DATETIME + "TEXT,"
            + SCORE + "TEXT"
            + " )";

    private static final String CREATE_QUESTIONS =
            "create table " + TABLE_QUESTIONS + " ("
            + QUESTION_NUM + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COUNTRY + " TEXT, "
            + CONTINENT + " TEXT "
            + " )";

    private QuizDBHelper( Context context ) {
        super( context, DB_NAME, null, DB_VER );
    }

    // Access method to the single instance of the class
    public static synchronized QuizDBHelper getInstance( Context context ) {
        // check if the instance already exists and if not, create the instance
        if( helperInstance == null ) {
            helperInstance = new QuizDBHelper( context.getApplicationContext() );
        }
        return helperInstance;
    }
    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( CREATE_QUIZ );
        db.execSQL( CREATE_QUESTIONS );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "drop table if exists " + TABLE_QUIZ );
        db.execSQL( "drop table if exists " + TABLE_QUESTIONS);
        onCreate( db );
    }

    public void clearDatabase(String TABLE_NAME){
        String clearDBQuery = "DELETE FROM " + TABLE_NAME;
        db.execSQL(clearDBQuery);
    }
}
