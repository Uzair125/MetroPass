package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.Metrocard;
import com.geektrust.backend.entities.Station;

import java.util.Optional;

public interface IStationRepo extends CRUDRepository<Station,String>{
    public Station findByName(String stationName);
}
