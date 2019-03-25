package com.coderbd.day2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MahbubActivity extends AppCompatActivity {

    EditText name, pass;
    Button btnAdd;
    ImageView goAnother;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText) findViewById(R.id.txtName);
        pass=(EditText)findViewById(R.id.editText2);
        btnAdd=(Button)findViewById(R.id.button);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb=new StringBuilder();
                sb.append("Name: "+name.getText().toString()+"\n");
                sb.append("Password: "+pass.getText().toString());
                Toast toast=Toast.makeText(getApplicationContext(),sb.toString(),Toast.LENGTH_LONG);
                toast.show();
            }
        });

        goAnother=(ImageView) findViewById(R.id.imageView2);
        goAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MahbubActivity.this, AnotherActivity.class);
                startActivity(intent);
            }
        });
    }
}
