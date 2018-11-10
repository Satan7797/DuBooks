package dunation.tk.dubooks.data.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class HistoryContract {
    HistoryContract() {
    }

    private static final String CONTENT_AUTHORITY = "dunation.tk.dubooks";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    private static final String PATH_HISTORY = "history";

    public static final class HistoryEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_HISTORY);
        static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HISTORY;
        static final String CONTEN_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HISTORY;
        final static String TABLE_NAME = "history";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_SEMESTER = "semester";
        public final static String COLUMN_SUBJECT = "subject";
        public final static String COLUMN_LINK = "link";
    }
}
