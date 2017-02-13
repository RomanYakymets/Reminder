package com.example.roma.todo.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.roma.todo.dto.RemindDbHelper;


public abstract class AbstractTabFragment extends Fragment {
    protected View view;
    protected RecyclerView recyclerView;
    protected RemindDbHelper dbHelper;
}
