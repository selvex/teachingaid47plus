package siriuscyberneticscorporation.teachingaid47plus.db_tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import siriuscyberneticscorporation.teachingaid47plus.Student;

public class DatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "teaching_aid.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT = "CREATE TABLE " + StudentTable.StudentTableEntry.TABLE_NAME + " ("
                + StudentTable.StudentTableEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY, "
                + StudentTable.StudentTableEntry.COLUMN_NAME_NAME + " TEXT, "
                + StudentTable.StudentTableEntry.COLUMN_NAME_CPERSON_NAME + " TEXT, "
                + StudentTable.StudentTableEntry.COLUMN_NAME_CPERSON_TEL + " TEXT, "
                + StudentTable.StudentTableEntry.COLUMN_NAME_CPERSON_EMAIL + " TEXT, "
                + StudentTable.StudentTableEntry.COLUMN_NAME_ADDRESS + " TEXT, "
                + StudentTable.StudentTableEntry.COLUMN_NAME_NOTE + " TEXT)"
                ;
        db.execSQL(CREATE_STUDENT);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + StudentTable.StudentTableEntry.TABLE_NAME);
        onCreate(db);
    }

    public long createStudent(Student std)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentTable.StudentTableEntry.COLUMN_NAME_NAME, std.getName());
        values.put(StudentTable.StudentTableEntry.COLUMN_NAME_CPERSON_NAME, std.getContactPersonName());
        values.put(StudentTable.StudentTableEntry.COLUMN_NAME_CPERSON_TEL, std.getContactPersonTelNumber());
        values.put(StudentTable.StudentTableEntry.COLUMN_NAME_CPERSON_EMAIL, std.getContactPersonEMail());
        values.put(StudentTable.StudentTableEntry.COLUMN_NAME_ADDRESS, std.getAddress());
        values.put(StudentTable.StudentTableEntry.COLUMN_NAME_NOTE, std.getNote());

        long student_id = db.insert(StudentTable.StudentTableEntry.TABLE_NAME, null, values);
        return student_id;
    }

    public Student getStudentById(long student_id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "select * from " + StudentTable.StudentTableEntry.TABLE_NAME + " WHERE "
                + StudentTable.StudentTableEntry.COLUMN_NAME_ID + " = " + student_id;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();
        else
            assert(false);

        Student returned_student = new Student();
        returned_student.setId(c.getInt(c.getColumnIndex(StudentTable.StudentTableEntry.COLUMN_NAME_ID)));
        returned_student.setName(c.getString(c.getColumnIndex(StudentTable.StudentTableEntry.COLUMN_NAME_NAME)));
        returned_student.setContactPersonName(c.getString(c.getColumnIndex(StudentTable.StudentTableEntry.COLUMN_NAME_CPERSON_NAME)));
        returned_student.setContactPersonTelNumber(c.getString(c.getColumnIndex(StudentTable.StudentTableEntry.COLUMN_NAME_CPERSON_TEL)));
        returned_student.setContactPersonEMail(c.getString(c.getColumnIndex(StudentTable.StudentTableEntry.COLUMN_NAME_CPERSON_EMAIL)));
        returned_student.setAddress(c.getString(c.getColumnIndex(StudentTable.StudentTableEntry.COLUMN_NAME_ADDRESS)));
        returned_student.setNote(c.getString(c.getColumnIndex(StudentTable.StudentTableEntry.COLUMN_NAME_NOTE)));

        return returned_student;
    }
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}