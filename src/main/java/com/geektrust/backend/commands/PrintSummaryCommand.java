package com.geektrust.backend.commands;

import com.geektrust.backend.entities.Station;
import com.geektrust.backend.repositories.IStationRepo;
import com.geektrust.backend.services.IPrintSummaryService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class PrintSummaryCommand implements ExecuteCommand{

    private final IPrintSummaryService printSummaryService;

    public PrintSummaryCommand(IPrintSummaryService printSummaryService) {
        this.printSummaryService = printSummaryService;
    }

    @Override
    public void execute(List<String> tokens) {
        printSummaryService.printSummary();
    }
}
