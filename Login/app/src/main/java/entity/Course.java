package entity;

/**
 * Created by nicole on 2018-02-27.
 */

public class Course {

    private String courseCode;

    public Course(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
