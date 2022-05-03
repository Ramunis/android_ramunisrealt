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

public class MainActivity5 extends AppCompatActivity {

    private String uid;
    private ListView lv6;
    private ListView lv7;
    private ListView lv8;
    private ListView lv9;
    private ListView lva;
    private ArrayAdapter ad6;
    private ArrayAdapter ad7;
    private ArrayAdapter ad8;
    private ArrayAdapter ad9;
    private ArrayAdapter ada;
    ArrayList<String> ar6 = new ArrayList<>();
    ArrayList<String> ar7 = new ArrayList<>();
    ArrayList<String> ar8 = new ArrayList<>();
    ArrayList<String> ar9 = new ArrayList<>();
    ArrayList<String> ara = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Bundle arguments = getIntent().getExtras();

        if(arguments!=null){
            uid = arguments.get("hello").toString();
        }

        lv6 = findViewById(R.id.lv6);
        lv7 = findViewById(R.id.lv7);
        lv8 = findViewById(R.id.lv8);
        lv9 = findViewById(R.id.lv9);
        lva = findViewById(R.id.lva);

        ar6.add("id");
        ar7.add("date");
        ar8.add("district");
        ar9.add("adres");
        ara.add("price");

        // MyTask mt = new MyTask();
        //mt.execute();
        new MyTask().execute();

        ad6 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar6);
        lv6.setAdapter(ad6);

        ad7 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar7);
        lv7.setAdapter(ad7);

        ad8 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar8);
        lv8.setAdapter(ad8);

        ad9 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar9);
        lv9.setAdapter(ad9);

        ada = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ara);
        lva.setAdapter(ada);

        lv6.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                String selectedItem = ar6.get(position);
                // установка текста элемента TextView
                //selection.setText(selectedItem);
                Toast myToast = Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG);
                myToast.show();
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
                intent8.putExtra("hello", uid);
                startActivity(intent8);
                return true;
            case R.id.mysale:
                // infoTextView.setText("Вы выбрали кота!");
                Intent intent9 = new Intent(this, MainActivity13.class);
                intent9.putExtra("hello", uid);
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
                url = new URL("http://192.168.1.64/moiobj?id="+uid+"");
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
                ar6.add(add.id);
                ar7.add(add.dc);
                ar8.add(add.area);
                ar9.add(add.adres);
                ara.add(add.price);
            }


            // ничего не возвращаем потому что я так захотел)
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // после запроса обновляем листвью
            lv6.setAdapter(ad6);
            lv7.setAdapter(ad7);
            lv8.setAdapter(ad8);
            lv9.setAdapter(ad9);
            lva.setAdapter(ada);
        }
    }

}