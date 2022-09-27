package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button botaoIniciar;
    EditText editJogadorUm, editJogadorDois;
    String nomeJogador1, nomeJogador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editJogadorUm = findViewById(R.id.editTextTextPlayer1);
        editJogadorDois = findViewById(R.id.editTextTextPlayer2);

        botaoIniciar = findViewById(R.id.buttonStart);
        botaoIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jogo = new Intent(getApplicationContext(), TicTacToeScreen.class);
                nomeJogador1 = editJogadorUm.getText().toString();
                nomeJogador2 = editJogadorDois.getText().toString();
                jogo.putExtra("Value1", nomeJogador1);
                jogo.putExtra("Value2", nomeJogador2);
                startActivity(jogo);
            }
        });
    }
}