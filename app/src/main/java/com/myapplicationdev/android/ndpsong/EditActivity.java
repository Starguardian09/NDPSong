package com.myapplicationdev.android.ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {
    Button btnUpdate, btnDelete, btnCancel;
    TextView tvSongT, tvSinger, tvYear, tvID,tvStars;
    EditText etSongT, etSinger,etYear;
    RadioGroup radioGroup;
    RadioButton rg0,rg1,rg2,rg3,rg4,rg5;
    Songs data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        tvID = findViewById(R.id.tvID);
        tvSongT = findViewById(R.id.tvSongT);
        tvSinger = findViewById(R.id.tvSinger);
        tvYear = findViewById(R.id.tvYear);
        tvStars = findViewById(R.id.tvStars);
        etSinger = findViewById(R.id.etSinger);
        etSongT = findViewById(R.id.etSongT);
        etYear = findViewById(R.id.etYear);
        radioGroup = findViewById(R.id.RadioGroup);


        rg1 = findViewById(R.id.rg1);
        rg2 = findViewById(R.id.rg2);
        rg3 = findViewById(R.id.rg3);
        rg4 = findViewById(R.id.rg4);
        rg5 = findViewById(R.id.rg5);


        //initialize the variables with UI here

        Intent i = getIntent();
        data = (Songs) i.getSerializableExtra("data");
        tvID.setText("Song ID : " +data.getId()+"");



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setSingers(etSinger.getText().toString());
                data.setTitle(etSongT.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                int rg = radioGroup.getCheckedRadioButtonId();
                rg0 = findViewById(rg);
                int rate = Integer.parseInt(rg0.getText().toString());
                Log.d("result", rg0+"");

                data.setStars(rate);

                dbh.updateSong(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                int id = data.getId();
                Log.d("song id: ", id+"");

                dbh.deleteSong(data.getId());
                finish();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}