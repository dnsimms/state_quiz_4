package edu.uga.cs.state_quiz_4;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.List;

//TODO DECIDE IF THIS IS NEEDED
/**
 * This class is here just in case but we might not need it anymore
 */
public class loadQuizzesHelper extends AppCompatActivity {

    private SQLiteDatabase db;
    private SQLiteOpenHelper loader;

    public loadQuizzesHelper(Context context){
        this.loader = loadQuizzes.getInstance(context);
    }

    public void open(){
        db = loader.getWritableDatabase();
    }

    public void close(){
        if(loader != null){
            loader.close();
            Log.d("Quiz Helper", "JobLeadsData: db closed");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

}
