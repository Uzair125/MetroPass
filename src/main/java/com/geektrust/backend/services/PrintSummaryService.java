package com.geektrust.backend.services;

import com.geektrust.backend.entities.Station;
import com.geektrust.backend.repositories.IStationRepo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class PrintSummaryService implements IPrintSummaryService{

    private final IStationRepo stationRepo;

    public PrintSummaryService(IStationRepo stationRepo) {
        this.stationRepo = stationRepo;
    }
    @Override
    public void printSummary() {
        List<Station> stationSummary= stationRepo.findAll();
        Collections.sort(stationSummary, Comparator.comparing(Station::getStationName).reversed());
        for (int i = 0; i < stationSummary.size(); i++) {
            System.out.println("TOTAL_COLLECTION  " + stationSummary.get(i).getStationName() + " " + stationSummary.get(i).getAmountCollected() + " " + stationSummary.get(i).getDiscountGiven());
            System.out.println("PASSENGER_TYPE_SUMMARY");
            for (Map.Entry<String,Integer> entry : stationSummary.get(i).getPassengerCount().entrySet())
                System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
