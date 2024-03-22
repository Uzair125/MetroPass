package com.geektrust.backend.services;

import com.geektrust.backend.entities.Metrocard;
import com.geektrust.backend.repositories.IMetroCardRepo;

public class MetroCardService implements IMetroCardService{

    public final IMetroCardRepo metroCardRepo;

    public MetroCardService(IMetroCardRepo metroCardRepo) {
        this.metroCardRepo = metroCardRepo;
    }

    @Override
    public Metrocard create(String cardNumber, Integer balance) {
        final Metrocard metrocard = new Metrocard(cardNumber,balance);
        return metroCardRepo.save(metrocard);
    }
}
