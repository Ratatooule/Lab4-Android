package com.example.lab4;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SecondFragment extends Fragment {

    public SecondFragment() {
    }

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
    }

    private boolean color = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //поиск view
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        final Button colorBtn = (Button) view.findViewById(R.id.color);

        colorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!color) {
                    v.setBackgroundColor(Color.GREEN);
                    color = true;
                } else {
                    v.setBackgroundColor(Color.LTGRAY);
                    color = false;
                }
            }
        });
        return view;
    }

}