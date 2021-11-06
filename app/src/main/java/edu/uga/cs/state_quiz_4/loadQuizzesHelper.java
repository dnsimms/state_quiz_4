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


public class loadQuizzesHelper extends AppCompatActivity {

    private SQLiteDatabase db;
    private SQLiteOpenHelper loader;
    private InputStream streamer = null;
    private CSVReader viewer = null;

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
        try{
            streamer = getAssets().open("state_capitals.csv");
        }catch(Exception e){
            e.printStackTrace();
        }

        viewer = new CSVReader(new InputStreamReader(streamer));
        populateBase();
    }

    public boolean populateBase(){

        ContentValues values = new ContentValues();

        boolean isWorking = false;
        //reading csv
        try{
            String[] lines;
            String file = "";

            while((lines = viewer.readNext()) !=null){
                //file = lines;
                //int finder =0;
                int count = 0;
                String word = "";
                for(int i = 0; i < lines.length; i++){
                    if(count == 0){
                        word = lines[i];
                        values.put(loadQuizzes.QUIZZES_COLUMN_CAPITAL, word);
                    }else if(count ==1){
                        word = lines[i];
                        values.put(loadQuizzes.QUIZZES_COLUMN_ALT1, word);
                    }else if(count == 2){
                        word = lines[i];
                        values.put(loadQuizzes.QUIZZES_COLUMN_ALT2, word);
                    }
                }

                /**
                while(finder != -1){
                    finder = file.indexOf(",");

                    if(count == 0){
                        word = file.substring(0, finder);
                        values.put(loadQuizzes.QUIZZES_COLUMN_CAPITAL, word);
                    }else if(count ==1){
                        word = file.substring(0, finder);
                        values.put(loadQuizzes.QUIZZES_COLUMN_ALT1, word);
                    }else if(count == 2){
                        word = file.substring(0, finder);
                        values.put(loadQuizzes.QUIZZES_COLUMN_ALT2, word);
                    }
                    file = file.substring(finder+1);
                    ++count;
                }**/
            long result = db.insert(loadQuizzes.TABLE_QUIZZES, null, values);
            if(result == -1){
                isWorking = false;
            }else{
                isWorking = true;
            }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isWorking;

    }
}
