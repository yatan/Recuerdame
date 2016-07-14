package com.example.fran.recuerdame;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView llista = (ListView) findViewById(R.id.llista);

        DatosSQLiteHelper datos = new DatosSQLiteHelper(this, "baseDB", null, 1);
        SQLiteDatabase db = datos.getReadableDatabase();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item_lista,
                datos.getAllResults(db),
                new String[] { "titulo" },
                new int[] {R.id.textView},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        //String[] values = new String[] { "Peli1", "Peli2", "Peli", "Peli", "Peli"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        //llista.setAdapter(new PelisAdaptor(this, values));
        llista.setAdapter(adapter);
    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.main_menu, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =(SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =(SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

/*
        MenuItemCompat searchMenuItem = menu.findItem(R.id.menu_search);
        searchMenuItem.setOnActionExpandListener(new OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do whatever you need
                return true; // KEEP IT TO TRUE OR IT DOESN'T OPEN !!
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do whatever you need
                return true; // OR FALSE IF YOU DIDN'T WANT IT TO CLOSE!
            }
        });
*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_settings:
                startActivity(new Intent(this, OpcionesActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
