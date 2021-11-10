package edu.uga.cs.state_quiz_4;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultPageHelper extends Fragment {

    private int results =0;

    public ResultPageHelper(){
        //results = results + getArguments().getInt("results");
        //System.out.println(results);
    }

    public void setResults(int res) {
        results += res;
    }

    public int getResults(){
        return results;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
