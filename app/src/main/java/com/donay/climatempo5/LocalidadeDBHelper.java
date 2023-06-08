package com.donay.climatempo5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalidadeDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "localidades2.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "localidades";
    private static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_UF = "uf";

    public LocalidadeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME + " TEXT, " +
                COLUMN_UF + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public void insertLocalidade(Localidade localidade) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, localidade.getId());
        values.put(COLUMN_NOME, localidade.getNome());
        values.put(COLUMN_UF, localidade.getUf());
        String whereClause = COLUMN_ID + "=?";
        String[] whereArgs = {localidade.getId()};
        long result = db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (result == -1) {

        }
        db.close();
    }
    public Cursor getAllLocalidades() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID + " AS _id", COLUMN_NOME, COLUMN_UF};
        return db.query(TABLE_NAME, columns, null, null, null, null, null);
    }

}

