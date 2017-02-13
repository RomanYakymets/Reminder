package com.example.roma.todo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.roma.todo.dto.RemindDbHelper;

public class NewTaskActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.add_new_task;
    Toolbar toolbar;
    TextView addTitle, addDetails;
    Spinner taskType;
    RemindDbHelper remindDbHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        initToolbar();
        initView();
    }

    private void initView() {
        addTitle = (TextView) findViewById(R.id.addTitle);
        addDetails = (TextView) findViewById(R.id.addDetails);
        taskType = (Spinner) findViewById(R.id.taskType);
    }
    public void onClickSubmitButton(View view){

        remindDbHelper = new RemindDbHelper(getApplicationContext());
        remindDbHelper.insertRemind(addTitle.getText().toString(), addDetails.getText().toString(),
                taskType.getSelectedItem().toString());
        onBackPressed();

    }
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.app_name);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
