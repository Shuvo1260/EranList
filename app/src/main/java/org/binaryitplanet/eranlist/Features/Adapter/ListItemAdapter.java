package org.binaryitplanet.eranlist.Features.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.binaryitplanet.eranlist.Features.View.ViewListItem;
import org.binaryitplanet.eranlist.Model.ItemData;
import org.binaryitplanet.eranlist.R;
import org.binaryitplanet.eranlist.Utils.Config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "ListItemAdapter";
    private final Context context;
    private List<ItemData> itemDataList;
    private List<ItemData> allItemDataList;

    public ListItemAdapter(Context context, List<ItemData> itemList) {
        this.context = context;
        this.itemDataList = itemList;
        this.allItemDataList = new ArrayList<>(itemList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(
                        parent.getContext())
                        .inflate(
                                R.layout.list_item_view, parent,
                                false
                        )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final View view = holder.view;
        TextView itemNum1 = view.findViewById(R.id.itemNum1);
        TextView itemNum2 = view.findViewById(R.id.itemNum2);
        TextView itemString = view.findViewById(R.id.itemString);

//        ProgressBar itemProgress = view.findViewById(R.id.itemProgress);

        itemNum1.setText(String.valueOf(itemDataList.get(position).getNum1()));
        itemNum2.setText(String.valueOf(itemDataList.get(position).getNum2()));
        itemString.setText(itemDataList.get(position).getString());
//        itemProgress.setProgress(itemDataList.get(position).getProgress());

        view.setOnClickListener(v -> {
            Intent intent = new Intent(context, ViewListItem.class);
            intent.putExtra(Config.ITEM_DATA, itemDataList.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemDataList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public ViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
        }
    }

    // Filter unit

    @Override
    public Filter getFilter() {
        return listFilter;
    }


    private Filter listFilter = new Filter() {


        // Run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ItemData> filterList = new ArrayList<>();

            // If the text is null it will show full list
            if(constraint.toString().isEmpty()){
                Log.d(TAG, "performFiltering: ");
                filterList.addAll(allItemDataList);
                Log.d(TAG, "performFiltering: " + allItemDataList);
            } else {
                // Finding filtered list based on user search
                for(ItemData itemData : allItemDataList){
                    if(String.valueOf(itemData.getNum1()).toLowerCase().contains(constraint.toString().toLowerCase()) ||
                            String.valueOf(itemData.getNum2()).toLowerCase().contains(constraint.toString().toLowerCase())){
                        filterList.add(itemData);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;

            return filterResults;
        }

        // Run on UI thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // Adding new filtered list into recycler view
            itemDataList.clear();
            itemDataList.addAll((Collection<? extends ItemData>) results.values);
            notifyDataSetChanged();
        }
    };
}
