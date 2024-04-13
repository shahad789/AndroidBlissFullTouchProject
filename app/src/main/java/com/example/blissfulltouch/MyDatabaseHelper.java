package com.example.blissfulltouch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BlissfulTouch.db";
    public static final int DATABASE_VERSION = 1;

    // Table names and column names
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_NAME = "clientname";
    public static final String COLUMN_PASSWORD = "pass";
    public static final String COLUMN_AGE = "age";

    public static final String TABLE_SERVICES = "services";
    public static final String COLUMN_SERVICE_NAME = "servicenm";

    public static final String TABLE_RESERVATION = "reservation";
    public static final String COLUMN_RESERVATION_ID = "id";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_USER_EMAIL_FK = "userEmail"; // User's email as foreign key
    public static final String COLUMN_SERVICE_NAME_FK = "serviceName"; // Service name as foreign key

    // Method to retrieve all reservations from the database
    public Cursor getAllReservations() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_RESERVATION, null);
    }


    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create users table
        String createUserTableQuery = "CREATE TABLE " + TABLE_USERS +
                "(" + COLUMN_EMAIL + " TEXT PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AGE + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createUserTableQuery);

        // Create services table with just the service name column
        String createServiceTableQuery = "CREATE TABLE " + TABLE_SERVICES +
                "(" + COLUMN_SERVICE_NAME + " TEXT PRIMARY KEY)";
        db.execSQL(createServiceTableQuery);

        // Create reservation table
        String createReservationTableQuery = "CREATE TABLE " + TABLE_RESERVATION +
                "(" + COLUMN_RESERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_TIME + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_USER_EMAIL_FK + " TEXT, " +
                COLUMN_SERVICE_NAME_FK + " TEXT, " +
                "FOREIGN KEY(" + COLUMN_USER_EMAIL_FK + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_EMAIL + "), " +
                "FOREIGN KEY(" + COLUMN_SERVICE_NAME_FK + ") REFERENCES " + TABLE_SERVICES + "(" + COLUMN_SERVICE_NAME + "))";
        db.execSQL(createReservationTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATION);
        onCreate(db);
    }

    // Method to add a user to the database
    public boolean addUser(String email, String name, String age, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_AGE, age);
        contentValues.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, contentValues);
        return result != -1;
    }

    // Method to add a new service to the database
    public boolean addService(String serviceName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SERVICE_NAME, serviceName);
        long result = db.insert(TABLE_SERVICES, null, contentValues);
        return result != -1;
    }



    // Method to retrieve user information by email
    public Cursor getUserInfo(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + "=?", new String[]{email});
    }

    // Method to retrieve service information by service name
    public Cursor getServiceInfo(String serviceName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_SERVICES + " WHERE " + COLUMN_SERVICE_NAME + "=?", new String[]{serviceName});
    }

    // Method to retrieve reservation information by service name
    public Cursor getReservationInfo(String serviceName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_RESERVATION + " WHERE " + COLUMN_SERVICE_NAME_FK + "=?", new String[]{serviceName});
    }



    // Method to update reservation information
    public boolean updateReservation(int id, String location, String time, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LOCATION, location);
        contentValues.put(COLUMN_TIME, time);
        contentValues.put(COLUMN_DATE, date);
        int result = db.update(TABLE_RESERVATION, contentValues, COLUMN_RESERVATION_ID + "=?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    // Method to delete a reservation from the database
    public boolean deleteReservation(int reservationId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_RESERVATION, COLUMN_RESERVATION_ID + "=?", new String[]{String.valueOf(reservationId)});
        return result > 0;
    }

    public void deleteAllReservations() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESERVATION, null, null);
        db.close();
    }


    public boolean checkemail(String mail) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ?", new String[]{mail});
        boolean exists = cursor.getCount() > 0;
        cursor.close(); // Close the cursor
        MyDB.close(); // Close the database connection
        return exists;
    }

    public boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{email, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close(); // Close the cursor
        MyDB.close(); // Close the database connection
        return exists;
    }

    public boolean addReservation(String location, String time, String date, String userEmail, String serviceName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LOCATION, location);
        contentValues.put(COLUMN_TIME, time);
        contentValues.put(COLUMN_DATE, date);
        // contentValues.put(COLUMN_USER_EMAIL_FK, userEmail);
        //contentValues.put(COLUMN_SERVICE_NAME_FK, serviceName);
        long result = db.insert(TABLE_RESERVATION, null, contentValues);
        return result != -1;
    }


    public Cursor getAllReservationsCursor() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_RESERVATION, null, null, null, null, null, null);
    }


}
