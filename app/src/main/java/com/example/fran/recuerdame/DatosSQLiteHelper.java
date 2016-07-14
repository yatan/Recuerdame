package com.example.fran.recuerdame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yatan on 7/14/16.
 */
public class DatosSQLiteHelper extends SQLiteOpenHelper {
    String sqlCreate = "CREATE TABLE Peliculas " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "titulo TEXT )";

    public DatosSQLiteHelper(Context context, String nombre,
                             SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Peliculas");
        db.execSQL(sqlCreate);
    }
}
