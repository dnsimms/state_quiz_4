package edu.uga.cs.state_quiz_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private SQLiteOpenHelper point;
    private InputStream streamer = null;
    private CSVReader viewer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        point = loadQuizzes.getInstance(this);
        db = loadQuizzes.getInstance(this).getWritableDatabase();
        new populateBase().execute();
    }

    public class populateBase extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] arguments) {
            ContentValues values = new ContentValues();

            boolean isWorking = false;
            //reading csv
            try{
                String[] lines;
                String file = "";

                streamer = getAssets().open("state_capitals.csv");
                viewer = new CSVReader(new InputStreamReader(streamer));
                while((lines = viewer.readNext()) !=null){
                    //file = lines;
                    //int finder =0;
                    int count = 0;
                    String word = "";
                    for(int i = 0; i < lines.length; i++){
                        if(count == 0){
                            word = lines[i];
                            values.put(loadQuizzes.QUIZZES_COLUMN_STATE, word);
                        }else if(count == 1){
                            word = lines[i];
                            values.put(loadQuizzes.QUIZZES_COLUMN_CAPITAL, word);
                        }else if(count ==2){
                            word = lines[i];
                            values.put(loadQuizzes.QUIZZES_COLUMN_ALT1, word);
                        }else if(count == 3){
                            word = lines[i];
                            values.put(loadQuizzes.QUIZZES_COLUMN_ALT2, word);
                        }
                        if(count != 3){
                            ++count;
                        }else{
                            count = 0;
                        }
                    }

                }
                long result = db.insert(loadQuizzes.TABLE_QUIZZES, null, values);
                if(result == -1){
                    isWorking = false;
                }else{
                    isWorking = true;
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return isWorking;
        }

        @Override
        protected void onPostExecute(Object o) {

        }
    }

    /**
    public class dataWriter extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] arguments) {
            point.open();

            if(point.populateBase()){
                Log.d("Main", "New table made with data" );
            }else{
                Log.d("Main", "New table FAILED" );
            }
            return arguments;
        }

        @Override
        protected void onPostExecute(Object o) {

        }
    }**/
    @Override
    protected void onResume() {
        if(point != null){
            loadQuizzes.getInstance(this).open();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if(point != null){
            point.close();
        }
        super.onPause();
    }
}