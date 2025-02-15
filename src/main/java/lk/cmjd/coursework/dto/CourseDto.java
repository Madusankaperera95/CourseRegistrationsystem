package lk.cmjd.coursework.dto;

public class CourseDto {
    private String CourseId;
    private String CourseName;
    private String CreditHours;
    private int department;
    private int prerequisites;

    private String departMentName;
    private String PreRequistName;
    private String MaximumCapacity;

    private String description;

    public CourseDto() {
    }

    public CourseDto(String courseId, String courseName, String creditHours, int department, int prerequisites, String maximumCapacity,String description) {
        CourseId = courseId;
        CourseName = courseName;
        CreditHours = creditHours;
        this.department = department;
        this.prerequisites = prerequisites;
        MaximumCapacity = maximumCapacity;
        this.description = description;
    }

    public CourseDto(String courseId, String courseName, String creditHours, String departMentName, String preRequistName, String maximumCapacity,String description) {
        CourseId = courseId;
        CourseName = courseName;
        CreditHours = creditHours;
        this.departMentName = departMentName;
        PreRequistName = preRequistName;
        MaximumCapacity = maximumCapacity;
        this.description = description;
    }

    public String getCourseId() {
        return CourseId;
    }

    public void setCourseId(String courseId) {
        CourseId = courseId;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCreditHours() {
        return CreditHours;
    }

    public void setCreditHours(String creditHours) {
        CreditHours = creditHours;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(int prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getMaximumCapacity() {
        return MaximumCapacity;
    }

    public String getDepartMentName() {
        return departMentName;
    }

    public void setDepartMentName(String departMentName) {
        this.departMentName = departMentName;
    }

    public String getPreRequistName() {
        return PreRequistName;
    }

    public void setPreRequistName(String preRequistName) {
        PreRequistName = preRequistName;
    }

    public void setMaximumCapacity(String maximumCapacity) {
        MaximumCapacity = maximumCapacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "CourseId='" + CourseId + '\'' +
                ", CourseName='" + CourseName + '\'' +
                ", CreditHours='" + CreditHours + '\'' +
                ", department=" + department +
                ", prerequisites=" + prerequisites +
                ", departMentName='" + departMentName + '\'' +
                ", PreRequistName='" + PreRequistName + '\'' +
                ", MaximumCapacity='" + MaximumCapacity + '\'' +
                '}';
    }
}
