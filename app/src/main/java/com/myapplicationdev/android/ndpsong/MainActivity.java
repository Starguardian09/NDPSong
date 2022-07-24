package com.myapplicationdev.android.ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);
        rb3=findViewById(R.id.rb3);
        rb4=findViewById(R.id.rb4);
        rb5=findViewById(R.id.rb5);
        lv = findViewById(R.id.lv);

        Intent i = getIntent();
        Songs data = (Songs) i.getSerializableExtra("data");

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


                String data = etSongT.getText().toString();
                String data1 = etSinger.getText().toString();
                String data2 = etYear.getText().toString();
                int data3 = radioGroup.getCheckedRadioButtonId();

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(data,data1,data2,data3);

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