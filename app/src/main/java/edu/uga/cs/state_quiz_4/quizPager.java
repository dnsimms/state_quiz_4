package edu.uga.cs.state_quiz_4;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class quizPager extends AppCompatActivity {
    private ViewPager vp;
    private questionFragmentCollectionAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_pager);

        vp = findViewById(R.id.pager);
        adapter = new questionFragmentCollectionAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
    }
}
