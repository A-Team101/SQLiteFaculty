package com.example.sqlitefaculty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddingFaculty extends AppCompatActivity {

    OpenHelper openHelper;
    String fId;
    String fName;
    String fAdd;
    String fDegree;
    SQLiteDatabase sqLiteDatabase;

    EditText fmId;
    EditText fmName;
    EditText fmAdd;
    EditText fmDegree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_faculty);
        openHelper = new OpenHelper(this);
        sqLiteDatabase = openHelper.getWritableDatabase();

        fmId=findViewById(R.id.editID);
        fmName=findViewById(R.id.editName);
        fmAdd=findViewById(R.id.editAdd);
        fmDegree=findViewById(R.id.editDegree);



    }

    public void clickAdd(View view){
        fId=fmId.getText().toString();
        fName=fmName.getText().toString();
        fAdd=fmAdd.getText().toString();
        fDegree=fmDegree.getText().toString();

        if(TextUtils.isEmpty(fId) || TextUtils.isEmpty(fName) || TextUtils.isEmpty(fAdd) || TextUtils.isEmpty(fDegree)){

            Toast.makeText(this, "Check the Empty Fields", Toast.LENGTH_SHORT).show();
        }else {

            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseInfo._ID, (byte[]) null);
            contentValues.put(DatabaseInfo.facultyid, fId);
            contentValues.put(DatabaseInfo.facultyname, fName);
            contentValues.put(DatabaseInfo.facultyadd, fAdd);
            contentValues.put(DatabaseInfo.facultydegree, fDegree);
            long rowId = sqLiteDatabase.insert(DatabaseInfo.TABLE_NAME, null, contentValues);
            if (rowId != -1) {

                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();

            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        super.onDestroy();
    }
}