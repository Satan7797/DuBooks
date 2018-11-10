package dunation.tk.dubooks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<List<LinkList>> {

    private TextView mEmptyTextView;
    private LinkAdapter adapter;
    private String yearPref;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmptyTextView = findViewById(R.id.empty_textView);
        yearPref = PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.year), getString(R.string.year));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (adapter != null) {
            adapter.clear();
        }

        adapter = new LinkAdapter(this, new ArrayList<LinkList>());

        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (yearPref.equals("year_one") || yearPref.equals("year_two") || yearPref.equals("year_three")) {
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                findViewById(R.id.loading_spinner).setVisibility(View.GONE);
                mEmptyTextView.setText("No Internet");
            } else {
                getSupportLoaderManager().initLoader(0, null, this);
            }

            ListView listView = findViewById(R.id.main_list);
            listView.setEmptyView(this.mEmptyTextView);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    LinkList linkList = (LinkList) adapterView.getItemAtPosition(i);
                    JSONArray jsonArray = linkList.getJsonArray();
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    Main2Activity.jsonArray = jsonArray;
                    startActivity(intent);
                }
            });
        } else {
            findViewById(R.id.loading_spinner).setVisibility(View.GONE);
            mEmptyTextView.setText("No source selected");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_history) {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent i = new Intent(android.content.Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(android.content.Intent.EXTRA_SUBJECT, "DuBooks Apk");
            i.putExtra(android.content.Intent.EXTRA_TEXT, "Download the apk from here: http://bit.ly/DuBooks");
            startActivity(Intent.createChooser(i, "Share via"));
        } else if (id == R.id.nav_pref) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:dunationtk@gmail.com"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Du Book");
            intent.putExtra(Intent.EXTRA_TEXT, "Year: \nCourse: \nSubject: ");
            startActivity(Intent.createChooser(intent, "Send Email"));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPostResume() {
        adapter.clear();
        super.onPostResume();
    }

    @NonNull
    @Override
    public Loader<List<LinkList>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new ListLoader(this, "https://raw.githubusercontent.com/Satan7797/DuBooks/master/" + yearPref + ".json");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<LinkList>> loader, List<LinkList> linkLists) {
        findViewById(R.id.loading_spinner).setVisibility(View.GONE);
        if (linkLists == null || linkLists.isEmpty()) {
            mEmptyTextView.setText(R.string.content_not_found);
        } else {
            adapter.addAll(linkLists);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<LinkList>> loader) {
        Log.i("MyActivity", "ON Loader reset");
        if (adapter != null)
            this.adapter.clear();
    }
}
