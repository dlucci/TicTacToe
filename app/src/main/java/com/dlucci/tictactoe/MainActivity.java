package com.dlucci.tictactoe;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements MainView, View.OnClickListener {

    RecyclerView recyclerView;

    MainAdapter adapter;

    TextView currentTurn;

    Button newGame;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);

        currentTurn = findViewById(R.id.currentTurn);

        newGame = findViewById(R.id.newGame);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        presenter = new MainPresenter(this);

        adapter = new MainAdapter(this, presenter);

        presenter.firstTurn();

        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();

        newGame.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        newGame.setOnClickListener(null);
    }

    @Override
    public void nextTurn(char first) {
        currentTurn.setText(Character.toString(first));
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("currentTurn", Character.toString(first)).apply();
    }

    @Override
    public void onClick(View view) {
        newGame();
    }

    @Override
    public void newGame() {
        adapter.setValues(this);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        presenter.firstTurn();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
