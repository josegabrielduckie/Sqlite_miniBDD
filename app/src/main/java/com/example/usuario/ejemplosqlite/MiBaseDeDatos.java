package com.example.usuario.ejemplosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MiBaseDeDatos extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "misestudiantes.db";
    public static final String TABLE_NAME = "estudiantes";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "APELLIDOS";
    public static final String COL_4 = "NOTA";

    public MiBaseDeDatos(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_2 + " TEXT," + COL_3 + " TEXT, " + COL_4 + " TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean insertar(String nombre, String apellidos, String nota){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nombre);
        contentValues.put(COL_3, apellidos);
        contentValues.put(COL_4, nota);
        long result = db.insert(TABLE_NAME,null, contentValues);
        db.close();
        if (result==-1){
            return false;
        }else{
            return true;
        }

    }

    //metodo cursor para listar
    Cursor listar(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos = db.rawQuery("Select * from "+TABLE_NAME,null);
        return  datos;//NOS DEVOLVERA UN OBJETO CURSOR
    }

    public boolean modificar(String id,String nombre, String apellidos, String nota){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contenido=new ContentValues();
        contenido.put(COL_1, id);
        contenido.put(COL_2, nombre);
        contenido.put(COL_3, apellidos);
        contenido.put(COL_4, nota);
        ///SERIA LO MISMO QUE UPDATE [TABLA] SET [CAMPO] WHERE [CAMPO]="VALOR" OR [CAMPO]="VALOR" OR [CAMPO]="VALOR"
        db.update(TABLE_NAME,contenido,"ID=?",new String[]{id});
        return true;
    }

    public boolean borrar(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contenido=new ContentValues();
        contenido.put(COL_1, id);
                    ///SERIA LO MISMO QUE DELETE FROM [TABLA] WHERE [CAMPO]="VALOR"
        db.delete(TABLE_NAME,"ID=?",new String[]{id});
        return true;
    }
}
