package com.example.roma.todo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roma.todo.R;
import com.example.roma.todo.adapter.RemindListAdapter;
import com.example.roma.todo.dto.RemindDbHelper;

public class AllItemFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_contents;

    public static AllItemFragment getInstance() {
        Bundle args = new Bundle();
        AllItemFragment fragment = new AllItemFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        dbHelper = new RemindDbHelper(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        recyclerView.setAdapter(new RemindListAdapter(dbHelper.getAllReminds()));
        return view;
    }


    @Override
    public void onResume() {
        dbHelper = new RemindDbHelper(getContext());
        recyclerView.setAdapter(new RemindListAdapter(dbHelper.getAllReminds()));
        super.onResume();
    }
}
