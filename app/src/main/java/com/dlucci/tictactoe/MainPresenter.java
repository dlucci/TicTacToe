package com.dlucci.tictactoe;

import android.content.Context;
import android.support.annotation.VisibleForTesting;

class MainPresenter {

    private static final String TAG = "MainPresenter";

    public static final int LENGTH = 4;

    MainView mainView;

    @VisibleForTesting
    MainPresenter() { }

    public MainPresenter(MainView mainView) {

        this.mainView = mainView;
    }

    boolean solution(int x, int numOfO, int numOfX, Context context, MainAdapter mainAdapter, char[] values) {

        //Check if there is a tie
        if (checkIfThereIsATie(numOfX, numOfO)) {
            mainAdapter.buildAndShowAlert("This Game is a tie!", context);
            return false;
        }


        char start = values[x];
        if (checkIfEmpty(start))
            return false;

        if(checkRow(x, start, values))
            return true;

        if(checkColumn(x, start, values))
            return true;

        //Check value at 0, LENGTH-1, LENGTH*(LENGTH-1), and (LENGTH^2)-1 to see if corners are conquered
        if (isACorner(x) && checkCorners(start, values)) {
            return true;
        }

        if(checkDiagonalToRight(x, start, values)){
            return true;
        }

        if(checkDiagonalToLeft(x, start, values)){
            return true;
        }

        //Check neighbors of current position to see if a box can be made
//        if(box(x, start, values)){
//            return true;
//        }

        return false;
    }

    public boolean box(int x, char start, char[] values) {
        //4 iterations
        if (x % (LENGTH) != 1 && x >= LENGTH && (x + 1) < (LENGTH * LENGTH)) {
            if (values[x] == start && values[x + 1] == start && values[x - LENGTH] == start)
                return true;

        }
        if (x % (LENGTH) != 1 && x < (LENGTH * (LENGTH - 1))) {
            if (values[x + 1] == start && values[x + LENGTH + 1] == start && values[x + LENGTH] == start)
                return true;
        }
        if (x < (LENGTH * (LENGTH - 1)) && x % LENGTH != 0) {
            if (values[x + LENGTH] == start && values[x + (LENGTH - 1)] == start && values[x - 1] == start)
                return true;
        }
        if (x >= LENGTH) {
            if (values[x - 1] == start && values[x - (LENGTH + 1)] == start && values[x - LENGTH] == start)
                return true;
        }

        return false;
    }

    public boolean isACorner(int x) {
        if (x == 0 || x == (LENGTH - 1) || x == (LENGTH * (LENGTH - 1)) || x == ((LENGTH * LENGTH) - 1)) {
            return true;
        }
        return false;
    }


    public void firstTurn() {

        int seed = Double.valueOf(Math.random() * 10).intValue();
        if (seed % 2 == 0)
            mainView.nextTurn(R.drawable.circle);
        else
            mainView.nextTurn(R.drawable.ex);
    }

    private boolean checkIfThereIsATie(int numOfX, int numOfO) {
        return (numOfX * numOfO) == (LENGTH * LENGTH);
    }

    private boolean checkIfEmpty(char value) {
        return value == ' ';
    }

    private boolean checkRow(int x, char start, char[] values) {
        int total = 0;

        int position = x - (x % 4);
        for (int i = position; i < position + LENGTH; i++) {
            if (start == values[i]) {
                total++;
                continue;
            } else
                return false;
        }

        return total == LENGTH;
    }

    private boolean checkColumn(int x, char start, char[] values) {

        int total = 0;
        int position = x % LENGTH;

        //Check every LENGTH element to see if a column has been conquered
        for (int i = position; i < LENGTH * LENGTH; i += LENGTH) {
            if (start == values[i]) {
                total++;
                continue;
            } else
                return false;
        }

        return total == LENGTH;
    }

    public boolean checkCorners(char start, char[] values) {
        return (values[0] == start && values[LENGTH - 1] == start
                && values[LENGTH * (LENGTH - 1)] == start && values[(LENGTH * LENGTH) - 1] == start);
    }

    public boolean checkDiagonalToRight(int x, char start, char[] values) {
        int total = 0;
        if (x % (LENGTH + 1) == 0) {
            for (int i = 0; i < (LENGTH * LENGTH); i += (LENGTH + 1)) {
                if (values[i] == start) {
                    total++;
                    continue;
                } else {
                    break;
                }
            }

            return total == LENGTH;
        }
        return false;
    }

    public boolean checkDiagonalToLeft(int x, char start, char[] values) {

        if (x % (LENGTH - 1) == 0) {
            int total = 0;
            for (int i = (LENGTH - 1); i < ((LENGTH * LENGTH) - 1); i += (LENGTH - 1)) {
                if (values[i] == start) {
                    total++;
                    continue;
                } else {
                    return false;
                }
            }

            return  total == LENGTH;
        }
        return false;
    }

    @VisibleForTesting
    public boolean isEmpty(char value){
        return checkIfEmpty(value);
    }

    @VisibleForTesting
    public boolean isTie(int numOfX, int numOfY) {
        return checkIfThereIsATie(numOfX, numOfY);
    }

    @VisibleForTesting
    public boolean isRowWinner(int x, char start, char[] values) {
        return checkRow(x, start, values);
    }

    @VisibleForTesting
    public boolean isColumnWinner(int x, char start, char[] values) {
        return checkColumn(x, start, values);
    }

    @VisibleForTesting
    public boolean isCorner(int x) {
        return isACorner(x);
    }
}
