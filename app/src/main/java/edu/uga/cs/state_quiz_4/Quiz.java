package edu.uga.cs.state_quiz_4;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Quiz extends AppCompatActivity {
    private SQLiteDatabase db;
    public int pushes =0, track = 4;
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
    private Button nextButtion;
    public ArrayList<String> quizQuestions;

    private static final String DEBUG_TAG = "Quiz.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        db = loadQuizzes.getInstance(this).getWritableDatabase();
        chosenStateName = findViewById(R.id.chosenStateName);
        radioGroup = findViewById(R.id.radioGroup);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        nextButtion = findViewById(R.id.nextButton);
        quizQuestions = new ArrayList<>();
        selectQuizzes();

        chosenStateName.setText(quizQuestions.get(0));
        choice1.setText(quizQuestions.get(1));
        choice2.setText(quizQuestions.get(2));
        choice3.setText(quizQuestions.get(3));

        nextButtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioGroup.getCheckedRadioButtonId() == -1){
                    //no buttons have been selected
                }else{
                    if(pushes < 6){
                        ++pushes;
                        chosenStateName.setText(quizQuestions.get(track));
                        choice1.setText(quizQuestions.get(++track));
                        choice2.setText(quizQuestions.get(++track));
                        choice3.setText(quizQuestions.get(++track));
                    }else{
                        //end quiz
                    }

                }
            }
        });

    }




    public void selectQuizzes() {

        Cursor cursor = null;

        try{
            cursor = db.query(loadQuizzes.TABLE_QUIZZES, grabAllColumns,
                    null, null, null, null, null);
            int max = 50, min = 1, counter = 0;
            Random rand = new Random();
            int[] values = new int[6]; //this will hold the random generated ids
            for(int i = 0; i < 6; i++){
                values[i] = rand.nextInt((max-min) + 1) + min;
            }
            String state1, state2, state3, state4, state5, state6;
            String cap1, cap2, cap3, cap4, cap5, cap6;
            String sec1, sec2, sec3, sec4, sec5, sec6;
            String thir1, thir2, thir3, thir4, thir5, thir6;

            if(cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    long inc = cursor.getLong(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_INC));
                    if((inc == values[0]) || (inc == values[1] )|| (inc == values[2]) || (inc == values[3])
                            || (inc == values[4]) || (inc == values[5])){
                        if(counter == 0){
                            state1 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_STATE));
                            cap1 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_CAPITAL));
                            sec1 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_ALT1));
                            thir1 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_ALT2));
                            quizQuestions.add(state1);
                            quizQuestions.add(cap1);
                            quizQuestions.add(sec1);
                            quizQuestions.add(thir1);
                        }else if (counter == 1){
                            state2 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_STATE));
                            cap2 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_CAPITAL));
                            sec2 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_ALT1));
                            thir2 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_ALT2));
                            quizQuestions.add(state2);
                            quizQuestions.add(cap2);
                            quizQuestions.add(sec2);
                            quizQuestions.add(thir2);
                        }else if (counter == 2){
                            state3 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_STATE));
                            cap3 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_CAPITAL));
                            sec3 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_ALT1));
                            thir3 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_ALT2));
                            quizQuestions.add(state3);
                            quizQuestions.add(cap3);
                            quizQuestions.add(sec3);
                            quizQuestions.add(thir3);
                        }else if (counter == 3){
                            state4 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_STATE));
                            cap4 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_CAPITAL));
                            sec4 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_ALT1));
                            thir4 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_ALT2));
                            quizQuestions.add(state4);
                            quizQuestions.add(cap4);
                            quizQuestions.add(sec4);
                            quizQuestions.add(thir4);
                        }else if (counter == 4){
                            state5 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_STATE));
                            cap5 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_CAPITAL));
                            sec5 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_ALT1));
                            thir5 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_ALT2));
                            quizQuestions.add(state5);
                            quizQuestions.add(cap5);
                            quizQuestions.add(sec5);
                            quizQuestions.add(thir5);
                        }else if (counter == 5){
                            state6 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_STATE));
                            cap6 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_CAPITAL));
                            sec6 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_ALT1));
                            thir6 = cursor.getString(cursor.getColumnIndex(loadQuizzes.QUIZZES_COLUMN_ALT2));
                            quizQuestions.add(state6);
                            quizQuestions.add(cap6);
                            quizQuestions.add(sec6);
                            quizQuestions.add(thir6);
                        }//end else if
                        ++counter;
                    }//top iff

                }
            }
            Log.d(DEBUG_TAG, "Number of quiz questions: " + cursor.getCount());
        }catch(Exception e){
            Log.d(DEBUG_TAG, "Exception trace: " + e);
        }finally{
            if(cursor != null){
                cursor.close();
            }
        }
    }
}