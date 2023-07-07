import org.example.Course;
import org.example.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class courseTest {
    List<String> courseDetails = new ArrayList<>();
    List<String> courseBDetails = new ArrayList<>();

    @Before
    public void initiate() {
        courseDetails.add("CourseA");
        courseDetails.add("Instructor1");
        courseDetails.add("2023/12/2");
        courseDetails.add("1");
        courseDetails.add("3");

        courseBDetails.add("CourseB");
        courseBDetails.add("Instructor1");
        courseBDetails.add("2023/12/2");
        courseBDetails.add("1");
        courseBDetails.add("3");
    }

    @Test
    public void testAddCourse() {
        Course course = new Course();
        assertEquals("OFFERING-CourseA-Instructor1", course.add(courseDetails));
    }

    @Test
    public void testAddingMultipleCourses() {
        Course courseA = new Course();
        String courseARegistrationId = courseA.add(courseDetails);


        Course courseB = new Course();
        String courseBRegistrationId = courseB.add(courseBDetails);
        HashMap<String, Course> courses = new HashMap<String, Course>();
        courses.put(courseARegistrationId, courseA);
        courses.put(courseBRegistrationId, courseB);

        assertEquals(2, courses.size());
    }


    @Test
    public void testAddingDuplicateCourses() {
        Course courseA = new Course();
        String courseARegistrationId = courseA.add(courseDetails);

        Course courseB = new Course();
        String courseBRegistrationId = courseB.add(courseBDetails);

        Course duplicateCourse = new Course();
        String duplicateCourseRegistrationId = courseB.add(courseBDetails);

        HashMap<String, Course> courses = new HashMap<String, Course>();
        courses.put(courseARegistrationId, courseA);
        courses.put(courseBRegistrationId, courseB);
        courses.put(duplicateCourseRegistrationId, duplicateCourse);

        assertEquals(2, courses.size());
    }

    @Test
    public void addEmployeeToCourse() {
        Course course = new Course();
        String courseRegistrationId = course.add(courseDetails);
        List<String> registrationDetails = course.addEmployeeToCourse(new Employee("abc@gmail.com"));

        Assert.assertEquals("REG-COURSE-abc-CourseA", registrationDetails.get(0));

    }

    @Test
    public void testMaxLimit() {
        Course course = new Course();
        course.add(courseDetails);

        course.addEmployeeToCourse(new Employee("abc@gmail.com"));
        course.addEmployeeToCourse(new Employee("def@gmail.com"));
        course.addEmployeeToCourse(new Employee("hij@gmail.com"));
        course.addEmployeeToCourse(new Employee("klm@gmail.com"));

        Assert.assertEquals(3, course.getRegistrationList().size());

    }

    @Test
    public void testDuplicateRegistration() {
        Course course = new Course();
        course.add(courseDetails);

        course.addEmployeeToCourse(new Employee("abc@gmail.com"));
        course.addEmployeeToCourse(new Employee("abc@gmail.com"));

        Assert.assertEquals(1, course.getRegistrationList().size());

    }

    @Test
    public void testCancelRegistration() {
        Course course = new Course();
        course.add(courseDetails);

        String courseRegistrationId = course.addEmployeeToCourse(new Employee("abc@gmail.com")).get(0);
        List<String> output = course.cancelRegistration(courseRegistrationId);

        Assert.assertEquals(0, course.getRegistrationList().size());
        Assert.assertEquals(output.get(0), courseRegistrationId);
        Assert.assertEquals(output.get(1), "CANCEL-ACCEPTED");

    }

    @Test
    public void testAllotment() {
        Course course = new Course();
        String courseOfferingId = course.add(courseDetails);

        course.addEmployeeToCourse(new Employee("abc@gmail.com"));
        course.addEmployeeToCourse(new Employee("def@gmail.com"));
        course.addEmployeeToCourse(new Employee("ijk@gmail.com"));

        Assert.assertEquals(false, course.getCouseAlltomentDone());

        course.allot();

        Assert.assertEquals(true, course.getCouseAlltomentDone());

    }

    @Test
    public void testCancelAfterAllotment() {
        Course course = new Course();
        course.add(courseDetails);

        String registrationId = course.addEmployeeToCourse(new Employee("abc@gmail.com")).get(0);
        course.addEmployeeToCourse(new Employee("def@gmail.com"));
        course.addEmployeeToCourse(new Employee("ijk@gmail.com"));

        course.allot();

        List<String> output = course.cancelRegistration(registrationId);

        Assert.assertEquals(3, course.getRegistrationList().size());
        Assert.assertEquals("CANCEL-REJECTED", output.get(1));

    }

}
