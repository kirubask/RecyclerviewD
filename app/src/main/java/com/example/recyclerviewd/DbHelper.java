package com.example.recyclerviewd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.recyclerviewd.Modal.Details;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private Context context;
    public  static final String DATABASE_NAME = "MY_DATABASE.DB";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE = "userlist";
    public static final String KEY_ID = "id";
    public static final String USER_NAME = "name";
    public static final String DesCRIPTION = "description";
    private SQLiteDatabase sqLiteDatabase;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(" CREATE TABLE " + DATABASE_TABLE +
                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_NAME + " TEXT," +
                DesCRIPTION + " TEXT ) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }

         public void add(Details details){
        ContentValues cv = new ContentValues();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        cv.put(DbHelper.USER_NAME,details.getName());
        cv.put(DbHelper.DesCRIPTION,details.getDescription());
        sqLiteDatabase.insert(DbHelper.DATABASE_TABLE,null,cv);

    }

    public  List<Details>  getdata(){
        String sql = "Select * from "+DATABASE_TABLE;
        sqLiteDatabase = this.getReadableDatabase();

        List<Details> detailsList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                detailsList.add(new Details(id,name,description));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return detailsList;
    }
    public void delete(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(DATABASE_TABLE,KEY_ID +" = ? ",new String[]{String.valueOf(id)});
    }

    public void update(Details details){
        ContentValues cv = new ContentValues();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        cv.put(DbHelper.USER_NAME,details.getName());
        cv.put(DbHelper.DesCRIPTION,details.getDescription());
        sqLiteDatabase.update(DATABASE_TABLE,cv,KEY_ID +" =?",new String[]{String.valueOf(details.getId())} );


    }


}
//sqLiteDatabase.rawQuery("Select * from DATABASE_TABLE",null);