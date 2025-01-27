package com.rupam.tictactoegame2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    //player representation
    //0-X
    //1-O

    int activeplayer = 0;
    int [] gamestate = {2,2,2,2,2,2,2,2,2};

    //    state meaning
    //    0 - X
    //    1 - O
    //     2 - NULL

    int [][] WiningState = {{0,1,2}, {3,4,5}, {6,7,8},
                            {0,3,6}, { 1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6}};
    public void TapBox(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gamestate[tappedImage] == 2) {
            gamestate[tappedImage] = activeplayer;
            img.setTranslationX(-1000f);
            if (activeplayer == 0) {
                img.setImageResource(R.drawable.x);
                activeplayer = 1;
                TextView Status = findViewById(R.id.Status);
                Status.setText("O's Turn - tap to Play");
            } else {
                img.setImageResource(R.drawable.o);
                activeplayer = 0;
                TextView Status = findViewById(R.id.Status);
                Status.setText("X's Turn - tap to Play");
            }
            img.animate().translationXBy(1000f).setDuration(300);
        }
        //chech if any player is won
        for(int[] WiningState: WiningState){
            if(gamestate[WiningState[0]] == gamestate[WiningState[1]]
                    && gamestate[WiningState[1]] == gamestate[WiningState[2]]
                    && gamestate[WiningState[0]] != 2) {
                //somebody has won! - Find out who!
                String winnerstr;
                gameActive = false;
                if (gamestate[WiningState[0]] == 0){
                    winnerstr = "X has won";
                }
                else{
                    winnerstr = "O has won";
                }
                //update the status bar foe winner annoucement
                TextView Status = findViewById(R.id.Status);
                Status.setText(winnerstr);
            }
        }
    }
    public void gameReset(View view){
        gameActive = true;
        activeplayer = 0;
        for(int i = 0; i< gamestate.length; i++){
            gamestate[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView Status = findViewById(R.id.Status);
        Status.setText("X's Turn - tap to Play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}