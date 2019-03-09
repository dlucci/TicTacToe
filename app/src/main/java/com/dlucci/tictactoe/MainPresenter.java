package com.dlucci.tictactoe;

import android.content.Context;

class MainPresenter {

    private static final String TAG = "MainPresenter";

    public static final int LENGTH = 4;

    MainView mainView;

    public MainPresenter(MainView mainView) {

        this.mainView = mainView;
    }


    /**
     * 1. Check if there is a tie
     * 2. Check every element  from beginning to beginning + LENGTH to see if a row has been conquered
     * 3. Check every LENGTH element to see if a column has been conquered
     * 4. Check value at 0, LENGTH-1, LENGTH*(LENGTH-1), and (LENGTH^2)-1 to see if corners are conquered
     * 5. Check neighbors of current position to see if a box can be made
     *
     */
    public boolean solution(int x, int numOfO, int numOfX, Context context, MainAdapter mainAdapter, char[] values) {

        if(numOfX + numOfO == (LENGTH*LENGTH)){
            mainAdapter.buildAndShowAlert("This Game is a tie!", context);
            return false;
        }


        char start = values[x];
        if (start == ' ')
            return false;

        int total = 0;

        int position = x - (x % 4);
        for (int i = position; i < position + LENGTH; i++) {
            if (start == values[i]) {
                total++;
                continue;
            } else
                break;
        }
        if (total == LENGTH)
            return  true;

        total = 0;
        position = x % LENGTH;

        //column
        for (int i = position; i < LENGTH * LENGTH; i += LENGTH) {
            if (start == values[i]) {
                total++;
                continue;
            } else
                break;
        }

        if (total == LENGTH)
            return true;

        //corners
        if (isACorner(x)) {
            if (values[0] == start && values[LENGTH - 1] == start
                    && values[LENGTH * (LENGTH - 1)] == start && values[(LENGTH * LENGTH) - 1] == start) {
                return true;
            }
        }

        total = 0;
        if(x%(LENGTH+1) == 0) {
            for (int i = 0; i < (LENGTH * LENGTH); i += (LENGTH + 1)) {
                if (values[i] == start) {
                    total++;
                    continue;
                } else {
                    break;
                }
            }

            if (total == LENGTH) {
                return true;
            }
        } else if (x%(LENGTH-1) == 0) {
            total = 0;
            for(int i = (LENGTH-1); i < ((LENGTH*LENGTH)-1); i += (LENGTH-1)){
                if(values[i] == start){
                    total++;
                    continue;
                } else {
                    break;
                }
            }

            if(total == LENGTH) {
                return true;
            }
        }

        //box
        return box(x, start, values);
    }

    private boolean box(int x, char start, char[] values) {
        //4 iterations
        if(x%(LENGTH) != 1 && x >= LENGTH && (x+1) < (LENGTH*LENGTH)) {
            if (values[x] == start && values[x + 1] == start && values[x - LENGTH] == start)
                return true;

        }
        if (x%(LENGTH) != 1 && x < (LENGTH*(LENGTH-1))) {
            if (values[x + 1] == start && values[x + LENGTH + 1] == start && values[x+LENGTH] == start)
                return true;
        }
        if (x < (LENGTH*(LENGTH-1)) && x%LENGTH != 0) {
            if (values[x + LENGTH] == start && values[x + (LENGTH - 1)] == start && values[x-1] == start)
                return true;
        }
        if (x >= LENGTH ) {
            if (values[x-1] == start && values[x - (LENGTH+1)] == start && values[x - LENGTH] == start)
                return true;
        }

        return false;
    }

    private static boolean isACorner(int x) {
        if (x == 0 || x == (LENGTH - 1) || x == (LENGTH * (LENGTH - 1)) || x == ((LENGTH * LENGTH) - 1)) {
            return true;
        }
        return false;
    }


    public void firstTurn() {

        int seed = Double.valueOf(Math.random()*10).intValue();
        if(seed%2 == 0)
            mainView.nextTurn(R.drawable.circle);
        else
            mainView.nextTurn(R.drawable.ex);
    }
}
