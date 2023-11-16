package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Custom> list;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new CustomAdapter(this,R.layout.list_item,list);
        listView.setAdapter(adapter);
        new ReadJSON().execute("https://dummyjson.com/products");
    }
    private class ReadJSON extends AsyncTask<String,Void,String>{
        StringBuilder content = new StringBuilder();
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return content.toString();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("products");
                for (int i = 0 ; i < array.length() ; i++){
                    JSONObject objectProduct = array.getJSONObject(i);
                    String thumbnail = objectProduct.getString("thumbnail");
                    String title = objectProduct.getString("title");
                    String branch = objectProduct.getString("brand");
                    String description = objectProduct.getString("description");
                    int price = objectProduct.getInt("price");
                    Custom custom = new Custom(thumbnail,title,branch,description,price);
                    list.add(custom);
                }
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }

            adapter.notifyDataSetChanged();

        }
    }
}