package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvTurnX;
    TextView tvTurnO;
    Button[] button = new Button[9];
    boolean turnXActive;
    Dialog dialogGameOver;
    Dialog dialogWin;
    int count = 9;
    Button btnRestart;
    Button btnPlayAgain;
    TextView tvMessage;
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
        tvTurnX = findViewById(R.id.tvTurnX);
        tvTurnO = findViewById(R.id.tvTurnO);

        button[0] = findViewById(R.id.btn1);
        button[1] = findViewById(R.id.btn2);
        button[2] = findViewById(R.id.btn3);
        button[3] = findViewById(R.id.btn4);
        button[4] = findViewById(R.id.btn5);
        button[5] = findViewById(R.id.btn6);
        button[6] = findViewById(R.id.btn7);
        button[7] = findViewById(R.id.btn8);
        button[8] = findViewById(R.id.btn9);

        turnXActive = true;
        tvTurnX.setVisibility(View.VISIBLE);
        tvTurnO.setVisibility(View.GONE);

        dialogGameOver = new Dialog(this);
        dialogGameOver.setContentView(R.layout.layout_dialog_game_over);
        dialogGameOver.setCancelable(false);

        dialogWin = new Dialog(this);
        dialogWin.setContentView(R.layout.layout_dialog_win);
        dialogWin.setCancelable(false);

        Window windowGameOver = dialogGameOver.getWindow();
        if (windowGameOver != null) {
            WindowManager.LayoutParams params = windowGameOver.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            windowGameOver.setAttributes(params);
        }

        Window windowWin = dialogWin.getWindow();
        if (windowWin != null) {
            WindowManager.LayoutParams params = windowWin.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.TOP;
            windowWin.setAttributes(params);
        }

        btnRestart = dialogGameOver.findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(v -> {
            count = 9;
            turnXActive = true;
            tvTurnX.setVisibility(View.VISIBLE);
            tvTurnO.setVisibility(View.GONE);
            for (Button value: button) {
                value.setText("");
            }
            dialogGameOver.dismiss();
        });

        btnPlayAgain = dialogWin.findViewById(R.id.btnPlayAgain);
        tvMessage = dialogWin.findViewById(R.id.tvMessage);

        btnPlayAgain.setOnClickListener(v -> {
            count = 9;
            turnXActive = true;
            tvTurnX.setVisibility(View.VISIBLE);
            tvTurnO.setVisibility(View.GONE);
            for (Button value: button) {
                value.setText("");
            }
            dialogWin.dismiss();
        });
        for (Button value : button) {
            value.setOnClickListener(this);
        }


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().equals("")) {
            return;
        }
        if (turnXActive) {
            ((Button)v).setText("X");
            count--;
            turnXActive = false;
            tvTurnX.setVisibility(View.GONE);
            tvTurnO.setVisibility(View.VISIBLE);
        } else {
            ((Button)v).setText("O");
            count--;
            turnXActive = true;
            tvTurnO.setVisibility(View.GONE);
            tvTurnX.setVisibility(View.VISIBLE);
        }

        if (count <= 0) {
            dialogGameOver.show();
        }

        if (win() == 'X') {
            tvMessage.setText("Player X won!");
            dialogWin.show();
        }
        if (win() == 'O') {
            tvMessage.setText("Player O won!");
            dialogWin.show();
        }
    }
    public char win() {
        if ((button[0].getText().equals("X") && button[1].getText().equals("X") && button[2].getText().equals("X"))
                || (button[3].getText().equals("X") && button[4].getText().equals("X") && button[5].getText().equals("X"))
                || (button[6].getText().equals("X") && button[7].getText().equals("X") && button[8].getText().equals("X"))
                || (button[0].getText().equals("X") && button[3].getText().equals("X") && button[6].getText().equals("X"))
                || (button[1].getText().equals("X") && button[4].getText().equals("X") && button[7].getText().equals("X"))
                || (button[2].getText().equals("X") && button[5].getText().equals("X") && button[8].getText().equals("X"))
                || (button[0].getText().equals("X") && button[4].getText().equals("X") && button[8].getText().equals("X"))
                || (button[2].getText().equals("X") && button[4].getText().equals("X") && button[6].getText().equals("X"))) {
            return 'X';
        }
        if ((button[0].getText().equals("O") && button[1].getText().equals("O") && button[2].getText().equals("O"))
                || (button[3].getText().equals("O") && button[4].getText().equals("O") && button[5].getText().equals("O"))
                || (button[6].getText().equals("O") && button[7].getText().equals("O") && button[8].getText().equals("O"))
                || (button[0].getText().equals("O") && button[3].getText().equals("O") && button[6].getText().equals("O"))
                || (button[1].getText().equals("O") && button[4].getText().equals("O") && button[7].getText().equals("O"))
                || (button[2].getText().equals("O") && button[5].getText().equals("O") && button[8].getText().equals("O"))
                || (button[0].getText().equals("O") && button[4].getText().equals("O") && button[8].getText().equals("O"))
                || (button[2].getText().equals("O") && button[4].getText().equals("O") && button[6].getText().equals("O"))) {
            return 'O';
        }
        return 'c';
    }
}