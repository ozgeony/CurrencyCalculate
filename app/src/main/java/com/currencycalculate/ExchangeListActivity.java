package com.currencycalculate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * Created by Özge ONAY on 24.03.2017.
 */

public class ExchangeListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exchange_list_activity);

        ListView doviz_tipi_list = (ListView) findViewById(R.id.doviz_tipi_list);

        doviz_tipi_list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(ExchangeListActivity.this,CalculatorActivity.class);
        String selectedItem=(String)parent.getItemAtPosition(position);
        String[] dovizTipi=selectedItem.split(" ");
        intent.putExtra("dovizTipi",dovizTipi[0]);
        intent.putExtra("dovizAlis",Double.parseDouble(dovizTipi[5]));
        intent.putExtra("dovizSatis",Double.parseDouble(dovizTipi[9]));
        setResult(AppCompatActivity.RESULT_OK,intent);
        finish();
    }
}
