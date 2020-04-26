package xauuug.projects.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean turn = true;

    Button btn11;
    Button btn12;
    Button btn13;
    Button btn21;
    Button btn22;
    Button btn23;
    Button btn31;
    Button btn32;
    Button btn33;

    int [][] matriz = new int[3][3];

    TextView tvJogador1Score;
    TextView tvJogador2Score;
    TextView tvJogador11Score;
    TextView tvJogador22Score;

    int jogador1Score;
    int jogador2Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for ( int i = 0; i < 3; i ++ ){
            for ( int j = 0; j < 3; j ++){
                matriz[i][j] = 0;
            }
        }

        btn11 = (Button)findViewById(R.id.btn11);
        btn12 = (Button)findViewById(R.id.btn12);
        btn13 = (Button)findViewById(R.id.btn13);
        btn21 = (Button)findViewById(R.id.btn21);
        btn22 = (Button)findViewById(R.id.btn22);
        btn23 = (Button)findViewById(R.id.btn23);
        btn31 = (Button)findViewById(R.id.btn31);
        btn32 = (Button)findViewById(R.id.btn32);
        btn33 = (Button)findViewById(R.id.btn33);

        tvJogador1Score = (TextView)findViewById(R.id.tvJogador1Score);
        tvJogador2Score = (TextView)findViewById(R.id.tvJogador2Score);
        tvJogador11Score = (TextView)findViewById(R.id.tvJogador11Score);
        tvJogador22Score = (TextView)findViewById(R.id.tvJogador22Score);


        jogador1Score = 0;
        jogador2Score = 0;

        showMessage();
    }

    public void onClickGame(View v) {

        switch (v.getId()) {
            case R.id.btn11: setMatriz(btn11, 1,1); break;
            case R.id.btn12: setMatriz(btn12,1,2); break;
            case R.id.btn13: setMatriz(btn13,1,3); break;
            case R.id.btn21: setMatriz(btn21,2,1); break;
            case R.id.btn22: setMatriz(btn22,2,2); break;
            case R.id.btn23: setMatriz(btn23,2,3); break;
            case R.id.btn31: setMatriz(btn31,3,1); break;
            case R.id.btn32: setMatriz(btn32,3,2); break;
            case R.id.btn33: setMatriz(btn33,3,3); break;
        }
    }

    public void setMatriz(Button btn, int i, int j) {
        int aux;

        if (matriz[i-1][j-1] != 0)
            return;

        if (turn) {
            aux = 1;
            btn.setText("X");
        } else {
            aux = 2;
            btn.setText("O");
        }

        turn = !turn;
        matriz[i-1][j-1] = aux;

        checkGame();
    }

    public void checkGame() {
        int count = 0;

        // Chec Lines
        for ( int i = 0; i < 3; i ++ ) {
            if (matriz[i][0] == matriz[i][1] &&
                    matriz[i][1] == matriz[i][2] && matriz[i][0] != 0) {
                gameWin();
            }
            if (matriz[0][i] == matriz[1][i] &&
                    matriz[1][i] == matriz[2][i] && matriz[0][i] != 0) {
                gameWin();
            }
        }

        if (matriz[0][0] == matriz[1][1] &&
                matriz[1][1] == matriz[2][2] && matriz[1][1] != 0) {
            gameWin();
        }

        if (matriz[0][2] == matriz[1][1] &&
                matriz[1][1] == matriz[2][0] && matriz[0][2] != 0) {
            gameWin();
        }

        for ( int i = 0; i < 3; i ++ ){
            for ( int j = 0; j < 3; j ++){
                if (matriz[i][j] != 0) {
                    count += 1;
                }
            }
        }

        if (count == 9 ){
            gameDraw();
            return;
        }
    }

    public void gameDraw() {
        Toast.makeText(MainActivity.this,
                "Jogo Empatou", Toast.LENGTH_SHORT).show();
        clearGame();
    }

    public void clearGame() {
        tvJogador1Score.setText(Integer.toString(jogador1Score));
        tvJogador2Score.setText(Integer.toString(jogador2Score));
        tvJogador11Score.setText(Integer.toString(jogador1Score));
        tvJogador22Score.setText(Integer.toString(jogador2Score));

        for ( int i = 0; i < 3; i ++ ){
            for ( int j = 0; j < 3; j ++){
                matriz[i][j] = 0;
            }
        }

        btn11.setText("");
        btn12.setText("");
        btn13.setText("");
        btn21.setText("");
        btn22.setText("");
        btn23.setText("");
        btn31.setText("");
        btn32.setText("");
        btn33.setText("");

        showMessage();
    }

    public void gameWin() {
        if (turn) {
            jogador2Score += 1;
        } else {
            jogador1Score += 1;
        }
        clearGame();
    }

    public void showMessage() {
        if (turn) {
            Toast.makeText(MainActivity.this,
                    "Jogador 1 começa", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this,
                    "Jogador 2 começa", Toast.LENGTH_LONG).show();
        }
    }
}
