package com.app.plan_lector.fragment.parent;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.plan_lector.R;

/**
 * Created by Gabriela Mejia on 30/10/2016.
 */
public class MyAccountParent extends Fragment implements View.OnClickListener {
    Activity context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.account_teacher, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        context = super.getActivity();
        init();
    }



    private void init() {

    }

    @Override
    public void onClick(View v) {

    }


}
