package org.example;

import java.util.HashMap;
import java.util.List;

public class Helper {

    HashMap<String, Integer> commandsAndParameters = new HashMap<String, Integer>();

    Helper() {
        commandsAndParameters.put("ADD-COURSE-OFFERING", 5);
        commandsAndParameters.put("REGISTER", 2);
        commandsAndParameters.put("ALLOT-COURSE", 1);
        commandsAndParameters.put("CANCEL", 1);
    }

    public boolean validateCommands(List<String> inputParams) {


        String firstCommand = inputParams.get(0);

        if (commandsAndParameters.get(firstCommand) == null || commandsAndParameters.get(firstCommand).intValue() != inputParams.size() - 1) {
            return false;
        }

        return true;

    }

}
