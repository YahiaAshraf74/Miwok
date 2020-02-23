package com.example.miwok;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {
    private RecyclerViewAdapter adapter;

    public NumbersFragment() {
        // Required empty public constructor
    }

    public static NumbersFragment getInstance() {
        return new NumbersFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_numbers, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view_numbers);
        adapter = new RecyclerViewAdapter(getActivity(), R.color.colorTextViewNumbers, DummyDate.miwokWordNumbers, DummyDate.defaultWordNumbers, DummyDate.audioNumbers, DummyDate.imageNumbers);
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
