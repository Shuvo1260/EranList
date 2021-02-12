package org.binaryitplanet.eranlist.Features.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import org.binaryitplanet.eranlist.Model.ItemData;
import org.binaryitplanet.eranlist.R;
import org.binaryitplanet.eranlist.Utils.Config;
import org.binaryitplanet.eranlist.databinding.ActivityMainBinding;
import org.binaryitplanet.eranlist.databinding.ActivityViewListItemBinding;

public class ViewListItem extends AppCompatActivity {
    final static private String TAG = "MainActivity";
    private ActivityViewListItemBinding binding;
    
    private ItemData itemData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_item);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_list_item);

        setActionBarMethod();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemData = (ItemData) getIntent().getSerializableExtra(Config.ITEM_DATA);
        
        setupView();
    }

    private void setupView() {
        binding.itemString.setText(itemData.getString());
        binding.itemProgress.setProgress(itemData.getProgress());
        binding.itemLocation.setText(itemData.getLocation());
        binding.itemDate.setText(itemData.getDate());
    }

    // Setting custom action bar
    private void setActionBarMethod() {
        binding.toolbar.setTitle(Config.TITLE_VIEW_ITEM_ACTIVITY);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back));
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}