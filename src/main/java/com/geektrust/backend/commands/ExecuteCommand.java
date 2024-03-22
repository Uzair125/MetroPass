package com.geektrust.backend.commands;

import java.util.List;

public interface ExecuteCommand {
    void execute(List<String> tokens);
}
