package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.Metrocard;

import java.util.*;
import java.util.stream.Collectors;

public class MetroCardRepo implements IMetroCardRepo{

    private final Map<String, Metrocard> metrocardMap;
    private Integer autoIncrement=0;

    public MetroCardRepo(){
        metrocardMap = new HashMap<String, Metrocard>();
    }

    public MetroCardRepo(Map<String, Metrocard> metrocardMap) {
        this.metrocardMap = metrocardMap;
        this.autoIncrement = metrocardMap.size();
    }

    @Override
    public Metrocard save(Metrocard entity) {
        if(entity.getId()==null){
            autoIncrement++;
            Metrocard m = new Metrocard(Integer.toString(autoIncrement),entity.getCardNumber(),entity.getBalance());
            metrocardMap.put(m.getCardNumber(),m);
            return  m;
        }
        metrocardMap.put(entity.getCardNumber(),entity);
        return entity;
    }

    @Override
    public List<Metrocard> findAll() {
        return null;
    }

    @Override
    public Optional<Metrocard> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void delete(Metrocard entity) {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Optional<Metrocard> findByCardnumber(String cardNumber) {
        List<Metrocard> cardList= metrocardMap.entrySet().stream().filter(e -> e.getValue().getCardNumber().equals(cardNumber))
                .map(Map.Entry::getValue).collect(Collectors.toList());
        if(cardList.isEmpty()){
            return Optional.empty();
        }
        return Optional.ofNullable(cardList.get(0));
    }
}
