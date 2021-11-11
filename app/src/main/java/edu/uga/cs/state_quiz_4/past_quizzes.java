package edu.uga.cs.state_quiz_4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class past_quizzes extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "past_quizzes";
    private static final String DB_NAME = "pastQuizzes.db";
    public SQLiteDatabase db;
    private static past_quizzes pastInstance;
    private loadQuizzes quizData = null;
    private int results = 0;


    //First, we will declare our table/column names
    public static final String TABLE_PQUIZZES = "pastQuizzes";
    public static final String PQUIZ_INC = "inc";
    public static final String PQUIZ_CORRECT = "correctAnswers";
    public static final String PQUIZ_DATE = "date";

    private static final String CREATE_TABLE =
            "create table " + TABLE_PQUIZZES + " (" + PQUIZ_INC
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PQUIZ_CORRECT
                    + " INTEGER, " + PQUIZ_DATE + " TEXT" + ")";


    private past_quizzes(Context context){

        super(context, DB_NAME, null, 1);
    }

    /**
     * This enables outside classes to access the instance of this class.
     * @param context
     * @return
     */
    public static synchronized past_quizzes getInstance(Context context){
        if(pastInstance == null){
            pastInstance = new past_quizzes(context.getApplicationContext());
        }
        return pastInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d(DEBUG_TAG, "Past Quizzes are here");
    }

    public void recieveResults(int res){
        results = res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_PQUIZZES);
        onCreate(db);
        Log.d(DEBUG_TAG, "Past Quiz table was upgraded");
    }
}
