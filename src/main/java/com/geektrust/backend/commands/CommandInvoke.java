package com.geektrust.backend.commands;

import com.geektrust.backend.exceptions.NoSuchCommandException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CommandInvoke {
    private static final Map<String, ExecuteCommand> commandMap = new HashMap<>();

    // Register the command into the HashMap
    public void register(String commandName, ExecuteCommand command){
        commandMap.put(commandName,command);
    }

    // Get the registered Command
    private ExecuteCommand get(String commandName){
        return commandMap.get(commandName);
    }

    // Execute the registered Command
    public void executeCommand(String commandName, List<String> tokens) throws NoSuchCommandException {
        ExecuteCommand command = get(commandName);
        if(command == null){
            // Handle Exception
            throw new NoSuchCommandException();
        }
        command.execute(tokens);
    }

}