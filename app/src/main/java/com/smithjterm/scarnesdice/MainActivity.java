package com.smithjterm.scarnesdice;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.stream.IntStream;

public class MainActivity extends Activity {

    private int userOverallScore = 0;
    private int userTurnScore = 0;
    private int computerOverallScore = 0;
    private int computerTurnScore = 0;
    public static final String TAG = "Main Activity";
    private Handler timerHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image = (ImageView) findViewById(R.id.diceView);
        image.setImageResource(R.drawable.dice2);

    }

    public void rollButton(View view) {
        Random rand = new Random();
        int randDice = rand.nextInt((6-1) + 1) + 1;
        TextView scoreDisplay = (TextView) findViewById(R.id.score_text);
        ImageView image = (ImageView) findViewById(R.id.diceView);

        //Log.i(TAG, "randDice= " + Integer.toString(randDice));

        switch(randDice) {
            case 1:
                //ResourcesCompat.getDrawable(getResources(), R.drawable.dice1, null);
                image.setImageResource(R.drawable.dice1);
                userTurnScore = 0;
                Log.i(TAG, "case: 1");
                break;

            case 2:
                image.setImageResource(R.drawable.dice2);
                userTurnScore += 2;
                break;

            case 3:
                image.setImageResource(R.drawable.dice3);
                userTurnScore += 3;
                break;

            case 4:
                image.setImageResource(R.drawable.dice4);
                userTurnScore += 4;
                break;

            case 5:
                image.setImageResource(R.drawable.dice5);
                userTurnScore += 5;
                break;

            case 6:
                image.setImageResource(R.drawable.dice6);
                userTurnScore += 6;
                break;

            default: break;
        }

        scoreDisplay.setText("Your score: " + userOverallScore + " Computer score:" +
                computerOverallScore + " your turn:" + userTurnScore);
    }

    public void resetButton(View view) {
        userOverallScore = 0;
        userTurnScore = 0;
        computerOverallScore = 0;
        computerTurnScore = 0;
        TextView scoreDisplay = (TextView) findViewById(R.id.score_text);
        scoreDisplay.setText("Your score: " + userOverallScore + " Computer score:" +
                computerOverallScore);
    }

    public void holdButton(View  view) {
        userOverallScore += userTurnScore;
        userTurnScore = 0;
        TextView scoreDisplay = (TextView) findViewById(R.id.score_text);
        scoreDisplay.setText("Your score: " + userOverallScore + " Computer score: "+computerOverallScore);
        computerTurn();
    }

    private void computerHoldButton () {
        computerOverallScore += computerTurnScore;
        computerTurnScore = 0;
        TextView scoreDisplay = (TextView) findViewById(R.id.score_text);
        scoreDisplay.setText("Your score: " + userOverallScore + " Computer score: "+computerOverallScore);
        Button rollButton = (Button) findViewById(R.id.roll_button);
        Button holdButton = (Button) findViewById(R.id.hold_button);
        rollButton.setEnabled(true);
        holdButton.setEnabled(true);
    }

    private void computerTurn (){
        Button rollButton = (Button) findViewById(R.id.roll_button);
        Button holdButton = (Button) findViewById(R.id.hold_button);
        rollButton.setEnabled(false);
        holdButton.setEnabled(false);


        Random rand = new Random();
        int randDice = rand.nextInt((6-1) + 1) + 1;
        TextView scoreDisplay = (TextView) findViewById(R.id.score_text);
        ImageView image = (ImageView) findViewById(R.id.diceView);

        //Log.i(TAG, "randDice= " + Integer.toString(randDice));



        switch(randDice) {
            case 1:
                //ResourcesCompat.getDrawable(getResources(), R.drawable.dice1, null);
                image.setImageResource(R.drawable.dice1);
                computerTurnScore = 0;
                Log.i(TAG, "case: 1");
                break;

            case 2:
                image.setImageResource(R.drawable.dice2);
                computerTurnScore += 2;
                break;

            case 3:
                image.setImageResource(R.drawable.dice3);
                computerTurnScore += 3;
                break;

            case 4:
                image.setImageResource(R.drawable.dice4);
                computerTurnScore += 4;
                break;

            case 5:
                image.setImageResource(R.drawable.dice5);
                computerTurnScore += 5;
                break;

            case 6:
                image.setImageResource(R.drawable.dice6);
                computerTurnScore += 6;
                break;

            default: break;
        }

        scoreDisplay.setText("Your score: " + userOverallScore + " Computer score:" +
                computerOverallScore + " your turn:" + userTurnScore);

        if (computerTurnScore < 20){
            timerHandler.postDelayed(new Runnable() {
                @Override
                    public void run() {
                        timerHandler.postDelayed(this, 500);
                        computerTurn();
                }
            }
            , 500);

        } else {
            computerHoldButton();

        }

    }
}
