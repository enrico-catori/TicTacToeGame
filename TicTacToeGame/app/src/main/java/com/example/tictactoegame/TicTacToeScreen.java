package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToeScreen extends AppCompatActivity implements View.OnClickListener {
    private Button endButton;
    private TextView PlayerOne, PlayerTwo;
    private String namePlayerOne, namePlayerTwo;

    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button [] buttons = new Button[9];
    private Button resetGame;

    private int playerOneScoreCount, playerTwoScoreCount, rountCount;
    boolean activePlayer;

    int [] gameState = {2,2,2,2,2,2,2,2,2};   // Estado das posições

    int [][] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},   // Posições possíveis de vitória
            {0,4,8}, {2,4,6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_screen);

        PlayerOne = findViewById(R.id.jogadorUm);
        PlayerTwo = findViewById(R.id.jogadorDois);

        namePlayerOne = getIntent().getExtras().getString("Value1");
        namePlayerTwo = getIntent().getExtras().getString("Value2");

        // Trazendo os nomes dos jogadoras da outra activity
        PlayerOne.setText(namePlayerOne);
        PlayerTwo.setText(namePlayerTwo);

        playerOneScore = (TextView) findViewById(R.id.jogadorUmScore);
        playerTwoScore = (TextView) findViewById(R.id.jogadorDoisScore);
        playerStatus = (TextView) findViewById(R.id.Status);

        resetGame = (Button) findViewById(R.id.encerrarJogo);

        // Armazenando os botões no array 'buttons'
        for(int i=0; i < buttons.length; i++) {
            String buttonId = "btn_" + i;
            int resourceId = getResources().getIdentifier(buttonId, "id", getPackageName());
            buttons[i] = (Button) findViewById((resourceId));
            buttons[i].setOnClickListener(this);
        }

        rountCount = 0;
        playerOneScoreCount = 0;   // Iniciando os contadores
        playerTwoScoreCount = 0;
        activePlayer = true;

        // Ação de retornar pra activity inicial
        endButton = findViewById(R.id.encerrarJogo);
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")) {   // Não permite que o botão seja pressionado novamente
            return;
        }
        String buttonId = v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer = Integer.parseInt(buttonId.substring(buttonId.length()-1, buttonId.length()));

        // Setando os ícones para o jogador ativo
        if(activePlayer) {
            ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.parseColor("#e63946"));
            gameState[gameStatePointer] = 0;
        } else {
            ((Button) v).setText("O");
            ((Button) v).setTextColor(Color.parseColor("#1d3557"));
            gameState[gameStatePointer] = 1;
        }
        rountCount++;

        // Verifica o ganhador
        if(checkWinner()) {
            if(activePlayer) {
                playerOneScoreCount++;
                updatePlayerScore();
                String winner = namePlayerOne + " ganhou!";
                Toast.makeText(this, winner, Toast.LENGTH_SHORT).show();
                playAgain();
            } else {
                playerTwoScoreCount++;
                updatePlayerScore();
                String winner = namePlayerTwo + " ganhou!";
                Toast.makeText(this, winner, Toast.LENGTH_SHORT).show();
                playAgain();
            }
        } else if(rountCount == 9) {   // Empate
            playAgain();
            Toast.makeText(this, "Empatou", Toast.LENGTH_SHORT).show();
        } else {
            activePlayer = !activePlayer;   // Continua
        }
        // Verifica quem está ganhando
        if(playerOneScoreCount > playerTwoScoreCount) {
            String winning = namePlayerOne + " está ganhando!";
            playerStatus.setText(winning);
        } else if(playerTwoScoreCount > playerOneScoreCount) {
            String winning = namePlayerTwo + " está ganhando!";
            playerStatus.setText(winning);
        } else {
            playerStatus.setText("");
        }
    }

    // Verifica se há ganhador
    public boolean checkWinner() {
        boolean winnerResult = false;

        for(int [] winningPosion : winningPositions) {
            if(gameState[winningPosion[0]] == gameState[winningPosion[1]] &&
                    gameState[winningPosion[1]] == gameState[winningPosion[2]] &&
                        gameState[winningPosion[0]] != 2) {
                winnerResult = true;
            }
        }
        return winnerResult;
    }

    // Atualiza os scores
    public void updatePlayerScore() {
        playerOneScore.setText((Integer.toString(playerOneScoreCount)));
        playerTwoScore.setText((Integer.toString(playerTwoScoreCount)));
    }

    // Inicia um novo jogo
    public void playAgain() {
        rountCount = 0;
        activePlayer = true;

        for(int i=0; i < buttons.length; i++) {
            gameState[i] = 2;
            buttons[i].setText("");
        }
    }
}