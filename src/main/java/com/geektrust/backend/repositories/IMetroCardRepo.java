package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.Metrocard;

import java.util.Optional;

public interface IMetroCardRepo extends CRUDRepository<Metrocard,String>{
    public Optional<Metrocard> findByCardnumber(String cardnumber);
}
