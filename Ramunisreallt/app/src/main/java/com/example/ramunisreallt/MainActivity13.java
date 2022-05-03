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

public class MainActivity13 extends AppCompatActivity {

    private String uid;
    private ListView lv600;
    private ListView lv700;
    private ListView lv800;
    private ListView lv900;
    private ListView lvc;
    private ArrayAdapter ad600;
    private ArrayAdapter ad700;
    private ArrayAdapter ad800;
    private ArrayAdapter ad900;
    private ArrayAdapter adc;
    ArrayList<String> ar600 = new ArrayList<>();
    ArrayList<String> ar700 = new ArrayList<>();
    ArrayList<String> ar800 = new ArrayList<>();
    ArrayList<String> ar900 = new ArrayList<>();
    ArrayList<String> arc = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);

        Bundle arguments = getIntent().getExtras();

        if(arguments!=null){
            uid = arguments.get("hello").toString();
        }

        lv600 = findViewById(R.id.lv600);
        lv700 = findViewById(R.id.lv700);
        lv800 = findViewById(R.id.lv800);
        lv900 = findViewById(R.id.lv900);
        lvc = findViewById(R.id.lvc);

        ar600.add("id");
        ar700.add("date");
        ar800.add("district");
        ar900.add("adres");
        arc.add("price");

        // MyTask mt = new MyTask();
        //mt.execute();
        new MyTask().execute();

        ad600 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar600);
        lv600.setAdapter(ad600);

        ad700 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar700);
        lv700.setAdapter(ad700);

        ad800 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar800);
        lv800.setAdapter(ad800);

        ad900 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar900);
        lv900.setAdapter(ad900);

        adc = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arc);
        lvc.setAdapter(adc);

        lv600.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                String selectedItem = ar600.get(position);
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
                url = new URL("http://192.168.1.64/moiprodagi?id="+uid+"");
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
                ar600.add(add.id);
                ar700.add(add.dc);
                ar800.add(add.area);
                ar900.add(add.adres);
                arc.add(add.price);
            }


            // ничего не возвращаем потому что я так захотел)
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // после запроса обновляем листвью
            lv600.setAdapter(ad600);
            lv700.setAdapter(ad700);
            lv800.setAdapter(ad800);
            lv900.setAdapter(ad900);
            lvc.setAdapter(adc);
        }
    }
}