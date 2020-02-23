package com.example.miwok;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {
    private RecyclerViewAdapter adapter;

    public ColorsFragment() {
        // Required empty public constructor
    }

    public static ColorsFragment getInstance() {
        return new ColorsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_colors, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view_colors);
        adapter = new RecyclerViewAdapter(getActivity(), R.color.colorTextViewColors, DummyDate.miwokWordColors, DummyDate.defaultWordColors, DummyDate.audioColors, DummyDate.imageColors);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.releaseMediaPlayer();
    }
}
