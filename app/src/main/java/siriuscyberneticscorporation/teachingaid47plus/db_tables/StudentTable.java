package siriuscyberneticscorporation.teachingaid47plus.db_tables;

import android.provider.BaseColumns;

/**
 * Created by SeLveX on 04.05.2016.
 */
public final class StudentTable {
    public StudentTable(){};

    public static abstract class StudentTableEntry implements BaseColumns {
        public static final String TABLE_NAME = "t_student";
        public static final String COLUMN_NAME_ID = "student_id";
        public static final String COLUMN_NAME_NAME = "student_name";
        public static final String COLUMN_NAME_CPERSON_NAME = "student_cperson_name";
        public static final String COLUMN_NAME_CPERSON_TEL = "student_cperson_tel";
        public static final String COLUMN_NAME_CPERSON_EMAIL = "student_cperson_email";
        public static final String COLUMN_NAME_ADDRESS = "student_address";
        public static final String COLUMN_NAME_NOTE = "student_note";
    }


}
