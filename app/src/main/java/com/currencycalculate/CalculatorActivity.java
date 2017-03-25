package com.currencycalculate;


import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Özge ONAY on 22.03.2017.
 */

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    String islemTipi="Satış";
    Double alisdegeri=3.62;
    Double satisdegeri=3.72;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_activity);
        Button doviz_tipini_degistir_btn = (Button) findViewById(R.id.doviz_tipini_degistir_btn);
        doviz_tipini_degistir_btn.setOnClickListener(this);

        init();


    }

    public void init() {
        final EditText tl_tutari_edt = (EditText) findViewById(R.id.tl_tutari_edt);
        final EditText tl_tutari_edt2 = (EditText) findViewById(R.id.tl_tutari_edt2);
        final EditText doviz_tutari_edt = (EditText) findViewById(R.id.doviz_tutari_edt);
        final EditText doviz_tutari_edt2 = (EditText) findViewById(R.id.doviz_tutari_edt2);
        Spinner alis_satis_spn= (Spinner)findViewById(R.id.alis_satis_spn);
        doviz_tutari_edt.setEnabled(false);
        doviz_tutari_edt2.setEnabled(false);
        alis_satis_spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                islemTipi=(String)parent.getItemAtPosition(position);
                String tl_tutari= tl_tutari_edt.getText().toString() + "." + tl_tutari_edt2.getText().toString();
                Double tl_parameter= Double.parseDouble(tl_tutari);
                Double doviz_tutari= calculateExchange(tl_parameter);
                String doviz_parameter= String.valueOf(doviz_tutari);
                String[] doviz=doviz_parameter.split("\\.");
                doviz_tutari_edt.setText(doviz[0]);
                doviz_tutari_edt2.setText(doviz[1]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        tl_tutari_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String tl_tutari= tl_tutari_edt.getText().toString() + "." + tl_tutari_edt2.getText().toString();
                Double tl_parameter= Double.parseDouble(tl_tutari);
                Double doviz_tutari= calculateExchange(tl_parameter);
                String doviz_parameter= String.valueOf(doviz_tutari);
                String[] doviz=doviz_parameter.split("\\.");
                doviz_tutari_edt.setText(doviz[0]);
                doviz_tutari_edt2.setText(doviz[1]);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tl_tutari_edt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String tl_tutari= tl_tutari_edt.getText().toString() + "." + tl_tutari_edt2.getText().toString();
                Double tl_parameter= Double.parseDouble(tl_tutari);
                Double doviz_tutari= calculateExchange(tl_parameter);
                String doviz_parameter= String.valueOf(doviz_tutari);
                String[] doviz=doviz_parameter.split("\\.");
                doviz_tutari_edt.setText(doviz[0]);
                doviz_tutari_edt2.setText(doviz[1]);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    public Double calculateExchange(double tl_tutari){
        double doviz_tutari=0;
        if(islemTipi.compareTo("Satış")==0){
                doviz_tutari= tl_tutari/satisdegeri;
        }
        if(islemTipi.compareTo("Alış")==0){
            doviz_tutari= tl_tutari/alisdegeri;
        }
        return Math.floor(doviz_tutari * 100) / 100;
    }
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(CalculatorActivity.this, ExchangeListActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EditText tl_tutari_edt = (EditText) findViewById(R.id.tl_tutari_edt);
        EditText tl_tutari_edt2 = (EditText) findViewById(R.id.tl_tutari_edt2);
        EditText doviz_tutari_edt = (EditText) findViewById(R.id.doviz_tutari_edt);
        EditText doviz_tutari_edt2 = (EditText) findViewById(R.id.doviz_tutari_edt2);
        if (resultCode == AppCompatActivity.RESULT_OK) {

            String dovizTipi = data.getStringExtra("dovizTipi");
            alisdegeri=data.getDoubleExtra("dovizAlis",alisdegeri);
            satisdegeri=data.getDoubleExtra("dovizSatis",satisdegeri);
            String tl_tutari= tl_tutari_edt.getText().toString() + "." + tl_tutari_edt2.getText().toString();
            Double tl_parameter= Double.parseDouble(tl_tutari);
            Double doviz_tutari= calculateExchange(tl_parameter);
            String doviz_parameter= String.valueOf(doviz_tutari);
            String[] doviz=doviz_parameter.split("\\.");
            doviz_tutari_edt.setText(doviz[0]);
            doviz_tutari_edt2.setText(doviz[1]);
            TextView doviz_birimi_txt = (TextView) findViewById(R.id.doviz_birimi_txt);
            doviz_birimi_txt.setText(dovizTipi);

        }

    }
}
