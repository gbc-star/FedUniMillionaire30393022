package au.edu.federation.itech3107.fedunimillionaire30393022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WelcomePage extends AppCompatActivity {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        btn1=findViewById(R.id.BtnStart);
        btn2=findViewById(R.id.Normal);
        btn3=findViewById(R.id.Leaderboard);
        btn4=findViewById(R.id.AOR);

        btn1.setOnClickListener(v -> {
            Intent intent=new Intent(WelcomePage.this, HotSeatQuestion1.class);
            startActivity(intent);
            finish();
        });

        btn2.setOnClickListener(view -> {
            Intent intent=new Intent(WelcomePage.this, NormalQuestion1.class);
            startActivity(intent);
            finish();
        });

        btn3.setOnClickListener(view -> {
            Intent intent=new Intent(WelcomePage.this, RankList.class);
            startActivity(intent);
            finish();
        });

        btn4.setOnClickListener(view -> {
            Intent intent=new Intent(WelcomePage.this, DeleteAddQuestion.class);
            startActivity(intent);
            finish();
        });
    }
}