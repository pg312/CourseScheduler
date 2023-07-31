package org.example;

import java.util.*;

public class Course {

    private String courseTitle;

    private String instructorName;

    private Date courseDate;

    private int minCapacity;

    private int maxCapacity;

    private String status;

    private String courseOfferingId;

    public void setCourseAllotmentDone(boolean courseAllotmentDone) {
        this.courseAllotmentDone = courseAllotmentDone;
    }

    private boolean courseAllotmentDone = false;

    public Course(String name, String author, Date courseDate, int minCapacity, int maxCapacity) {

        this.courseTitle = name;
        this.instructorName = author;
        this.courseDate = courseDate;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;

    }

    public String generateCourseOfferingId() {
         this.courseOfferingId =  "OFFERING-" + this.courseTitle + "-" + this.instructorName;
        return this.courseOfferingId;
    }

    public HashMap<String, Employee> getRegistrationList() {
        return registrationList;
    }

    private HashMap<String, Employee> registrationList = new HashMap<>();



    public boolean getCouseAlltomentDone() {
        return this.courseAllotmentDone;
    }

    public String getStatus() {
        return this.status;
    }

    public int getMinCapacity() {
        return minCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String addEmployee(Employee employee) {
        String registrationId = "REG-COURSE"+employee.getName()+this.courseTitle;
        this.registrationList.put(registrationId, employee);
        this.status = "ACCEPTED";
        return registrationId;
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

    public boolean isCourseAllotmentDone() {
        return courseAllotmentDone;
    }

    public String cancelRegistration(String registrationId){
        if(this.courseAllotmentDone){
            return "CANCEL_REJECTED";
        }
        this.getRegistrationList().remove(registrationId);
        return "CANCEL_ACCEPTED";
    }
}
