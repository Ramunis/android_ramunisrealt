package com.example.ramunisreallt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MainActivity9 extends AppCompatActivity implements View.OnClickListener{

    private Button button4, button5;

    private String oldaid;
    private String aid;
    private String adc;
    private String ads;
    private String arealtor;
    private String ausername;
    public String acity;
    public String aarea;
    public String aadres;
    public String aservice;
    public String asquare;
    public String aterm;
    public String aprice;
    public String apay;
    public String aperair;
    private String apic;

    private TextView textView4;
    private TextView adddc;
    private TextView addds;
    private TextView addusername;
    private TextView addcity;
    private TextView addarea;
    private TextView addadres;
    private TextView addservice;
    private TextView addsquare;
    private TextView addterm;
    private TextView addprice;
    private TextView addpay;
    private TextView addrepair;
    private ImageView imageView;
    private Bitmap mIcon11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        adc = "";
        ads = "";
        arealtor = "";
        ausername = "";
        acity = "";
        aarea = "";
        String aadres ="";
        aservice ="";
        asquare ="";
        aterm ="";
        aprice ="";
        apay ="";
        aperair ="";
        apic ="";

        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        textView4 = (TextView) findViewById(R.id.addid);
        adddc = (TextView) findViewById(R.id.adddc);
        addds = (TextView) findViewById(R.id.addds);
        addusername = (TextView) findViewById(R.id.addusername);
        addcity = (TextView) findViewById(R.id.addcity);
        addarea = (TextView) findViewById(R.id.addarea);
        addadres = (TextView) findViewById(R.id.addadres);
        addservice = (TextView) findViewById(R.id.addservice);
        addsquare = (TextView) findViewById(R.id.addsquare);
        addterm = (TextView) findViewById(R.id.addterm);
        addprice = (TextView) findViewById(R.id.addprice);
        addpay = (TextView) findViewById(R.id.addpay);
        addrepair = (TextView) findViewById(R.id.addrepair);
        imageView = (ImageView) findViewById(R.id.imageView);

        Bundle arguments = getIntent().getExtras();

        if(arguments!=null){
            aid = arguments.get("hello").toString();
            oldaid = aid;
            textView4.setText("Объявление №"+aid);
        }

        new MyTask().execute();

        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button4:
                Intent intent2 = new Intent(this, MainActivity10.class);
                // передача объекта с ключом "hello" и значением "Hello World"
                intent2.putExtra("hello", arealtor);
                // запуск SecondActivity
                startActivity(intent2);
                break;
            case R.id.button5:
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.1.64/affare/getdeal/"+oldaid+""));
                startActivity(browserIntent);
                break;
            default:
                break;
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
                url = new URL("http://192.168.1.64/objvlenie?id="+aid+"");
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
            Type type = new TypeToken<List<Profile>>(){}.getType();
            List<Profile> contactList = gson.fromJson(json, type);
            for (Profile add : contactList){
                //System.out.print(add.id + "\n" + add.dc+ "\n" + add.area+ "\n" + add.adres+ "\n" + add.price);
                aid = "Объявление №"+add.id;
                adc= "Дата Постройки: "+add.dc;
                ads="Дата Объявления: "+add.ds;
                arealtor = add.realtor;
                ausername="Риелтор: "+add.username;
                acity="Город: "+add.city;
                aarea="Район: "+add.area;
                aadres="Адрес: "+add.adres;
                aservice="Услуга: "+add.service;
                asquare="Площадь: "+add.square;
                aterm="Срок: "+add.term;
                aprice="Цена: "+add.price;
                apay="Оплата: "+add.pay;
                aperair="Ремонт: "+add.perair;
                apic = add.pic;
            }

            String urldisplay = apic;
            mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                //Log.e("Error", e.getMessage());
                e.printStackTrace();
            }


            // ничего не возвращаем потому что я так захотел)
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // после запроса обновляем листвью
            textView4.setText(aid);
            adddc.setText(adc);
            addds.setText(ads);
            arealtor = arealtor;
            addusername.setText(ausername);
            addcity.setText(acity);
            addarea.setText(aarea);
            addadres.setText(aadres);
            addservice.setText(aservice);
            addsquare.setText(asquare);
            addterm.setText(aterm);
            addprice.setText(aprice);
            addpay.setText(apay);
            addrepair.setText(aperair);
            apic = apic;
            imageView.setImageBitmap(mIcon11);


        }
    }
}