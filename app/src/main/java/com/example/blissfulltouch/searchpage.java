package com.example.blissfulltouch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.Objects;


public class searchpage extends AppCompatActivity {

    ListView listView;

    String[] serviceslist = {"manicure and pedicure",
            "moroccan bath",
            "hot stone massage",
            "organ relaxing massage",
            "swedish massage",
            "balinese massage",
            "french manicure",
            "hand nail polish",
            "nail art design "};
    ArrayAdapter<String> arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpage);
        listView = findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, serviceslist);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        Objects.requireNonNull(searchView).setQueryHint("search here");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                arrayAdapter.getFilter().filter(newText.toLowerCase()); // Convert newText to lowercase


                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
