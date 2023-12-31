package org.example;

import java.util.HashMap;
import java.util.List;

public class ValidateInput {
    public boolean validateCommands(HashMap<String, Integer> commandsAndParameters, List<String> inputParams) {
        String firstCommand = inputParams.get(0);
        return commandsAndParameters.get(firstCommand) != null && commandsAndParameters.get(firstCommand).intValue() == inputParams.size() - 1;

    }
}
