package com.geektrust.backend;

import com.geektrust.backend.appConfig.AppConfiguration;
import com.geektrust.backend.commands.CommandInvoke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        AppConfiguration applicationConfig = new AppConfiguration();
        CommandInvoke commandInvoker = applicationConfig.getCommandInvoker();
        List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        BufferedReader reader;
        String inputFile = commandLineArgs.get(0);
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                List<String> tokens = Arrays.asList(line.split(" "));
                commandInvoker.executeCommand(tokens.get(0),tokens);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
