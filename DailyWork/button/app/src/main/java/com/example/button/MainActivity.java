package com.example.button;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText id;
    EditText productname;
    EditText quantity;
    MyDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
     id = (EditText)findViewById(R.id.ID);
     productname =(EditText)findViewById(R.id.NAME);
     quantity =(EditText)findViewById(R.id.QTY);
     helper = new MyDbAdapter(this);
 }

 public void  saveProduct(View view){
        Product product = new Product(productname.getText().toString(), Integer.parseInt(quantity.getText().toString()));
                long i = helper.insertData(product);
                if (i < 0){
                    Message.message(this,"Unsuccessful");
                }else {
                    Message.message(this,"Successfull");
                }
 }

}
