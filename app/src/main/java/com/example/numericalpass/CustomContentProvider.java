package com.example.numericalpass;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by deepaksood619 on 7/6/16.
 */

public class CustomContentProvider extends ContentProvider {

    private static final String TAG = CustomContentProvider.class.getSimpleName();

    private static final String PROVIDER_NAME = "com.example.numericalpass.provider";
    static final String URL = "content://"+PROVIDER_NAME+"/users";
    static final Uri CONTENT_URI = Uri.parse(URL);

    private static HashMap<String, String> STUDENTS_PROJECTION_MAP;

    static final int STUDENTS = 1;
    static final int STUDENT_ID = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "users", STUDENTS);
        uriMatcher.addURI(PROVIDER_NAME, "users/#", STUDENT_ID);        // # Matches a string of numeric characters of any length.
    }

    private static final String DATABASE_NAME = "Evaluation";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Users";

    static final String COL_1 = "_id";
    static final String COL_2 = "username";
    static final String COL_3 = "number_of_successful_login";

    private static final String CREATE_DB_TABLE =
            "CREATE TABLE "+ TABLE_NAME +
                    " ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + " "+COL_2+" TEXT NOT NULL,"
                    + " "+COL_3+" TEXT NOT NULL);"
            ;

    private static final String DROP_DB_TABLE =
            "DROP TABLE IF EXISTS "
                    +TABLE_NAME
            ;

    private SQLiteDatabase db;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_DB_TABLE);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        Log.v(TAG,"onCreate");

        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        db = dbHelper.getWritableDatabase();
        return (db == null)? false : true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.v(TAG,"inside Query");
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                qb.setProjectionMap(STUDENTS_PROJECTION_MAP);
                break;

            case STUDENT_ID:
                qb.appendWhere( COL_1 +" = " +uri.getPathSegments().get(1));    //uri.getLastPathSegment();
                break;

            default:
                throw new IllegalArgumentException("unknown uri "+uri);
        }

        if(sortOrder == null || sortOrder == "") {
            sortOrder = COL_2;
        }

        Cursor cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.v(TAG,"inside getType");

        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                return "vnd.android.cursor.dir/vnd.example.users";

            case STUDENT_ID:
                return "vnd.android.cursor.item/vnd.example.users";

            default:
                throw new IllegalArgumentException("unsupported uri "+uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.v(TAG,"inside Insert");
        long rowId = db.insert( TABLE_NAME, "", values);

        if(rowId > 0) {
            Uri uri1 = ContentUris.withAppendedId(CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(uri1, null);
            return uri1;
        }
        throw new SQLException("Failed to add a record into "+uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
