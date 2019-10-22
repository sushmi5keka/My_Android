package com.israt.mydailyexpense;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class AddActivity extends AppCompatActivity {

    private FloatingActionButton add;
    private Spinner selectSp;
    private EditText amountEd;

    private ImageView imageIv;
    private Button cameraBtn, galleryBtn, dateBtn, timeBtn, insertBtn, updateBtn;
    private TextView addTv,editTv;

    private String id, amountt, typeOfExpense, date, time;
    private int amount;
//    private int image;

    private int idInt;

    private SqlHelper sqlHelper;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        init();

        spinnerTask();

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimePicker();
            }
        });

        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFromGallery();
            }
        });

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFromCamera();
            }
        });

        insertData();

        getData();

        setData();

        updateData();

//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(AddActivity.this,AddActivity.class);
//                startActivity(intent);
//            }
//        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.expenseList:

                        Intent intent = new Intent(AddActivity.this, ListActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.dashboard:

                        Intent intents = new Intent(AddActivity.this, DashboardActivity.class);
                        startActivity(intents);
                        return true;

                }

                return false;
            }
        });
    }


    private void spinnerTask() {

        final String[] typeExpense = {"select a expense type","Food","Transport","Phone Recharge","Medicine","Others"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(AddActivity.this, android.R.layout.simple_list_item_activated_1, typeExpense);
        selectSp.setAdapter(arrayAdapter);

        typeOfExpense = typeExpense[0];

        selectSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeOfExpense = typeExpense[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void insertData() {

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = Integer.valueOf(amountEd.getText().toString());
                date = dateBtn.getText().toString().trim();
                time = timeBtn.getText().toString().trim();


                long id = sqlHelper.insertData(typeOfExpense, amount, date, time);
                Toast.makeText(AddActivity.this, "Data inserted.." + id, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this, ListActivity.class);
//                startActivity(intent);
                getData();


                final String[] typeExpense = {"select a expense type","Food","Transport","Phone Recharge","Medicine","Others"};
                ArrayAdapter arrayAdapter = new ArrayAdapter(AddActivity.this, android.R.layout.simple_list_item_activated_1, typeExpense);
                selectSp.setAdapter(arrayAdapter);

                typeOfExpense = typeExpense[0];
                amountEd.setText("");
                dateBtn.setText("Date");
                timeBtn.setText("Time");
                imageIv.setImageResource(R.drawable.ic_image_black_24dp);

            }
        });

    }

    private void updateData() {

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = Integer.valueOf(amountEd.getText().toString());
                date = dateBtn.getText().toString().trim();
                time = timeBtn.getText().toString().trim();


                sqlHelper.updateData(idInt,typeOfExpense, amount, date, time);
                Toast.makeText(AddActivity.this, "Data updated.." + id, Toast.LENGTH_SHORT).show();

                final String[] typeExpense = {"select a expense type","Food","Transport","Phone Recharge","Medicine","Others"};
                ArrayAdapter arrayAdapter = new ArrayAdapter(AddActivity.this, android.R.layout.simple_list_item_activated_1, typeExpense);
                selectSp.setAdapter(arrayAdapter);

                typeOfExpense = typeExpense[0];
                amountEd.setText("");
                dateBtn.setText("Date");
                timeBtn.setText("Time");
                imageIv.setImageResource(R.drawable.ic_image_black_24dp);

            }
        });

    }

    private void getData() {

        id = getIntent().getStringExtra("id");
        typeOfExpense = getIntent().getStringExtra("type");
        amountt = getIntent().getStringExtra("amount");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setData() {

        int selectposition = 0;

        final String[] typeExpense = {"select a expense type","Food","Transport","Phone Recharge","Medicine","Others"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(AddActivity.this, android.R.layout.simple_list_item_activated_1, typeExpense);
        selectSp.setAdapter(arrayAdapter);
        for (int i = 0; i < typeExpense.length; i++) {
            if (Objects.equals(typeOfExpense, typeExpense[i])) {
                selectposition = i;
            }
        }

//        for (int i = 0; i < typeExpense.length; i++) {
//            if (typeOfExpense.equals(typeExpense[i])) {
//                selectposition = i;
//            }
//        }

        selectSp.setSelection(selectposition);

        selectSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeOfExpense = typeExpense[position];
                //Toast.makeText(getApplicationContext(),typeOfExpense+" is selected",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (id != null) {

            insertBtn.setVisibility(View.INVISIBLE);
            updateBtn.setVisibility(View.VISIBLE);
            addTv.setVisibility(View.INVISIBLE);
            editTv.setVisibility(View.VISIBLE);

            idInt = Integer.parseInt(id);

            amountEd.setText(amountt);
            dateBtn.setText(date);
            timeBtn.setText(time);

        }

    }


    private void openDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String currentDate = year + "/" + month + "/" + day;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

                Date date = null;
                try {
                    date = simpleDateFormat.parse(currentDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dateBtn.setText(simpleDateFormat.format(date));
//                long dateInmilis = date.getTime();
            }
        };

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this, dateSetListener, year, month, day);
        datePickerDialog.show();


    }

    private void openTimePicker() {

        AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
        View view = getLayoutInflater().inflate(R.layout.custom_time_picker, null);

        Button doneBtn = view.findViewById(R.id.doneBtn);
        final TimePicker timePicker = view.findViewById(R.id.timePicker);

        builder.setView(view);

        final Dialog dialog = builder.create();
        dialog.show();

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa");

                @SuppressLint({"NewApi", "LocalSuppress"}) int hour = timePicker.getHour();
                @SuppressLint({"NewApi", "LocalSuppress"}) int min = timePicker.getMinute();

                Time time = new Time(hour, min, 0);

                timeBtn.setText(timeFormat.format(time));
                dialog.dismiss();

            }
        });

    }

    private void getImageFromGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 0);

    }

    private void getImageFromCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);

    }

    private void init() {
        add = findViewById(R.id.addFab);
        selectSp = findViewById(R.id.selectSp);
        amountEd = findViewById(R.id.amountEt);
        dateBtn = findViewById(R.id.dateBtn);
        timeBtn = findViewById(R.id.timeBtn);
        imageIv = findViewById(R.id.imageIv);
        galleryBtn = findViewById(R.id.galleryBtn);
        cameraBtn = findViewById(R.id.camaraBtn);
        insertBtn = findViewById(R.id.insertBtn);
        updateBtn = findViewById(R.id.updateBtn);
        sqlHelper = new SqlHelper(this);
        addTv = findViewById(R.id.addTv);
        editTv = findViewById(R.id.editTv);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 0) {
                Uri uri = data.getData();
                imageIv.setImageURI(uri);

            } else if (requestCode == 1) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                imageIv.setImageBitmap(bitmap);
            }
        }

    }
}
