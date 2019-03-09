package com.dlucci.tictactoe;

import android.widget.ImageView;

interface MainView {

    void nextTurn(int first);

    ImageView getCurrentImage();

    void newGame();
}
