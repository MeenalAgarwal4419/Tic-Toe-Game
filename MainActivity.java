package Meenal.Agarwal;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];
    private boolean player_1 = true;
    private int player_1_points;
    private int player_2_points;

    private TextView text_view1;
    private TextView text_view2;
    private int count_rounds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_view1 = findViewById(R.id.text1);
        text_view2 = findViewById(R.id.text2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button button_reset = findViewById(R.id.bmain);
        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 reset();
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (player_1) {
            ((Button) v).setText("X");
            count_rounds++;
        } else {
            ((Button) v).setText("O");
            count_rounds++;
        }

        if (checkPoints()) {
            if (player_1) {
                player1_win();
                textPlayer2();

                reset();

            }
            if (!player_1)
            {
                player2_win();
                textPlayer1();
                reset();

            }
        }
        if(count_rounds!=9)
        {
            player_1=!player_1;
        }
        if(count_rounds==9)
        {
            draw();
        }
    }
    private void draw()
    {
        reset();
        Toast t=Toast.makeText(getApplicationContext(),"NEW GAME",Toast.LENGTH_SHORT);
        count_rounds=0;
        t.show();
    }
    private void reset()

    { for(int i=0;i<3;i++) {
        for (int j = 0 ;j < 3; j++) {
            buttons[i][j].setText("");
            text_view1.setText("Player 1:" + player_1_points);
            text_view2.setText("Player 2:" + player_2_points);
            count_rounds=0;
        }
    }
}

    private void player1_win()
    {
       player_1_points++;
      Toast toast=  Toast.makeText(getApplicationContext() , "Meenal WIN", Toast.LENGTH_SHORT);
                toast.show();
                count_rounds=0;
        }

    private void player2_win()
    {
        player_2_points++;
        Toast toast=  Toast.makeText(getApplicationContext() , "Meenal", Toast.LENGTH_SHORT);
        count_rounds=0;
        toast.show();
    }
    private  void textPlayer1()
    {
text_view1.setText("Player1:"+player_1_points);

    }
    private  void textPlayer2()
    {
        text_view1.setText("Player2:"+player_2_points);
    }
            private boolean checkPoints()
            {
                  String[][] field=new String[3][3];
                  for(int i=0;i<3;i++) {
                      for (int j = 0; j < 3; j++) {
                          field[i][j]=buttons[i][j].getText().toString();
                      }
                  }
                for (int i = 0; i < 3; i++) {
                    if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !(field[i][0].equals("")))
                        return true;
                }
                for (int i = 0; i < 3; i++) {
                    if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !(field[0][i].equals(""))) {
                        return true;
                    }
                }
                if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !(field[0][0].equals(""))) {
                    return true;
                }
                if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !(field[0][2].equals(""))) {
                    return true;
                }
                return false;
            }

        }

