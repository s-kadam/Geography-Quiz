package edu.uga.cs.geographyquiz;

public class Quiz {
    private long QuizNum;
    private String date;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    private float score;

    public Quiz()
    {
        this.QuizNum = -1;
        this.date = null;
        this.question1 = null;
        this.question2 = null;
        this.question3 = null;
        this.question4 = null;
        this.question5 = null;
        this.question6 = null;
        this.score = -1;
    }
    public Quiz(long QuizNum, String date, String question1, String question2, String question3, String question4, String question5, String question6, float score)
    {
        this.QuizNum = QuizNum;
        this.date = date;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
        this.question6 = question6;
        this.score = score;
    }
    public long getQuizNum(){return QuizNum;}
    public void setQuizNum(long QuizNum){this.QuizNum = QuizNum;}

    public String getDate(){return date;}
    public void setDate(String date){this.date = date;}

    public String getQuestion1(){return question1;}
    public void setQuestion1(String question1){this.question1=question1;}

    public String getQuestion2(){return question2;}
    public void setQuestion2(String question2){this.question2=question2;}

    public String getQuestion3(){return question3;}
    public void setQuestion3(String question3){this.question2=question3;}

    public String getQuestion4(){return question4;}
    public void setQuestion4(String question4){this.question2=question4;}

    public String getQuestion5(){return question5;}
    public void setQuestion5(String question5){this.question2=question5;}

    public String getQuestion6(){return question6;}
    public void setQuestion6(String question6){this.question2=question6;}

    public float getScore(){return score;}
    public void setScore(float score){this.score=score;}

    public String quizToString()
    {
        return "Quiz #" +QuizNum + "\nDate: " + date + "\nQuestion 1: " + question1 +"\nQuestion 2: " + question2 +"\nQuestion 3: " + question3  +"\nQuestion 4: " + question4 +"\nQuestion 5: " + question5  +"\nQuestion 6: " + question6;
    }
}
