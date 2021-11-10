package edu.uga.cs.state_quiz_4;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.InputStream;

public class loadQuizzes extends SQLiteOpenHelper {
    private static final String DEBUG_TAG = "loadQuizzes";
    private int results = 0, clicks = 0;
    private static final String DB_NAME = "quizzes.db";
    public SQLiteDatabase dataB;

    //First, we will declare our table/column names
    public static final String TABLE_QUIZZES = "quizzes";
    public static final String QUIZZES_COLUMN_INC = "inc";
    public static final String QUIZZES_COLUMN_STATE = "state";
    public static final String QUIZZES_COLUMN_CAPITAL = "captial";
    public static final String QUIZZES_COLUMN_ALT1 = "alt1";
    public static final String QUIZZES_COLUMN_ALT2 = "alt2";

    //Since we are using constants, we must generate our statement like this
    private static final String CREATE_TABLE =
            "create table " + TABLE_QUIZZES + " (" + QUIZZES_COLUMN_INC
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QUIZZES_COLUMN_STATE
            + " TEXT, " + QUIZZES_COLUMN_CAPITAL + " TEXT, " + QUIZZES_COLUMN_ALT1
            + " TEXT, " + QUIZZES_COLUMN_ALT2 + " TEXT" + ")";



    //Let's use the singleton way to refer to the instance
    //refers to the instance we made
    private static loadQuizzes quizInstance;

    //This will create our version 1 database
    private loadQuizzes(Context context){

        super(context, DB_NAME, null, 1);
        context.deleteDatabase(DB_NAME);
    }

    /**
     * This enables outside classes to access the instance of this class.
     * @param context
     * @return
     */
    public static synchronized loadQuizzes getInstance(Context context){
        if(quizInstance == null){
            quizInstance = new loadQuizzes(context.getApplicationContext());
        }
        return quizInstance;
    }


    //On create will bring the table to life
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists " + TABLE_QUIZZES);
        db.execSQL(CREATE_TABLE);
        Log.d(DEBUG_TAG, "Table " + TABLE_QUIZZES + " is here");
    }


    public void setResults(int res){
        if((res == 0) && results > 0){
            --results;
        }
        this.results += res;
        System.out.println(results);
    }

    public int getResults(){
        return results;
    }
    public void setClicks(int click){
        this.clicks +=click;
    }

    public int getClicks(){
        return clicks;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_QUIZZES);
        onCreate(db);
        Log.d(DEBUG_TAG, "Table " + TABLE_QUIZZES + " was upgraded");
    }


    //close database
    public void close(){
        if(quizInstance != null){
            quizInstance.close();
            Log.d(DEBUG_TAG, "Database is closed");
        }
    }
}
