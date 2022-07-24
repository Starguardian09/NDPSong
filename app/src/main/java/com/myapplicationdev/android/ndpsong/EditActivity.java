package com.myapplicationdev.android.ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {
    Button btnUpdate, btnDelete;
    TextView tvSongT, tvSinger, tvYear;
    EditText etSongT, etSinger,etYear;
    RadioGroup radioGroup;
    RadioButton rb1,rb2,rb3,rb4,rb5;
    Songs data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        tvSongT = findViewById(R.id.tvSongT);
        etSongT = findViewById(R.id.etSongT);
        tvSinger = findViewById(R.id.tvSinger);
        etSinger = findViewById(R.id.etSinger);
        tvYear = findViewById(R.id.tvYear);
        etYear = findViewById(R.id.etYear);
        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);
        rb3=findViewById(R.id.rb3);
        rb4=findViewById(R.id.rb4);
        rb5=findViewById(R.id.rb5);

        Intent i = getIntent();
        data = (Songs) i.getSerializableExtra("data");

        tvSongT.setText("ID: " + data.getId());
        etSongT.setText(data.getSongContent());
        btnUpdate.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setSongContent(etSongT.getText().toString());
                dbh.updateSong(data);
                dbh.close();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(data.getId());

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setSongContent(etSongT.getText().toString());
                Intent i2 = new Intent(EditActivity.this,
                        MainActivity.class);
                i2.putExtra("data", data);
                startActivity(i2);
                dbh.updateSong(data);
                dbh.close();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(data.getId());
                Intent i2 = new Intent(EditActivity.this,
                        MainActivity.class);
                i2.putExtra("data", data);
                startActivity(i2);
                dbh.updateSong(data);
                dbh.close();
            }
        });

    }
}