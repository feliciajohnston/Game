package com.example.csaper6.game.GamePlay.InventoryClasses;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.csaper6.game.GamePlay.Inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by csaper6 on 5/11/17.
 */
public class InventoryFragment extends ListFragment {
    private List<Inventory> inventory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
         /* make hero list */
        inventory = new ArrayList<>();

        /* fill custom adapter */
        Inventory adapter = new InventoryAdapter(getActivity(), inventory);
        /* set listview adapter */
        setListAdapter(adapter);
        return rootView;
    }
}
