package edu.uga.cs.geographyquiz;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class QuizRecycler extends RecyclerView.Adapter<QuizRecycler.QuizHolder>{

    private List<Quiz> quizList;

    public QuizRecycler( List<Quiz> quizList ) {
        this.quizList = quizList;
    }

    // The adapter must have a ViewHolder class to "hold" one item to show.
    class QuizHolder extends RecyclerView.ViewHolder {

        TextView quizNum;

        TextView question1;
        TextView question2;
        TextView question3;
        TextView question4;
        TextView question5;
        TextView question6;
        TextView score;
        public QuizHolder(View itemView ) {
            super(itemView);

            quizNum = (TextView) itemView.findViewById( R.id.quizNumber );
            question1 = (TextView) itemView.findViewById( R.id.question1 );
            question2 = (TextView) itemView.findViewById( R.id.question2 );
            question3 = (TextView) itemView.findViewById( R.id.question3 );
            question4 = (TextView) itemView.findViewById( R.id.question4 );
            question5 = (TextView) itemView.findViewById( R.id.question5 );
            question6 = (TextView) itemView.findViewById( R.id.question6 );
            score = (TextView) itemView.findViewById( R.id.score );
        }
    }

    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.quiz_cube, null );
        return new QuizHolder( view );
    }

    // Fills the View values
    @Override
    public void onBindViewHolder( QuizHolder holder, int position ) {
        Quiz quiz = quizList.get( position );
        holder.quizNum.setText( String.valueOf(quiz.getQuizNum()));
        holder.question1.setText( quiz.getQuestion1().toString() );
        holder.question2.setText( quiz.getQuestion2().toString() );
        holder.question3.setText( quiz.getQuestion3().toString() );
        holder.question4.setText( quiz.getQuestion4().toString() );
        holder.question5.setText( quiz.getQuestion5().toString() );
        holder.question6.setText( quiz.getQuestion6().toString() );
        holder.score.setText( Float.toString(quiz.getScore()) );

    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }
}
