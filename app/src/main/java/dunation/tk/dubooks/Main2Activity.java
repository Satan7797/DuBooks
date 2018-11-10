package dunation.tk.dubooks;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dunation.tk.dubooks.data.data.HistoryContract.HistoryEntry;

public class Main2Activity extends AppCompatActivity {
    public static JSONArray jsonArray;
    LinkAdapter2 adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ArrayList<LinkList> arrayList = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                arrayList.add(new LinkList(jsonObject.getString("semester"),
                        jsonObject.getString("subject"),
                        jsonObject.getString("link")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListView listView = findViewById(R.id.list_view_2);
        adapter2 = new LinkAdapter2(this, arrayList);
        listView.setAdapter(adapter2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LinkList linkList = (LinkList) adapterView.getItemAtPosition(i);
                String semester = linkList.getSemester();
                String subject = linkList.getSubject();
                String link = linkList.getUrl();
                insertHistory(semester, subject, link);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkList.getUrl()));
                startActivity(intent);
            }
        });
    }

    private void insertHistory(String semester, String subject, String link) {
        ContentValues values = new ContentValues();
        values.put(HistoryEntry.COLUMN_SEMESTER, semester);
        values.put(HistoryEntry.COLUMN_SUBJECT, subject);
        values.put(HistoryEntry.COLUMN_LINK, link);

        getContentResolver().insert(HistoryEntry.CONTENT_URI, values);
    }
}

