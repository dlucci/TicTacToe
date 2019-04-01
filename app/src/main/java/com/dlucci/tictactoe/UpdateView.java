package com.dlucci.tictactoe;

import android.content.Context;

interface UpdateView {

    void updateItem(int position, char next, Context context);
}
