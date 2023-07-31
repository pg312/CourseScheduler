package org.example;

import java.text.SimpleDateFormat;
import java.util.*;

public class CourseService {
    private final CourseDAO courseDAO = new CourseDAO();
    HashMap<String, Integer> coursesAndParameters = CourseDAO.getCommandsAndParameters();

    public HashMap<String, Integer> getCoursesAndParameters() {
        return coursesAndParameters;
    }

    public List<String> addCourse(List<String> inputs) throws IllegalArgumentException {
        Course course = new Course(inputs.get(0), inputs.get(1), new Date(inputs.get(2)), Integer.parseInt(inputs.get(3)), Integer.parseInt(inputs.get(4)));
        String courseOfferingId = course.generateCourseOfferingId();
        courseDAO.addCourse(courseOfferingId, course);
        return Collections.singletonList(courseOfferingId);
    }

    public List<String> addEmployeeToCourse(Employee employee, String course) {
        Course courseObject = courseDAO.getCourse(course);
        List<String> output = new ArrayList<>();
        if (!isCourseFull(courseObject)) {
            String registrationId = courseDAO.addEmployeeToCourse(employee, courseObject);
            output.add(registrationId + " " + courseObject.getStatus());
        } else {
            output.add("COURSE_FULL_ERROR");
        }
        return output;
    }

    private boolean isCourseFull(Course course) {
        return course.getRegistrationList().size() >= course.getMaxCapacity();
    }

    public List<String> allotCourse(String courseOfferingId) {
        Course course = courseDAO.allotCourse(courseOfferingId);
        List<String> output = new ArrayList<>();
        HashMap<String, Employee> registrationList = course.getRegistrationList();
        for (String key : registrationList.keySet()) {
            output.add(convertString(key, course, registrationList.get(key)));
        }
        return output;
    }

    private String convertString(String key, Course course, Employee employee) {
        return key + " " + employee.getEmailId() + " " + course.getCourseOfferingId() + " " + course.getCourseTitle() + " " + course.getInstructorName() + " " + new SimpleDateFormat("yyyy-MM-dd").format(course.getCourseDate()) + " " + course.getStatus();
    }

    public List<String> cancelRegistration(List<String> parameters) {
        List<String> output = new ArrayList<>();
        String registrationId = parameters.get(0);
        String courseName = registrationId.substring(registrationId.lastIndexOf("-"));
        output.add(registrationId + " " + courseDAO.cancelRegistration(courseName, registrationId));
        return output;
    }
}
