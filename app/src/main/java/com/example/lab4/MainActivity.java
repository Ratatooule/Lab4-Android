package com.example.lab4;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FrameLayout fragmentSlot;

    private int status = 1;
    private final int SUM_FRAGMENT = 1;
    private final int BTN_FRAGMENT = 2;
    private ArrayList<HistoryItem> history;
    public static final String HISTORY_KEY = "history";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        history = new ArrayList<>();

        fragmentSlot = findViewById(R.id.fragment_place);

        if (fragmentSlot != null) {
            changeToSumFragment();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(HISTORY_KEY, history);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(HISTORY_KEY))
            history = savedInstanceState.getParcelableArrayList(HISTORY_KEY);
    }

    private void changeFragment(android.app.Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fragment);
        fragmentTransaction.commit();
    }

    private void changeToBtnFragment() {
        changeFragment(SecondFragment.newInstance());
        status = BTN_FRAGMENT;
    }

    private void changeToSumFragment() {
        changeFragment(FirstFragment.newInstance());
        status = SUM_FRAGMENT;
    }

    public void switchFragment(View view) {
        if (status == SUM_FRAGMENT)
            changeToBtnFragment();
        else if (status == BTN_FRAGMENT)
            changeToSumFragment();
    }

    public void switchActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void addToHistory(HistoryItem newItem) {
        history.add(newItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.history_open) {
            Intent intent = new Intent(this, HistoryActivity.class);
            intent.putParcelableArrayListExtra(HISTORY_KEY, history);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}