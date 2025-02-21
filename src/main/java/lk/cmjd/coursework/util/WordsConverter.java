package lk.cmjd.coursework.util;

public class WordsConverter {

    public static String getRealValue(String val){

        String returnVal = null;
        if(val.equals("FIRSTSEMESTER")){
            returnVal= "First Semester";
        }
        if(val.equals("SECONDSEMESTER")){
            returnVal= "Second Semester";
        }

        if(val.equals("COMPLETED")){
            returnVal= "Completed";
        }
        if(val.equals("ONGOING")){
            returnVal= "Ongoing";
        }

        return returnVal;

    }
}
