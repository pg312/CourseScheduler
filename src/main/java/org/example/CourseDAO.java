package org.example;

import java.util.HashMap;

public class CourseDAO {
    private static final HashMap<String, Integer> commandsAndParameters = new HashMap<String, Integer>();
    private HashMap<String, Course> courses = new HashMap<>();

    CourseDAO() {
        commandsAndParameters.put("ADD-COURSE-OFFERING", 5);
        commandsAndParameters.put("REGISTER", 2);
        commandsAndParameters.put("ALLOT-COURSE", 1);
        commandsAndParameters.put("CANCEL", 1);
    }
    public static HashMap<String, Integer> getCommandsAndParameters() {
        return commandsAndParameters;
    }

    public void addCourse(String courseOfferingId, Course course) {
        courses.put(courseOfferingId, course);
    }

    public Course getCourse(String courseOfferingId) {
        return courses.get(courseOfferingId);
    }

    public String addEmployeeToCourse(Employee employee, Course course) {
        return course.addEmployee(employee);
    }

    public Course allotCourse(String courseOfferingId) {
        Course course = courses.get(courseOfferingId);
        course.setCourseAllotmentDone(true);
        return course;
    }
    private Course getCourseByName(String courseName) {
        for (String key :
                courses.keySet()) {
            if (key.contains(courseName))
                return courses.get(key);

        }
        return null;
    }
    public String cancelRegistration(String courseName, String registrationId) {
        Course course = getCourseByName(courseName);
        return course.cancelRegistration(registrationId);
    }
}
