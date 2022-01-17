package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int active_player = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean isGameActive = true;
    TextView textView, p1scr, p2scr;
    Button restartBtn;
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textview);
        restartBtn = findViewById(R.id.restartBtn);

        gridLayout = findViewById(R.id.gridLayout);

//        p1scr = findViewById(R.id.player1_score);
//        p2scr = findViewById(R.id.player2_score);

    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && isGameActive) {

            gameState[tappedCounter] = active_player;

//            counter.setTranslationY(-1500);

            if (active_player == 0) {
                textView.setText("O's Turn");
                counter.setImageResource(R.drawable.x);
                active_player = 1;
            } else {
                textView.setText("X's Turn");
                counter.setImageResource(R.drawable.o);
                active_player = 0;
            }

//            counter.animate().translationYBy(1500).rotation(3600).setDuration(1000);

            Log.i("clicked", "button");

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                    // someone won

                    String winner = "";

                    if (active_player == 1) {
                        winner = "X";
                    } else if (active_player == 0) {
                        winner = "O";
                    }

                    isGameActive = false;
                    restartBtn.setVisibility(View.VISIBLE);
                    textView.setText(winner + " won");
                    
//                    Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    public void restart(View view) {
        textView.setText("X's Turn");
        active_player = 0;
        isGameActive = true;
        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            // do stuff with child view
            child.setImageDrawable(null);
        }

        for(int i=0;i<9;i++) {
            gameState[i] = 2;
        }

    }
}