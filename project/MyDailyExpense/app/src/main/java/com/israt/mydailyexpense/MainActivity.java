package com.israt.mydailyexpense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton add;
    private ImageView imageMIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageMIv = findViewById(R.id.mainImageIv);

        imageMIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });


//        add = findViewById(R.id.addFab);
//
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//   Intent intent = new Intent(MainActivity.this,AddActivity.class);
//                startActivity(intent);
//            }
//        });

//        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//                switch (menuItem.getItemId()) {
//
//                    case R.id.expenseList:
//                        Intent intent = new Intent(MainActivity.this,ListActivity.class);
//                        startActivity(intent);
//                        return true;
//
//                    case R.id.dashboard:
//                        Intent intents = new Intent(MainActivity.this,DashboardActivity.class);
//                        startActivity(intents);
//                        return true;
//
//                }
//
//                return false;
//            }
//        });

    }



}
