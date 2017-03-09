package com.naruto.dptest;

/**
 * Created by Codefingers-1 on 14-02-2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class DBController  extends SQLiteOpenHelper {
    public static final String DATABASENAME = "dpboss";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "records";
//day

    public static final String KEY_MDOPEN = "md_open";
    public static final String KEY_MDOPEN_DIGIT = "md_open_digit";
    public static final String KEY_MDCLOSE = "md_close";
    public static final String KEY_MDCLOSE_DIGIT = "md_close_digit";

//day
    public static final String KEY_KL_OPEN  = "kl_open";
    public static final String KEY_KL_OPEN_DIGIT  = "kl_open_digit";
    public static final String KEY_KL_CLOSE = "kl_close";
    public static final String KEY_KL_CLOSE_DIGIT = "kl_close_digit";
//night
    public static final String KEY_MNOPEN = "mn_open";
    public static final String KEY_MNOPEN_DIGIT = "mn_open_digit";
    public static final String KEY_MNCLOSE = "mn_close";
    public static final String KEY_MNCLOSE_DIGIT = "mn_close_digit";

    public static final String KEY_MUM_OPEN = "mum_open";
    public static final String KEY_MUM_OPEN_DIGIT = "mum_open_digit";
    public static final String KEY_MUM_CLOSE = "mum_close";
    public static final String KEY_MUM_CLOSE_DIGIT = "mum_close_digit";
    public static final String KEY_DATE = "_date";
    public static final String KEY_DAYOFMONTH = "_dayofweek";

    private static final String KEY_UPDATESTATUS = "updatestatus";


    public DBController(Context applicationcontext) {
        super(applicationcontext, Environment.getExternalStorageDirectory() +"/" + DATABASENAME, null,DATABASE_VERSION);
    }
    //Creates Table
    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE "+TABLE_NAME+" ("+KEY_DATE+"TEXT PRIMARY KEY, "+KEY_DAYOFMONTH+" TEXT, "+KEY_MDOPEN+" TEXT, "+KEY_MDOPEN_DIGIT+" TEXT, "+KEY_MDCLOSE+" TEXT, "+KEY_MDCLOSE_DIGIT+" TEXT, " +KEY_KL_OPEN+" TEXT, " +KEY_KL_OPEN_DIGIT+" TEXT, " +KEY_KL_CLOSE+" TEXT, " +KEY_KL_CLOSE_DIGIT+" TEXT, " +KEY_MNOPEN+" TEXT, " +KEY_MNOPEN_DIGIT+" TEXT, " +KEY_MNCLOSE+" TEXT, " +KEY_MNCLOSE_DIGIT+" TEXT, " +KEY_MUM_OPEN+" TEXT, " +KEY_MUM_OPEN_DIGIT+" TEXT, " +KEY_MUM_CLOSE+" TEXT, " +KEY_MUM_CLOSE_DIGIT+" TEXT, " +KEY_UPDATESTATUS+" TEXT)";
        database.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        database.execSQL(query);
        onCreate(database);
    }
    /**
     * Inserts User into SQLite DB
     * @param queryValues
     */
    public void insertUser(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE,queryValues.get(KEY_DATE));
        values.put(KEY_DAYOFMONTH,queryValues.get(KEY_DAYOFMONTH));
        values.put(KEY_MDOPEN, queryValues.get(KEY_MDOPEN));
        values.put(KEY_MDOPEN_DIGIT, queryValues.get(KEY_MDOPEN_DIGIT));
        values.put(KEY_MDCLOSE, queryValues.get(KEY_MDCLOSE));
        values.put(KEY_MDCLOSE_DIGIT, queryValues.get(KEY_MDCLOSE_DIGIT));

        values.put(KEY_KL_OPEN, queryValues.get(KEY_KL_OPEN));
        values.put(KEY_KL_OPEN_DIGIT, queryValues.get(KEY_KL_OPEN_DIGIT));
        values.put(KEY_KL_CLOSE, queryValues.get(KEY_KL_CLOSE));
        values.put(KEY_KL_CLOSE_DIGIT, queryValues.get(KEY_KL_CLOSE_DIGIT));

        values.put(KEY_MNOPEN, queryValues.get(KEY_MNOPEN));
        values.put(KEY_MNOPEN_DIGIT, queryValues.get(KEY_MNOPEN_DIGIT));
        values.put(KEY_MNCLOSE, queryValues.get(KEY_MNCLOSE));
        values.put(KEY_MNCLOSE_DIGIT, queryValues.get(KEY_MNCLOSE_DIGIT));

        values.put(KEY_MUM_OPEN, queryValues.get(KEY_MUM_OPEN));
        values.put(KEY_MUM_OPEN_DIGIT, queryValues.get(KEY_MUM_OPEN_DIGIT));
        values.put(KEY_MUM_CLOSE, queryValues.get(KEY_MUM_CLOSE));
        values.put(KEY_MUM_CLOSE_DIGIT, queryValues.get(KEY_MUM_CLOSE_DIGIT));

        values.put(KEY_UPDATESTATUS, "no");
        database.insertWithOnConflict(TABLE_NAME, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        database.close();
    }

    /**
     * Get list of Users from SQLite DB as Array List
     * @return
     */
    public ArrayList<HashMap<String, String>> getAllUsers() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM "+TABLE_NAME;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("userId", cursor.getString(0));
                map.put("userName", cursor.getString(1));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return wordList;
    }

    /**
     * Compose JSON out of SQLite records
     * @return
     */
    public String composeJSONfromSQLite(){
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM users where udpateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("userId", cursor.getString(0));
                map.put("userName", cursor.getString(1));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        Gson gson = new GsonBuilder().create();
        //Use GSON to serialize Array List to JSON
        return gson.toJson(wordList);
    }

    /**
     * Get Sync status of SQLite
     * @return
     */
    public String getSyncStatus(){
        String msg = null;
        if(this.dbSyncCount() == 0){
            msg = "SQLite and Remote MySQL DBs are in Sync!";
        }else{
            msg = "DB Sync neededn";
        }
        return msg;
    }

    /**
     * Get SQLite records that are yet to be Synced
     * @return
     */
    public int dbSyncCount(){
        int count = 0;
        String selectQuery = "SELECT  * FROM records where udpateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
    }

    /**
     * Update Sync status against each User ID
     * @param id
     * @param status
     */
    public void updateSyncStatus(String id, String status){
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "Update users set udpateStatus = '"+ status +"' where userId="+"'"+ id +"'";
        Log.d("query",updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}