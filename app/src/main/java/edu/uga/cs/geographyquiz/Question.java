package edu.uga.cs.geographyquiz;

public class Question {
    private long quizNumber;
    private String country;
    private String continent;

    public Question() {
        this.quizNumber = -1;
        this.country = null;
        this.continent = null;
    }

    public Question(long quizNumber, String country, String continent){
        this.quizNumber = quizNumber;
        this.country = country;
        this.continent = continent;
    }

    public long getQuizNumber() {
        return quizNumber;
    }

    public void setQuizNumber(long quizNumber) {
        this.quizNumber = quizNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String toStringQuestion() {
        return "Question "
                + quizNumber
                + ": What continent is "
                + country
                + " located in?";
    }
}
