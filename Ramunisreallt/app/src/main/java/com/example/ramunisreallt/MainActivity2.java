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
import android.widget.Button;
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

public class MainActivity2 extends AppCompatActivity {

    private ListView lv1;
   private ListView lv2;
   private ListView lv3;
    private ListView lv4;
    private ListView lv5;
    private ArrayAdapter ad1;
    private ArrayAdapter ad2;
    private ArrayAdapter ad3;
    private ArrayAdapter ad4;
    private ArrayAdapter ad5;
   ArrayList<String> ar1 = new ArrayList<>();
    ArrayList<String> ar2 = new ArrayList<>();
    ArrayList<String> ar3 = new ArrayList<>();
    ArrayList<String> ar4 = new ArrayList<>();
    ArrayList<String> ar5 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         lv1 = findViewById(R.id.lv1);
        lv2 = findViewById(R.id.lv2);
        lv3 = findViewById(R.id.lv3);
        lv4 = findViewById(R.id.lv4);
        lv5 = findViewById(R.id.lv5);

        ar1.add("id");
        ar2.add("date");
        ar3.add("district");
        ar4.add("adres");
        ar5.add("price");

       // MyTask mt = new MyTask();
        //mt.execute();
        new MyTask().execute();

         ad1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar1);
        lv1.setAdapter(ad1);

        ad2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar2);
        lv2.setAdapter(ad2);

        ad3 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar3);
        lv3.setAdapter(ad3);

        ad4 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar4);
        lv4.setAdapter(ad4);

        ad5 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar5);
        lv5.setAdapter(ad5);

        Intent intent = new Intent(this, MainActivity9.class);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                String selectedItem = ar1.get(position);
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
                url = new URL("http://192.168.1.64/arenda");
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
                ar1.add(add.id);
                ar2.add(add.dc);
                ar3.add(add.area);
                ar4.add(add.adres);
                ar5.add(add.price);
            }


            // ничего не возвращаем потому что я так захотел)
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // после запроса обновляем листвью
            lv1.setAdapter(ad1);
            lv2.setAdapter(ad2);
            lv3.setAdapter(ad3);
            lv4.setAdapter(ad4);
            lv5.setAdapter(ad5);
        }
    }

}