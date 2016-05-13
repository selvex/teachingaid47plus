package siriuscyberneticscorporation.teachingaid47plus;

import com.orm.SugarRecord;

/**
 * Created by Johannes on 29.04.2016.
 */
public class Subject extends SugarRecord{

    private String name;

    private SchoolClass schoolClass;

    public Subject(String name, SchoolClass schoolClass) {
        this.name = name;
        this.schoolClass = schoolClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject() {}
}
