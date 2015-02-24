package me.qiufeng.www.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.qiufeng.www.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RedCardFragment extends Fragment {


    public RedCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_red_card_fragment, container, false);
    }


}
