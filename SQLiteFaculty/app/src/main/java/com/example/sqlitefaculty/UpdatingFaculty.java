package com.example.sqlitefaculty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UpdatingFaculty extends AppCompatActivity {

    EditText fmId;
    EditText fmName;
    EditText fmAdd;
    EditText fmDegree;

    String fId;
    String fName;
    String fAdd;
    String fDegree;

    List<stringName> facultyDetails;
    OpenHelper openHelper;
    SQLiteDatabase sqLiteDatabase;

    int rowId=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_update);
        openHelper = new OpenHelper(this);
        sqLiteDatabase=openHelper.getWritableDatabase();

        fmId=findViewById(R.id.editCodeUpdate);
        fmName=findViewById(R.id.editNameUpdate);
        fmAdd=findViewById(R.id.editDesUpdate);
        fmDegree=findViewById(R.id.editQuantityUpdate);

        rowId = getIntent().getIntExtra("prodId", -1);

        Cursor cursor = sqLiteDatabase.query(DatabaseInfo.TABLE_NAME, null, DatabaseInfo._ID + " = " + rowId, null, null,null, null);
        facultyDetails= new ArrayList<stringName>();
        facultyDetails.clear();

        if(cursor != null && cursor.getCount() != 0){
            while (cursor.moveToNext()) {
                fmId.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.facultyid)));
                fmName.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.facultyname)));
                fmAdd.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.facultyadd)));
                fmDegree.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.facultydegree)));
            }
        }else{
            Toast.makeText(this, "No Data Found!!", Toast.LENGTH_SHORT).show();
        }

    }

    public void clickUpdate(View view){
        fId=fmId.getText().toString();
        fName=fmName.getText().toString();
        fAdd=fmAdd.getText().toString();
        fDegree=fmDegree.getText().toString();

        if(TextUtils.isEmpty(fId) || TextUtils.isEmpty(fName) || TextUtils.isEmpty(fAdd) || TextUtils.isEmpty(fDegree)){
            Toast.makeText(this, "Check the Empty Fields", Toast.LENGTH_SHORT).show();
        }else {

            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseInfo.facultyid, fId);
            contentValues.put(DatabaseInfo.facultyadd, fAdd);
            contentValues.put(DatabaseInfo.facultyname, fName);
            contentValues.put(DatabaseInfo.facultydegree, fDegree);

            int updateId = sqLiteDatabase.update(DatabaseInfo.TABLE_NAME, contentValues, DatabaseInfo._ID + " = " + rowId, null);
            if (updateId != -1) {
                Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();

            } else {
                Toast.makeText(this, "Something Wrong!!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        super.onDestroy();
    }
}