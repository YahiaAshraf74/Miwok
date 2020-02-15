package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DummyDate.setDummyDate();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_activity_main, DummyDate.listItemMenu) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = super.getView(position, convertView, parent);
                convertView.setBackgroundColor(getResources().getColor(DummyDate.colorListItemMenuId.get(position % DummyDate.colorListItemMenuId.size())));
                return convertView;
            }
        };
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getItemAtPosition(position).toString()) {
                    case "Numbers":
                        startActivity(new Intent(MainActivity.this,NumbersActivity.class));
                        break;
                    case "Family Members":
                        startActivity(new Intent(MainActivity.this,FamilyMembersActivity.class));
                        break;
                    case "Colors":
                        startActivity(new Intent(MainActivity.this,ColorsActivity.class));
                        break;
                    case "Phrases":
                        startActivity(new Intent(MainActivity.this,PhrasesActivity.class));
                        break;
                    default:
                        startActivity(new Intent(MainActivity.this,TestActivity.class));
                        break;
                }
            }
        });
    }
}
