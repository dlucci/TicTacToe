package com.dlucci.tictactoe;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView cell;
    public UpdateView updateView;

    public MainViewHolder(@NonNull View itemView, UpdateView updateView) {
        super(itemView);

        cell = itemView.findViewById(R.id.image);
        this.updateView = updateView;
        this.itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        char currentTurn = preferences.getString("currentTurn", " ").charAt(0);
        if(currentTurn == 'o') {
            cell.setText(Character.toString('o'));
            preferences.edit().putString("currentTurn", "x").apply();
            updateView.updateItem(getAdapterPosition(), 'o', view.getContext());
        } else {
            cell.setText(Character.toString('x'));
            preferences.edit().putString("currentTurn", "o").apply();
            updateView.updateItem(getAdapterPosition(), 'x', view.getContext());
        }

        itemView.setOnClickListener(null);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        itemView.setOnClickListener(null);
    }
}
