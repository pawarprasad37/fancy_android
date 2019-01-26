package com.example.android.android_me.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BodyPartFragment extends Fragment implements View.OnClickListener {
    private static final String RES_ID_LIST = "RES_ID_LIST";
    private static final String SELECTED_INDEX = "SELECTED_INDEX";

    private List<Integer> resIdList;
    private int selectedIndex;

    public BodyPartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            resIdList = savedInstanceState.getIntegerArrayList(RES_ID_LIST);
            selectedIndex = savedInstanceState.getInt(SELECTED_INDEX);
        }

        ImageView rootView = (ImageView) inflater.inflate(R.layout.fragment_body_part, null);
        if (resIdList != null) {
            rootView.setImageResource(resIdList.get(selectedIndex));
        }
        rootView.setOnClickListener(this);
        return rootView;
    }

    public BodyPartFragment setResIdList(List<Integer> resIdList) {
        this.resIdList = resIdList;
        return this;
    }

    public BodyPartFragment setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        return this;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() != R.id.iv_body_part ||
                !(view instanceof ImageView)) {
            return;
        }
        selectedIndex++;
        if (selectedIndex == resIdList.size()) {
            selectedIndex = 0;
        }
        ((ImageView) view).setImageResource(resIdList.get(selectedIndex));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(RES_ID_LIST, (ArrayList<Integer>) resIdList);
        outState.putInt(SELECTED_INDEX, selectedIndex);
    }
}
