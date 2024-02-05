package com.example.mysqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AlumnosSQLLiteHelper extends SQLiteOpenHelper {

    String sqlCreacion = "CREATE TABLE alumno(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, ciclo TEXT)";
    String sqlBorrado = "DROP TABLE IF EXISTS alumno";

    public AlumnosSQLLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreacion); //Al crear una base de datos

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlBorrado);
        db.execSQL(sqlCreacion);
        //Para realizar una actualización de la bbdd

    }
}
