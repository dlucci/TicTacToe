package com.dlucci.tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MainPresenterTest {

    MainPresenter mainPresenter;

    @Mock
    MainView mainView;

    char[] winningRowTop = {
            'x', 'x', 'x', 'x',
            ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' '};

    char[] winningRowSecond = {
            ' ', ' ', ' ', ' ',
            'x', 'x', 'x', 'x',
            ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' '};

    char[] winningRowThird = {
            ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ',
            'x', 'x', 'x', 'x',
            ' ', ' ', ' ', ' '};

    char[] winningRowBottom = {
            ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ',
            'x', 'x', 'x', 'x'};

    char[] losingRowTop = {
            'x', 'o', 'x', 'x',
            ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' '};

    char[] losingRowSecond = {
            ' ', ' ', ' ', ' ',
            'x', 'o', 'x', 'x',
            ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' '};

    char[] losingRowThird = {
            ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ',
            'x', 'o', 'x', 'x',
            ' ', ' ', ' ', ' '};


    char[] losingRowBottom = {
            ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ',
            'x', 'o', 'x', 'x'};

    char[] winningColumnFirst = {
            'x', ' ', ' ', ' ',
            'x', ' ', ' ', ' ',
            'x', ' ', ' ', ' ',
            'x', ' ', ' ', ' '};

    char[] winningColumnSecond = {
            ' ', 'x', ' ', ' ',
            ' ', 'x', ' ', ' ',
            ' ', 'x', ' ', ' ',
            ' ', 'x', ' ', ' '};

    char[] winningColumnThird = {
            ' ', ' ', 'x', ' ',
            ' ', ' ', 'x', ' ',
            ' ', ' ', 'x', ' ',
            ' ', ' ', 'x', ' '};

    char[] winningColumnFourth = {
            ' ', ' ', ' ', 'x',
            ' ', ' ', ' ', 'x',
            ' ', ' ', ' ', 'x',
            ' ', ' ', ' ', 'x'};

    char[] losingColumnFirst = {
            'x', ' ', ' ', ' ',
            'x', ' ', ' ', ' ',
            'o', ' ', ' ', ' ',
            'x', ' ', ' ', ' '};

    char[] losingColumnSecond = {
            ' ', 'x', ' ', ' ',
            ' ', 'x', ' ', ' ',
            ' ', 'o', ' ', ' ',
            ' ', 'x', ' ', ' '};

    char[] losingColumnThird = {
            ' ', ' ', 'x', ' ',
            ' ', ' ', 'x', ' ',
            ' ', ' ', 'o', ' ',
            ' ', ' ', 'x', ' '};

    char[] losingColumnFourth = {
            ' ', ' ', ' ', 'x',
            ' ', ' ', ' ', 'x',
            ' ', ' ', ' ', 'o',
            ' ', ' ', ' ', 'x'};

    char[] winningCorners = {
            'x', ' ', ' ', 'x',
            ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ',
            'x', ' ', ' ', 'x'};

    char[] losingCorners = {
            'o', ' ', ' ', 'x',
            ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ',
            'x', ' ', ' ', 'x'};

    char[] winningDiagonalToRight = {
            'x', ' ', ' ', ' ',
            ' ', 'x', ' ', ' ',
            ' ', ' ', 'x', ' ',
            ' ', ' ', ' ', 'x'};

    char[] winningDiagonalToLeft = {
            ' ', ' ', ' ', 'x',
            ' ', ' ', 'x', ' ',
            ' ', 'x', ' ', ' ',
            'x', ' ', ' ', ' '};

    char[] winningBox = {
            'x', 'x', ' ', ' ',
            'x', 'x', 'x', ' ',
            ' ', 'x', 'x', 'x',
            ' ', ' ', 'x', 'x'};


    @Before
    public void setUp() {
        mainPresenter = new MainPresenter();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_mainPresenterConstructor() {
        MainPresenter presenter = new MainPresenter(mainView);
        assertNotNull(presenter);
        assertNotNull(presenter.mainView);
    }

    @Test
    public void test_CheckIfThereIsATie() {
        assertTrue(mainPresenter.isTie(4, 4));
        assertFalse(mainPresenter.isTie(3, 4));
        assertFalse(mainPresenter.isTie(4, 3));
    }

    @Test
    public void test_checkIfEmpty() {
        assertTrue(mainPresenter.isEmpty(' '));
        assertFalse(mainPresenter.isEmpty('f'));
    }

    @Test
    public void test_checkRow() {
        assertTrue(mainPresenter.isRowWinner(0, 'x', winningRowTop));
        assertTrue(mainPresenter.isRowWinner(4, 'x', winningRowSecond));
        assertTrue(mainPresenter.isRowWinner(8, 'x', winningRowThird));
        assertTrue(mainPresenter.isRowWinner(12, 'x', winningRowBottom));

        assertFalse(mainPresenter.isRowWinner(0, 'x', losingRowTop));
        assertFalse(mainPresenter.isRowWinner(4, 'x', losingRowSecond));
        assertFalse(mainPresenter.isRowWinner(8, 'x', losingRowThird));
        assertFalse(mainPresenter.isRowWinner(12, 'x', losingRowBottom));

        assertFalse(mainPresenter.isRowWinner(12, 'o', winningRowBottom));
    }

    @Test
    public void test_checkColumn() {
        assertTrue(mainPresenter.isColumnWinner(0, 'x', winningColumnFirst));
        assertTrue(mainPresenter.isColumnWinner(1, 'x', winningColumnSecond));
        assertTrue(mainPresenter.isColumnWinner(2, 'x', winningColumnThird));
        assertTrue(mainPresenter.isColumnWinner(3, 'x', winningColumnFourth));

        assertFalse(mainPresenter.isColumnWinner(0, 'x', losingColumnFirst));
        assertFalse(mainPresenter.isColumnWinner(1, 'x', losingColumnSecond));
        assertFalse(mainPresenter.isColumnWinner(2, 'x', losingColumnThird));
        assertFalse(mainPresenter.isColumnWinner(3, 'x', losingColumnFourth));

        assertFalse(mainPresenter.isColumnWinner(0, 'o', winningColumnFirst));
    }

    @Test
    public void test_isACorner() {
        assertTrue(mainPresenter.isCorner(0));
        assertFalse(mainPresenter.isCorner(1));
    }

    @Test
    public void test_checkCorners() {
        assertTrue(mainPresenter.checkCorners('x', winningCorners));
        assertFalse(mainPresenter.checkCorners('o', losingCorners));
    }

    @Test
    public void test_checkDiagonalToRight() {
        assertTrue(mainPresenter.checkDiagonalToRight(0, 'x', winningDiagonalToRight));
        assertFalse(mainPresenter.checkDiagonalToRight(0, 'x', winningColumnFirst));
        assertFalse(mainPresenter.checkDiagonalToRight(0, 'o', winningDiagonalToRight));
        assertFalse(mainPresenter.checkDiagonalToRight(0, 'x', winningBox));
    }

    @Test
    public void test_checkDiagonalToLeft() {
        assertTrue(mainPresenter.checkDiagonalToLeft(0, 'x', winningDiagonalToLeft));
        assertFalse(mainPresenter.checkDiagonalToLeft(0, 'x', winningColumnFirst));
        assertFalse(mainPresenter.checkDiagonalToLeft(0, 'o', winningDiagonalToLeft));
        assertFalse(mainPresenter.checkDiagonalToLeft(0, 'x', winningBox));
    }

    @Test
    public void test_box() {
        assertTrue(mainPresenter.box(15, 'x', winningBox));
        assertTrue(mainPresenter.box(6, 'x', winningBox));
        assertTrue(mainPresenter.box(9, 'x', winningBox));
        assertTrue(mainPresenter.box(10, 'x', winningBox));

        assertFalse(mainPresenter.box(10, 'o', winningBox));
    }

}