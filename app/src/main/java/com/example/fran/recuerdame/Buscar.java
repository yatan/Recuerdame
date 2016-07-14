package com.example.fran.recuerdame;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Yatan on 20/06/2016.
 */

public class Buscar extends AppCompatActivity {

    private static final String TAG_PELIS = "results";
    private static final String TAG_TITUL = "title";
    private static final String TAG_DATA = "release_date";

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private ArrayList<String> parsejar(String msg){
        ArrayList<String> mylist = new ArrayList<String>();
        try {
            JSONObject jsonObj = new JSONObject(msg);

            // Getting JSON Array node
            JSONArray contacts = jsonObj.getJSONArray(TAG_PELIS);

            // looping through All Contacts
            for (int i = 0; i < contacts.length(); i++) {
                JSONObject c = contacts.getJSONObject(i);

                String id = c.getString(TAG_TITUL);
                mylist.add(id);
                String data = c.getString(TAG_DATA);
                mylist.add(data);
                /*
                // Phone node is JSON Object
                JSONObject phone = c.getJSONObject(TAG_PHONE);
                String mobile = phone.getString(TAG_PHONE_MOBILE);
                String home = phone.getString(TAG_PHONE_HOME);
                String office = phone.getString(TAG_PHONE_OFFICE);

                // tmp hashmap for single contact
                HashMap<String, String> contact = new HashMap<String, String>();

                // adding each child node to HashMap key => value
                contact.put(TAG_ID, id);
                contact.put(TAG_NAME, name);
                contact.put(TAG_EMAIL, email);
                contact.put(TAG_PHONE_MOBILE, mobile);

                // adding contact to contact list
                contactList.add(contact);
                */
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mylist;
    }

    private void handleIntent(Intent intent) {
        //TextView textp = (TextView) findViewById(R.id.textView);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            String query = intent.getStringExtra(SearchManager.QUERY);

            //use the query to search

            //textp.setText(query);

            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url ="https://api.themoviedb.org/3/search/movie?api_key=6799a0af563b9295f79baa801cf0514e&query="+query;

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string..substring(0,500)
                            //mTextView.setText(parsejar(response).toString());
                            final ListView llista = (ListView) findViewById(R.id.lista_result);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Buscar.this, android.R.layout.simple_list_item_1, parsejar(response));

                            ArrayList<String> tmp = parsejar(response);

                            String[] stockArr = new String[tmp.size()];
                            stockArr = tmp.toArray(stockArr);

                            llista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                @Override
                                public void onItemClick (AdapterView< ? > adapter, View view, int position, long arg){
                                    Intent in = new Intent(getApplicationContext(), ActivityInfo.class);
                                    in.putExtra("titulo", "asdas");
                                    startActivity(in);
                                    //Toast.makeText(getApplicationContext(), "selected Item Name is " + llista.getItemAtPosition(position), Toast.LENGTH_LONG).show();
                                }
                            });

                            llista.setAdapter(new PelisBuscador(getBaseContext(), stockArr ));

                            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //mTextView.setText("That didn't work!");
                }
            });
// Add the request to the RequestQueue.
            queue.add(stringRequest);
        }
    }

}
