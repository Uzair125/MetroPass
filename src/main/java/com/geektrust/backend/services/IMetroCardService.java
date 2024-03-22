package com.geektrust.backend.services;

import com.geektrust.backend.entities.Metrocard;

public interface IMetroCardService {
    public Metrocard create(String cardNumber, Integer balance);
}
