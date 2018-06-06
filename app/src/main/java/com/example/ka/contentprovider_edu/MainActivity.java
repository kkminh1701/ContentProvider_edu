package com.example.ka.contentprovider_edu;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText edtName, edtGrade;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAddName(View view){
        ContentValues values = new ContentValues();

        values.put(StudentProvider.NAME, ((EditText) findViewById(R.id.edtName)).getText().toString());
        values.put(StudentProvider.GRADE, ((EditText) findViewById(R.id.edtGrade)).getText().toString());

        Uri uri = getContentResolver().insert(StudentProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_SHORT).show();
    }

    public void onClickRetrieveStudents (View view){
        String URI = "content://com.example.ka.provider.College/students";
        Uri students = Uri.parse(URI);
        Cursor c =  managedQuery(students, null, null, null, "name");
        if(c.moveToFirst()){
            do{
                Toast.makeText(this, c.getString(c.getColumnIndex(StudentProvider._ID)) +
                        ", " + c.getString(c.getColumnIndex(StudentProvider.NAME)) +
                        ", " + c.getString(c.getColumnIndex(StudentProvider.GRADE)), Toast.LENGTH_SHORT).show();
            }while (c.moveToNext());
        }
    }


}


