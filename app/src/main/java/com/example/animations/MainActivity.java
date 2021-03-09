package com.example.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Use of boolean restricts the game to 2 players.
    // Use integers to extend beyond 2.

    //0 == red
    //1 == yellow
    //2 == empty
    int activePlayer = 0;

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningConditions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameActive = true;

    @SuppressLint("SetTextI18n")
    public void drop(View view) {

        ImageView counter = (ImageView) view;

        //tappedCounter contains the value of which grid_location is tapped

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        //this will check if the space is empty or not
        // and prevent overlapping of counters

        if(gameState[tappedCounter] == 2 && gameActive) {

            //this will assign the grid_location to the activePlayer
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);

                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.yellow);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(1800).setDuration(600);

            for (int[] winningPosition : winningConditions) {

                //If condition to check if in each case of winningCondition,
                // do all the winningPosition have same value of activePlayer

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {

                    gameActive = false;

                    // Winner will be declared here
                    String winner;

                    if (activePlayer == 0) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    Button playAgain = (Button)findViewById(R.id.playAgain);
                    TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won !!");

                    playAgain.animate().translationXBy(1000).setDuration(1500);
                    winnerTextView.animate().translationXBy(1000).setDuration(1500);

                }
            }
        }
    }

    public void clear(View view) {

        // reset gameState
        Arrays.fill(gameState, 2);

        activePlayer = 0;
        gameActive = true;

        //resetting Game Board

        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);

        for (int i = 0 ; i < gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView)gridLayout.getChildAt(i);

            counter.setImageDrawable(null);
        }

        // resetting Game Arena
        Button playAgain = (Button)findViewById(R.id.playAgain);
        TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);

        winnerTextView.animate().translationXBy(-1000);
        playAgain.animate().translationXBy(-1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playAgain = (Button)findViewById(R.id.playAgain);
        TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);

        winnerTextView.setX(-1000);
        playAgain.setX(-1000);
    }
}