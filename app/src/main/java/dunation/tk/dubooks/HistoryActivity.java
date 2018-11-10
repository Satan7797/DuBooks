package dunation.tk.dubooks;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import dunation.tk.dubooks.data.data.HistoryContract;

public class HistoryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private HistoryCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("History");
        ListView historyListView = findViewById(R.id.list_view_2);
        mCursorAdapter = new HistoryCursorAdapter(this, null);
        historyListView.setAdapter(mCursorAdapter);
        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                HistoryContract.HistoryEntry._ID,
                HistoryContract.HistoryEntry.COLUMN_SEMESTER,
                HistoryContract.HistoryEntry.COLUMN_SUBJECT
        };

        return new CursorLoader(this,
                HistoryContract.HistoryEntry.CONTENT_URI,
                projection,
                null,
                null,
                HistoryContract.HistoryEntry._ID+" DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
