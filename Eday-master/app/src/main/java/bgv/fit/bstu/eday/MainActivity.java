package bgv.fit.bstu.eday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import bgv.fit.bstu.eday.Models.User;

public class MainActivity extends AppCompatActivity {
    DBHelper databaseHelper;
    SQLiteDatabase db;
    WorkWithDB workWithDB;
    TextView weatherinfo;
    String readyurl = "https://favqs.com/api/qotd";
    public static Integer UserId = 0;
    public static String UserName = "";
    public static String UserSurname = "";
    public static byte [] UserPhoto;
    public static String UserLogin = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DBHelper(getApplicationContext());
        db = databaseHelper.getWritableDatabase();
        workWithDB = new WorkWithDB(db);
        weatherinfo = findViewById(R.id.weathertv);
        try {
            new GetURLData().execute(readyurl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Tasks(View view){
        Intent intent = new Intent(this,TaskActivity.class);
        startActivity(intent);
    }

    public void Profile(View view){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }

    private class GetURLData extends AsyncTask<String, String, String>{

        protected void onPreExecute(){
            super.onPreExecute();
            weatherinfo.setText("Данные о цитате загружаются...");
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line=reader.readLine()) != null)
                    buffer.append(line).append("\n");

                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(connection != null)
                    connection.disconnect();

                try {
                    if (reader != null) {
                        reader.close();
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                weatherinfo.setText("В "+jsonObject.getJSONObject("quote").getString("body"));
            } catch(Exception e) {

            }
        }
    }
}