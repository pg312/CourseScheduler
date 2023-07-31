package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CourseController {
    private CourseService courseService = new CourseService();
    private ValidateInput validateInput = new ValidateInput();
    HashMap<String, Integer> coursesAndParameters = courseService.getCoursesAndParameters();
    public List<String> processInput(List<String> inputs) {
        List<String> output = new ArrayList<>();
        if (validateInput.validateCommands(coursesAndParameters, inputs)) {
            output = callActionMethod(inputs);
        } else output.add("INPUT_ERROR");
        return output;

    }
    private List<String> callActionMethod(List<String> inputs) {
        List<String> output = new ArrayList<>();
        String command = inputs.get(0);
        List<String> parameters = inputs.subList(1, inputs.size());
        switch (command) {
            case "ADD-COURSE-OFFERING":
                output = courseService.addCourse(parameters);
                break;
            case "REGISTER":
                Employee employee = new Employee(parameters.get(0));
                output = courseService.addEmployeeToCourse(employee, parameters.get(1));
                break;
            case "ALLOT-COURSE":
                output = courseService.allotCourse(parameters.get(0));
                break;
            case "CANCEL":
                output = courseService.cancelRegistration(parameters);
                break;

        }
        return output;
    }
}
