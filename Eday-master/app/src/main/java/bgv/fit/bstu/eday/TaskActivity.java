package bgv.fit.bstu.eday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import bgv.fit.bstu.eday.Models.Task;

public class TaskActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context=this;
    DBHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        ArrayList<Task> tasks= new ArrayList<Task>();
        databaseHelper = new DBHelper(getApplicationContext());
        db = databaseHelper.getWritableDatabase();
        userCursor = db.rawQuery("select * from Tasks",null);
        userCursor.moveToFirst();
        while (!userCursor.isAfterLast()) {
            Task task = new Task();
            task.setId(userCursor.getInt(0));
            task.setName(userCursor.getString(1));
            task.setDescription(userCursor.getString(2));
            task.setDate(userCursor.getString(3));
            task.setTime(userCursor.getString(4));
            task.setUserId(userCursor.getInt(5));
            userCursor.moveToNext();
            tasks.add(task);
            //teachers.add(new Audience("hello"));
            TaskAdapter taskAdapter = new TaskAdapter(tasks);
            recyclerView.setAdapter(taskAdapter);
        }
    }

    public void CreateTaskPage(View view){
        Intent intent = new Intent(this,NewTaskActivity.class);
        startActivity(intent);
    }

    public void LoadData(String query, ArrayList<String> arraylist, String name_of_column, Spinner spinner){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraylist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}