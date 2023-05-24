package com.example.app6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtVal1, txtVal2, txtVal3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVal1=(TextView)findViewById(R.id.txtmatricula);
        txtVal2=(TextView)findViewById(R.id.txtnombre);
        txtVal3=(TextView)findViewById(R.id.txtapellido);

    }

    public void guardar(View view){

        admindb base = new admindb(this, "escuela", null, 1);
        base.getWritableDatabase();
        String matricula = txtVal1.getText().toString();
        String nombre = txtVal2.getText().toString();
        String apellido = txtVal3.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("matricula", matricula);
        registro.put("nombre", nombre);
        registro.put("apellido", apellido);

        Toast.makeText(this, "registro con exito", Toast.LENGTH_SHORT).show();

    }

    public void buscar(View view){
        admindb base = new admindb(this, "escuela",null,1);
        SQLiteDatabase BaseDatos = base.getWritableDatabase();

        String matricula = txtVal1.getText().toString();

        Cursor fila = BaseDatos.rawQuery("select nombre, apellido from alumno where matricula="
                + matricula, null);

    txtVal2.setText(fila.getString(0));
    txtVal3.setText(fila.getString(1));
    BaseDatos.close();
    }

    public void modificar(View view){
        admindb base = new admindb(this, "escuela", null, 1);
        SQLiteDatabase BaseDatos = base.getWritableDatabase();

        String matricula = txtVal1.getText().toString();
        String nombre = txtVal2.getText().toString();
        String apellido = txtVal3.getText().toString();

        ContentValues registo = new ContentValues();
        registo.put("matricula", matricula);
        registo.put("nombre", nombre);
        registo.put("apellido", apellido);

        BaseDatos.update("alumnos",registo,"matricula=" + matricula,null);
        BaseDatos.close();
    }

}