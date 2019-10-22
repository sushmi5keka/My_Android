package com.israt.mydailyexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView typeDtv, amountDtv, dateDtv, timeDtv;
    private String type, amount, date, time;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();

        getData();

        setData();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });


    }


    private void getData() {

        type = getIntent().getStringExtra("type");
        amount = getIntent().getStringExtra("amount");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");


    }

    private void setData() {

        typeDtv.setText(type);
        amountDtv.setText(amount);
        dateDtv.setText(date);
        timeDtv.setText(time);

    }

    private void init() {

        typeDtv = findViewById(R.id.typeDTv);
        amountDtv = findViewById(R.id.amountDTv);
        dateDtv = findViewById(R.id.dateDTv);
        timeDtv = findViewById(R.id.timeDTv);
        backBtn = findViewById(R.id.backBtn);
    }
}
