package com.example.sqlitefaculty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    OpenHelper dbHelper;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter userAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Button btnRegister;

    List<stringName> facultyDetails;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new OpenHelper(this);
        sqLiteDatabase= dbHelper.getReadableDatabase();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);



        facultyDetails = new ArrayList<stringName>();
        facultyDetails.clear();
        Cursor c1 = sqLiteDatabase.query(DatabaseInfo.TABLE_NAME, null, null, null, null, null, null);

        if (c1 != null && c1.getCount() != 0) {
            facultyDetails.clear();
            while (c1.moveToNext()) {
                stringName fucDetails = new stringName();

                fucDetails.setP_id(c1.getInt(c1.getColumnIndex(DatabaseInfo._ID)));
                fucDetails.setP_idnum(c1.getString(c1.getColumnIndex(DatabaseInfo.facultyid)));
                fucDetails.setP_name(c1.getString(c1.getColumnIndex(DatabaseInfo.facultyname)));
                fucDetails.setP_address(c1.getString(c1.getColumnIndex(DatabaseInfo.facultyadd)));
                fucDetails.setP_degree(c1.getString(c1.getColumnIndex(DatabaseInfo.facultydegree)));
                facultyDetails.add(fucDetails);


            }


        }

        c1.close();
        layoutManager = new LinearLayoutManager(this);
        userAdapter = new RecycleAdapter(facultyDetails);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);


    }

    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        super.onDestroy();
    }

    public void clickAddProduct(View view){
        startActivity(new Intent(this,AddingFaculty.class));
    }
}