package org.binaryitplanet.eranlist.Features.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

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
        ListItemAdapter adapter = new ListItemAdapter(this, itemDataList);
        binding.list.setAdapter(adapter);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setItemViewCacheSize(1000);
    }

    private void setDummyData() {
        itemDataList.add(new ItemData(
           "10:15", 10, "12/02/2021", "Euro"
        ));
        itemDataList.add(new ItemData(
           "10:30", 80, "12/02/2021", "Euro"
        ));
        itemDataList.add(new ItemData(
           "10:45", 45, "12/02/2021", "Euro"
        ));
        itemDataList.add(new ItemData(
           "11:15", 65, "12/02/2021", "Euro"
        ));
        itemDataList.add(new ItemData(
           "10:15", 10, "12/02/2021", "Euro"
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