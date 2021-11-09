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
        System.out.println(quizQuestions.get(0));
        choice1.setText(quizQuestions.get(1));
        System.out.println(choice1.getText());
        choice2.setText(quizQuestions.get(2));
        System.out.println(choice2.getText());
        choice3.setText(quizQuestions.get(3));
        System.out.println(choice3.getText());

        nextButtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioGroup.getCheckedRadioButtonId() == -1){
                    //no buttons have been selected
                }else{
                    if(pushes < 7){
                        System.out.println(pushes);
                        ++pushes;
                        chosenStateName.setText(quizQuestions.get(track));
                        System.out.println(chosenStateName.getText());
                        ++track;
                        choice1.setText(quizQuestions.get(track));
                        System.out.println(choice1.getText());
                        ++track;
                        choice2.setText(quizQuestions.get(track));
                        System.out.println(choice2.getText());
                        ++track;
                        choice3.setText(quizQuestions.get(track));
                        System.out.println(choice3.getText());
                        ++track;
                    }else{
                        //end quiz
                    }

                }
            }
        });

    }


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
    }



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
            }


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
            Log.d(DEBUG_TAG, "Number of quiz questions: " + cursor.getCount());
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