package au.edu.federation.itech3107.fedunimillionaire30393022;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RankList extends AppCompatActivity {

    Button btn1;
    Button btn2;
    EditText edt1;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_list);

        final TextView textView = findViewById(R.id.List);
        dbHelper = new MyDatabaseHelper(this, "winner.db", null, 1);

//        search in the database and show on the screen
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("winner", null, null, null, null, null, null);
        StringBuilder content = new StringBuilder();
        content.append("id" + "--" + "WinnerName" + "--" + "WinnerMoney" + "----" + "FinishTime" + "\n");
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String WinnerName = cursor.getString(cursor.getColumnIndex("WinnerName"));
                @SuppressLint("Range") String WinnerMoney = cursor.getString(cursor.getColumnIndex("WinnerMoney"));
                @SuppressLint("Range") String FinishTime = cursor.getString(cursor.getColumnIndex("FinishTime"));
                content.append(id + "\t\t" + WinnerName + "\t\t$" + WinnerMoney + "\t\t" + FinishTime + "\n");
            }while (cursor.moveToNext());
        }
        textView.setText(content.toString());

//        user input the id they want to delete,and press button to refresh the page
        btn2 = findViewById(R.id.Delete);
        edt1 = findViewById(R.id.editText);
        btn2.setOnClickListener(view -> {
            String Id = edt1.getText().toString();
            db.delete("winner","id=?",new String[]{Id});

            dbHelper = new MyDatabaseHelper(this, "winner.db", null, 1);
            SQLiteDatabase db1 = dbHelper.getWritableDatabase();
            Cursor cursorD = db1.query("winner", null, null, null, null, null, null);
            StringBuilder contentD = new StringBuilder();
            contentD.append("id" + "--" + "WinnerName" + "---" + "WinnerMoney" + "-----" + "FinishTime" + "\n");
            if (cursorD.moveToFirst()) {
                do {
                    @SuppressLint("Range") int id = cursorD.getInt(cursorD.getColumnIndex("id"));
                    @SuppressLint("Range") String WinnerName = cursorD.getString(cursorD.getColumnIndex("WinnerName"));
                    @SuppressLint("Range") String WinnerMoney = cursorD.getString(cursorD.getColumnIndex("WinnerMoney"));
                    @SuppressLint("Range") String FinishTime = cursorD.getString(cursorD.getColumnIndex("FinishTime"));
                    contentD.append(id + "\t\t" + WinnerName + "\t\t$" + WinnerMoney + "\t\t" + FinishTime + "\n");
                }while (cursorD.moveToNext());
            }
            textView.setText(contentD.toString());
            edt1.setText("", null);
        });

        btn3 = findViewById(R.id.TimeUp);
        btn3.setOnClickListener(view -> {
            dbHelper = new MyDatabaseHelper(this, "winner.db", null, 1);
            SQLiteDatabase db2 = dbHelper.getWritableDatabase();
            @SuppressLint("Recycle") Cursor cursorTU = db2.rawQuery("select * from winner order by FinishTime desc", null);
//            Cursor cursorTU = db2.query("winner", null, null, null, null, null, "FinishTime" + DESC);
            StringBuilder contentTU = new StringBuilder();
            contentTU.append("id" + "--" + "WinnerName" + "---" + "WinnerMoney" + "-----" + "FinishTime" + "\n");
            if (cursorTU.moveToFirst()) {
                do {
                    @SuppressLint("Range") int id = cursorTU.getInt(cursorTU.getColumnIndex("id"));
                    @SuppressLint("Range") String WinnerName = cursorTU.getString(cursorTU.getColumnIndex("WinnerName"));
                    @SuppressLint("Range") String WinnerMoney = cursorTU.getString(cursorTU.getColumnIndex("WinnerMoney"));
                    @SuppressLint("Range") String FinishTime = cursorTU.getString(cursorTU.getColumnIndex("FinishTime"));
                    contentTU.append(id + "\t\t" + WinnerName + "\t\t$" + WinnerMoney + "\t\t" + FinishTime + "\n");
                }while (cursorTU.moveToNext());
            }
            textView.setText(contentTU.toString());
        });

        btn4 = findViewById(R.id.TimeDown);
        btn4.setOnClickListener(view -> {
            dbHelper = new MyDatabaseHelper(this, "winner.db", null, 1);
            SQLiteDatabase db3 = dbHelper.getWritableDatabase();
            @SuppressLint("Recycle") Cursor cursorTD = db3.rawQuery("select * from winner order by FinishTime asc", null);
            StringBuilder contentTD = new StringBuilder();
            contentTD.append("id" + "--" + "WinnerName" + "---" + "WinnerMoney" + "-----" + "FinishTime" + "\n");
            if (cursorTD.moveToFirst()) {
                do {
                    @SuppressLint("Range") int id = cursorTD.getInt(cursorTD.getColumnIndex("id"));
                    @SuppressLint("Range") String WinnerName = cursorTD.getString(cursorTD.getColumnIndex("WinnerName"));
                    @SuppressLint("Range") String WinnerMoney = cursorTD.getString(cursorTD.getColumnIndex("WinnerMoney"));
                    @SuppressLint("Range") String FinishTime = cursorTD.getString(cursorTD.getColumnIndex("FinishTime"));
                    contentTD.append(id + "\t\t" + WinnerName + "\t\t$" + WinnerMoney + "\t\t" + FinishTime + "\n");
                }while (cursorTD.moveToNext());
            }
            textView.setText(contentTD.toString());
        });

        btn5 = findViewById(R.id.MoneyUp);
        btn5.setOnClickListener(view -> {
            dbHelper = new MyDatabaseHelper(this, "winner.db", null, 1);
            SQLiteDatabase db4 = dbHelper.getWritableDatabase();
            @SuppressLint("Recycle") Cursor cursorMU = db4.rawQuery("select * from winner order by WinnerMoney desc", null);
            StringBuilder contentMU = new StringBuilder();
            contentMU.append("id" + "--" + "WinnerName" + "---" + "WinnerMoney" + "-----" + "FinishTime" + "\n");
            if (cursorMU.moveToFirst()) {
                do {
                    @SuppressLint("Range") int id = cursorMU.getInt(cursorMU.getColumnIndex("id"));
                    @SuppressLint("Range") String WinnerName = cursorMU.getString(cursorMU.getColumnIndex("WinnerName"));
                    @SuppressLint("Range") String WinnerMoney = cursorMU.getString(cursorMU.getColumnIndex("WinnerMoney"));
                    @SuppressLint("Range") String FinishTime = cursorMU.getString(cursorMU.getColumnIndex("FinishTime"));
                    contentMU.append(id + "\t\t" + WinnerName + "\t\t$" + WinnerMoney + "\t\t" + FinishTime + "\n");
                }while (cursorMU.moveToNext());
            }
            textView.setText(contentMU.toString());
        });

        btn6 = findViewById(R.id.MoneyDown);
        btn6.setOnClickListener(view -> {
            dbHelper = new MyDatabaseHelper(this, "winner.db", null, 1);
            SQLiteDatabase db5 = dbHelper.getWritableDatabase();
            @SuppressLint("Recycle") Cursor cursorMD = db5.rawQuery("select * from winner order by WinnerMoney asc", null);
            StringBuilder contentMD = new StringBuilder();
            contentMD.append("id" + "--" + "WinnerName" + "---" + "WinnerMoney" + "-----" + "FinishTime" + "\n");
            if (cursorMD.moveToFirst()) {
                do {
                    @SuppressLint("Range") int id = cursorMD.getInt(cursorMD.getColumnIndex("id"));
                    @SuppressLint("Range") String WinnerName = cursorMD.getString(cursorMD.getColumnIndex("WinnerName"));
                    @SuppressLint("Range") String WinnerMoney = cursorMD.getString(cursorMD.getColumnIndex("WinnerMoney"));
                    @SuppressLint("Range") String FinishTime = cursorMD.getString(cursorMD.getColumnIndex("FinishTime"));
                    contentMD.append(id + "\t\t" + WinnerName + "\t\t$" + WinnerMoney + "\t\t" + FinishTime + "\n");
                }while (cursorMD.moveToNext());
            }
            textView.setText(contentMD.toString());
        });

        btn1 = findViewById(R.id.Back);
        btn1.setOnClickListener(view -> {
            Intent intent=new Intent(RankList.this, WelcomePage.class);
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