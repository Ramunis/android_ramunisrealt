package com.example.ramunisreallt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

public class MainActivity6 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        Intent myIntent;

        myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.1.64/newrent"));
        startActivity(myIntent);

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

}