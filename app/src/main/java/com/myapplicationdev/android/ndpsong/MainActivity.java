package com.myapplicationdev.android.ndpsong;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShowList;
    EditText etSongTitle, etSingers, etYear;
    RadioGroup rating;
    RadioButton ratingButton;
    ArrayList<com.myapplicationdev.android.ndpsong.Songs> al;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnAdd);
        btnShowList = findViewById(R.id.btnDelete);
        etSongTitle = findViewById(R.id.etSongT);
        etSingers = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rating = findViewById(R.id.radiogroup);


        al = new ArrayList<com.myapplicationdev.android.ndpsong.Songs>();

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String songData = etSongTitle.getText().toString();
                String singerData = etSingers.getText().toString();
                int yearData = parseInt(etYear.getText().toString());
                int radioId = rating.getCheckedRadioButtonId();
                ratingButton = findViewById(radioId);
                int ratingData = parseInt(ratingButton.getText().toString());

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSongs(songData,singerData,yearData,ratingData);

                if (inserted_id != -1) {
                    al.clear();
                    al.addAll(dbh.getAllSongs());

                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,
                        com.myapplicationdev.android.ndpsong.ShowSong.class);
                startActivity(i);
            }
        });
    }
    public void checkButton(View v){
        int radioId = rating.getCheckedRadioButtonId();
        ratingButton = findViewById(radioId);
    }


}