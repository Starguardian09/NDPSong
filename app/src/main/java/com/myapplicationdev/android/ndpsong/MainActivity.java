package com.myapplicationdev.android.ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnList;
    TextView tvSongT, tvSinger, tvYear;
    EditText etSongT, etSinger,etYear;
    RadioGroup radioGroup;
    RadioButton rb1,rb2,rb3,rb4,rb5;
    ArrayList<Songs> al;
    ListView lv;
    ArrayAdapter<Songs> aa;
    Songs data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnUpdate);
        btnList = findViewById(R.id.btnDelete);
        tvSongT = findViewById(R.id.tvSongT);
        etSongT = findViewById(R.id.etSongT);
        tvSinger = findViewById(R.id.tvSinger);
        etSinger = findViewById(R.id.etSinger);
        tvYear = findViewById(R.id.tvYear);
        etYear = findViewById(R.id.etYear);
        radioGroup=findViewById(R.id.radiogroup);
        rb1=findViewById(R.id.rg1);
        rb2=findViewById(R.id.rg2);
        rb3=findViewById(R.id.rg3);
        rb4=findViewById(R.id.rg4);
        rb5=findViewById(R.id.rg5);
        lv = findViewById(R.id.lv);

        Intent i = getIntent();
        data = (Songs) i.getSerializableExtra("data");

        al = new ArrayList<Songs>();
        aa = new ArrayAdapter<Songs>(this,
                android.R.layout.simple_list_item_1, al);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,
                        ShowSong.class);
                startActivity(i);
            }

        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DBHelper dbh = new DBHelper(MainActivity.this);

                String title = etSongT.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int rg = 1;

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rg1:
                        rg = 1;
                        break;
                    case R.id.rg2:
                        rg = 2;
                        break;
                    case R.id.rg3:
                        rg = 3;
                        break;
                    case R.id.rg4:
                        rg = 4;
                        break;
                    case R.id.rg5:
                        rg = 5;
                        break;
                }

                long inserted_id = dbh.insertSong(title,singer,year,rg);

                if (inserted_id != -1){
                    al.clear();
                    al.addAll(dbh.getAllSong());
                    aa.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


}