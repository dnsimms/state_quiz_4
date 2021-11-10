package edu.uga.cs.state_quiz_4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;

public class quiz_questions_frag extends Fragment {

    private SQLiteDatabase db;
    public int pushes =0, track = 0;
    private static final String[] grabAllColumns = {
            loadQuizzes.QUIZZES_COLUMN_INC,
            loadQuizzes.QUIZZES_COLUMN_STATE,
            loadQuizzes.QUIZZES_COLUMN_CAPITAL,
            loadQuizzes.QUIZZES_COLUMN_ALT1,
            loadQuizzes.QUIZZES_COLUMN_ALT2
    };
    private TextView chosenStateName;
    private RadioButton choice1, choice2, choice3;
    private RadioGroup radioGroup;
    private int position;
    public ArrayList<String> quizQuestions;

    private static final String DEBUG_TAG = "quiz_questions_frag";


    public quiz_questions_frag(int pos){
        position = pos;

        //TODO: Check to see when pos is == 7 and make chosenStateName, choice1, choice2, choice3 invisible and your designate quiz results buttons and stuff visible
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_quiz, container, false);

        db = loadQuizzes.getInstance(getContext()).getWritableDatabase(); // this gets the created database

        chosenStateName = view.findViewById(R.id.chosenStateName);// has the state name
        radioGroup = view.findViewById(R.id.radioGroup);
        choice1 = view.findViewById(R.id.choice1);
        choice2 = view.findViewById(R.id.choice2);
        choice3 = view.findViewById(R.id.choice3);
        quizQuestions = new ArrayList<>();
        selectQuizzes();//grabs the quiz questions
        updatePosition();//puts them in the buttons

        //TODO: you can use radioGroup.getCheckedRadioButtonId to know
        //TODO: google how to check when a specific radio button is clicked
        //TODO: if something is clicked, if it's == -1 it is not clicked
        //TODO: I would create a method to use elseifs and manually check what state chosenStateName has and then see if the selected answer matches the hard coded correct answer in ur method
        return view;
    }


    /**
     * This puts the text for the questions and answers
     */
    public void updatePosition (){
        chosenStateName.setText(quizQuestions.get(track));
        ++track;
        choice1.setText(quizQuestions.get(track));
        ++track;
        choice2.setText(quizQuestions.get(track));
        ++track;
        choice3.setText(quizQuestions.get(track));
        ++track;
    }//don't mess with this

    /**
     * Adds each question into the array list. They are in order of state, capital, alt1, alt2.
     * @param cursor database cursor
     */
    public void insertQuestions(Cursor cursor){
        String state, cap, alt1, alt2;
        state = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_STATE));
        cap = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_CAPITAL));
        alt1 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_ALT1));
        alt2 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_ALT2));
        quizQuestions.add(state);
        quizQuestions.add(cap);
        quizQuestions.add(alt1);
        quizQuestions.add(alt2);
    }//don't mess with this

    /**
     * Randomly grabs rows from the database to create quiz questions
     */
    public void selectQuizzes() {

        Cursor cursor = null;

        try{
            cursor = db.query(loadQuizzes.TABLE_QUIZZES, grabAllColumns,
                    null, null, null, null, null);
            int max = 50, min = 2, counter = 0;
            Random rand = new Random();
            int[] values = new int[6]; //this will hold the random generated ids
            for(int i = 0; i < 6; i++){
                values[i] = rand.nextInt((max-min) + 1) + min;
            }//generates the random row numbers


            if(cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    long inc = cursor.getLong(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_INC));
                    if((inc == values[0]) || (inc == values[1] )|| (inc == values[2]) || (inc == values[3])
                            || (inc == values[4]) || (inc == values[5])){
                        if((counter == 0) ||(counter == 1) || (counter == 2) || (counter == 3) || (counter == 4) || (counter == 5)){
                            insertQuestions(cursor);
                        }//end else if
                        ++counter;
                    }//top iff

                }
            }
            Log.d(DEBUG_TAG, "Number of rows: " + cursor.getCount());
        }catch(Exception e){
            Log.d(DEBUG_TAG, "Exception trace: " + e);
        }finally{
            if(cursor != null){
                cursor.close();
                db.close();
            }
        }
    }

}
