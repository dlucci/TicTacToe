package com.dlucci.tictactoe;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements MainView, View.OnClickListener {

    RecyclerView recyclerView;

    MainAdapter adapter;

    ImageView currentTurn;

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
    public void nextTurn(int first) {
        currentTurn.setImageResource(first);
        currentTurn.setTag(first);
    }

    @Override
    public void onClick(View view) {
        newGame();
    }

    @Override
    public void newGame() {
        adapter.setValues();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        presenter.firstTurn();
    }

    @Override
    public ImageView getCurrentImage() {
        return currentTurn;
    }
}
