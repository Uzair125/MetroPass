package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.Metrocard;
import com.geektrust.backend.entities.Station;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StationRepo implements IStationRepo{

    private final Map<String, Station> stationMap;
    private Integer autoIncrement=0;

    public StationRepo(){
        stationMap = new HashMap<String, Station>();
    }

    public StationRepo(Map<String, Station> stationMap) {
        this.stationMap = stationMap;
        this.autoIncrement = stationMap.size();
    }
    @Override
    public Station save(Station entity) {
        if(entity.getId()==null){
            autoIncrement++;
            Station s = new Station(Integer.toString(autoIncrement),entity.getStationName(),entity.getAmountCollected(),entity.getDiscountGiven(),entity.getPassengerCount(),entity.getPassengersTravelled());
            stationMap.put(s.getStationName(),s);
            return  s;
        }
        stationMap.put(entity.getStationName(),entity);
        return entity;
    }

    @Override
    public List<Station> findAll() {
        return stationMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public Optional<Station> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void delete(Station entity) {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Station findByName(String stationName) {
        List<Station> stationList= stationMap.entrySet().stream().filter(e -> e.getValue().getStationName().equals(stationName))
                .map(Map.Entry::getValue).collect(Collectors.toList());
        if(stationList.isEmpty()){
            return null;
        }
        return stationList.get(0);
    }
}
