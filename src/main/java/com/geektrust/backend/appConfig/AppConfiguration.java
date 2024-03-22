package com.geektrust.backend.appConfig;

import com.geektrust.backend.commands.ExecuteCommand;
import com.geektrust.backend.commands.PrintSummaryCommand;
import com.geektrust.backend.commands.ChargePassengerCommand;
import com.geektrust.backend.commands.CommandInvoke;
import com.geektrust.backend.commands.CreateMetroCardCommand;
import com.geektrust.backend.services.*;
import com.geektrust.backend.repositories.*;

public class AppConfiguration {

    private final IStationRepo stationRepo = new StationRepo();
    private final IMetroCardRepo metroCardRepo = new MetroCardRepo();
    private final IStationService stationService = new StationService(stationRepo, metroCardRepo);
    private final IMetroCardService metroCardService = new MetroCardService(metroCardRepo);

    private final CreateMetroCardCommand createMetroCardCommand = new CreateMetroCardCommand(metroCardService);
    private final ChargePassengerCommand chargePassengerCommand = new ChargePassengerCommand(stationService);
    private final IPrintSummaryService printSummaryService = new PrintSummaryService(stationRepo);
    private final PrintSummaryCommand printSummaryCommand = new PrintSummaryCommand(printSummaryService);
    private final CommandInvoke commandInvoke = new CommandInvoke();

    public CommandInvoke getCommandInvoker(){
        commandInvoke.register("BALANCE",createMetroCardCommand);
        commandInvoke.register("CHECK_IN",chargePassengerCommand);
        commandInvoke.register("PRINT_SUMMARY",printSummaryCommand);
        return commandInvoke;
    }
}
