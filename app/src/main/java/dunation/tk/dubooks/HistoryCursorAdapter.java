package dunation.tk.dubooks;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import dunation.tk.dubooks.data.data.HistoryContract;

public class HistoryCursorAdapter extends CursorAdapter {
    HistoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_layout_2, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView semesterTextView = view.findViewById(R.id.semester);
        TextView subjectTextView = view.findViewById(R.id.subject);

        int semesterColumn = cursor.getColumnIndex(HistoryContract.HistoryEntry.COLUMN_SEMESTER);
        int subjectColumn = cursor.getColumnIndex(HistoryContract.HistoryEntry.COLUMN_SUBJECT);

        String semester = cursor.getString(semesterColumn);
        String subject = cursor.getString(subjectColumn);

        semesterTextView.setText(semester);
        subjectTextView.setText(subject);
    }
}
