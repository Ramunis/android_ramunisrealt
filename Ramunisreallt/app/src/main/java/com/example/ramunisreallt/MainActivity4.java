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

public class MainActivity4 extends AppCompatActivity {

    private ListView lv100;
    private ListView lv200;
    private ListView lv300;
    private ListView lv400;
    private ListView lv500;
    private ArrayAdapter ad100;
    private ArrayAdapter ad200;
    private ArrayAdapter ad300;
    private ArrayAdapter ad400;
    private ArrayAdapter ad500;
    ArrayList<String> ar100 = new ArrayList<>();
    ArrayList<String> ar200 = new ArrayList<>();
    ArrayList<String> ar300 = new ArrayList<>();
    ArrayList<String> ar400 = new ArrayList<>();
    ArrayList<String> ar500 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        lv100 = findViewById(R.id.lv100);
        lv200 = findViewById(R.id.lv200);
        lv300 = findViewById(R.id.lv300);
        lv400 = findViewById(R.id.lv400);
        lv500 = findViewById(R.id.lv500);

        ar100.add("id");
        ar200.add("date");
        ar300.add("district");
        ar400.add("adres");
        ar500.add("price");

        // MyTask mt = new MyTask();
        //mt.execute();
        new MyTask().execute();

        ad100 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar100);
        lv100.setAdapter(ad100);

        ad200 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar200);
        lv200.setAdapter(ad200);

        ad300 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar300);
        lv300.setAdapter(ad300);

        ad400 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar400);
        lv400.setAdapter(ad400);

        ad500 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar500);
        lv500.setAdapter(ad500);

        Intent intent = new Intent(this, MainActivity9.class);
        lv100.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                String selectedItem = ar100.get(position);
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
                url = new URL("http://192.168.1.64/newpokupka");
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
                ar100.add(add.id);
                ar200.add(add.dc);
                ar300.add(add.area);
                ar400.add(add.adres);
                ar500.add(add.price);
            }


            // ничего не возвращаем потому что я так захотел)
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // после запроса обновляем листвью
            lv100.setAdapter(ad100);
            lv200.setAdapter(ad200);
            lv300.setAdapter(ad300);
            lv400.setAdapter(ad400);
            lv500.setAdapter(ad500);
        }
    }

}