package com.dlucci.tictactoe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MainAdapter extends RecyclerView.Adapter<MainViewHolder> implements UpdateView {

    private static final String TAG = "MainAdapter";

    char[] values;

    MainView mainView;

    MainPresenter presenter;

    int numOfX = 0;

    int numOfO = 0;

    public MainAdapter(MainView mainView, MainPresenter presenter) {

        setValues(mainView.getContext());

        this.mainView = mainView;

        this.presenter = presenter;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new MainViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_row, viewGroup, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return values.length;
    }

    @Override
    public void updateItem(int position, char next, Context context) {
        if(next == 'o') {
            values[position] = 'o';
            numOfO++;
            mainView.nextTurn('x');
        }else {
            values[position] = 'x';
            numOfX++;
            mainView.nextTurn('o');
        }

        if(presenter.solution(position, numOfO, numOfX, context, this, values)){
            buildAndShowAlert(values[position] + " has won!", context);
        }
    }

    public void buildAndShowAlert(String msg, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mainView.newGame();
                        dialogInterface.dismiss();
                    }
                });

        builder.create().show();
    }


    public void setValues(Context context) {

        values = new char[]{
                ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' '};

        numOfO = 0;
        numOfX = 0;
        PreferenceManager.getDefaultSharedPreferences(context).edit().remove("currentTurn").apply();
    }
}
