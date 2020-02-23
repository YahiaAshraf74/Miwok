package com.example.miwok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    public static final String KEY_POSITION = "com.example.miwok.KEY_POSITION";

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
        ListView listView = findViewById(R.id.list_item);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                switch (position) {
                    case 0:
                        intent.putExtra(KEY_POSITION, 0);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra(KEY_POSITION, 1);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra(KEY_POSITION, 2);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.putExtra(KEY_POSITION, 3);
                        startActivity(intent);
                        break;
                    default:
                        intent.putExtra(KEY_POSITION, 4);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
