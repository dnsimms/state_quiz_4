package edu.uga.cs.state_quiz_4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class questionFragmentCollectionAdapter extends FragmentStatePagerAdapter {
    public questionFragmentCollectionAdapter(FragmentManager fm){
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        quiz_questions_frag questionsFrag = new quiz_questions_frag(position);

        //TODO: You'll have to write code to check when the position is at 7
        //TODO: I would make the quiz buttons and textviews invisible at position 7 and make quiz result buttons and text views visible
        return questionsFrag;
    }

    @Override
    public int getCount() {
        return 6;
    }
}
