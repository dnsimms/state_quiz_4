package edu.uga.cs.state_quiz_4;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class PastResultPage extends AppCompatActivity {

    private int results =0;
    private String date ="";
    private TableRow rows;
    private android.widget.TableRow.LayoutParams layParam;
    private static final String[] grabAll = {
            past_quizzes.PQUIZ_DATE,
            past_quizzes.PQUIZ_CORRECT
    };



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.past_quiz_layout);

        TableLayout table = findViewById(R.id.mainTable);
        layParam =
                new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
        layParam.setMargins(20, 0, 20, 0);
        rows = new TableRow(getBaseContext());
        getPastResults();
        table.addView(rows);

    }

    public void retResults(Cursor cursor, TextView v){
        date = cursor.getString(cursor.getColumnIndex(past_quizzes.PQUIZ_DATE));
        results = cursor.getInt(cursor.getColumnIndex(past_quizzes.PQUIZ_CORRECT));
        String newline = System.getProperty("line.separator");
        String message = "\nDate: " + date + " Score: " + results + newline;
        v.setText(message);
        rows.addView(v);
    }

    public void getPastResults(){
        Cursor cursor = null;

        try{
            cursor = past_quizzes.getInstance(this).getWritableDatabase().query(past_quizzes.TABLE_PQUIZZES, grabAll, null, null,
            null, null, null);
            if(cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    TextView text = new TextView(getBaseContext());
                    retResults(cursor, text);
                }
            }

        }catch(Exception e){

        }
    }


}
