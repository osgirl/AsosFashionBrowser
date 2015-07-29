package com.horaceb.asosfashionbrowser.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.horaceb.asosfashionbrowser.R;
import com.horaceb.asosfashionbrowser.data.model.ItemListing;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter for the items within a catalogue.
 */
public class CatalogueAdapter extends ArrayAdapter<ItemListing> {

    private final List<ItemListing> items;

    private final LayoutInflater inflater;

    public CatalogueAdapter(Context context, @NonNull final List<ItemListing> items) {
        super(context, R.layout.list_item_item, items);
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.list_item_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.price.setText(getItem(position).getPrice());
        holder.titleView.setText(getItem(position).getTitle());
        Glide.with(getContext())
                .load(getItem(position).getImageUrl())
                .into(holder.imageView);

        return convertView;
    }

    @Override
    public ItemListing getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getProductId();
    }

    static final class ViewHolder {

        @Bind(R.id.item_price)
        TextView price;

        @Bind(R.id.item_preview_image)
        ImageView imageView;

        @Bind(R.id.item_title)
        TextView titleView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
