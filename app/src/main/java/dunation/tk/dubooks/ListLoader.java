package dunation.tk.dubooks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class ListLoader extends AsyncTaskLoader<List<LinkList>> {

    private String mUrl;
    ListLoader(@NonNull Context context, String str) {
        super(context);
        mUrl=str;
    }

    @Nullable
    @Override
    public List<LinkList> loadInBackground() {
        return ListExtractor.listFound(mUrl);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
