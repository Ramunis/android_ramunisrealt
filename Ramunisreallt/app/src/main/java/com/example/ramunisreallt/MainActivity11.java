package com.example.ramunisreallt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity11 extends AppCompatActivity implements View.OnClickListener{

    private Button button6;
    private EditText c;
    private EditText s;
    private EditText t;
    private EditText b;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        button6 = (Button) findViewById(R.id.button6);
        c = (EditText) findViewById(R.id.vc);
        s = (EditText) findViewById(R.id.vs);
        t = (EditText) findViewById(R.id.vt);
        b = (EditText) findViewById(R.id.vb);
        text = (TextView)  findViewById(R.id.textView13);

        button6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        float cena=0;
        float start=0;
        float term=0;
        float bet=0;

        switch(v.getId()){

            case R.id.button6:
                cena = Float.parseFloat(c.getText().toString());
                start = Float.parseFloat(s.getText().toString());
                term = Float.parseFloat(t.getText().toString());
                bet = Float.parseFloat(b.getText().toString());

                float monthbet = bet/12/100;

                float firts =1+monthbet;
                float end = term*12;
                float commonbet = (float) Math.pow(firts, end);
                float monthpay = cena * monthbet * commonbet /(commonbet-1);

                float perpart = cena * monthbet;
                float mainpart = monthpay - perpart;

                float overpay = monthpay * (term*12)-cena;

                //alert(c+s+t+b);
                //document.getElementById('output').value = monthpay;
                text.setText("Платёж в месяц = "+monthpay+";Переплата = "+overpay);

                break;
            default:
                break;
        }
    }
}