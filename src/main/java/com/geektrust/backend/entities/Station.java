package com.geektrust.backend.entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class Station extends BaseEntity{
    private final String stationName;
    private final Integer amountCollected;
    private final Integer discountGiven;
    private final TreeMap<String,Integer> passengerCount;
    private final HashSet<String> passengersTravelled;

    public Station(Station station){
        this(station.id,station.stationName,station.amountCollected,station.discountGiven,station.passengerCount,station.passengersTravelled);
    }

    public Station(String id, String stationName, Integer amountCollected, Integer discountGiven, TreeMap<String, Integer> passengerCount, HashSet<String> passengersTravelled) {
        this(stationName,amountCollected,discountGiven,passengerCount,passengersTravelled);
        this.id = id;
    }

    public Station(String stationName, Integer amountCollected, Integer discountGiven, TreeMap<String, Integer> passengerCount, HashSet<String> passengersTravelled) {
        this.stationName = stationName;
        this.amountCollected = amountCollected;
        this.discountGiven = discountGiven;
        this.passengerCount = passengerCount;
        this.passengersTravelled = passengersTravelled;
    }

    public String getStationName(){
        return  stationName;
    }

    public Integer getAmountCollected(){
        return amountCollected;
    }

    public Integer getDiscountGiven(){
        return discountGiven;
    }

    public TreeMap<String,Integer> getPassengerCount(){
        return passengerCount;
    }

    public HashSet<String> getPassengersTravelled(){
        return  passengersTravelled;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        Station other = (Station) obj;
//        if (id == null) {
//            if (other.id != null)
//                return false;
//        } else if (!id.equals(other.id))
//            return false;
//        return true;
//    }
}
