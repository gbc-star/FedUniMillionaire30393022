package au.edu.federation.itech3107.fedunimillionaire30393022;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper  extends SQLiteOpenHelper {
    public static final String CREATE_Winner = "create table Winner ("
            + "id integer primary key autoincrement, "
            + "WinnerName text, "
            + "WinnerMoney integer, "
            + "FinishTime text)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_Winner);
        Toast.makeText(mContext, "Succeed", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
