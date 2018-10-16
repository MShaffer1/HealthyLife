package michelle.healthylife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBhandler extends SQLiteOpenHelper {


    private static final String TAG = "DatabaseHelper";


    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "recipes.db";

    //Table Name
    public static final String TABLE_RECIPES = "recipes";

    //Table Values
    public static final String COL0 = "id";
    public static final String COL1 = "recipeName";
    public static final String COL2 = "ingredients";
    public static final String COL3 = "instructions";
    public static final String COL4 = "category";


    public DBhandler(Context context) {
        super(context, "recipes.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + DATABASE_NAME + " ( " + COL0 +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL1 + " TEXT," + COL2 +
                " TEXT," + COL3 + " TEXT," + COL4 + " TEXT " + ")";
        db.execSQL(createTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);

    }

    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item);


        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_RECIPES);

        long result = db.insert(TABLE_RECIPES, null, contentValues);

        if (result == -1) {
            return false;
        } else {

            return true;
        }

    }


     protected String deleteData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return String.valueOf(db.delete(TABLE_RECIPES, "recipeName = ?",new String[] {name}));
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_RECIPES;
        Cursor data = db.rawQuery(query, null);
        return data;
    }




    public Cursor getItemID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_RECIPES +
                " WHERE " + COL2 + " WHERE " + COL3 + " WHERE " + COL4 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public void updateName(String newName, int id, String oldName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_RECIPES + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }
}



