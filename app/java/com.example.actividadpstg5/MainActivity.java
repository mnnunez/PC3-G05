package com.example.actividadpstg5;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText matricula, nombres, apellidos, genero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matricula=(EditText)findViewById(R.id.editTextPersonName4);
        nombres=(EditText)findViewById(R.id.editTextPersonName);
        apellidos=(EditText)findViewById(R.id.editTextPersonName2);
        genero=(EditText)findViewById(R.id.editTextPersonName3);
    }
}

