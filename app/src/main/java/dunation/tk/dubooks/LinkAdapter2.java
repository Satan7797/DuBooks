package dunation.tk.dubooks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class LinkAdapter2 extends ArrayAdapter<LinkList> {
    LinkAdapter2(@NonNull Context context, @NonNull List<LinkList> objects) {
        super(context, 0, objects);
    }

    private static class ViewHolder {
        TextView semesterText, subjectText;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        LinkList word = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.list_layout_2, parent, false);

            viewHolder.semesterText = convertView.findViewById(R.id.semester);
            viewHolder.subjectText = convertView.findViewById(R.id.subject);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        assert word != null;
        viewHolder.semesterText.setText(word.getSemester());
        viewHolder.subjectText.setText(word.getSubject());

        return convertView;
    }
}

