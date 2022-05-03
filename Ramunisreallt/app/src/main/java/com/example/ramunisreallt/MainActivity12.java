package com.example.ramunisreallt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity12 extends AppCompatActivity {

    private String uid;
    private ListView lv60;
    private ListView lv70;
    private ListView lv80;
    private ListView lv90;
    private ListView lvb;
    private ArrayAdapter ad60;
    private ArrayAdapter ad70;
    private ArrayAdapter ad80;
    private ArrayAdapter ad90;
    private ArrayAdapter adb;
    ArrayList<String> ar60 = new ArrayList<>();
    ArrayList<String> ar70 = new ArrayList<>();
    ArrayList<String> ar80 = new ArrayList<>();
    ArrayList<String> ar90 = new ArrayList<>();
    ArrayList<String> arb = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);

        Bundle arguments = getIntent().getExtras();

        if(arguments!=null){
            uid = arguments.get("hello").toString();
        }

        lv60 = findViewById(R.id.lv60);
        lv70 = findViewById(R.id.lv70);
        lv80 = findViewById(R.id.lv80);
        lv90 = findViewById(R.id.lv90);
        lvb = findViewById(R.id.lvb);

        ar60.add("id");
        ar70.add("date");
        ar80.add("district");
        ar90.add("adres");
        arb.add("price");

        // MyTask mt = new MyTask();
        //mt.execute();
        new MyTask().execute();

        ad60 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar60);
        lv60.setAdapter(ad60);

        ad70 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar70);
        lv70.setAdapter(ad70);

        ad80 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar80);
        lv80.setAdapter(ad80);

        ad90 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar90);
        lv90.setAdapter(ad90);

        adb = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arb);
        lvb.setAdapter(adb);

        lv60.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                String selectedItem = ar60.get(position);
                // установка текста элемента TextView
                //selection.setText(selectedItem);
                Toast myToast = Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG);
                myToast.show();
            }
        });
    }

    public class MyTask extends AsyncTask<String, Void, String> {

        // Метод выполняющий запрос в фоне, в версиях выше 4 андроида, запросы в главном потоке выполнять
        // нельзя, поэтому все что вам нужно выполнять - выносите в отдельный тред
        @Override
        protected String doInBackground(String... arg) {

            // класс который захватывает страницу
            String json="";
            URL url = null;
            try {
                url = new URL("http://192.168.1.64/moipokupki?id="+uid+"");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            URLConnection con = null;
            try {
                con = url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (Reader reader = new InputStreamReader(con.getInputStream(),
                    StandardCharsets.UTF_8)) {
                try (BufferedReader buf = new BufferedReader(reader)) {
                    String line = "";
                    while ((line = buf.readLine()) != null) {
                        json += line;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //System.out.print(json);

            //GsonBuilder builder = new GsonBuilder();
            //Gson gson = builder.create();
            //Advert add = gson.fromJson(json, Advert.class);
            //System.out.print(add.id + "\n" + add.dc+ "\n" + add.area+ "\n" + add.adres+ "\n" + add.price);

            Gson gson = new Gson();
            Type type = new TypeToken<List<Advert>>(){}.getType();
            List<Advert> contactList = gson.fromJson(json, type);
            for (Advert add : contactList){
                //System.out.print(add.id + "\n" + add.dc+ "\n" + add.area+ "\n" + add.adres+ "\n" + add.price);
                ar60.add(add.id);
                ar70.add(add.dc);
                ar80.add(add.area);
                ar90.add(add.adres);
                arb.add(add.price);
            }


            // ничего не возвращаем потому что я так захотел)
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // после запроса обновляем листвью
            lv60.setAdapter(ad60);
            lv70.setAdapter(ad70);
            lv80.setAdapter(ad80);
            lv90.setAdapter(ad90);
            lvb.setAdapter(adb);
        }
    }
}