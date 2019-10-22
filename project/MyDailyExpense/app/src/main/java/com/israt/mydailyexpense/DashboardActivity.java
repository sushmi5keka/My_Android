package com.israt.mydailyexpense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private Spinner selectDSp;
    private TextView totalExpTv;
    private String typeOfExpense;
    List<Expenses> expanseList;
    SqlHelper helper;
    double totaAmount = 0.0;
//    double totaAmountt = 0.0;
    private FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init();
        spinnerTask();
        geDataFromDatabase();


//        add = findViewById(R.id.addFab);
//
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(DashboardActivity.this,AddActivity.class);
//                startActivity(intent);
//            }
//        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.expenseList:

                        Intent intent = new Intent(DashboardActivity.this, ListActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.dashboard:
//                        Intent intents = new Intent(DashboardActivity.this,DashboardActivity.class);
//                        startActivity(intents);
                        return true;

                }

                return false;
            }
        });



    }

    private void geDataFromDatabase() {

        expanseList.clear();

        String types = selectDSp.getSelectedItem().toString();

        String sql = "SELECT * FROM " + helper.TABLE_NAME + " WHERE " + helper.COL_TYPE + " LIKE '%" + types + "%'";

        Cursor cursor = helper.showData(sql);


        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(helper.COL_ID));
            String sql1 = "SELECT * FROM " + helper.TABLE_NAME + " WHERE " + helper.COL_TYPE + " LIKE '%" + types + "%' AND Id =" + id;
            sqlquary(sql1);

        }

        totalExpTv.setText(String.valueOf(totaAmount)+ "  Taka");

    }

    private void sqlquary(String sql) {

        Cursor cursor = helper.showData(sql);
        if (cursor.moveToNext()) {

            String amount = cursor.getString(cursor.getColumnIndex(helper.COL_AMOUNT));
            double newAmountAdd = Double.parseDouble(amount);
            totaAmount = totaAmount + newAmountAdd;

        }

    }

    private void init() {

        selectDSp = findViewById(R.id.selectDSp);
        totalExpTv = findViewById(R.id.totalExpTv);

        helper = new SqlHelper(this);
        expanseList = new ArrayList<>();
    }

    private void spinnerTask() {

        final String[] typeExpense = {"select a expense type", "Food", "Transport", "Phone Recharge", "Medicine", "Others"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(DashboardActivity.this, android.R.layout.simple_list_item_activated_1, typeExpense);
        selectDSp.setAdapter(arrayAdapter);

        typeOfExpense = typeExpense[0];

        selectDSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeOfExpense = typeExpense[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
