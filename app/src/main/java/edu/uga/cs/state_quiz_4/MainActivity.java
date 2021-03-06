package edu.uga.cs.state_quiz_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db, pastDB;
    private SQLiteOpenHelper point;
    private InputStream streamer = null;
    private CSVReader viewer = null;
    private Button start, viewRes;
    private TextView text1, text2;
    private int total = 0;
    private String date = "";
    private ConstraintLayout splashView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        db = loadQuizzes.getInstance(this).getWritableDatabase();
        pastDB = past_quizzes.getInstance(this).getWritableDatabase();

        start = findViewById(R.id.startQuiz);
        splashView = findViewById(R.id.splashView);
        viewRes = findViewById(R.id.viewResults);
        text1 = findViewById(R.id.textView1);
        text2 = findViewById(R.id.textView2);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.close();
                splashView.setVisibility(View.INVISIBLE);
                openQuiz();
            }
        });

        viewRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splashView.setVisibility(View.INVISIBLE);
                openPast();
            }
        });

        new populateBase().execute();
    }

    public void openPast(){
        Intent intent = new Intent(this, PastResultPage.class);
        startActivity(intent);
    }
    public void openQuiz() {
        Intent intent = new Intent(this, quizPager.class);
        startActivity(intent);
    }

    /**
     * Helper method to insert the csv data into the database
     * @param state state
     * @param capital capital
     * @param alt1 second city
     * @param alt2 third city
     * @return true is inserted correctly, false otherwise
     */
    public Boolean insertData(String state, String capital, String alt1, String alt2){
        ContentValues values = new ContentValues();
        values.put(loadQuizzes.QUIZZES_COLUMN_STATE, state);
        values.put(loadQuizzes.QUIZZES_COLUMN_CAPITAL, capital);
        values.put(loadQuizzes.QUIZZES_COLUMN_ALT1, alt1);
        values.put(loadQuizzes.QUIZZES_COLUMN_ALT2, alt2);
        long result = db.insert(loadQuizzes.TABLE_QUIZZES, null, values);
        if(result == -1){
            return false;
        }
            return true;
    }



    public class populateBase extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] arguments) {
            ContentValues values = new ContentValues();

            boolean isWorking = false;
            //reading csv
            try{
                String[] lines;

                streamer = getAssets().open("state_capitals.csv");
                //an external library method, must add a dependency to your gradle
                viewer = new CSVReader(new InputStreamReader(streamer));
                while((lines = viewer.readNext()) !=null){
                    int count = 0;
                    String state = "";
                    String capital = "";
                    String alt1 = "";
                    String alt2 = "";
                    for(int i = 0; i < lines.length; i++){
                        if(count == 0){
                            state = lines[i];
                        }else if(count == 1){
                            capital = lines[i];
                        }else if(count ==2){
                            alt1 = lines[i];
                        }else if(count == 3){
                            alt2 = lines[i];
                        }

                        if(count != 3){
                            ++count;
                        }else{
                            count = 0;
                            if(insertData(state, capital, alt1, alt2)){
                                isWorking = true;
                            }

                        }
                    }

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

    @Override
    protected void onResume() {
        if(db != null){
            loadQuizzes.getInstance(this).getWritableDatabase();
       }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if(db != null){
            db.close();
        }
        super.onPause();
    }
}