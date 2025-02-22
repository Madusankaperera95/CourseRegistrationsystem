package lk.cmjd.coursework.util;

import lk.cmjd.coursework.util.Enums.SemesterTypes;

public class SemesterUtil {

    private SemesterTypes key;

    private String value;

    public SemesterUtil() {
    }

    public SemesterUtil(SemesterTypes key, String value) {
        this.key = key;
        this.value = value;
    }

    public SemesterTypes getKey() {
        return key;
    }

    public void setKey(SemesterTypes key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
