package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.next();

        BufferedReader bi = new BufferedReader(
                new FileReader(filePath));

        String eachLine;
        HashMap<String, Course> courses = new HashMap<String, Course>();
        Helper helper = new Helper();
        processInput(bi, courses, helper);
    }

    private static void processInput(BufferedReader bi, HashMap<String, Course> courses, Helper helper) throws IOException {
        String eachLine;
        while ((eachLine = bi.readLine()) != null) {
            List<String> inputs = List.of(eachLine.split(" "));

            List<String> output = new ArrayList<>();

            if (helper.validateCommands(inputs)) {
                callActionMethod(courses, inputs, output);

            } else {
                System.out.println("INPUT_DATA_ERROR");
            }
        }
    }

    private static void callActionMethod(HashMap<String, Course> courses, List<String> inputs, List<String> output) {
        switch (inputs.get(0)) {

            case "ADD-COURSE-OFFERING":
                Course course = new Course();
                String courseOfferingId = course.add(inputs.subList(1, inputs.size()));
                courses.put(courseOfferingId, course);
                output.add(courseOfferingId);
                break;

            case "REGISTER":

                output = courses.get(inputs.get(2)).addEmployeeToCourse(new Employee(inputs.get(1)));
                break;
            case "ALLOT-COURSE":

                courses.get(inputs.get(1)).allot();
                break;
            case "CANCEL":
                String registrationId = inputs.get(1);
                String courseId = registrationId.substring(registrationId.lastIndexOf("-"));
                Course course1 = null;
                for (Map.Entry<String, Course> entry : courses.entrySet()) {
                    if (entry.getKey().indexOf(courseId) != -1) {
                        course1 = entry.getValue();
                    }
                }
                ;
                if (course1 != null) {
                    output = course1.cancelRegistration(registrationId);
                } else
                    output.add("Invalid registration id");
                break;

        }
        output.forEach(System.out::println);
    }


}