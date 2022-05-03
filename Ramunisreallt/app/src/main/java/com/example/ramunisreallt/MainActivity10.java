package com.example.ramunisreallt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

public class MainActivity10 extends AppCompatActivity {

    private String aid;
    //private TextView textView40;
    private ListView lv01;
    private ListView lv02;
    private ListView lv03;
    private ListView lv04;
    private ListView lv05;
    private ArrayAdapter ad01;
    private ArrayAdapter ad02;
    private ArrayAdapter ad03;
    private ArrayAdapter ad04;
    private ArrayAdapter ad05;
    ArrayList<String> ar01 = new ArrayList<>();
    ArrayList<String> ar02 = new ArrayList<>();
    ArrayList<String> ar03 = new ArrayList<>();
    ArrayList<String> ar04 = new ArrayList<>();
    ArrayList<String> ar05 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        //textView40 = (TextView) findViewById(R.id.tx40);
        lv01 = findViewById(R.id.lv01);
        lv02 = findViewById(R.id.lv02);
        lv03 = findViewById(R.id.lv03);
        lv04 = findViewById(R.id.lv04);
        lv05 = findViewById(R.id.lv05);

        ar01.add("id");
        ar02.add("date");
        ar03.add("district");
        ar04.add("adres");
        ar05.add("price");

        new MyTask().execute();

        ad01 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar01);
        lv01.setAdapter(ad01);

        ad02 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar02);
        lv02.setAdapter(ad02);

        ad03 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar03);
        lv03.setAdapter(ad03);

        ad04 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar04);
        lv04.setAdapter(ad04);

        ad05 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar05);
        lv05.setAdapter(ad05);

        Bundle arguments = getIntent().getExtras();

        if(arguments!=null){
            aid = arguments.get("hello").toString();
            //textView40.setText("Пользователь №"+aid);
        }

        Intent intent = new Intent(this, MainActivity9.class);
        lv01.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                String selectedItem = ar01.get(position);
                // установка текста элемента TextView
                //selection.setText(selectedItem);
                Toast myToast = Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG);
                myToast.show();

                // передача объекта с ключом "hello" и значением "Hello World"
                intent.putExtra("hello", selectedItem);
                // запуск SecondActivity
                startActivity(intent);
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
                url = new URL("http://192.168.1.64/prodavec?id="+aid+"");
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
                ar01.add(add.id);
                ar02.add(add.dc);
                ar03.add(add.area);
                ar04.add(add.adres);
                ar05.add(add.price);
            }


            // ничего не возвращаем потому что я так захотел)
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // после запроса обновляем листвью
            lv01.setAdapter(ad01);
            lv02.setAdapter(ad02);
            lv03.setAdapter(ad03);
            lv04.setAdapter(ad04);
            lv05.setAdapter(ad05);
        }
    }

}