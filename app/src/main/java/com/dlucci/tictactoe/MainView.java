package com.dlucci.tictactoe;

import android.content.Context;

interface MainView {

    void nextTurn(char first);

    Context getContext();

    void newGame();
}
