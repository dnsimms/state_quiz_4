package edu.uga.cs.state_quiz_4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class past_quizzes extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "past_quizzes";
    private static final String DB_NAME = "pastQuizzes.db";
    public SQLiteDatabase db;

    //First, we will declare our table/column names
    public static final String TABLE_PQUIZZES = "pastQuizzes";
    public static final String QUIZZES_COLUMN_INC = "inc";
    public static final String QUIZZES_COLUMN_STATE = "state";
    public static final String QUIZZES_COLUMN_CAPITAL = "captial";
    public static final String QUIZZES_COLUMN_ALT1 = "alt1";
    public static final String QUIZZES_COLUMN_ALT2 = "alt2";


    private past_quizzes(Context context){
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
