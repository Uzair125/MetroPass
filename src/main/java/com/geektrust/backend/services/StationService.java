package com.geektrust.backend.services;

import com.geektrust.backend.entities.*;
import com.geektrust.backend.exceptions.InvalidMetrocardNumber;
import com.geektrust.backend.exceptions.InvalidPassengerType;
import com.geektrust.backend.exceptions.InvalidStation;
import com.geektrust.backend.helpers.CheckValidPassengerType;
import com.geektrust.backend.helpers.CheckValidStation;
import com.geektrust.backend.repositories.IMetroCardRepo;
import com.geektrust.backend.repositories.IStationRepo;
import java.util.HashSet;
import java.util.Optional;
import java.util.TreeMap;

public class StationService implements IStationService{
    private final IStationRepo stationRepo;
    private final IMetroCardRepo metroCardRepo;

    public StationService(IStationRepo stationRepo, IMetroCardRepo metroCardRepo){
        this.stationRepo = stationRepo;
        this.metroCardRepo = metroCardRepo;
    }
    @Override
    public Station chargePassenger(String cardNumber, String passengerType, String stationName) throws InvalidStation, InvalidMetrocardNumber, InvalidPassengerType {
        Metrocard metrocard = metroCardRepo.findByCardnumber(cardNumber).orElseThrow(() -> new InvalidMetrocardNumber("Card number: " + cardNumber + " not found!"));
        CheckValidStation checkStationValidity = new CheckValidStation();
        if(!checkStationValidity.validStation(stationName))
            throw new InvalidStation("Invalid Station: " + stationName);

        CheckValidPassengerType CheckValidPassengerType = new CheckValidPassengerType();
        if(!CheckValidPassengerType.validPassengerType(passengerType))
            throw new InvalidPassengerType("Invalid Passenger Type: " + passengerType);

        //Calculate the charges
        int passengerFee = PassengerCharge.valueOf(passengerType).getCharge();
        int discountGiven = 0;
        boolean discountFlag = false;
        final Station arrivalStation;
        if(stationName.equals("AIRPORT")) {
            arrivalStation = stationRepo.findByName("CENTRAL");
        }else{
            arrivalStation = stationRepo.findByName("AIRPORT");
        }
        if(arrivalStation != null && arrivalStation.getPassengersTravelled().contains(cardNumber)){
            passengerFee = passengerFee/2;
            discountGiven = passengerFee;
            discountFlag = true;
            HashSet<String> passengersTravelled = arrivalStation.getPassengersTravelled();
            passengersTravelled.remove(cardNumber);
            final Station s = new Station(arrivalStation.getId(), arrivalStation.getStationName(), arrivalStation.getAmountCollected(), arrivalStation.getDiscountGiven(), arrivalStation.getPassengerCount(), passengersTravelled);
            stationRepo.save(s);
        }

//      Check and Update balance and if balance is less than charge then the passenger will be charged extra 2%
        passengerFee = updateCardBalance(metrocard, passengerFee);

        //Check if station exists in Repo
        return updateStationDetails(stationName, cardNumber, passengerType, passengerFee, discountGiven, discountFlag);

    }

//    private boolean validStation(String checkStation) {
//        for (Stations s : Stations.values()) {
//            if (s.name().equals(checkStation)) {
//                return true;
//            }
//        }
//        return false;
//    }

//    private boolean validPassengerType(String passengerType) {
//        for (PassengerType pt : PassengerType.values()) {
//            if (pt.name().equals(passengerType)) {
//                return true;
//            }
//        }
//        return false;
//    }

    private int updateCardBalance(Metrocard metrocard, int passengerFee) {
        int balance = metrocard.getBalance();
        if(balance<passengerFee){
            int addBalance = passengerFee - balance;
            balance = 0;
            passengerFee = passengerFee + (int)(addBalance * 0.02);
        }
        else{
            balance = balance - passengerFee;
        }
        final Metrocard m = new Metrocard(metrocard.getId(), metrocard.getCardNumber(), balance);
        metroCardRepo.save(m);
        return passengerFee;
    }

    private Station updateStationDetails(String stationName, String cardNumber, String passengerType, int passengerFee, int discountGiven, boolean discountFlag){
        final Station station = stationRepo.findByName(stationName);
        if(station == null){
            TreeMap<String,Integer> passengerCount = new TreeMap<String,Integer>();
            passengerCount.put(passengerType,1);
            HashSet<String> passengersTravelled = new HashSet<String>();
            passengersTravelled.add(cardNumber);
            final Station s = new Station(stationName,passengerFee,discountGiven,passengerCount,passengersTravelled);
            return stationRepo.save(s);
        }
        else{
            passengerFee = passengerFee + station.getAmountCollected();
            discountGiven = discountGiven + station.getDiscountGiven();
            TreeMap<String,Integer> passengerCount = station.getPassengerCount();
            passengerCount.put(passengerType,passengerCount.getOrDefault(passengerType,0)+1);
            HashSet<String> passengersTravelled = station.getPassengersTravelled();
            if(!discountFlag)
                passengersTravelled.add(cardNumber);
            final Station s = new Station(station.getId(), stationName, passengerFee, discountGiven, passengerCount, passengersTravelled);
            return stationRepo.save(s);
        }
    }
}
