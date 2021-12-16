package bgv.fit.bstu.eday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bgv.fit.bstu.eday.Models.Task;
import bgv.fit.bstu.eday.Models.User;

public class NewTaskActivity extends AppCompatActivity {
    EditText name, description, date, time;
    DBHelper databaseHelper;
    SQLiteDatabase db;
    WorkWithDB workWithDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        init();
        databaseHelper = new DBHelper(getApplicationContext());
        db = databaseHelper.getWritableDatabase();
        workWithDB = new WorkWithDB(db);
    }

    public void SaveTask(View view){
        if (name.getText().toString().equals("") || description.getText().toString().equals("") || date.getText().toString().equals("") || time.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                Task task = new Task();
                task.setName(name.getText().toString());
                task.setDescription(description.getText().toString());
                task.setDate(date.getText().toString());
                task.setTime(time.getText().toString());
                task.setUserId(MainActivity.UserId);
                workWithDB.InsertTask(task);
                Toast.makeText(getApplicationContext(), "Успешне добавление", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), TaskActivity.class);
                startActivity(intent);
                Log.d("Task add", "Success");
            } catch(Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Не получилось добавить", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void init(){
        name = findViewById(R.id.tnameet);
        description = findViewById(R.id.descret);
        date = findViewById(R.id.dateet);
        time = findViewById(R.id.timeet);
    }
}