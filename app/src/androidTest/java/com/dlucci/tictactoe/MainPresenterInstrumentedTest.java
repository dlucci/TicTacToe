package com.dlucci.tictactoe;

import androidx.test.runner.AndroidJUnit4;
import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainPresenterInstrumentedTest {

    @Mock
    MainView mainView;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void test_firstTurn() {
        MainPresenter presenter = new MainPresenter(mainView);
        presenter.firstTurn();

        ImageView imageView = new ImageView(ApplicationProvider.getApplicationContext());
        imageView.setImageDrawable(ApplicationProvider.getApplicationContext().getDrawable(R.drawable.circle));
        Mockito.when(presenter.mainView.getCurrentImage()).thenReturn(imageView);

        int id = (Integer) presenter.mainView.getCurrentImage().getTag();

        int circle = R.drawable.circle;
        int ex = R.drawable.ex;

        assertTrue(id == circle || id == ex);

    }

}
