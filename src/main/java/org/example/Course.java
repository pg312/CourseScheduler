package org.example;

import java.util.*;

public class Course {

    private String courseTitle;

    private String instructorName;

    private Date courseDate;

    public int minCapacity;

    public int maxCapacity;

    public String status;

    private String courseOfferingId;

    private boolean courseAllotmentDone = false;

    public HashMap<String, Employee> getRegistrationList() {
        return registrationList;
    }

    private HashMap<String, Employee> registrationList = new HashMap<>();

    public String add(List<String> courseDetails) throws IllegalArgumentException {
        this.courseTitle = courseDetails.get(0);
        this.instructorName = courseDetails.get(1);
        this.courseDate = new Date(courseDetails.get(2));
        this.minCapacity = Integer.parseInt(courseDetails.get(3));
        this.maxCapacity = Integer.parseInt(courseDetails.get(4));
        this.courseOfferingId = generateCourseOfferingId();
        return this.courseOfferingId;

    }

    private String generateCourseOfferingId() {
        return "OFFERING-" + this.courseTitle + "-" + this.instructorName;
    }

    public List<String> addEmployeeToCourse(Employee employee) {
        List<String> registrationDetails = new ArrayList<String>();

        if (this.registrationList.size() < this.maxCapacity) {
            String registrationId = "REG-COURSE-" + employee.getName() + "-" + this.courseTitle;
            registrationList.put(registrationId, employee);
            registrationDetails.add(registrationId);
            registrationDetails.add("ACCEPTED");
        } else {
            registrationDetails.add("");
            registrationDetails.add("COURSE_FULL_ERROR");
        }

        return registrationDetails;
    }

    public List<String> cancelRegistration(String registrationId) {
        List<String> output = new ArrayList<>();
        if (!this.courseAllotmentDone) {
            output.add(registrationId);
            output.add("CANCEL-ACCEPTED");
            registrationList.remove(registrationId);
        } else {
            output.add(registrationId);
            output.add("CANCEL-REJECTED");
        }
        return output;

    }

    public void allot() {
        this.courseAllotmentDone = true;
        List<String> sortedList = new ArrayList<>(registrationList.keySet());
        Collections.sort(sortedList);
        for (String key : sortedList) {
            System.out.print(key + " " + registrationList.get(key).getEmailId() + " " + this.courseOfferingId + " " + this.courseTitle + " " + this.instructorName + " " + this.courseDate + this.status);
            System.out.println();
        }

    }


    public boolean getCouseAlltomentDone() {
        return this.courseAllotmentDone;
    }

    public String getStatus() {
        return this.status;
    }
}
