package org.example;

import java.util.Date;
import java.util.HashMap;

public class Course {
    private final String courseTitle;
    private final String instructorName;
    private final Date courseDate;
    private final int minCapacity;
    private final int maxCapacity;
    private final HashMap<String, Employee> registrationList = new HashMap<>();
    private String status;
    private String courseOfferingId;
    private boolean courseAllotmentDone = false;

    public Course(String name, String author, Date courseDate, int minCapacity, int maxCapacity) {

        this.courseTitle = name;
        this.instructorName = author;
        this.courseDate = courseDate;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;

    }

    public String generateCourseOfferingId() {
        this.courseOfferingId = "OFFERING-" + this.courseTitle + "-" + this.instructorName;
        return this.courseOfferingId;
    }

    public HashMap<String, Employee> getRegistrationList() {
        return registrationList;
    }
    public String getStatus() {
        return this.status;
    }
    public int getMaxCapacity() {
        return maxCapacity;
    }
    public String getCourseTitle() {
        return courseTitle;
    }
    public String getCourseOfferingId() {
        return courseOfferingId;
    }
    public String getInstructorName() {
        return instructorName;
    }
    public Date getCourseDate() {
        return courseDate;
    }
    public void setCourseAllotmentDone(boolean courseAllotmentDone) {
        this.courseAllotmentDone = courseAllotmentDone;
    }

    public String cancelRegistration(String registrationId) {
        if (this.courseAllotmentDone) {
            return "CANCEL_REJECTED";
        }
        this.getRegistrationList().remove(registrationId);
        return "CANCEL_ACCEPTED";
    }
    public String addEmployee(Employee employee) {
        String registrationId = "REG-COURSE" + employee.getName() + this.courseTitle;
        this.registrationList.put(registrationId, employee);
        this.status = registrationList.size() > this.maxCapacity ? "ACCEPTED" : "COURSE_CANCELLED";
        return registrationId;
    }
}
