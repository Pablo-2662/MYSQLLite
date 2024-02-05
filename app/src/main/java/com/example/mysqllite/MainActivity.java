package com.example.mysqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText textoNombre, textoCiclo;
    Button botonInsertar, botonActualizar, botonConsultar;
    SQLiteDatabase baseDatos;
    TextView textoDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNombre = (EditText)findViewById(R.id.textoNombre);
        textoCiclo = (EditText) findViewById(R.id.textoCiclo);
        botonInsertar = (Button) findViewById(R.id.botonInsertar);
        botonActualizar = (Button) findViewById(R.id.botonActualizar);
        botonConsultar = (Button) findViewById(R.id.botonConsultar);
        textoDatos = (TextView) findViewById(R.id.datosRecogidos);



        //Establezco conexion
        AlumnosSQLLiteHelper baseDatosAlumno = new AlumnosSQLLiteHelper(this,"bbddAlumnos",null,1);
        baseDatos = baseDatosAlumno.getWritableDatabase();

        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = String.valueOf(textoNombre.getText());
                String ciclo = String.valueOf(textoCiclo.getText());


                /**PRIMERA FORMA**/
                String sentencia = "INSERT INTO ALUMNO (nombre, ciclo) VALUES ('"+nombre+"','"+ciclo+"');";
                baseDatos.execSQL(sentencia);

                /**SEGUNDA FORMA**/
                //ContentValues contenido = new ContentValues();
                //contenido.put("nombre",nombre);
                //contenido.put("ciclo",ciclo);
                //baseDatos.insert("alumno",null, contenido);
            }
        });

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //baseDatos.execSQL("UPDATE ALUMNO SET ciclo='D.A.M' WHERE ciclo='dam'");

                ContentValues cv = new ContentValues();
                cv.put("ciclo","D.A.M");
                baseDatos.update("alumno",cv,"ciclo='dam'",null);
            }
        });

        botonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Cursor c = baseDatos.rawQuery("SELECT id,nombre FROM alumno WHERE nombre=?", new String[]{"pablo"});
                  String cadena="";
                  if(c.moveToFirst()){
                      do{
                          int id=c.getInt(0);
                          String nombre = c.getString(1);
                          cadena+="ID: "+id+"NOMBRE: "+nombre+"\n";

                      }while(c.moveToNext());
                  }
                  textoDatos.setText(cadena);
                  c.close();
            }
        });


    }
}