package com.example.ramunisreallt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class MainActivity3 extends AppCompatActivity {

    private ListView lv10;
    private ListView lv20;
    private ListView lv30;
    private ListView lv40;
    private ListView lv50;
    private ArrayAdapter ad10;
    private ArrayAdapter ad20;
    private ArrayAdapter ad30;
    private ArrayAdapter ad40;
    private ArrayAdapter ad50;
    ArrayList<String> ar10 = new ArrayList<>();
    ArrayList<String> ar20 = new ArrayList<>();
    ArrayList<String> ar30 = new ArrayList<>();
    ArrayList<String> ar40 = new ArrayList<>();
    ArrayList<String> ar50 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        lv10 = findViewById(R.id.lv10);
        lv20 = findViewById(R.id.lv20);
        lv30 = findViewById(R.id.lv30);
        lv40 = findViewById(R.id.lv40);
        lv50 = findViewById(R.id.lv50);

        ar10.add("id");
        ar20.add("date");
        ar30.add("district");
        ar40.add("adres");
        ar50.add("price");

        // MyTask mt = new MyTask();
        //mt.execute();
        new MyTask().execute();

        ad10 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar10);
        lv10.setAdapter(ad10);

        ad20 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar20);
        lv20.setAdapter(ad20);

        ad30 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar30);
        lv30.setAdapter(ad30);

        ad40 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar40);
        lv40.setAdapter(ad40);

        ad50 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar50);
        lv50.setAdapter(ad50);

        Intent intent = new Intent(this, MainActivity9.class);
        lv10.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                String selectedItem = ar10.get(position);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();

        TextView infoTextView = findViewById(R.id.textView);

        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.rent:
                // infoTextView.setText("Вы выбрали кота!");
                Intent intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                return true;
            case R.id.buy:
                // infoTextView.setText("Вы выбрали кошку!");
                Intent intent2 = new Intent(this, MainActivity3.class);
                startActivity(intent2);
                return true;
            case R.id.newbuild:
                // infoTextView.setText("Вы выбрали котёнка!");
                Intent intent3 = new Intent(this, MainActivity4.class);
                startActivity(intent3);
                return true;
            case R.id.my:
                // infoTextView.setText("Вы выбрали кота!");
                Intent intent4 = new Intent(this, MainActivity5.class);
                startActivity(intent4);
                return true;
            case R.id.mybuy:
                // infoTextView.setText("Вы выбрали кота!");
                Intent intent8 = new Intent(this, MainActivity12.class);
                startActivity(intent8);
                return true;
            case R.id.mysale:
                // infoTextView.setText("Вы выбрали кота!");
                Intent intent9 = new Intent(this, MainActivity13.class);
                startActivity(intent9);
                return true;
            case R.id.buying:
                // infoTextView.setText("Вы выбрали кошку!");
                Intent intent5 = new Intent(this, MainActivity6.class);
                startActivity(intent5);
                return true;
            case R.id.buyed:
                // infoTextView.setText("Вы выбрали котёнка!");
                Intent intent6 = new Intent(this, MainActivity7.class);
                startActivity(intent6);
                return true;
            case R.id.calc:
                // infoTextView.setText("Вы выбрали котёнка!");
                Intent intent7 = new Intent(this, MainActivity11.class);
                startActivity(intent7);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
                url = new URL("http://192.168.1.64/pokupka");
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
                ar10.add(add.id);
                ar20.add(add.dc);
                ar30.add(add.area);
                ar40.add(add.adres);
                ar50.add(add.price);
            }


            // ничего не возвращаем потому что я так захотел)
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // после запроса обновляем листвью
            lv10.setAdapter(ad10);
            lv20.setAdapter(ad20);
            lv30.setAdapter(ad30);
            lv40.setAdapter(ad40);
            lv50.setAdapter(ad50);
        }
    }

}