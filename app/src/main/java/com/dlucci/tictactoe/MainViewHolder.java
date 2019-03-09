package com.dlucci.tictactoe;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView imageView;
    public UpdateView updateView;
    public ImageView currentTurn;

    public MainViewHolder(@NonNull View itemView, UpdateView updateView, ImageView currentTurn) {
        super(itemView);

        imageView = itemView.findViewById(R.id.image);
        this.updateView = updateView;
        this.itemView.setOnClickListener(this);

        this.currentTurn = currentTurn;
    }

    @Override
    public void onClick(View view) {
        if(currentTurn.getTag().equals(R.drawable.circle)) {
            imageView.setImageResource(R.drawable.circle);
            updateView.updateItem(getAdapterPosition(), R.drawable.circle, view.getContext());
        } else {
            imageView.setImageResource(R.drawable.ex);
            updateView.updateItem(getAdapterPosition(), R.drawable.ex, view.getContext());
        }

        itemView.setOnClickListener(null);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        itemView.setOnClickListener(null);
    }
}
