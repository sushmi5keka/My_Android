package com.israt.mydailyexpense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private FloatingActionButton add;
    private List<Expenses> list;
    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;

    private SqlHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        add = findViewById(R.id.addFab);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ListActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.expenseList:

//                        Intent intent = new Intent(ListActivity.this,ListActivity.class);
//                        startActivity(intent);
                        return true;

                    case R.id.dashboard:

                        Intent intents = new Intent(ListActivity.this,DashboardActivity.class);
                        startActivity(intents);
                        return true;

                }

                return false;
            }
        });

        init();

//
        String sql1="SELECT * FROM "+sqlHelper.TABLE_NAME;
        getData(sql1);
        //
    }
//
    private void getData(String sql) {

        Cursor cursor = sqlHelper.showData(sql);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(cursor.getColumnIndex(sqlHelper.COL_ID));
            String type = cursor.getString(cursor.getColumnIndex(sqlHelper.COL_TYPE));
            int amount = cursor.getInt(cursor.getColumnIndex(sqlHelper.COL_AMOUNT));
            String date = cursor.getString(cursor.getColumnIndex(sqlHelper.COL_DATE));
            String time = cursor.getString(cursor.getColumnIndex(sqlHelper.COL_TIME));


            list.add(new Expenses(id, type, amount, date, time));
            adapter.notifyDataSetChanged();

        }


    }

    private void init() {

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclearViewlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExpenseAdapter(list,this);
        recyclerView.setAdapter(adapter);

        sqlHelper = new SqlHelper(this);

    }
}
