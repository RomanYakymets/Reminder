package com.example.roma.todo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.roma.todo.dto.RemindDbHelper;

public class EditTaskActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.add_new_task;
    Toolbar toolbar;
    TextView addTitle, addDetails;
    Spinner taskType;
    RemindDbHelper remindDbHelper;
    SQLiteDatabase db;
    Integer _id;
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

        Intent mIntent = getIntent();
        _id = mIntent.getIntExtra("_id", 0);
        addTitle.setText(mIntent.getStringExtra("Title"));
        addDetails.setText(mIntent.getStringExtra("Details"));

        String arr[] = getApplicationContext().getResources().getStringArray(R.array.remind_array);

        if(mIntent.getStringExtra("Type").equals(arr[0])){
            taskType.setSelection(0);
        }else if(mIntent.getStringExtra("Type").equals(arr[1])){
            taskType.setSelection(1);
        }else if(mIntent.getStringExtra("Type").equals(arr[2])){
            taskType.setSelection(2);
        }
    }
    public void onClickSubmitButton(View view){

        remindDbHelper = new RemindDbHelper(getApplicationContext());
        remindDbHelper.update(_id, addTitle.getText().toString(), taskType.getSelectedItem().toString(),
                addDetails.getText().toString());
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
