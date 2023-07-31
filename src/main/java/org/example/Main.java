package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.next();

        BufferedReader bi = new BufferedReader(
                new FileReader(filePath));

        processInput(bi);
    }
    private static void processInput(BufferedReader bi) throws IOException {
        String eachLine;
        CourseController courseController = new CourseController();

        while ((eachLine = bi.readLine()) != null) {
            List<String> inputs = List.of(eachLine.split(" "));
            List<String> output = courseController.processInput(inputs);
            output.forEach(System.out::println);
        }
    }


}