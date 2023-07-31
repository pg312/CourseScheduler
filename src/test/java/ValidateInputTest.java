import org.example.ValidateInput;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidateInputTest {

    private static final HashMap<String, Integer> commandsAndParameters = new HashMap<String, Integer>();

    @Before
    public void setUp() throws Exception {
        commandsAndParameters.put("ADD-COURSE-OFFERING", 5);
        commandsAndParameters.put("REGISTER", 2);
        commandsAndParameters.put("ALLOT-COURSE", 1);
        commandsAndParameters.put("CANCEL", 1);
    }

    @Test
    public void testAddCourseInput() {
        ValidateInput validateInput = new ValidateInput();
        List<String> inputs = new ArrayList<>();
        inputs.add("ADD-COURSE-OFFERING");
        inputs.add("CourseA");
        inputs.add("Don");
        inputs.add("2023/2/23");
        inputs.add("1");
        inputs.add("3");
        assertTrue(validateInput.validateCommands(commandsAndParameters, inputs));
    }

    @Test
    public void testRegisterInput() {
        ValidateInput validateInput = new ValidateInput();
        List<String> inputs = new ArrayList<>();
        inputs.add("REGISTER");
        inputs.add("bvp@gmail.com");
        inputs.add("course-offering-id");
        assertTrue(validateInput.validateCommands(commandsAndParameters, inputs));
    }

    @Test
    public void testAllotCourse() {
        ValidateInput validateInput = new ValidateInput();
        List<String> inputs = new ArrayList<>();
        inputs.add("ALLOT-COURSE");
        inputs.add("course-offering-id");
        assertTrue(validateInput.validateCommands(commandsAndParameters, inputs));
    }

    @Test
    public void testCancelCourse() {
        ValidateInput validateInput = new ValidateInput();
        List<String> inputs = new ArrayList<>();
        inputs.add("CANCEL");
        inputs.add("course-registration-id");
        assertTrue(validateInput.validateCommands(commandsAndParameters, inputs));
    }

    @Test
    public void testInvalidCommand() {
        ValidateInput validateInput = new ValidateInput();
        List<String> inputs = new ArrayList<>();
        inputs.add("ALLOT");
        inputs.add("course-offering-id");
        assertFalse(validateInput.validateCommands(commandsAndParameters, inputs));
    }

    @Test
    public void testWrongNumberOfParameters() {
        ValidateInput validateInput = new ValidateInput();
        List<String> inputs = new ArrayList<>();

        inputs.add("ADD-COURSE-OFFERING");
        inputs.add("CourseA");
        inputs.add("Don");
        inputs.add("2023/2/23");
        assertFalse(validateInput.validateCommands(commandsAndParameters, inputs));

        inputs.clear();

        inputs.add("REGISTER");
        inputs.add("bvp@gmail.com");
        assertFalse(validateInput.validateCommands(commandsAndParameters, inputs));

        inputs.clear();

        inputs.add("ALLOT-COURSE");
        inputs.add("course-offering-id");
        inputs.add("course-offering-id");
        assertFalse(validateInput.validateCommands(commandsAndParameters, inputs));

        inputs.clear();

        inputs.add("CANCEL");
        inputs.add("course-registration-id");
        inputs.add("random");
        assertFalse(validateInput.validateCommands(commandsAndParameters, inputs));
    }
}
