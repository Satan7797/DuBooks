package dunation.tk.dubooks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LinkAdapter extends ArrayAdapter<LinkList> {
    LinkAdapter(Context context, ArrayList<LinkList> words) {
        super(context, 0, words);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listviewlayout, parent, false);
        }

        // Get the {@link LinkList} object located at this position in the list
        LinkList current = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID course_view.
        TextView courseTextView =  listItemView.findViewById(R.id.course_view);

        assert current != null;
        courseTextView.setText(current.getCourse());

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
