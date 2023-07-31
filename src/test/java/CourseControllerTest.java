import org.example.CourseController;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CourseControllerTest {

    @Test
    public void testAddCourse() {
        CourseController courseController = new CourseController();
        List<String> inputs = new ArrayList<>();
        inputs.add("ADD-COURSE-OFFERING");
        inputs.add("CourseA");
        inputs.add("Don");
        inputs.add("2023/2/23");
        inputs.add("1");
        inputs.add("3");
        List<String> output = courseController.processInput(inputs);
        assertEquals(List.of("OFFERING-CourseA-Don"), output);
    }

    @Test
    public void testRegisterCourse() {
        CourseController courseController = new CourseController();
        List<String> inputs = new ArrayList<>();
        inputs.add("ADD-COURSE-OFFERING");
        inputs.add("CourseB");
        inputs.add("DonB");
        inputs.add("2023/2/23");
        inputs.add("1");
        inputs.add("10");
        String courseOfferingId = courseController.processInput(inputs).get(0);

        inputs.clear();
        inputs.add("REGISTER");
        inputs.add("bvp@gmail.com");
        inputs.add(courseOfferingId);
        List<String> output = courseController.processInput(inputs);

        assertEquals("REG-COURSE-bvp-CourseB ACCEPTED", output.get(0));

    }

    @Test
    public void testAllotCourse() {
        CourseController courseController = new CourseController();
        List<String> inputs = new ArrayList<>();
        inputs.add("ADD-COURSE-OFFERING");
        inputs.add("CourseB");
        inputs.add("DonB");
        inputs.add("2023/2/23");
        inputs.add("1");
        inputs.add("10");
        String courseOfferingId = courseController.processInput(inputs).get(0);

        inputs.clear();
        inputs.add("REGISTER");
        inputs.add("bvp@gmail.com");
        inputs.add(courseOfferingId);
        courseController.processInput(inputs);

        inputs.clear();

        inputs.add("ALLOT-COURSE");
        inputs.add(courseOfferingId);
        List<String> output = courseController.processInput(inputs);

        assertEquals("REG-COURSE-bvp-CourseB bvp@gmail.com " + courseOfferingId + " CourseB DonB 2023-02-23 ACCEPTED", output.get(0));

    }

    @Test
    public void testCancelCourse() {
        CourseController courseController = new CourseController();
        List<String> inputs = new ArrayList<>();
        inputs.add("ADD-COURSE-OFFERING");
        inputs.add("CourseB");
        inputs.add("DonB");
        inputs.add("2023/2/23");
        inputs.add("1");
        inputs.add("10");
        String courseOfferingId = courseController.processInput(inputs).get(0);

        inputs.clear();
        inputs.add("REGISTER");
        inputs.add("bvp@gmail.com");
        inputs.add(courseOfferingId);
        List<String> output = courseController.processInput(inputs);

        inputs.clear();

        inputs.add("CANCEL");
        inputs.add(output.get(0).substring(0,output.get(0).indexOf(" ")));

        output.clear();
        output = courseController.processInput(inputs);

        assertEquals("REG-COURSE-bvp-CourseB CANCEL_ACCEPTED", output.get(0));

    }
}
