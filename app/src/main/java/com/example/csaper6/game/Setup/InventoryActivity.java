package com.example.csaper6.game.Setup;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.csaper6.game.GamePlay.Player;
import com.example.csaper6.game.R;

/**
 * Created by csaper6 on 5/9/17.
 */
public class InventoryActivity extends AppCompatActivity {
    private ListView inventoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        inventoryList = (ListView) findViewById(R.id.listView);

        Player p = new Player();
        p.getInventory();



        AlertDialog.Builder builder = new AlertDialog.Builder(InventoryActivity.this);
        builder.setTitle("Inventory");

    }
}
