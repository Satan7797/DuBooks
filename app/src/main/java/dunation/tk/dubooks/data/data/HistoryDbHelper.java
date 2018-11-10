package dunation.tk.dubooks.data.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import dunation.tk.dubooks.data.data.HistoryContract.HistoryEntry;

public class HistoryDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "history.db";
    private static final int DATABASE_VERSION = 1;

    HistoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SAL_CREATE_HISTORY_TABLE = " CREATE TABLE " + HistoryEntry.TABLE_NAME + "("
                + HistoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HistoryEntry.COLUMN_SEMESTER + " TEXT NOT NULL, "
                + HistoryEntry.COLUMN_SUBJECT + " TEXT NOT NULL, "
                + HistoryEntry.COLUMN_LINK + " TEXT NOT NULL );";

        sqLiteDatabase.execSQL(SAL_CREATE_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
