package com.dlucci.tictactoe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

class MainAdapter extends RecyclerView.Adapter<MainViewHolder> implements UpdateView {

    private static final String TAG = "MainAdapter";

    char[] values;

    MainView mainView;

    MainPresenter presenter;

    int numOfX = 0;

    int numOfO = 0;

    public MainAdapter(MainView mainView, MainPresenter presenter) {

        setValues();

        this.mainView = mainView;

        this.presenter = presenter;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new MainViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_row, viewGroup, false), this, mainView.getCurrentImage());
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return values.length;
    }

    @Override
    public void updateItem(int position, int id, Context context) {
        if(id == R.drawable.circle) {
            values[position] = 'o';
            numOfO++;
            mainView.nextTurn(R.drawable.ex);
        }else {
            values[position] = 'x';
            numOfX++;
            mainView.nextTurn(R.drawable.circle);
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


    public void setValues() {

        values = new char[]{' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' '};

        numOfO = 0;
        numOfX = 0;

    }
}
