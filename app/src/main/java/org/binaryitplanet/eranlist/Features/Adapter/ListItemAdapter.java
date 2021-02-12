package org.binaryitplanet.eranlist.Features.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.binaryitplanet.eranlist.Features.View.ViewListItem;
import org.binaryitplanet.eranlist.Model.ItemData;
import org.binaryitplanet.eranlist.R;
import org.binaryitplanet.eranlist.Utils.Config;

import java.util.List;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder> {

    private static final String TAG = "ListItemAdapter";
    private final Context context;
    private List<ItemData> itemDataList;

    public ListItemAdapter(Context context, List<ItemData> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;
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
        TextView itemString = view.findViewById(R.id.itemString);
        ProgressBar itemProgress = view.findViewById(R.id.itemProgress);

        itemString.setText(itemDataList.get(position).getString());
        itemProgress.setProgress(itemDataList.get(position).getProgress());

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

}
