package org.binaryitplanet.eranlist.Features.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

import org.binaryitplanet.eranlist.Features.Adapter.ListItemAdapter;
import org.binaryitplanet.eranlist.Model.ItemData;
import org.binaryitplanet.eranlist.R;
import org.binaryitplanet.eranlist.Utils.Config;
import org.binaryitplanet.eranlist.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    final static private String TAG = "MainActivity";
    private ActivityMainBinding binding;

    private ArrayList<ItemData> itemDataList;

    private ActionBarDrawerToggle drawerToggle;


    private SearchView searchView;
    private EditText searchEditText;

    private ListItemAdapter adapter;

    private Handler handler;
    private Runnable runnable;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        runnable = () -> {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        };

        handler = new Handler();

        itemDataList = new ArrayList<>();


        settingNavigation();

        setActionBarMethod();

        setDummyData();

        setupList();
    }

    private void settingNavigation() {
        drawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.nav_open_drawer, R.string.nav_close_drawer);
        binding.drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        binding.navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupList() {
        adapter = new ListItemAdapter(this, itemDataList);
        binding.list.setAdapter(adapter);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setItemViewCacheSize(1000);
    }

    private void setDummyData() {
        itemDataList.add(new ItemData(
                1, 1, "10:15", 10, "12/02/2021", "Euro"
        ));
        itemDataList.add(new ItemData(
                1, 2, "10:30", 80, "12/02/2021", "Euro"
        ));
        itemDataList.add(new ItemData(
                2, 1, "10:45", 45, "12/02/2021", "Euro"
        ));
        itemDataList.add(new ItemData(
                2, 3, "11:15", 65, "12/02/2021", "Euro"
        ));
        itemDataList.add(new ItemData(
                4, 3, "10:15", 10, "12/02/2021", "Euro"
        ));
    }


    // Setting custom action bar
    private void setActionBarMethod() {
        binding.toolbar.setTitle(Config.TITLE_MAIN_ACTIVITY);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_menu));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        searchView = (SearchView) menu.findItem(R.id.searchId).getActionView();
        setSearchView();
        return super.onCreateOptionsMenu(menu);
    }
    // Setting search view of toolbar
    private void setSearchView() {
        searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.white));
        searchEditText.setHintTextColor(getResources().getColor(R.color.white));
        searchEditText.setHint("Search...");

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        // Filtering list based on user search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                setKeyBoardHideThread();
                return false;
            }
        });
    }

    private void setKeyBoardHideThread() {
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, Config.KEYBOARD_DELAY_TIME);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        if (item.getItemId() == R.id.contact) {
            intent = new Intent(this, Contact.class);
        } else if (item.getItemId() == R.id.settings) {
            intent = new Intent(this, Settings.class);
        } else if (item.getItemId() == R.id.about) {
            intent = new Intent(this, AboutUs.class);
        }
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        binding.drawerLayout.openDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}