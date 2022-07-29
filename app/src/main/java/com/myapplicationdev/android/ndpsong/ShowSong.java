package com.myapplicationdev.android.ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.myapplicationdev.android.ndpsong.DBHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class ShowSong extends AppCompatActivity {


    ArrayList<com.myapplicationdev.android.ndpsong.Songs> al;
/*    ArrayList<com.myapplicationdev.android.ndpsong.Songs> alYear;
    Set<com.myapplicationdev.android.ndpsong.Songs> alYearWithoutDuplicates;
    ArrayList<String> alYearString;
     ArrayAdapter<Songs> aa;
    ArrayAdapter<com.myapplicationdev.android.ndpsong.Songs> aaYear;*/
    ListView lv;
    ToggleButton tbStar;
    CustomAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbh = new DBHelper(ShowSong.this);
        al.clear();
        al.addAll(dbh.getAllSongs());

        adapter.notifyDataSetChanged();

//        year = findViewById(R.id.yearFilter);
//
//        alYear = dbh.getAllYears();
////        alYearWithoutDuplicates = new LinkedHashSet<>(alYear);
////        alYearWithoutDuplicates.addAll(alYear);
////        alYear.clear();
////        alYear.addAll(alYearWithoutDuplicates);
//        aaYear = new ArrayAdapter(this,android.R.layout.simple_spinner_item,alYear);
//        aaYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        year.setAdapter(aaYear);
//
//        aaYear.notifyDataSetChanged();
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_song);

        lv = findViewById(R.id.lv2);
        tbStar = findViewById(R.id.btnShowSong);

        al = new ArrayList<com.myapplicationdev.android.ndpsong.Songs>();
        // aa = new ArrayAdapter<Songs>(this,
        // android.R.layout.simple_list_item_1, al);
        adapter = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(adapter);
        // lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                com.myapplicationdev.android.ndpsong.Songs data = al.get(position);
                Intent i = new Intent(ShowSong.this,
                        com.myapplicationdev.android.ndpsong.EditActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });


        tbStar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                String filterText = "5";

                DBHelper dbh = new DBHelper(ShowSong.this);

                al.clear();
                if(checked){
                    al.addAll(dbh.getAllFiveStar(filterText));
                }else{
                    al.addAll(dbh.getAllSongs());
                }

                adapter.notifyDataSetChanged();
            }
        });
    }
}