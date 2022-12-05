package au.edu.federation.itech3107.fedunimillionaire30393022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FinishPage extends AppCompatActivity {

    EditText edt1;
    Button btn2;
    TextView tv;

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_page);

        edt1=findViewById(R.id.UserName);
        btn2=findViewById(R.id.Submit);
        tv=findViewById(R.id.textView4);

        dbHelper = new MyDatabaseHelper(this, "winner.db", null, 1);
        Button createDatabase = (Button) findViewById(R.id.Submit);

        String Win_money = getIntent().getStringExtra("Win_Money");
        String Win_time = getIntent().getStringExtra("WinTime");

        tv.setText("You win $ " + Win_money);

        createDatabase.setOnClickListener(v -> {
            String User_Name = edt1.getText().toString();
            dbHelper.getWritableDatabase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();//临时变量
            values.put("WinnerName",User_Name);
            values.put("WinnerMoney",Win_money);
            values.put("FinishTime",Win_time);
            db.insert("Winner",null,values);
            Intent intent=new Intent(FinishPage.this, RankList.class);
            startActivity(intent);
            finish();
        });

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK ) {
            //do something.
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }
}