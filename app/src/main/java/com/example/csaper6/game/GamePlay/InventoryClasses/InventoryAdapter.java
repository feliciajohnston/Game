package com.example.csaper6.game.GamePlay.InventoryClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.csaper6.game.GamePlay.Inventory;
import com.example.csaper6.game.R;

import java.util.List;

/**
 * Created by csaper6 on 5/11/17.
 */
public class InventoryAdapter extends ArrayAdapter<Inventory> {
    public InventoryAdapter(Context context, List<Inventory
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /* if not given view, make one */
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_inventory, null);
        }
        /* get item at position */
        Inventory items = getItem(position);
        /* wire view */
        TextView nameText = (TextView) convertView.findViewById(R.id.textView_name);
        TextView powerText = (TextView) convertView.findViewById(R.id.textView_power);
        /* text into views */


        return convertView;
    }
}

