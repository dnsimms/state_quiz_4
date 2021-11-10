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
    private TextView chosenStateName, title, resultsText;
    private RadioButton choice1, choice2, choice3;
    private RadioGroup radioGroup;
    private int position, totalCorrect = 0, clicks = 0;
    public ArrayList<String> quizQuestions;
    private String userAnswer = "", quesState = "";
    private boolean answeredCorrectly = false, notClicked = true;

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
        title = view.findViewById(R.id.questionTitle);
        resultsText = view.findViewById(R.id.results);
        quizQuestions = new ArrayList<>();
        selectQuizzes();//grabs the quiz questions
        //puts them in the buttons


        updatePosition();

        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnInvisible();
                String ans = "" + choice1.getText();
                String currState = "" + chosenStateName.getText();
                captureAnswer(ans, currState);
                if(notClicked){
                    ++clicks;
                    loadQuizzes.getInstance(getContext()).setClicks(clicks);
                    System.out.println("Here are the clicks " + loadQuizzes.getInstance(getContext()).getClicks());
                    notClicked = false;
                }

            }
        });
        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnInvisible();
                String ans = "" + choice2.getText();
                String currState = "" + chosenStateName.getText();
                captureAnswer(ans, currState);
                if(notClicked){
                    ++clicks;
                    loadQuizzes.getInstance(getContext()).setClicks(clicks);
                    System.out.println("Here are the clicks " +clicks);
                    notClicked = false;
                }
            }
        });

        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnInvisible();
                String ans = "" + choice3.getText();
                String currState = "" + chosenStateName.getText();
                captureAnswer(ans, currState);
                if(notClicked){
                    ++clicks;
                    loadQuizzes.getInstance(getContext()).setClicks(clicks);
                    System.out.println("Here are the clicks " +clicks);
                    notClicked = false;
                }

            }
        });




        //TODO: you can use radioGroup.getCheckedRadioButtonId to know
        //TODO: google how to check when a specific radio button is clicked
        //TODO: if something is clicked, if it's == -1 it is not clicked
        //TODO: I would create a method to use elseifs and manually check what state chosenStateName has and then see if the selected answer matches the hard coded correct answer in ur method
        return view;
    }

    public void turnInvisible(){
        int holdClicks = loadQuizzes.getInstance(getContext()).getClicks();
        if(holdClicks == 6){
            choice1.setVisibility(View.INVISIBLE);
            choice2.setVisibility(View.INVISIBLE);
            choice3.setVisibility(View.INVISIBLE);
            chosenStateName.setVisibility(View.INVISIBLE);
            resultsText.setVisibility(View.VISIBLE);
            title.setText("Your Score is: ");
            String results = "" + loadQuizzes.getInstance(getContext()).getResults();
            resultsText.setText(results);


            //chosenStateName.setText(rHelper.getResults());
        }

    }

    private void captureAnswer(String answer, String state){
        userAnswer = answer;
        quesState = state;

        if(state.equalsIgnoreCase("Alabama")){
            if(userAnswer.equalsIgnoreCase("Montgomery") && !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Montgomery"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }
        }else if (state.equalsIgnoreCase("Arizona")){
            if(userAnswer.equalsIgnoreCase("Phoenix") && !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Phoenix"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Arkansas")){
            if(userAnswer.equalsIgnoreCase("Little Rock") && !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Little Rock"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("California")){
            if(userAnswer.equalsIgnoreCase("Sacramento")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Sacramento"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Colorado")){
            if(userAnswer.equalsIgnoreCase("Denver")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Denver"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Connecticut")){
            if(userAnswer.equalsIgnoreCase("Hartford")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Hartford"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Delaware")){
            if(userAnswer.equalsIgnoreCase("Dover")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Dover"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Florida")){
            if(userAnswer.equalsIgnoreCase("Tallahassee")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Tallahassee"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Georgia")){
            if(userAnswer.equalsIgnoreCase("Atlanta")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Atlanta"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Alaska")){
            if(userAnswer.equalsIgnoreCase("Juneau")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Juneau"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Hawaii")){
            if(userAnswer.equalsIgnoreCase("Honolulu")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Honolulu"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Idaho")){
            if(userAnswer.equalsIgnoreCase("Boise")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Boise"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Illinois")){
            if(userAnswer.equalsIgnoreCase("Springfield")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Springfield"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Indiana")){
            if(userAnswer.equalsIgnoreCase("Indianapolis")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Indianapolis"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Iowa")){
            if(userAnswer.equalsIgnoreCase("Des Moines")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Des Moines"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Kansas")){
            if(userAnswer.equalsIgnoreCase("Topeka")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Topeka"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Kentucky")){
            if(userAnswer.equalsIgnoreCase("Frankfort")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Frankfort"))){
                    --totalCorrect;
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Louisiana")){
            if(userAnswer.equalsIgnoreCase("Baton Rouge")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Baton Rouge"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Maine")){
            if(userAnswer.equalsIgnoreCase("Augusta")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Augusta"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Maryland")){
            if(userAnswer.equalsIgnoreCase("Annapolis")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Annapolis"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Massachusetts")){
            if(userAnswer.equalsIgnoreCase("Boston")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Boston"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Michigan")){
            if(userAnswer.equalsIgnoreCase("Lansing")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Lansing"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Minnesota")){
            if(userAnswer.equalsIgnoreCase("Saint Paul")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Saint Paul"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Mississippi")){
            if(userAnswer.equalsIgnoreCase("Jackson")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Jackson"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Missouri")){
            if(userAnswer.equalsIgnoreCase("Jefferson City")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Jefferson City"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Montana")){
            if(userAnswer.equalsIgnoreCase("Helena")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Helena"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Nebraska")){
            if(userAnswer.equalsIgnoreCase("Lincoln")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Lincoln"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Nevada")){
            if(userAnswer.equalsIgnoreCase("Carson City")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Carson City"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("New Hampshire")){
            if(userAnswer.equalsIgnoreCase("Concord")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Concord"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("New Jersey")){
            if(userAnswer.equalsIgnoreCase("Trenton")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Trenton"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("New Mexico")){
            if(userAnswer.equalsIgnoreCase("Sante Fe")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Santa Fe"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("New York")){
            if(userAnswer.equalsIgnoreCase("Albany")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Albany"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("North Carolina")){
            if(userAnswer.equalsIgnoreCase("Raleigh")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Raleigh"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("North Dakota")){
            if(userAnswer.equalsIgnoreCase("Bismarck")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Bismarck"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Ohio")){
            if(userAnswer.equalsIgnoreCase("Columbus")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Columbus"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Oklahoma")){
            if(userAnswer.equalsIgnoreCase("Oklahoma City")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Oklahoma City"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Oregon")){
            if(userAnswer.equalsIgnoreCase("Salem")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Salem"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Pennsylvania")){
            if(userAnswer.equalsIgnoreCase("Harrisburg")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Harrisburg"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Rhode Island")){
            if(userAnswer.equalsIgnoreCase("Providence")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Providence"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("South Carolina")){
            if(userAnswer.equalsIgnoreCase("Columbia")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Columbia"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("South Dakota")){
            if(userAnswer.equalsIgnoreCase("Pierre")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Pierre"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Tennessee")){
            if(userAnswer.equalsIgnoreCase("Nashville")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Nashville"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Texas")){
            if(userAnswer.equalsIgnoreCase("Austin")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Austin"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Utah")){
            if(userAnswer.equalsIgnoreCase("Salt Lake City")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Salt Lake City"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Vermont")){
            if(userAnswer.equalsIgnoreCase("Montpelier")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Montpelier"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Virginia")){
            if(userAnswer.equalsIgnoreCase("Richmond")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Richmond"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Washington")){
            if(userAnswer.equalsIgnoreCase("Olympia")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Olympia"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("West Virginia")){
            if(userAnswer.equalsIgnoreCase("Charleston")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Charleston"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Wisconsin")){
            if(userAnswer.equalsIgnoreCase("Madison")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Madison"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

        }else if (state.equalsIgnoreCase("Wyoming")){
            if(userAnswer.equalsIgnoreCase("Cheyenne")&& !answeredCorrectly){
                ++totalCorrect;
                loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                answeredCorrectly = true;
            }else{
                if(totalCorrect == 1 && !(userAnswer.equalsIgnoreCase("Cheyenne"))){
                    --totalCorrect;
                    loadQuizzes.getInstance(getContext()).setResults(totalCorrect);
                    answeredCorrectly = false;
                }
            }

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

            answeredCorrectly = false;

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
            //TODO: if we have time double check for duplicates


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
