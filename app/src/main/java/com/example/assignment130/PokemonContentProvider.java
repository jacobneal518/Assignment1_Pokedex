package com.example.assignment130;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PokemonContentProvider extends ContentProvider {


    public final static String DBNAME = "PokemonDB";

    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(SQL_CREATE_MAIN);
        }
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    };


    public final static String TABLE_NAMESTABLE = "Names";

    public final static String COLUMN_ID = "_ID";
    public final static String COLUMN_NATIONAL_NUMBER = "NATIONAL_NUMBER";
    public final static String COLUMN_NAME = "NAME";

    public final static String COLUMN_SPECIES = "SPECIES";
    public final static String COLUMN_GENDER = "GENDER";

    public final static String COLUMN_HEIGHT = "HEIGHT";
    public final static String COLUMN_WEIGHT = "WEIGHT";

    public final static String COLUMN_LEVEL = "PRICE";

    public final static String COLUMN_HP = "HP";
    public final static String COLUMN_ATK = "ATK";
    public final static String COLUMN_DEF = "DEF";




    public final static String[] dbColumns = {
            COLUMN_ID,
            COLUMN_NATIONAL_NUMBER,
            COLUMN_NAME,
            COLUMN_SPECIES,
            COLUMN_GENDER,
            COLUMN_HEIGHT,
            COLUMN_WEIGHT,
            COLUMN_LEVEL,
            COLUMN_HP,
            COLUMN_ATK,
            COLUMN_DEF
    };

    public static final String AUTHORITY = "com.example.provider.pokemon";
    public static final Uri CONTENT_URI = Uri.parse(
            "content://" + AUTHORITY +"/" + TABLE_NAMESTABLE);

    private static UriMatcher sUriMatcher;

    private MainDatabaseHelper mOpenHelper;

    private static final String SQL_CREATE_MAIN = "CREATE TABLE " +
            TABLE_NAMESTABLE +  // Table's name
            "(" +               // The columns in the table
            COLUMN_ID +
            " INTEGER PRIMARY KEY, " +
            COLUMN_NATIONAL_NUMBER +
            " INTEGER," +
            COLUMN_NAME +
            " TEXT," +
            COLUMN_SPECIES +
            " TEXT," +
            COLUMN_GENDER +
            " TEXT," +
            COLUMN_HEIGHT +
            " REAL," +
            COLUMN_WEIGHT +
            " REAL," +
            COLUMN_LEVEL +
            " INTEGER," +
            COLUMN_HP +
            " INTEGER," +
            COLUMN_ATK +
            " INTEGER," +
            COLUMN_DEF +
            " INTEGER" +
            ")";


    public PokemonContentProvider() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().delete(TABLE_NAMESTABLE, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        String nationalNumber = values.getAsString(COLUMN_NATIONAL_NUMBER).trim();
        String name = values.getAsString(COLUMN_NAME).trim();
        String species = values.getAsString(COLUMN_SPECIES).trim();
        String gender = values.getAsString(COLUMN_GENDER).trim();
        String height = values.getAsString(COLUMN_HEIGHT).trim();
        String weight = values.getAsString(COLUMN_WEIGHT).trim();
        String level = values.getAsString(COLUMN_LEVEL).trim();
        String hp = values.getAsString(COLUMN_HP).trim();
        String atk = values.getAsString(COLUMN_ATK).trim();
        String def = values.getAsString(COLUMN_DEF).trim();






        if (nationalNumber.equals("") || name.equals("") || species.equals("") || gender.equals("") || height.equals("") || weight.equals("") || level.equals("") || hp.equals("") || atk.equals("") || def.equals(""))
            return null;


        long id = mOpenHelper.getWritableDatabase().insert(TABLE_NAMESTABLE, null, values);

        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return mOpenHelper.getReadableDatabase().query(TABLE_NAMESTABLE, projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().update(TABLE_NAMESTABLE, values, selection, selectionArgs);
    }
}
