package com.geektrust.backend.commands;

import com.geektrust.backend.entities.Station;
import com.geektrust.backend.services.IStationService;

import java.util.List;

public class ChargePassengerCommand implements ExecuteCommand{

    public final IStationService stationService;

    public ChargePassengerCommand(IStationService stationService) {
        this.stationService = stationService;
    }

    @Override
    public void execute(List<String> tokens) {
        String cardNumber = tokens.get(1);
        String passengerType = tokens.get(2);
        String station = tokens.get(3);
        try{
            Station s = stationService.chargePassenger(cardNumber,passengerType,station);
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}
