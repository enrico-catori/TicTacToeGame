package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicTacToeScreen extends AppCompatActivity implements View.OnClickListener {
    Button Botaoencerrar;
    private TextView jogadorUmScore, jogadorDoisScore, Status;
    private Button [] buttons = new Button[9];
    private Button encerrarJogo;

    private int jogadorUmContagem, jogadorDoisContagem;
    private int contagem;
    boolean jogadorAtivo;

    int [] estadoJogo = {2,2,2,2,2,2,2,2,2};

    int [][] posicoesVencedoras = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},   // Posições possíveis de vitória
            {0,4,8}, {2,4,6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_screen);

        jogadorUmScore = (TextView) findViewById(R.id.jogadorUmScore);
        jogadorDoisScore = (TextView) findViewById(R.id.jogadorDoisScore);
        Status = (TextView) findViewById(R.id.Status);

        encerrarJogo = (Button) findViewById(R.id.encerrarJogo);

        for(int i=0; i < buttons.length; i++) {
            String buttonId = "btn_" + i;
            int resourceId = getResources().getIdentifier(buttonId, "id", getPackageName());
            buttons[i] = (Button) findViewById((resourceId));
            buttons[i].setOnClickListener(this);
        }

        contagem = 0;
        jogadorUmContagem = 0;
        jogadorDoisContagem = 0;
        jogadorAtivo = true;

        // Ação de retornar pra activity inicial
        Botaoencerrar = findViewById(R.id.encerrarJogo);
        Botaoencerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Log.i("test", "clicado");
    }
}