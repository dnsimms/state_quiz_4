package edu.uga.cs.state_quiz_4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView title;
    private TextView chosenStateName;
    private RadioButton choice1, choice2, choice3;
    private RadioGroup radioGroup;
    private int position;
    public ArrayList<String> quizQuestions;
    public String compareAnswer;
    private int score = 0;

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

        title = view.findViewById(R.id.questionTitle);
        chosenStateName = view.findViewById(R.id.chosenStateName);// has the state name
        radioGroup = view.findViewById(R.id.radioGroup);
        choice1 = view.findViewById(R.id.choice1);
        choice2 = view.findViewById(R.id.choice2);
        choice3 = view.findViewById(R.id.choice3);
        quizQuestions = new ArrayList<>();




        selectQuizzes();//grabs the quiz questions
        updatePosition();//puts them in the buttons
        checkAnswers();
        //turnInvisible();
       // compareFunction();






        //TODO: you can use radioGroup.getCheckedRadioButtonId to know
        //TODO: google how to check when a specific radio button is clicked
        //TODO: if something is clicked, if it's == -1 it is not clicked
        //TODO: I would create a method to use elseifs and manually check what state chosenStateName has and then see if the selected answer matches the hard coded correct answer in ur method
        return view;
    }



    public String checkAnswers() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton radioButton = (RadioButton) getActivity().findViewById(checkedId);
                //title.setText(radioButton.getText());
                compareAnswer = radioButton.getText().toString();
                //title.setText(compareAnswer);

                if(chosenStateName.getText().toString().equals("Alabama") && compareAnswer.equals("Montgomery")) {
                    title.setText("LETS GO");
                }
            }
        });
    //not returning unless clicked
    return compareAnswer;
    }

    public void compareFunction() {
        //gettext to string works
        if(chosenStateName.getText().toString() == "Alabama" && compareAnswer.equals("Montgomery")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Alaska" && compareAnswer.equals("Juneau")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Arizona" && compareAnswer.equals("Phoenix")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Arkansas" && compareAnswer.equals("Little Rock")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "California" && compareAnswer.equals("Sacramento")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Connecticut" && compareAnswer.equals("Hartford")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Delaware" && compareAnswer.equals("Dover")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Florida" && compareAnswer.equals("Tallahassee")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Georgia" && compareAnswer.equals("Atlanta")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Hawaii" && compareAnswer.equals("Honolulu")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Idaho" && compareAnswer.equals("Boise")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Illinois" && compareAnswer.equals("Springfield")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Indiana" && compareAnswer.equals("Indianapolis")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Iowa" && compareAnswer.equals("Des Moines")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Kansas" && compareAnswer.equals("Topeka")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Kentucky" && compareAnswer.equals("Frankfort")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Louisiana" && compareAnswer.equals("Baton Rouge")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Maine" && compareAnswer.equals("Augusta")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Maryland" && compareAnswer.equals("Annapolis")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Massachusetts" && compareAnswer.equals("Boston")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Michigan" && compareAnswer.equals("Lansing")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Minnesota" && compareAnswer.equals("Saint Paul")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Mississippi" && compareAnswer.equals("Jackson")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Missouri" && compareAnswer.equals("Jefferson City")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Montana" && compareAnswer.equals("Helena")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Nebraska" && compareAnswer.equals("Lincoln")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Nevada" && compareAnswer.equals("Carson City")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "New Hampshire" && compareAnswer.equals("Concord")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "New Jersey" && compareAnswer.equals("Trenton   ")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "New Mexico" && compareAnswer.equals("Santa Fe")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "New York" && compareAnswer.equals("Albany")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "North Carolina" && compareAnswer.equals("Raleigh")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "North Dakota" && compareAnswer.equals("Bismarck")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Ohio" && compareAnswer.equals("Columbus")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Oklahoma" && compareAnswer.equals("Oklahoma City")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Oregon" && compareAnswer.equals("Salem")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Pennsylvania" && compareAnswer.equals("Harrisburg")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Rhode Island" && compareAnswer.equals("Providence")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "South Carolina" && compareAnswer.equals("Columbus")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "South Dakota" && compareAnswer.equals("Pierre")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Tennessee" && compareAnswer.equals("Nashville")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Texas" && compareAnswer.equals("Austin")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Utah" && compareAnswer.equals("Salt Lake City")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Vermont" && compareAnswer.equals("Montpelier")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Virginia" && compareAnswer.equals("Richmond")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Washington" && compareAnswer.equals("Olympia")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "West Virginia" && compareAnswer.equals("Charleston")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Wisconsin" && compareAnswer.equals("Madison")) {
            score++;
        }
        else if(chosenStateName.getText().toString() == "Wyoming" && compareAnswer.equals("Cheyenne")) {
            score++;
        }




    }

    public void test() {

    }

    public void turnInvisible() {

        if(position == 6) {
        choice1.setVisibility(View.INVISIBLE);
        choice2.setVisibility(View.INVISIBLE);
        choice3.setVisibility(View.INVISIBLE);
        title.setText("Your Score is: ");

        }
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
