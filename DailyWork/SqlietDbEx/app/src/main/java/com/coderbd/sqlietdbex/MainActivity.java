package com.coderbd.sqlietdbex;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText id, name, qty;
    MyDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = (EditText) findViewById(R.id.productID);
        name = (EditText) findViewById(R.id.productName);
        qty = (EditText) findViewById(R.id.productQuantity);
        helper = new MyDbAdapter(this);
    }
    public void saveProduct(View view) {
        Product product = new Product(name.getText().toString(), Integer.parseInt(qty.getText().toString()));
        long i = helper.insertData(product);
        if (i < 0) {
            Message.message(this, "Unsuccessful");
        } else {
            Message.message(this, "Successful");
        }
    }
}
