package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 -> yellow, 1 -> red, 2 -> empty
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    boolean gameActive = true;
    int[][] winnningPositions ={ {0,1,2} , {3,4,5} , {6,7,8},
                                 {0,3,6} , {1,4,7} , {2,5,8},
                                 {0,4,8} , {2,4,6}};

    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;
        int counterTag =  Integer.parseInt(counter.getTag().toString());

        TextView textView = (TextView) findViewById(R.id.winnerTextView);
        Button button = (Button) findViewById(R.id.button);

        if (gameState[counterTag] == 2 && gameActive == true)
        {
        gameState[counterTag] = activePlayer;
        counter.setTranslationY(-1500);

        if (activePlayer == 0)
        {
            counter.setImageResource(R.drawable.yellow);
            counter.animate().translationYBy(1500).rotationYBy(360).setDuration(600);
            activePlayer = 1;

            for (int i=0;i<8;i++)
            {

                if(gameState[winnningPositions[i][0]] == 0)
                {
                    if (gameState[winnningPositions[i][1]] == 0)
                    {
                        if (gameState[winnningPositions[i][2]] == 0)
                        {
                            Toast.makeText(this, "yellow win", Toast.LENGTH_SHORT).show();
                            gameActive = false;
                            textView.setText("yellow win");
                            textView.setVisibility(View.VISIBLE);
                            button.setVisibility(View.VISIBLE);


                        }
                    }
                }
            }
        }
        else if (activePlayer == 1)
        {
            counter.setImageResource(R.drawable.red);
            counter.animate().translationYBy(1500).rotationYBy(360).setDuration(600);
            activePlayer = 0;


            for (int i=0;i<8;i++)
            {

                if(gameState[winnningPositions[i][0]] == 1)
                {
                    if (gameState[winnningPositions[i][1]] == 1)
                    {
                        if (gameState[winnningPositions[i][2]] == 1)
                        {
                            Toast.makeText(this, "Red win", Toast.LENGTH_SHORT).show();
                            gameActive = false;
                            textView.setText("Red win");
                            textView.setVisibility(View.VISIBLE);
                            button.setVisibility(View.VISIBLE);

                        }
                    }
                }
            }
        }

    }
    }
    public void playAgain(View view){
        TextView textView = (TextView) findViewById(R.id.winnerTextView);
        Button button = (Button) findViewById(R.id.button);

        button.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);

        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<grid.getChildCount(); i++) {
            ImageView counter = (ImageView) grid.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for (int i=0; i<gameState.length;i++){
            gameState[i] = 2;
        }

        gameActive = true;
        activePlayer = 0;



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}