package com.geektrust.backend.services;
import com.geektrust.backend.entities.Station;
public interface IStationService {
    public Station chargePassenger(String cardNumber,String passengerType, String stationName);
}
